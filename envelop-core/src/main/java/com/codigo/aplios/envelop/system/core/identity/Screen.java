package com.codigo.aplios.envelop.system.core.identity;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/*
 * Programowanie asynchroniczne
 * http://www.kdgregory.com/index.php?page=swing.async
 * http://www.javaworld.com/article/2073477/swing-gui-programming/customize-swingworker-to-improve-
 * swing-guis.html
 */

public class Screen { // extends JFrame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Screen app = new Screen();
				app.createUI();
			}
		});
	}

	public void createUI() {
		JFrame frm = new JFrame("Stream splitter v.0.1 RC");
		frm.setSize(800, 600);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setResizable(false);
		frm.setLocationRelativeTo(null);
		frm.setVisible(true);
	}
}
