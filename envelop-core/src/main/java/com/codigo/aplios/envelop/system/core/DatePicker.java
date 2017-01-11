package com.codigo.aplios.envelop.system.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 * A custom component that mimics a combo box, displaying
 * a perpetual calendar rather than a 'list'.
 *
 * @author Jane Griscti jane@janeg.ca
 * @version 1.0 Oct 24, 2002
 */
public class DatePicker extends JPanel implements java.awt.event.FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7253834640655284169L;
	// -- class fields
	private static final DateFormatSymbols dfs = new DateFormatSymbols(Locale.getDefault());
	private static final String[] months = dfs.getMonths();
	private static final String[] dayNames = new String[7];
	private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
	private static final Dimension screenSize = toolkit.getScreenSize();
	private static final PopupFactory factory = PopupFactory.getSharedInstance();
	private static Logger cat = Logger.getLogger("com.codigo.aplios.contos.system.gui.CalendarComboBox");
	// -- instance fields used with 'combo-box' panel
	protected final JPanel inputPanel = new JPanel();

	// private final JFormattedTextField input
	// = new JFormattedTextField( new Date() );
	// private final JLabel input = new JLabel();
	protected final JTextField input = new JTextField();
	protected final BasicArrowButton comboBtn = new BasicArrowButton(SwingConstants.SOUTH);

	// -- instance fields used with calendar panel
	private final JPanel calPanel = new JPanel();
	private final JTextField calLabel = new JTextField(11);
	protected final Calendar current = new GregorianCalendar(Locale.getDefault());
	protected final CalendarModel display = new CalendarModel(6, 6);
	protected final JTable table = new JTable(display);

	private JButton closeCalendarBtn = new JButton("Zamknij");
	// private final BasicArrowButton nextBtn = new BasicArrowButton( SwingConstants.EAST );
	// private final BasicArrowButton prevBtn = new BasicArrowButton( SwingConstants.WEST );
	private JButton month_next = new JButton("+ month  ");
	private JButton month_prev = new JButton("- month  ");
	private JButton year_next = new JButton("+ year    ");
	private JButton year_prev = new JButton("- year    ");
	private JButton ffyear_next = new JButton("+ decade");
	private JButton rewyear_prev = new JButton("- decade");

	protected SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	private Popup popup;

	/**
	 * Create a new calendar combo-box object set with today's date.
	 */
	public DatePicker() {
		this(new GregorianCalendar());
		calLabel.setEditable(false);

	}

	/**
	 * Create a new calendar combo-box object set with the given date.
	 *
	 * @param cal
	 *        a calendar object
	 * @see java.util.GregorianCalendar
	 */
	public DatePicker(final Calendar cal) {
		super();

		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		sf.setLenient(true);
		buildInputPanel();
		buildCalendarDisplay();
		registerListeners();
		add(inputPanel);
	}

	/*
	 * Creates a field and 'combo box' button above the calendar
	 * to allow user input.
	 */
	protected void buildInputPanel() {

		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

		input.setColumns(12);
		inputPanel.add(input);

		inputPanel.add(Box.createHorizontalStrut(0));

		input.addFocusListener(this);
		comboBtn.setActionCommand("combo");
		inputPanel.add(comboBtn);

	}

	@Override
	public void focusLost(java.awt.event.FocusEvent e) {

		cat.fine("FOcus lost");
		try {
			String date_text = input.getText()
					.trim();
			if (date_text.length() > 10)
				throw new Exception();
			else if (date_text == null || date_text.trim()
					.equals("")) {
				setDate(null);
			} else {

				Date new_date = sf.parse(date_text);
				setDate(new_date);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
					"The date format should be dd-mm-yyyy\nPlease change the date as it has been ignored",
					"Date format error", JOptionPane.ERROR_MESSAGE);
			cat.log(Level.SEVERE, "Error ", ex);
			// ex.printStackTrace();
		}
	}

	@Override
	public void focusGained(java.awt.event.FocusEvent e) {

		// not needed
	}

	/*
	 * Builds the calendar panel to be displayed in the popup
	 */
	private void buildCalendarDisplay() {

		// Allow for individual cell selection and turn off
		// grid lines.
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(false);

		// Calendar (table) column headers
		// Set column headers to weekday names as given by
		// the default Locale.
		//
		// Need to re-map the retreived names. If used as is,
		// the table model ends up with an extra empty column as
		// the returned names begin at index 1, not zero.
		String[] names = dfs.getShortWeekdays();

		for (int i = 1; i < names.length; i++) {
			dayNames[i - 1] = "" + names[i].charAt(0);
		}

		display.setColumnIdentifiers(dayNames);
		table.setModel(display);

		// Set the column widths. Need to turn
		// auto resizing off to make this work.
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int count = table.getColumnCount();

		for (int i = 0; i < count; i++) {
			TableColumn col = table.getColumnModel()
					.getColumn(i);
			col.setPreferredWidth(20);
		}

		// Column headers are only displayed automatically
		// if the table is put in a JScrollPane. Don't want
		// to use one here, so need to add the headers
		// manually.
		JTableHeader header = table.getTableHeader();
		header.setFont(header.getFont()
				.deriveFont(Font.BOLD));
		header.setForeground(Color.blue);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(header);
		panel.add(table);

		calPanel.setBorder(new LineBorder(Color.BLACK));
		calPanel.setLayout(new BorderLayout());
		calPanel.add(buildCalendarManagmentPanel(), BorderLayout.NORTH);
		calPanel.add(panel);
		calPanel.add(buildCalendarNavigationPanel(), BorderLayout.SOUTH);
	}

	/*
	 * Creates a small panel above the month table to display the month and
	 * year along with the 'prevBtn', 'nextBtn' month selection buttons
	 * and a 'closeCalendarBtn'.
	 */
	private JPanel buildCalendarManagmentPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		// Add a text display of the selected month and year.
		// A JTextField is used for the label instead of a JLabel
		// as it is easier to ensure a consistent size; JLabel
		// expands and contracts with the text size
		calLabel.setEditable(false);
		int fontSize = calLabel.getFont()
				.getSize();
		calLabel.setFont(calLabel.getFont()
				.deriveFont(Font.PLAIN, fontSize - 2));
		panel.add(calLabel);

		panel.add(closeCalendarBtn);

		return panel;
	}

	private JPanel buildCalendarNavigationPanel() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		Box vertical = Box.createVerticalBox();

		month_next.setActionCommand("nextMonth");
		month_prev.setActionCommand("previousMonth");
		year_next.setActionCommand("nextYear");
		year_prev.setActionCommand("previousYear");
		ffyear_next.setActionCommand("nextYear10");
		rewyear_prev.setActionCommand("previousYear10");

		Dimension preferred = ffyear_next.getPreferredSize();

		month_next.setPreferredSize(preferred);
		month_prev.setPreferredSize(preferred);
		year_next.setPreferredSize(preferred);
		year_prev.setPreferredSize(preferred);
		ffyear_next.setPreferredSize(preferred);
		rewyear_prev.setPreferredSize(preferred);

		closeCalendarBtn.setActionCommand("close");
		vertical.add(month_prev);
		vertical.add(year_prev);
		vertical.add(rewyear_prev);
		panel.add(vertical);

		vertical = Box.createVerticalBox();
		vertical.add(month_next);
		vertical.add(year_next);
		vertical.add(ffyear_next);

		panel.add(vertical);

		return panel;
	}

	/*
	 * Register all required listeners with appropriate
	 * components
	 */
	private void registerListeners() {

		ButtonActionListener btnListener = new ButtonActionListener();

		// 'Combo-box' listeners
		// input.addKeyListener( new InputListener() );
		comboBtn.addActionListener(btnListener);

		// Calendar (table) selection listener
		// Must be added to both the table selection model
		// and the column selection model; otherwise, new
		// column selections on the same row are not recognized
		CalendarSelectionListener listener = new CalendarSelectionListener();
		table.getSelectionModel()
				.addListSelectionListener(listener);
		table.getColumnModel()
				.getSelectionModel()
				.addListSelectionListener(listener);

		// Calendar navigation listeners
		month_next.addActionListener(btnListener);
		month_prev.addActionListener(btnListener);
		year_next.addActionListener(btnListener);
		year_prev.addActionListener(btnListener);
		ffyear_next.addActionListener(btnListener);
		rewyear_prev.addActionListener(btnListener);
		closeCalendarBtn.addActionListener(btnListener);

	}

	/*
	 * Fill the table model with the days in the selected month.
	 * Rows in the table correspond to 'weeks', columns to 'days'.
	 *
	 * Strategy:
	 * 1. get the first calendar day in the new month
	 * 2. find it's position in the first week of the month to
	 * determine the starting column for the day numbers
	 * 3. find the actual number of days in the month
	 * 4. fill the calendar with the day values, erasing any days
	 * left over from the old month
	 */
	private void updateTable(Calendar cal) {

		Calendar dayOne = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);

		// compute the number of days in the month and
		// the start column for the first day in the first week
		int actualDays = cal.getActualMaximum(Calendar.DATE);
		int startIndex = dayOne.get(Calendar.DAY_OF_WEEK) - 1;

		// fill the calendar for the new month
		int day = 1;
		for (int row = 0; row < 6; row++) {
			for (int col = 0; col < 7; col++) {
				if ((col < startIndex && row == 0) || day > actualDays) {
					// overwrite any left over values from old month
					display.setValueAt("", row, col);
				} else {
					display.setValueAt(new Integer(day), row, col);
					day++;
				}
			}
		}

		// set the month, year label
		calLabel.setText(months[cal.get(Calendar.MONTH)] + ", " + cal.get(Calendar.YEAR));

		// set the calendar selection
		// table.changeSelection( cal.get( Calendar.WEEK_OF_MONTH ) - 1,
		 //cal.get( Calendar.DAY_OF_WEEK ) - 1,
		 //false, false );
	}

	/*
	 * Gets a Popup to hold the calendar display and determines
	 * it's position on the screen.
	 */
	private Popup getPopup() {

		Point p = input.getLocationOnScreen();
		Dimension inputSize = input.getPreferredSize();
		Dimension calendarSize = calPanel.getPreferredSize();

		if ((p.y + calendarSize.height) < screenSize.height - 50) {
			// will fit below input panel
			popup = factory.getPopup(input, calPanel, p.x, p.y + inputSize.height);
		} else {
			// need to fit it above input panel
			popup = factory.getPopup(input, calPanel, p.x, p.y - calendarSize.height);
		}
		return popup;
	}

	/*
	 * Returns the currently selected date as a <code>Calendar</code> object.
	 *
	 * @return Calendar the currently selected calendar date
	 */
	public Calendar getDate() {

		cat.fine("Calendar combo box getDate called " + current.toString());
		if (input.getText() == null || input.getText()
				.trim()
				.equals(""))
			return null;
		else
			return current;
	}

	protected void performAdditionalFunction() {

		// only for extension purposes
	}

	/**
	 * Sets the current date and updates the UI to reflect the new date.
	 * 
	 * @param newDate
	 *        the new date as a <code>Date</code> object.
	 * @see Date
	 */
	public void setDate(Date newDate) {

		if (newDate != null) {
			current.setTime(newDate);
			input.setText(sf.format(newDate)
					.toString());
		} else
			input.setText("");
	}

	/*
	 * Creates a custom model to back the table.
	 */
	private class CalendarModel extends DefaultTableModel {

		public CalendarModel(int row, int col) {
			super(row, col);
		}

		/**
		 * Overrides the method to return an Integer class
		 * type for all columns. The numbers are automatically
		 * right-aligned by a default renderer that's supplied
		 * as part of JTable.
		 */
		@Override
		public Class getColumnClass(int column) {

			return Integer.class;
		}

		/**
		 * Overrides the method to disable cell editing.
		 * The default is editable.
		 */
		@Override
		public boolean isCellEditable(int row, int col) {

			return false;
		}
	}

	/*
	 * Captures the 'prevBtn', 'nextBtn', 'comboBtn' and
	 * 'closeCalendarBtn' actions.
	 *
	 * The combo button is disabled when the popup is shown
	 * and enabled when the popup is hidden. Failure to do
	 * so results in the popup screen area not being cleared
	 * correctly if the user clicks the button while the popup
	 * is being displayed.
	 */
	private class ButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String cmd = e.getActionCommand();

			if (cmd.equals("previousMonth")) {
				current.add(Calendar.MONTH, -1);
				input.setText(sf.format(current.getTime())
						.toString());
			} else if (cmd.equals("nextMonth")) {
				current.add(Calendar.MONTH, 1);
				input.setText(sf.format(current.getTime())
						.toString());
			} else if (cmd.equals("previousYear")) {
				current.add(Calendar.YEAR, -1);
				input.setText(sf.format(current.getTime())
						.toString());
			} else if (cmd.equals("nextYear")) {
				current.add(Calendar.YEAR, 1);
				input.setText(sf.format(current.getTime())
						.toString());
			} else if (cmd.equals("previousYear10")) {
				current.add(Calendar.YEAR, -10);
				input.setText(sf.format(current.getTime())
						.toString());
			} else if (cmd.equals("nextYear10")) {
				current.add(Calendar.YEAR, 10);
				input.setText(sf.format(current.getTime())
						.toString());
			} else if (cmd.equals("close")) {
				popup.hide();
				comboBtn.setEnabled(true);
			} else {
				comboBtn.setEnabled(false);
				popup = getPopup();
				popup.show();
			}

			updateTable(current);
		}
	}

	/*
	 * Captures a user selection in the calendar display and
	 * changes the value in the 'combo box' to match the selected date.
	 *
	 */
	private class CalendarSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {

			if (!e.getValueIsAdjusting()) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();

				Object value = null;
				try {
					value = display.getValueAt(row, col);
				} catch (ArrayIndexOutOfBoundsException ex) {
					// ignore, happens when the calendar is
					// displayed for the first time
				}

				if (value instanceof Integer) {
					int day = ((Integer) value).intValue();
					current.set(Calendar.DATE, day);
					// input.setText( current.getTime().toString() );
					input.setText(sf.format(current.getTime())
							.toString());
					performAdditionalFunction();
					popup.hide();
					comboBtn.setEnabled(true);
				}
			}
		}
	}
}
