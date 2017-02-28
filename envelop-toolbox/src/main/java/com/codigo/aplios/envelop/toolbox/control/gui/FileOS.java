package com.codigo.aplios.envelop.toolbox.control.gui;

import com.codigo.aplios.envelop.toolbox.control.gui.sheet.render.FileNameTableCellRender;
import com.codigo.aplios.envelop.toolbox.control.gui.sheet.render.ImageTableCellRender;
import com.codigo.aplios.envelop.toolbox.domain.form.JForm;
import com.codigo.aplios.envelop.toolbox.model.VolumeListModel;
import com.codigo.aplios.envelop.toolbox.model.FileTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

public class FileOS {

    private JFrame frmToolbox;

    private JTable table;

    private JTable table_1;

    /**
     * Launch the application.
     *
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws IllegalAccessException {

        // FileTableDemo.scrollToVisible(table, 0, 0);
        // frame.setSize(1024, 860);
        // frame.setLocationRelativeTo(null);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setVisible(true);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOS window = new FileOS();
                    window.frmToolbox.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    /**
     * Create the application.
     *
     * @throws IllegalAccessException
     */
    public FileOS() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        initialize();

        FileSystemView fsv = FileSystemView.getFileSystemView();

        File[] drives = File.listRoots();
        if (drives != null && drives.length > 0) {
            this.comboBox_1.removeAll();
            for (File aDrive : drives) {
                //    this.comboBox_1.addItem(aDrive.getName());
                //  this.comboBox.addItem(aDrive.getName());
                // System.out.println("Drive Letter: " + aDrive);
                // System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive));
                // System.out.println("\tTotal space: " + aDrive.getTotalSpace());
                // System.out.println("\tFree space: " + aDrive.getFreeSpace());
                // System.out.println();
            }
        }

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    //UIManager.setLookAndFeel(info.getClassName());
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    // FontUIResource font = new FontUIResource("Tahoma",
                    // Font.PLAIN, 12);
                    // UIManager.put("Table.font", font);

