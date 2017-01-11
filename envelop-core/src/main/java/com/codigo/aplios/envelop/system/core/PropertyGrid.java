package com.codigo.aplios.envelop.system.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.EventObject;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


public class PropertyGrid extends JTable {
	public PropertyGrid(JFrame parent) {
		labelRenderer = new LabelCellRenderer();
		labelEditor = new LabelCellEditor();

		fields = new LinkedHashMap<>();
		curRow = 0;

		eventListener = null;

		this.parent = parent;
		this.setModel(new PGModel());
		this.setUI(new PGUI());

		this.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public void clear() {

		this.removeAll();
		this.clearSelection();

		fields.clear();
		curRow = 0;
	}

	public void setEventListener(EventListener listener) {

		eventListener = listener;
	}

	public void addCategory(String name, String caption) {

		if (fields.containsKey(name))
			return;

		Field field = new Field();
		field.name = name;

		field.row = curRow++;
		field.type = "category";
		field.choices = null;
		field.value = null;

		field.label = new JLabel(caption);
		field.renderer = labelRenderer;
		field.editor = labelEditor;

		fields.put(name, field);

		field.label.setFont(field.label.getFont()
				.deriveFont(Font.BOLD));
		field.label.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void addField(String name, String caption, String type, java.util.List choices, Object val) {

		if (fields.containsKey(name)) {
			if (!val.equals(fields.get(name).value))
				fields.get(name).value = null;

			return;
		}

		Field field = new Field();
		field.name = name;

		field.row = curRow++;
		field.type = type;
		field.choices = choices;
		field.value = val;

		field.label = new JLabel(caption);
		field.renderer = null;

		switch (type) {
		case "text":
		case "int":
			field.editor = new TextCellEditor(field);
			break;

		case "float":
			field.renderer = new FloatCellRenderer();
			field.editor = new FloatCellEditor(field);
			break;

		case "list":
			field.editor = new ListCellEditor(field);
			break;

		case "bool":
			field.renderer = new BoolCellRenderer();
			field.editor = new BoolCellEditor(field);
			break;

		case "objname":
			field.editor = new ObjectCellEditor(field);
			break;
		}

		if (field.renderer == null)
			field.renderer = new GeneralCellRenderer();

		fields.put(name, field);
	}

	public void setFieldValue(String field, Object value) {

		if (!fields.containsKey(field))
			return;

		Field f = fields.get(field);
		if (f.value == null)
			return;
		f.value = value;
	}

	public void removeField(String field) {

		if (!fields.containsKey(field))
			return;

		Field f = fields.get(field);
		f.renderer = null;
		f.editor = null;
		fields.remove(field);
	}

	@Override
	public Rectangle getCellRect(int row, int col, boolean includeSpacing) {

		Rectangle rect = super.getCellRect(row, col, includeSpacing);
		try {
			Field field = (Field) fields.values()
					.toArray()[row];

			if (field.type.equals("category")) {
				if (col == 0)
					rect.width = this.getBounds().width;
				else
					rect.width = 0;
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
		}

		return rect;
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int col) {

		Field field = (Field) fields.values()
				.toArray()[row];

		if (col == 0)
			return labelRenderer;
		if (col == 1 && field.renderer != null)
			return field.renderer;

		return super.getCellRenderer(row, col);
	}

	@Override
	public TableCellEditor getCellEditor(int row, int col) {

		Field field = (Field) fields.values()
				.toArray()[row];

		if (col == 0)
			return labelEditor;
		if (col == 1)
			return field.editor;

		return super.getCellEditor(row, col);
	}

	public class PGModel extends AbstractTableModel {
		@Override
		public int getRowCount() {

			return fields.size();
		}

		@Override
		public int getColumnCount() {

			return 2;
		}

		@Override
		public Object getValueAt(int row, int col) {

			Field field = (Field) fields.values()
					.toArray()[row];

			if (col == 0)
				return field.label.getText();
			else {
				if (!field.type.equals("category")) {
					// if (field.value == null) return "<multiple>";
					return field.value;
				}
			}

			return null;
		}

		@Override
		public String getColumnName(int col) {

			if (col == 0)
				return "Property";
			return "Value";
		}

		@Override
		public boolean isCellEditable(int row, int col) {

			if (col == 0)
				return false;
			return true;
		}
	}

	// based off
	// http://code.google.com/p/spantable/source/browse/SpanTable/src/main/java/spantable/SpanTableUI.java
	public class PGUI extends BasicTableUI {
		@Override
		public void paint(Graphics g, JComponent c) {

			Rectangle r = g.getClipBounds();
			int firstRow = table.rowAtPoint(new Point(r.x, r.y));
			int lastRow = table.rowAtPoint(new Point(r.x, r.y + r.height));
			// -1 is a flag that the ending point is outside the table:
			if (lastRow < 0)
				lastRow = table.getRowCount() - 1;
			for (int row = firstRow; row <= lastRow; row++)
				paintRow(row, g);
		}

		private void paintRow(int row, Graphics g) {

			Rectangle clipRect = g.getClipBounds();
			for (int col = 0; col < table.getColumnCount(); col++) {
				Rectangle cellRect = table.getCellRect(row, col, true);
				if (cellRect.width == 0)
					continue;
				if (cellRect.intersects(clipRect)) {
					paintCell(row, col, g, cellRect);
				}
			}
		}

		private void paintCell(int row, int column, Graphics g, Rectangle area) {

			int verticalMargin = table.getRowMargin();
			int horizontalMargin = table.getColumnModel()
					.getColumnMargin();

			Color c = g.getColor();
			g.setColor(table.getGridColor());
			// Acmlmboard border method
			g.drawLine(area.x + area.width - 1, area.y, area.x + area.width - 1, area.y + area.height - 1);
			g.drawLine(area.x, area.y + area.height - 1, area.x + area.width - 1, area.y + area.height - 1);
			g.setColor(c);

			area.setBounds(area.x + horizontalMargin / 2, area.y + verticalMargin / 2, area.width - horizontalMargin,
					area.height - verticalMargin);

			if (table.isEditing() && table.getEditingRow() == row && table.getEditingColumn() == column) {
				Component component = table.getEditorComponent();
				component.setBounds(area);
				component.validate();
			} else {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component component = table.prepareRenderer(renderer, row, column);
				if (renderer != null && component != null) {
					if (component.getParent() == null)
						rendererPane.add(component);
					rendererPane.paintComponent(g, component, table, area.x, area.y, area.width, area.height, true);
				}
			}
		}
	}

	public class LabelCellRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			Field field = (Field) fields.values()
					.toArray()[row];
			field.label.setBackground(Color.CYAN);
			if (col == 0)
				return field.label;
			return null;
		}
	}

	public class GeneralCellRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			if (value == null)
				value = "<multiple>";
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		}
	}

	public class FloatCellRenderer extends DefaultTableCellRenderer {
		JLabel label;

		public FloatCellRenderer() {
			label = new JLabel();
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			if (value == null) {
				label.setText("<multiple>");
				return label;
			}

			// make float rendering consistent with JSpinner's display
			DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(Locale.getDefault());
			df.applyPattern("#.###");
			String formattedval = df.format(value);
			label.setText(formattedval);
			// label.setHorizontalAlignment(SwingConstants.RIGHT);
			return label;
		}
	}

	public class BoolCellRenderer extends DefaultTableCellRenderer {
		JCheckBox cb;

		public BoolCellRenderer() {
			cb = new JCheckBox();
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {

			if (value == null) {
				cb.getModel()
						.setSelected(true);
				cb.getModel()
						.setArmed(true);
			} else
				cb.setSelected((boolean) value);
			return cb;
		}
	}

	public class LabelCellEditor implements TableCellEditor {
		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {

			return null;
		}

		@Override
		public Object getCellEditorValue() {

			return null;
		}

		@Override
		public boolean isCellEditable(EventObject anEvent) {

			return false;
		}

		@Override
		public boolean shouldSelectCell(EventObject anEvent) {

			return false;
		}

		@Override
		public boolean stopCellEditing() {

			return true;
		}

		@Override
		public void cancelCellEditing() {

		}

		@Override
		public void addCellEditorListener(CellEditorListener l) {

		}

		@Override
		public void removeCellEditorListener(CellEditorListener l) {

		}
	}

	public class FloatCellEditor extends AbstractCellEditor implements TableCellEditor {
		JSpinner spinner;
		Field field;

		public FloatCellEditor(Field f) {
			field = f;

			spinner = new JSpinner();
			spinner.setModel(new SpinnerNumberModel(10.00f, -Float.MAX_VALUE, Float.MAX_VALUE, 1f));
			spinner.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent evt) {

					// guarantee the value we're giving out is a Float. herp derp
					Object val = spinner.getValue();
					float fval = (val instanceof Double) ? (float) (double) val : (float) val;
					field.value = fval;
					eventListener.propertyChanged(field.name, fval);
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return spinner.getValue();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			spinner.setValue(value == null ? 0f : value);
			return spinner;
		}
	}

	public class TextCellEditor extends AbstractCellEditor implements TableCellEditor {
		JTextField textfield;
		Field field;
		boolean isInt;

		public TextCellEditor(Field f) {
			field = f;
			isInt = f.type.equals("int");

			textfield = new JTextField(f.value.toString());
			textfield.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent evt) {

				}

				@Override
				public void keyTyped(KeyEvent evt) {

				}

				@Override
				public void keyReleased(KeyEvent evt) {

					Object val = textfield.getText();
					try {
						if (isInt)
							val = Integer.parseInt((String) val);
						textfield.setForeground(Color.getColor("text"));

						field.value = val;
						eventListener.propertyChanged(field.name, val);
					} catch (NumberFormatException ex) {
						textfield.setForeground(new Color(0xFF4040));
					}
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return textfield.getText();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			if (value == null)
				value = isInt ? "0" : "<multiple>";
			textfield.setText(value.toString());
			return textfield;
		}
	}

	public class ListCellEditor extends AbstractCellEditor implements TableCellEditor {
		JComboBox combo;
		Field field;

		public ListCellEditor(Field f) {
			field = f;

			combo = new JComboBox(f.choices.toArray());

			combo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {

					Object val = combo.getSelectedItem();

					if (!field.value.equals(val)) {
						field.value = val;
						eventListener.propertyChanged(field.name, val);
					}
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return combo.getSelectedItem();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			if (value == null)
				combo.setSelectedIndex(0);
			else
				combo.setSelectedItem(value);
			return combo;
		}
	}

	public class BoolCellEditor extends AbstractCellEditor implements TableCellEditor {
		JCheckBox checkbox;
		Field field;

		public BoolCellEditor(Field f) {
			field = f;

			checkbox = new JCheckBox();
			checkbox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {

					boolean val = checkbox.isSelected();
					field.label.setText(Boolean.toString(val));
					field.value = val;
					eventListener.propertyChanged(field.name, val);
				}
			});
		}

		@Override
		public Object getCellEditorValue() {

			return checkbox.isSelected();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			checkbox.setSelected(value == null ? false : (boolean) value);
			return checkbox;
		}
	}

	public class ObjectCellEditor extends AbstractCellEditor implements TableCellEditor {
		JPanel container;
		JTextField textfield;
		JButton button;
		Field field;

		public ObjectCellEditor(Field f) {
			field = f;

			container = new JPanel();
			container.setLayout(new BorderLayout());

			textfield = new JTextField(f.value.toString());
			container.add(textfield, BorderLayout.CENTER);
			textfield.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent evt) {

				}

				@Override
				public void keyTyped(KeyEvent evt) {

				}

				@Override
				public void keyReleased(KeyEvent evt) {

					String val = textfield.getText();

					// textfield.setForeground(ObjectDB.objects.containsKey(val) ? Color.getColor(
					// "text") : new Color(0xFF4040));

					field.value = val;
					eventListener.propertyChanged(field.name, val);
				}
			});

			button = new JButton("...");
			container.add(button, BorderLayout.EAST);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					// GalaxyEditorForm gform = (GalaxyEditorForm) parent;

					// ObjectSelectForm objsel = new ObjectSelectForm(gform, gform.zoneArcs.get(
					// gform.galaxyName).gameMask, textfield.getText());
					// objsel.setVisible(true);

					// String val = objsel.selectedObject;
					// textfield.setText(val);
					// textfield.setForeground(ObjectDB.objects.containsKey(val) ? Color.getColor(
					// "text") : new Color(0xFF4040));

					// field.value = val;
					// eventListener.propertyChanged(field.name, val);
				}
			});

			int btnheight = button.getPreferredSize().height;
			button.setPreferredSize(new Dimension(btnheight, btnheight));

			// textfield.setForeground(ObjectDB.objects.containsKey((String) field.value) ? Color
			// .getColor("text") : new Color(0xFF4040));
		}

		@Override
		public Object getCellEditorValue() {

			return textfield.getText();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {

			textfield.setText(value == null ? "<multiple>" : value.toString());
			return container;
		}
	}

	public class Field {
		String name;

		String type;
		int row;
		java.util.List choices;
		Object value;

		JLabel label;
		TableCellRenderer renderer;
		TableCellEditor editor;
	}

	public interface EventListener {
		public void propertyChanged(String propname, Object value);
	}

	public LinkedHashMap<String, PropertyGrid.Field> fields;
	private int curRow;

	private EventListener eventListener;

	private JFrame parent;

	private LabelCellRenderer labelRenderer;
	private LabelCellEditor labelEditor;
	private static JComboBox WygladProgramujComboBox;
	private static JFrame frame = new JFrame("Prognostic :: Administracja");

	public static void main(String s[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		// Add a window listner for close button
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				JOptionPane.showMessageDialog(frame, e.toString(), "Czy chcesz zmknąc program?",
						JOptionPane.QUESTION_MESSAGE);
				System.exit(0);
			}
		});
		// This is an empty content area in the frame
		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Filepath)));
		String appdata = System.getenv("APPDATA");
		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("znaczek.ico")));
		ImageIcon imgicon = new ImageIcon("D://IIM.ico");
		frame.setIconImage(imgicon.getImage());

		DatePicker calcmb = new DatePicker(Calendar.getInstance(Locale.getDefault()));

		JLabel jlbempty = new JLabel("");
		PropertyGrid pg = new PropertyGrid(frame);
		pg.setRowHeight(21);
		pg.addCategory("Visibility", "Okna");
		pg.addField("F1", "Codigo", "bool", null, true);
		pg.addField("Float", "Wartość", "float", null, 1.01);
		pg.addField("Logic", "Logical", "list", Arrays.asList("False", "True"), "False");
		pg.addField("Const", "Stała", "list", Arrays.asList(2, 3, 4, 5, 6), 2);

		pg.addCategory("Visibility1", "Okna1");
		pg.addField("F11", "Codigo1", "bool", null, true);
		pg.addField("Float1", "Wartość1", "float", null, 1);
		pg.addField("Logic1", "Logical1", "list", Arrays.asList("False", "True"), "False");
		pg.addField("Const1", "Stała1", "list", Arrays.asList(2, 3, 4, 5, 6, 23, 23, 2322, 23), 2);

		jlbempty.setPreferredSize(new Dimension(175, 100));
		WygladProgramujComboBox = new JComboBox(new String[] {
				"1",
				"2",
				"3",
				"4" });

		WygladProgramujComboBox.addActionListener(PropertyGrid::WygladProgramu_jComboBoxActionPerformed);
		String[] columnNames = {
				"First Name",
				"Last Name",
				"Sport",
				"# of Years",
				"Vegetarian",
				"Percent" };

		Object[][] data = {
				{
						"Kathy",
						"Smith",
						"Snowboarding",
						new Double(5.01),
						new Boolean(false),
						new Double(0.50), },
				{
						"Andrzej",
						"Radziszewski",
						"Snowboarding",
						new Double(-5.01),
						new Boolean(true),
						new Double(-0.50), },
				{
						"Andrzej",
						"Radziszewski",
						"Pudło",
						new Double(-225.02521),
						new Boolean(true),
						new Double(-0.50), },

				{
						"Adriam",
						"Kalisamon",
						"<p>df</p>",
						new Double(13215.01),
						new Boolean(false),
						new Double(.50), },
				{
						"John",
						"Doe",
						"Rowing",
						new Double(3.65),
						new Boolean(true),
						new Double(0.75), },
				{
						"Sue",
						"Black",
						"Knitting",
						new Double(2.232),
						new Boolean(false),
						new Double(1), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.333),
						new Boolean(true),
						new Double(1), },
				{
						"Jane12",
						"White12",
						"Speed12 reading",
						new Double(210.3555),
						new Boolean(true),
						new Double(0.10), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.23),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.232),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jane3",
						"White3",
						"Speed3 reading",
						new Double(20.232),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jan4e",
						"Whit4e",
						"Speed4 reading",
						new Double(20.0),
						new Boolean(true),
						new Double(0.1), },
				{
						"Jo6e",
						"Bro5wn",
						"Pool3",
						new Double(10.89),
						new Boolean(false),
						new Double(1) },
				{
						"Kathy",
						"Smith",
						"Snowboarding",
						new Double(5.01),
						new Boolean(false),
						new Double(0.50), },
				{
						"John",
						"Doe",
						"Rowing",
						new Double(3.65),
						new Boolean(true),
						new Double(0.75), },
				{
						"Sue",
						"Black",
						"Knitting",
						new Double(2.232),
						new Boolean(false),
						new Double(1), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.333),
						new Boolean(true),
						new Double(1), },
				{
						"Jane12",
						"White12",
						"Speed12 reading",
						new Double(210.3555),
						new Boolean(true),
						new Double(0.10), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.23),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.232),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jane3",
						"White3",
						"Speed3 reading",
						new Double(20.232),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jan412312e",
						"Whit4e23",
						"Speed4 rea213ding",
						new Double(20.0),
						new Boolean(true),
						new Double(0.1), },
				{
						"Jo6e23",
						"Bro5wn1",
						"Pool3232",
						new Double(10.89),
						new Boolean(false),
						new Double(1) },
				{
						"Kathy",
						"Smith",
						"Snowboarding",
						new Double(5.01),
						new Boolean(false),
						new Double(0.50), },
				{
						"John",
						"Doe",
						"Rowing",
						new Double(3.65),
						new Boolean(true),
						new Double(0.75), },
				{
						"Sue",
						"Black",
						"Knitting",
						new Double(2.232),
						new Boolean(false),
						new Double(1), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.333),
						new Boolean(true),
						new Double(1), },
				{
						"Jane12",
						"White12",
						"Speed12 reading",
						new Double(210.3555),
						new Boolean(true),
						new Double(0.10), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.23),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jane",
						"White",
						"Speed reading",
						new Double(20.232),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jane3",
						"White3",
						"Speed3 reading",
						new Double(20.232),
						new Boolean(true),
						new Double(0.100), },
				{
						"Jan4e",
						"Whit4e",
						"Speed4 reading",
						new Double(20.0),
						new Boolean(true),
						new Double(0.1), },
				{
						"Jo6e",
						"Bro5wn",
						"Pool3",
						new Double(12220.89),
						new Boolean(false),
						new Double(1) } };

		class CustomTableCellRender extends DefaultTableCellRenderer {

			Border padding = BorderFactory.createEmptyBorder(0, 2, 0, 2);

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				setBorder(BorderFactory.createCompoundBorder(getBorder(), padding));

				return this;
			}
		}

		class PercentTableCellRenderer extends CustomTableCellRender {

			private final NumberFormat numFormat = NumberFormat.getPercentInstance(Locale.getDefault());

			@Override
			public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				final Component result =
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof Number) {
					setHorizontalAlignment(JLabel.RIGHT);
					setText(numFormat.format(value));
				} else {
					setText("");
				}

				return result;
			}
		}

		class CurrencyTableCellRenderer extends CustomTableCellRender {

			private final NumberFormat FORMAT = NumberFormat.getCurrencyInstance();

			@Override
			public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				final Component result =
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof Number) {
					setHorizontalAlignment(JLabel.RIGHT);
					setText(FORMAT.format(value));
				} else {
					setText("");
				}

				setBackground(Color.orange);
				if (10.0 >= ((Number) value).doubleValue())
					setForeground(Color.BLUE);
				else
					setForeground(Color.RED);
				setFont(getFont().deriveFont(Font.PLAIN));
				return result;
			}
		}

		class BooleanTableCellRenderer extends CustomTableCellRender {

			@Override
			public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				final Component result =
						super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (value instanceof Boolean) {
					setHorizontalAlignment(JLabel.LEFT);
					setText((Boolean) value ? "Tak" : "Nie");
				} else {
					setText("");
				}

				setBackground(Color.LIGHT_GRAY);
				setForeground(Color.yellow);
				setFont(getFont().deriveFont(Font.PLAIN));
				return result;
			}
		}

		class HeaderRenderer implements UIResource, TableCellRenderer {

			private TableCellRenderer original;

			public HeaderRenderer(TableCellRenderer original) {
				this.original = original;
			}

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component comp =
						original.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				comp.setFont(comp.getFont()
						.deriveFont(Font.BOLD));
				return comp;
			}

		}

		final JSortTable sorttable = new JSortTable(data, columnNames);

		sorttable.setRowHeight(21);

		sorttable.getColumnModel()
				.getColumn(0)
				.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
				.getColumn(1)
				.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
				.getColumn(2)
				.setCellRenderer(new CustomTableCellRender());

		sorttable.getColumnModel()
				.getColumn(3)
				.setCellRenderer(new CurrencyTableCellRenderer());

		sorttable.getColumnModel()
				.getColumn(4)
				.setCellRenderer(new BooleanTableCellRenderer());

		sorttable.getColumnModel()
				.getColumn(5)
				.setCellRenderer(new PercentTableCellRenderer());

		sorttable.setFont(new Font(sorttable.getFont()
				.getName(), Font.PLAIN, 11));

		frame.setTitle("DB Suite Studio Manager Free Edition v1.6.2.1");

		frame.getContentPane()
				.add(jlbempty, BorderLayout.NORTH);
		frame.getContentPane()
				.add(WygladProgramujComboBox, BorderLayout.BEFORE_LINE_BEGINS);
		frame.getContentPane()
				.add(new JScrollPane(sorttable), BorderLayout.CENTER);

		JButton button = new JButton("alert");
		button.setIconTextGap(10);
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.setBorder(BorderFactory.createEmptyBorder(4, 4, 2, 20));
//		try {
//			button.setIcon(new ImageIcon(ImageIO.read(Thread.currentThread()
//					.getContextClassLoader()
//					.getResourceAsStream("ic_add_alert_black_18dp.png"))));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		frame.getContentPane()
				.add(button, BorderLayout.SOUTH);

		// frame.pack();
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	private static void WygladProgramu_jComboBoxActionPerformed(ActionEvent evt) {

		// TODO add your handling code here:
		JComboBox control = (JComboBox) evt.getSource();

		/* Konfiguracja wyglądu program */
		try {
			frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if (WygladProgramujComboBox.getSelectedIndex() == 0) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(frame);
			}

			if (WygladProgramujComboBox.getSelectedIndex() == 1) {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);
			}

			if (WygladProgramujComboBox.getSelectedIndex() == 2) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);
			}
			if (WygladProgramujComboBox.getSelectedIndex() == 3) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(control, e.toString(), "Błąd", JOptionPane.ERROR_MESSAGE);
				}
				SwingUtilities.updateComponentTreeUI(control);

			}
		} finally {
			control.setCursor(Cursor.getDefaultCursor());
		}
		System.gc();

	}
}
