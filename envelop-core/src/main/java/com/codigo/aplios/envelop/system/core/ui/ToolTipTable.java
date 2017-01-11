package com.codigo.aplios.envelop.system.core.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ToolTipTable {
  public static void main(String[] args) {
    try {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception evt) {}
  
    JFrame f = new JFrame("Tool Tip Table");
    JTable tbl = new JTable(new CurrencyTableModel());
    tbl
        .setDefaultRenderer(java.lang.Number.class,
            new ToolTipFractionCellRenderer(10, 3, 6,
                SwingConstants.RIGHT));

    TableColumnModel tcm = tbl.getColumnModel();
    tcm.getColumn(0).setPreferredWidth(150);
    tcm.getColumn(0).setMinWidth(150);
    TextWithIconCellRenderer renderer = new TextWithIconCellRenderer();
    tcm.getColumn(0).setCellRenderer(renderer);
    tcm.getColumn(1).setPreferredWidth(100);
    tcm.getColumn(1).setMinWidth(100);

    // Add the stripe renderer.
    StripedTableCellRenderer.installInTable(tbl, Color.lightGray,
        Color.white, null, null);

    // Add the custom header renderer
    MultiLineHeaderRenderer headerRenderer = new MultiLineHeaderRenderer(
        SwingConstants.CENTER, SwingConstants.BOTTOM);
    headerRenderer.setBackground(Color.blue);
    headerRenderer.setForeground(Color.white);
    headerRenderer.setFont(new Font("Dialog", Font.BOLD, 12));
    int columns = tableHeaders.length;
    for (int i = 0; i < columns; i++) {
      tcm.getColumn(i).setHeaderRenderer(headerRenderer);
      tcm.getColumn(i).setHeaderValue(tableHeaders[i]);
    }

    tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tbl.setPreferredScrollableViewportSize(tbl.getPreferredSize());

    JScrollPane sp = new JScrollPane(tbl);
    f.getContentPane().add(sp, "Center");
    f.pack();
    f.addWindowListener(new WindowAdapter() {
      @Override
	public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });
    f.setVisible(true);
  }

  // Header values. Note the table model provides
  // string column names that are the default header
  // values.
  public static Object[][] tableHeaders = new Object[][] {
      new String[] { "Currency" },
      new String[] { "Yesterday's", "Rate" },
      new String[] { "Today's", "Rate" },
      new String[] { "Rate", "Change" } };
}

@SuppressWarnings("serial")
class FractionCellRenderer extends DefaultTableCellRenderer {
  public FractionCellRenderer(int integer, int fraction, int align) {
    this.integer = integer; // maximum integer digits
    this.fraction = fraction; // exact number of fraction digits
    this.align = align; // alignment (LEFT, CENTER, RIGHT)
  }

  @Override
protected void setValue(Object value) {
    if (value != null && value instanceof Number) {
      formatter.setMaximumIntegerDigits(integer);
      formatter.setMaximumFractionDigits(fraction);
      formatter.setMinimumFractionDigits(fraction);
      setText(formatter.format(((Number) value).doubleValue()));
    } else {
      super.setValue(value);
    }
    setHorizontalAlignment(align);
  }

  protected int integer;

  protected int fraction;

  protected int align;

  protected static NumberFormat formatter = NumberFormat.getInstance();
}

class TextWithIconCellRenderer extends DefaultTableCellRenderer {
  @Override
protected void setValue(Object value) {
    if (value instanceof DataWithIcon) {
      if (value != null) {
        DataWithIcon d = (DataWithIcon) value;
        Object dataValue = d.getData();

        setText(dataValue == null ? "" : dataValue.toString());
        setIcon(d.getIcon());
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setVerticalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.LEFT);
        setVerticalAlignment(SwingConstants.CENTER);
      } else {
        setText("");
        setIcon(null);
      }
    } else {
      super.setValue(value);
    }
  }
}

class MultiLineHeaderRenderer extends JPanel implements TableCellRenderer {
  public MultiLineHeaderRenderer(int horizontalAlignment,
      int verticalAlignment) {
    this.horizontalAlignment = horizontalAlignment;
    this.verticalAlignment = verticalAlignment;
    switch (horizontalAlignment) {
    case SwingConstants.LEFT:
      alignmentX = (float) 0.0;
      break;

    case SwingConstants.CENTER:
      alignmentX = (float) 0.5;
      break;

    case SwingConstants.RIGHT:
      alignmentX = (float) 1.0;
      break;

    default:
      throw new IllegalArgumentException(
          "Illegal horizontal alignment value");
    }
    setBorder(headerBorder);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setOpaque(true);

    background = null;
  }