                    // UIManager.put("Table.foreground", Color.RED);
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

        }

        // Figure out what directory to display
        File dir;
        // dir = new File(System.getProperty("user.home"));
        dir = new File("C:");

        // Create a TableModel object to represent the contents of the directory
        // this.model = new FileTableModel(dir);
        // Display it all in a scrolling window and make the window appear
        // JFrame frame = new JFrame("FileTableDemo");
        JForm form = new JForm();
        JFrame frame = form.makeFrame("FileTableDemo");

        this.comboBox_1.addActionListener((event)
                -> {
            this.model1.reload(new File(((JComboBox) event.getSource()).getSelectedItem().toString()));
            this.model1.fireTableDataChanged();
        });

        // Create a JTable and tell it to display our model
        // FileTable table = new FileTable(model);
        // table.setModel(model);
        // table.setRowHeight(24);
        // table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        //
        // //createKeybindings(table);
        // table.setAutoCreateRowSorter(true);
        // table.getColumnModel().getColumn(0).setMaxWidth(20);
        // table.getColumnModel().getColumn(2).setWidth(10);
        // table.clearSelection();
        // table.setIntercellSpacing(new Dimension(0, 1));
        // table.setSelectionBackground(Color.BLACK);
        // table.setSelectionForeground(Color.WHITE);
        // frame.getContentPane().add(new JScrollPane(table));
        //
        // // table.setShowHorizontalLines(false);
        //
        // table.setShowVerticalLines(false);
        // table.setFocusable(true);
        // table.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());
        // table.getColumnModel().getColumn(1).setCellRenderer(new FileRender());
        // table.requestFocus(true);
        // table.changeSelection(0, 0, false, false);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmToolbox = new JFrame();
        frmToolbox.setTitle("Toolbox");
        frmToolbox.getContentPane().setBackground(new Color(245, 245, 220));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0};
        gridBagLayout.rowHeights = new int[]{26, 29, 488, 0, 30, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        frmToolbox.getContentPane().setLayout(gridBagLayout);

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(SystemColor.control));
        FlowLayout flowLayout_2 = (FlowLayout) panel.getLayout();
        flowLayout_2.setVgap(0);
        flowLayout_2.setHgap(0);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        frmToolbox.getContentPane().add(panel, gbc_panel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(null);
        FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 0;
        gbc_panel_1.gridy = 1;
        frmToolbox.getContentPane().add(panel_1, gbc_panel_1);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(SystemColor.controlHighlight));
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 2;
        frmToolbox.getContentPane().add(panel_2, gbc_panel_2);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{0};
        gbl_panel_2.rowHeights = new int[]{452};
        gbl_panel_2.columnWeights = new double[]{1.0};
        gbl_panel_2.rowWeights = new double[]{1.0};
        panel_2.setLayout(gbl_panel_2);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(.5d);
        GridBagConstraints gbc_splitPane = new GridBagConstraints();
        gbc_splitPane.fill = GridBagConstraints.BOTH;
        gbc_splitPane.gridx = 0;
        gbc_splitPane.gridy = 0;
        panel_2.add(splitPane, gbc_splitPane);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new EmptyBorder(0, 0, 0, 0));
        splitPane.setRightComponent(panel_4);
        GridBagLayout gbl_panel_4 = new GridBagLayout();
        gbl_panel_4.columnWidths = new int[]{0, 0};
        gbl_panel_4.rowHeights = new int[]{0, 0, 0, 313, 30, 0};
        gbl_panel_4.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        panel_4.setLayout(gbl_panel_4);

        JPanel panel_7 = new JPanel();
        GridBagConstraints gbc_panel_7 = new GridBagConstraints();
        gbc_panel_7.fill = GridBagConstraints.BOTH;
        gbc_panel_7.gridx = 0;
        gbc_panel_7.gridy = 3;
        panel_4.add(panel_7, gbc_panel_7);
        GridBagLayout gbl_panel_7 = new GridBagLayout();
        gbl_panel_7.columnWidths = new int[]{0, 0};
        gbl_panel_7.rowHeights = new int[]{394, 0};
        gbl_panel_7.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_7.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_7.setLayout(gbl_panel_7);

        File dir1 = new File("C:");
        model1 = new FileTableModel(dir1);
        table_1 = new JTable(model1);
        table_1.getColumnModel().getColumn(0).setCellRenderer(new ImageTableCellRender());
        table_1.setRowHeight(30);
        table_1.getColumnModel().getColumn(1).setCellRenderer(new FileNameTableCellRender());

        GridBagConstraints gbc_table_1 = new GridBagConstraints();
        gbc_table_1.fill = GridBagConstraints.BOTH;
        gbc_table_1.gridx = 0;
        gbc_table_1.gridy = 0;
        panel_7.add(table_1, gbc_table_1);

        JPanel panel_9 = new JPanel();
        GridBagConstraints gbc_panel_9 = new GridBagConstraints();
        gbc_panel_9.fill = GridBagConstraints.BOTH;
        gbc_panel_9.gridx = 0;
        gbc_panel_9.gridy = 4;
        panel_4.add(panel_9, gbc_panel_9);
        GridBagLayout gbl_panel_9 = new GridBagLayout();
        gbl_panel_9.columnWidths = new int[]{0, 0};
        gbl_panel_9.rowHeights = new int[]{0, 0};
        gbl_panel_9.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_9.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_9.setLayout(gbl_panel_9);

        JLabel lblNewLabel = new JLabel("0 b/ 533,62 k w 0 / plik(ach/ów)");
        lblNewLabel.setForeground(new Color(0, 0, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel.setBackground(Color.ORANGE);
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 2, 0, 0);
        gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        panel_9.add(lblNewLabel, gbc_lblNewLabel);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(null);
        splitPane.setLeftComponent(panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[]{0, 0};
        gbl_panel_3.rowHeights = new int[]{37, 0, 0, 290, 30, 0};
        gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
        panel_3.setLayout(gbl_panel_3);

        JPanel panel_10 = new JPanel();
        GridBagConstraints gbc_panel_10 = new GridBagConstraints();
        gbc_panel_10.insets = new Insets(0, 0, 5, 0);
        gbc_panel_10.fill = GridBagConstraints.BOTH;
        gbc_panel_10.gridx = 0;
        gbc_panel_10.gridy = 0;
        panel_3.add(panel_10, gbc_panel_10);
        GridBagLayout gbl_panel_10 = new GridBagLayout();
        gbl_panel_10.columnWidths = new int[]{52, 474, 35, 0, 0};
        gbl_panel_10.rowHeights = new int[]{20, 0};
        gbl_panel_10.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_10.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_10.setLayout(gbl_panel_10);

        comboBox_1 = new JComboBox<VolumeListModel>(new VolumeListModel());
        comboBox_1.setSelectedIndex(1);
        GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
        gbc_comboBox_1.insets = new Insets(0, 0, 0, 5);
        gbc_comboBox_1.fill = GridBagConstraints.BOTH;
        gbc_comboBox_1.gridx = 0;
        gbc_comboBox_1.gridy = 0;
        panel_10.add(comboBox_1, gbc_comboBox_1);

        JLabel lblNewLabel_2 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 0;
        panel_10.add(lblNewLabel_2, gbc_lblNewLabel_2);

        JButton btnNewButton_7 = new JButton("\\");
        GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
        gbc_btnNewButton_7.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton_7.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton_7.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton_7.gridx = 2;
        gbc_btnNewButton_7.gridy = 0;
        panel_10.add(btnNewButton_7, gbc_btnNewButton_7);

        JButton btnNewButton_6 = new JButton("..");
        GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
        gbc_btnNewButton_6.fill = GridBagConstraints.VERTICAL;
        gbc_btnNewButton_6.anchor = GridBagConstraints.EAST;
        gbc_btnNewButton_6.gridx = 3;
        gbc_btnNewButton_6.gridy = 0;
        panel_10.add(btnNewButton_6, gbc_btnNewButton_6);

        JPanel panel_6 = new JPanel();
        GridBagConstraints gbc_panel_6 = new GridBagConstraints();
        gbc_panel_6.insets = new Insets(0, 0, 5, 0);
        gbc_panel_6.fill = GridBagConstraints.BOTH;
        gbc_panel_6.gridx = 0;
        gbc_panel_6.gridy = 3;
        panel_3.add(panel_6, gbc_panel_6);
        GridBagLayout gbl_panel_6 = new GridBagLayout();
        gbl_panel_6.columnWidths = new int[]{0, 0};
        gbl_panel_6.rowHeights = new int[]{398, 0};
        gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_6.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_6.setLayout(gbl_panel_6);

        File dir = new File("C:");
        FileTableModel model = new FileTableModel(dir);
        table = new JTable(model);

        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.gridx = 0;
        gbc_table.gridy = 0;
        panel_6.add(table, gbc_table);

        JPanel panel_8 = new JPanel();
        GridBagConstraints gbc_panel_8 = new GridBagConstraints();
        gbc_panel_8.fill = GridBagConstraints.BOTH;
        gbc_panel_8.gridx = 0;
        gbc_panel_8.gridy = 4;
        panel_3.add(panel_8, gbc_panel_8);
        GridBagLayout gbl_panel_8 = new GridBagLayout();
        gbl_panel_8.columnWidths = new int[]{0, 0};
        gbl_panel_8.rowHeights = new int[]{0, 0};
        gbl_panel_8.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_8.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        panel_8.setLayout(gbl_panel_8);

        JLabel label = new JLabel("0 b/ 533,62 k w 0 / plik(ach/ów)");
        label.setForeground(new Color(0, 0, 255));
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBackground(Color.ORANGE);
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.insets = new Insets(0, 2, 0, 0);
        gbc_label.fill = GridBagConstraints.BOTH;
        gbc_label.gridx = 0;
        gbc_label.gridy = 0;
        panel_8.add(label, gbc_label);

        JPanel panel_11 = new JPanel();
        GridBagConstraints gbc_panel_11 = new GridBagConstraints();
        gbc_panel_11.fill = GridBagConstraints.BOTH;
        gbc_panel_11.gridx = 0;
        gbc_panel_11.gridy = 3;
        frmToolbox.getContentPane().add(panel_11, gbc_panel_11);
        GridBagLayout gbl_panel_11 = new GridBagLayout();
        gbl_panel_11.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel_11.rowHeights = new int[]{30, 0};
        gbl_panel_11.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_11.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_11.setLayout(gbl_panel_11);

        JLabel lblNewLabel_1 = new JLabel("New label");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 0;
        panel_11.add(lblNewLabel_1, gbc_lblNewLabel_1);

        comboBox = new JComboBox<>(new VolumeListModel());
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 1;
        gbc_comboBox.gridy = 0;
        panel_11.add(comboBox, gbc_comboBox);

        JPanel panel_5 = new JPanel();
        panel_5.setFocusable(false);
        panel_5.setFocusTraversalKeysEnabled(false);
        panel_5.setAlignmentY(0.0f);
        panel_5.setAlignmentX(0.0f);
        panel_5.setRequestFocusEnabled(false);
        panel_5.setBorder(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.gridx = 0;
        gbc_panel_5.gridy = 4;
        frmToolbox.getContentPane().add(panel_5, gbc_panel_5);
        // btnNewButton.setBorder(null);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        GridBagLayout gbl_panel_5 = new GridBagLayout();
        gbl_panel_5.columnWidths = new int[]{174, 174, 174, 174, 174, 174, 174, 0};
        gbl_panel_5.rowHeights = new int[]{30, 0};
        gbl_panel_5.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_5.setLayout(gbl_panel_5);

        JButton btnNewButton = new JButton("F3 Podgląd");
        btnNewButton.setMnemonic(KeyEvent.VK_FIND);
        btnNewButton.setMargin(new Insets(0, 0, 0, 0));
        btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton.setFocusable(false);
        btnNewButton.setRequestFocusEnabled(false);
        btnNewButton.setRolloverEnabled(false);
        btnNewButton.setVerifyInputWhenFocusTarget(false);
        btnNewButton.setFocusTraversalKeysEnabled(false);
        btnNewButton.setDefaultCapable(false);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBorder(emptyBorder);
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton.gridx = 0;
        gbc_btnNewButton.gridy = 0;
        panel_5.add(btnNewButton, gbc_btnNewButton);

        JButton btnNewButton_1 = new JButton("F4 Edycja");
        btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton_1.setBorder(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_1.gridx = 1;
        gbc_btnNewButton_1.gridy = 0;
        panel_5.add(btnNewButton_1, gbc_btnNewButton_1);

        JButton btnNewButton_2 = new JButton("F5 Kopiuj");
        btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton_2.setIcon(
                new ImageIcon(
                        "D:\\codigo.warehouse\\projekty\\eclipse.workspace\\envelop-system\\envelop-ui\\resource\\ic_copyright_black_24dp.png"));
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton_2.setBorder(new EmptyBorder(0, 0, 0, 0));
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_2.gridx = 2;
        gbc_btnNewButton_2.gridy = 0;
        panel_5.add(btnNewButton_2, gbc_btnNewButton_2);

        JButton btnNewButton_3 = new JButton("F6 Zmień / Przesuń");
        btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_3.gridx = 3;
        gbc_btnNewButton_3.gridy = 0;
        panel_5.add(btnNewButton_3, gbc_btnNewButton_3);

        JButton btnFNowyKatalog = new JButton("F7 Nowy katalog");
        btnFNowyKatalog.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFNowyKatalog.setIcon(null);
        btnFNowyKatalog.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnFNowyKatalog.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnFNowyKatalog = new GridBagConstraints();
        gbc_btnFNowyKatalog.fill = GridBagConstraints.BOTH;
        gbc_btnFNowyKatalog.gridx = 4;
        gbc_btnFNowyKatalog.gridy = 0;
        panel_5.add(btnFNowyKatalog, gbc_btnFNowyKatalog);

        JButton btnNewButton_4 = new JButton("F8 Usuń");
        btnNewButton_4.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton_4.setIcon(
                new ImageIcon(
                        "D:\\codigo.warehouse\\projekty\\eclipse.workspace\\envelop-system\\envelop-ui\\resource\\ic_delete_sweep_black_18dp.png"));
        btnNewButton_4.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
        gbc_btnNewButton_4.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_4.gridx = 5;
        gbc_btnNewButton_4.gridy = 0;
        panel_5.add(btnNewButton_4, gbc_btnNewButton_4);

        btnNewButton_5 = new JButton("ALT+F4 Zakończ");
        btnNewButton_5.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNewButton_5.setIcon(
                new ImageIcon(
                        "D:\\codigo.warehouse\\projekty\\eclipse.workspace\\envelop-system\\envelop-ui\\resource\\ic_exit_to_app_black_24dp.png"));
        btnNewButton_5.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 12));
        GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
        gbc_btnNewButton_5.fill = GridBagConstraints.BOTH;
        gbc_btnNewButton_5.gridx = 6;
        gbc_btnNewButton_5.gridy = 0;
        panel_5.add(btnNewButton_5, gbc_btnNewButton_5);
        frmToolbox.setBounds(100, 100, 1240, 689);
        frmToolbox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmToolbox.setLocationRelativeTo(null);
    }

    private JButton btnNewButton_5;

    private JComboBox<VolumeListModel> comboBox;

    private JComboBox<VolumeListModel> comboBox_1;

    private FileTableModel model1;
}
