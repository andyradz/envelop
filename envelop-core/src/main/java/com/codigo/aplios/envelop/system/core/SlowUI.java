package com.codigo.aplios.envelop.system.core;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * A UI with a variety of components. Sometimes these components take a long
 * time to run, sometimes they don't. In a production app, this is a great
 * way to annoy users.
 */
public class SlowUI {
	public static void main(String[] argv) throws Exception {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				JFrame frame = new JFrame("Slow UI");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(buildContent());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	private static JPanel buildContent() {

		JTextArea text1 = new JTextArea(5, 20);
		text1.setText("I have a 20% chance of introducing a 400 millisecond delay\n"
				+ " which doesn't seem like much until you're typing");
		text1.getDocument()
				.addDocumentListener(new RandomSlowdown(20, 400L));

		JButton button1 = new JButton("I never run slowly");
		JButton button2 = new JButton("I sleep for 10 seconds");
		button2.addActionListener(new RandomSlowdown(100, 10000L));

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(text1, BorderLayout.CENTER);
		panel.add(button1, BorderLayout.NORTH);
		panel.add(button2, BorderLayout.SOUTH);
		return panel;
	}

	/**
	 * An event handler that introduces random sleeps into its operation. This might
	 * be what you see if you read a file in an event handler: often fast, sometimes
	 * really slow.
	 */
	private static class RandomSlowdown implements ActionListener, DocumentListener {
		private int _chance;
		private long _millis;
		private Random _rnd;

		public RandomSlowdown(int chance, long millis) {
			_chance = chance;
			_millis = millis;
			_rnd = new Random();
		}

		@Override
		public void changedUpdate(DocumentEvent evt) {

			commonHandler();
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {

			commonHandler();
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {

			commonHandler();
		}

		@Override
		public void actionPerformed(ActionEvent evt) {

			commonHandler();
		}

		private void commonHandler() {

			if (_rnd.nextInt(100) >= _chance)
				return;
			try {
				Thread.sleep(_millis);
			} catch (InterruptedException ignored) {
				// this should never happen
			}
		}

	}
}
