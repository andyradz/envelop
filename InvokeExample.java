package gui;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class InvokeExample extends javax.swing.JFrame {

	private javax.swing.JButton badWayOneButton;
	private javax.swing.JButton badWayTwoButton;
	private javax.swing.JButton goodNewWayButton;
	private javax.swing.JButton goodOldWayButton;
	private javax.swing.JLabel statusLabel;

	public InvokeExample() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		java.awt.GridBagConstraints gridBagConstraints;

		goodOldWayButton = new javax.swing.JButton();
		goodNewWayButton = new javax.swing.JButton();
		badWayOneButton = new javax.swing.JButton();
		badWayTwoButton = new javax.swing.JButton();
		statusLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		getContentPane().setLayout(new java.awt.GridBagLayout());

		goodOldWayButton.setText("Dobrze (po staremu)");
		goodOldWayButton.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				goodOldWayButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 5);
		getContentPane().add(goodOldWayButton, gridBagConstraints);

		goodNewWayButton.setText("Dobrze (po nowemu)");
		goodNewWayButton.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				goodNewWayButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
		getContentPane().add(goodNewWayButton, gridBagConstraints);

		badWayOneButton.setText("Pierwszy zły");
		badWayOneButton.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				badWayOneButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 5);
		getContentPane().add(badWayOneButton, gridBagConstraints);

		badWayTwoButton.setText("Drugi zły");
		badWayTwoButton.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				badWayTwoButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 0.25;
		gridBagConstraints.insets = new java.awt.Insets(10, 5, 5, 10);
		getContentPane().add(badWayTwoButton, gridBagConstraints);

		statusLabel.setText("Gotowe");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 4;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
		getContentPane().add(statusLabel, gridBagConstraints);

		pack();
	}

	private void goodOldWayButtonActionPerformed(java.awt.event.ActionEvent evt) {

		statusLabel.setText("Proszę czekać...");
		setButtonsEnabled(false);

		// Wykonujemy coś, co zajmuje dużo czasu, więc odpalamy to w
		// wątku i aktualizujemy stan, gdy zadanie zostane wykonane.
		Thread worker = new Thread() {

			@Override
			public void run() {

				// Coś co zajmuje dużo czasu ... w prawdziwym życiu, może to
				// być na przykład pobieranie danych z bazy danych,
				// wywołanie zdalnej metody, itp.
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
				}

				// Aktualizujemy status zadania wykorzystując invokeLater().
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {

						statusLabel.setText("Gotowe");
						setButtonsEnabled(true);
					}
				});
			}
		};

		worker.start(); // Uruchamiamy wątek, aby nie blokować EDT.
	}

	private void goodNewWayButtonActionPerformed(java.awt.event.ActionEvent evt) {

		setButtonsEnabled(false);

		// Wykonujemy to samo zadanie, tym razem korzystając z klasy
		// SwingWorker'a.
		SwingWorker<?,?> worker = new SwingWorker() {

			@Override
			protected void process(List chunks) {

				// Aktualizujemy status zadania wykorzystując fakt, że metoda
				// process() wykonywana jest w EDT.
				statusLabel.setText(chunks.get(0).toString());
			}

			@Override
			protected Object doInBackground() throws Exception {

				try {
					publish("Proszę czekać...");

					Thread.sleep(5000);
				} catch (InterruptedException ex) {
				}

				return null;
			}

			@Override
			protected void done() {

				// Aktualizujemy status zadania wykorzystując fakt, że metoda
				// done() wykonywana jest w ETD.
				statusLabel.setText("Gotowe");
				setButtonsEnabled(true);
			}
		};
		worker.execute(); // Uruchamiamy SwingWorker'a, aby nie blokować EDT.
	}

	private void badWayOneButtonActionPerformed(java.awt.event.ActionEvent evt) {

		statusLabel.setText("Proszę czekać...");
		setButtonsEnabled(false);

		// Wykonujemy to samo zadanie, tym razem jednak bez wykorzystania
		// dodatkowego wątku.
		try {
			Thread.sleep(5000); // Głodzimy wątek EDT
		} catch (InterruptedException ex) {
		}

		// Aktualizujemy status zadania.
		statusLabel.setText("Gotowe");
		setButtonsEnabled(true);
	}

	private void badWayTwoButtonActionPerformed(java.awt.event.ActionEvent evt) {

		statusLabel.setText("Proszę czekać... ");
		setButtonsEnabled(false);

		// Przykład niewłaściwego wykorzystania invokeLater().
		// Runnable nie powinien głodzić wątku EDT.
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {
					// Dispatch thread is starving!
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
				}

				// Aktualizujemy status zadania.
				statusLabel.setText("Gotowe");
				setButtonsEnabled(true);
			}
		});
	}

	// Pozwala włączać i wyłaczać przyciski.
	private void setButtonsEnabled(boolean b) {

		goodOldWayButton.setEnabled(b);
		goodNewWayButton.setEnabled(b);
		badWayOneButton.setEnabled(b);
		badWayTwoButton.setEnabled(b);
	}

	public static void main(String args[]) {

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				new InvokeExample().setVisible(true);
			}
		});
	}
}