  @Override
public void setForeground(Color foreground) {
    this.foreground = foreground;
    super.setForeground(foreground);
  }

  @Override
public void setBackground(Color background) {
    this.background = background;
    super.setBackground(background);
  }

  @Override
public void setFont(Font font) {
    this.font = font;
  }

  // Implementation of TableCellRenderer interface
  @Override
public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    removeAll();
    invalidate();

    if (value == null) {
      // Do nothing if no value
      return this;
    }

    // Set the foreground and background colors
    // from the table header if they are not set
    if (table != null) {
      JTableHeader header = table.getTableHeader();
      if (header != null) {
        if (foreground == null) {
          super.setForeground(header.getForeground());
        }

        if (background == null) {
          super.setBackground(header.getBackground());
        }
      }
    }

    if (verticalAlignment != SwingConstants.TOP) {
      add(Box.createVerticalGlue());
    }

    Object[] values;
    int length;
    if (value instanceof Object[]) {
      // Input is an array - use it
      values = (Object[]) value;
    } else {
      // Not an array - turn it into one
      values = new Object[1];
      values[0] = value;
    }
    length = values.length;

    // Configure each row of the header using
    // a separate JLabel. If a given row is
    // a JComponent, add it directly..
    for (int i = 0; i < length; i++) {
      Object thisRow = values[i];

      if (thisRow instanceof JComponent) {
        add((JComponent) thisRow);
      } else {
        JLabel l = new JLabel();
        setValue(l, thisRow, i);
        add(l);
      }
    }

    if (verticalAlignment != SwingConstants.BOTTOM) {
      add(Box.createVerticalGlue());
    }
    return this;
  }

  // Configures a label for one line of the header.
  // This can be overridden by derived classes
  protected void setValue(JLabel l, Object value, int lineNumber) {
    if (value != null && value instanceof Icon) {
      l.setIcon((Icon) value);
    } else {
      l.setText(value == null ? "" : value.toString());
    }
    l.setHorizontalAlignment(horizontalAlignment);
    l.setAlignmentX(alignmentX);
    l.setOpaque(false);
    l.setForeground(foreground);
    l.setFont(font);
  }

  protected int verticalAlignment;

  protected int horizontalAlignment;

  protected float alignmentX;

  // These attributes may be explicitly set
  // They are defaulted to the colors and attributes
  // of the table header
  protected Color foreground;

  protected Color background;

  // These attributes have fixed defaults
  protected Border headerBorder = UIManager
      .getBorder("TableHeader.cellBorder");

  protected Font font = UIManager.getFont("TableHeader.font");
}

class DataWithIcon {
  public DataWithIcon(Object data, Icon icon) {
    this.data = data;
    this.icon = icon;
  }

  public Icon getIcon() {
    return icon;
  }

  public Object getData() {
    return data;
  }

  @Override
public String toString() {
    return data.toString();
  }

  protected Icon icon;

  protected Object data;
}

class CurrencyTableModel extends AbstractTableModel {
  protected String[] columnNames = { "Currency", "Yesterday", "Today",
      "Change" };

  // Constructor: calculate currency change to create the last column
  public CurrencyTableModel() {
    for (int i = 0; i < data.length; i++) {
      data[i][DIFF_COLUMN] = new Double(
          ((Double) data[i][NEW_RATE_COLUMN]).doubleValue()
              - ((Double) data[i][OLD_RATE_COLUMN]).doubleValue());
    }
  }

  // Implementation of TableModel interface
  @Override
public int getRowCount() {
    return data.length;
  }

  @Override
public int getColumnCount() {
    return COLUMN_COUNT;
  }

  @Override
public Object getValueAt(int row, int column) {
    return data[row][column];
  }

  @Override
public Class getColumnClass(int column) {
    return (data[0][column]).getClass();
  }

  @Override
public String getColumnName(int column) {
    return columnNames[column];
  }

  protected static final int OLD_RATE_COLUMN = 1;

  protected static final int NEW_RATE_COLUMN = 2;

  protected static final int DIFF_COLUMN = 3;

  protected static final int COLUMN_COUNT = 4;

  protected static final Class thisClass = CurrencyTableModel.class;

