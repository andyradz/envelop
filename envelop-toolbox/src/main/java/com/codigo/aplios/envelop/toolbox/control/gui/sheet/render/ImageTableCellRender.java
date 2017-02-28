package com.codigo.aplios.envelop.toolbox.control.gui.sheet.render;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageTableCellRender extends DefaultTableCellRenderer {

	public ImageTableCellRender() {
		// super();
	}

	/*
	 * @see TableCellRenderer#getTableCellRendererComponent(JTable, Object, boolean, boolean, int,
	 * int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		sun.awt.shell.ShellFolder sf = null;
		Image image = null;

		JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		File ff = new File(table.getModel().getValueAt(row, column + 1).toString());
		// File ff = new File("c:/windows");
		Icon icon = null;
		try {

			sf = sun.awt.shell.ShellFolder.getShellFolder(ff);

			icon = new ImageIcon(sf.getIcon(false));
			image = ((ImageIcon) icon).getImage();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// setText("<dir> " + String.valueOf(sf.getTotalSpace()));
		setIcon(icon);
		// setSize(table.getColumnModel().getColumn(column).getWidth() - 150,
		// getPreferredSize().height);

		// if (isSelected) {
		// setBackground(table.getSelectionBackground());
		// setForeground(table.getSelectionForeground());
		// } else {
		// setBackground(table.getBackground());
		// setForeground(table.getForeground());
		// }

		return this;
	}
}
