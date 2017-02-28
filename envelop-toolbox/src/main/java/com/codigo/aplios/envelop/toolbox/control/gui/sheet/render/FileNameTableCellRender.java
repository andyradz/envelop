package com.codigo.aplios.envelop.toolbox.control.gui.sheet.render;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FileNameTableCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int col) {

        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        String myValue = String.valueOf(value);
        int index = myValue.lastIndexOf("\\");
        myValue = myValue.substring(index + 1);
        setText(myValue);
        return l;
    }

}