  protected Object[][] data = new Object[][] {
      {
          new DataWithIcon("Belgian Franc", new ImageIcon(thisClass
              .getResource("belgium.gif"))),
          new Double(37.6460110), new Double(37.6508921), null },
      {
          new DataWithIcon("British Pound", new ImageIcon(thisClass
              .getResource("gb.gif"))), new Double(0.6213051),
          new Double(0.6104102), null },
      {
          new DataWithIcon("Canadian Dollar", new ImageIcon(thisClass
              .getResource("canada.gif"))),
          new Double(1.4651209), new Double(1.5011104), null },
      {
          new DataWithIcon("French Franc", new ImageIcon(thisClass
              .getResource("france.gif"))),
          new Double(6.1060001), new Double(6.0100101), null },
      {
          new DataWithIcon("Italian Lire", new ImageIcon(thisClass
              .getResource("italy.gif"))),
          new Double(1181.3668977), new Double(1182.104), null },
      {
          new DataWithIcon("German Mark", new ImageIcon(thisClass
              .getResource("germany.gif"))),
          new Double(1.8191804), new Double(1.8223421), null },
      {
          new DataWithIcon("Japanese Yen", new ImageIcon(thisClass
              .getResource("japan.gif"))),
          new Double(141.0815412), new Double(121.0040432), null } };
}

class ToolTipFractionCellRenderer extends FractionCellRenderer {
  public ToolTipFractionCellRenderer(int integer, int fraction,
      int maxFraction, int align) {
    super(integer, fraction, align);
    this.maxFraction = maxFraction; // Number of tooltip fraction digits
  }

  @Override
public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    Component comp = super.getTableCellRendererComponent(table, value,
        isSelected, hasFocus, row, column);

    if (value != null && value instanceof Number) {
      formatter.setMaximumIntegerDigits(integer);
      formatter.setMaximumFractionDigits(maxFraction);
      formatter.setMinimumFractionDigits(maxFraction);
      ((JComponent) comp).setToolTipText(formatter
          .format(((Number) value).doubleValue()));
    }

    return comp;
  }

  protected int maxFraction;
}

class StripedTableCellRenderer implements TableCellRenderer {
  public StripedTableCellRenderer(TableCellRenderer targetRenderer,
      Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
    this.targetRenderer = targetRenderer;
    this.evenBack = evenBack;
    this.evenFore = evenFore;
    this.oddBack = oddBack;
    this.oddFore = oddFore;
  }

  // Implementation of TableCellRenderer interface
  @Override
public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    TableCellRenderer renderer = targetRenderer;
    if (renderer == null) {
      // Get default renderer from the table
      renderer = table.getDefaultRenderer(table.getColumnClass(column));
    }

    // Let the real renderer create the component
    Component comp = renderer.getTableCellRendererComponent(table, value,
        isSelected, hasFocus, row, column);

    // Now apply the stripe effect
    if (isSelected == false && hasFocus == false) {
      if ((row & 1) == 0) {
        comp.setBackground(evenBack != null ? evenBack : table
            .getBackground());
        comp.setForeground(evenFore != null ? evenFore : table
            .getForeground());
      } else {
        comp.setBackground(oddBack != null ? oddBack : table
            .getBackground());
        comp.setForeground(oddFore != null ? oddFore : table
            .getForeground());
      }
    }

    return comp;
  }

  // Convenience method to apply this renderer to single column
  public static void installInColumn(JTable table, int columnIndex,
      Color evenBack, Color evenFore, Color oddBack, Color oddFore) {
    TableColumn tc = table.getColumnModel().getColumn(columnIndex);

    // Get the cell renderer for this column, if any
    TableCellRenderer targetRenderer = tc.getCellRenderer();

    // Create a new StripedTableCellRenderer and install it
    tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer,
        evenBack, evenFore, oddBack, oddFore));
  }

  // Convenience method to apply this renderer to an entire table
  public static void installInTable(JTable table, Color evenBack,
      Color evenFore, Color oddBack, Color oddFore) {
    StripedTableCellRenderer sharedInstance = null;
    int columns = table.getColumnCount();
    for (int i = 0; i < columns; i++) {
      TableColumn tc = table.getColumnModel().getColumn(i);
      TableCellRenderer targetRenderer = tc.getCellRenderer();
      if (targetRenderer != null) {
        // This column has a specific renderer
        tc.setCellRenderer(new StripedTableCellRenderer(targetRenderer,
            evenBack, evenFore, oddBack, oddFore));
      } else {
        // This column uses a class renderer - use a shared renderer
        if (sharedInstance == null) {
          sharedInstance = new StripedTableCellRenderer(null,
              evenBack, evenFore, oddBack, oddFore);
        }
        tc.setCellRenderer(sharedInstance);
      }
    }
  }

  protected TableCellRenderer targetRenderer;

  protected Color evenBack;

  protected Color evenFore;

  protected Color oddBack;

  protected Color oddFore;
}
