import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public final class MyPanel extends JPanel {
	private final Box box = Box.createVerticalBox();
	private final Component glue = Box.createVerticalGlue();

	public MyPanel() {
		super(new BorderLayout());
		box.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
		JScrollPane scroll = new JScrollPane(box);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getVerticalScrollBar().setUnitIncrement(25);
		add(makeToolBar(), BorderLayout.NORTH);
		add(scroll);
		addComp(new JLabel("aaaaaaaaaaaaaaaaaaaaaa"));
		addComp(MakeComponentUtil.makeButton());
		addComp(MakeComponentUtil.makeCheckBox());
		addComp(MakeComponentUtil.makeLabel());
		setPreferredSize(new Dimension(320, 240));
	}

	private void addComp(final JComponent comp) {

		comp.setMaximumSize(new Dimension(Short.MAX_VALUE, comp.getPreferredSize().height));
		box.remove(glue);
		box.add(Box.createVerticalStrut(5));
		box.add(comp);
		box.add(glue);
		box.revalidate();
		EventQueue.invokeLater(() -> comp.scrollRectToVisible(comp.getBounds()));
	}

	private JToolBar makeToolBar() {

		JToolBar bar = new JToolBar();
		bar.add(new AbstractAction("add JLabel") {
			@Override
			public void actionPerformed(ActionEvent e) {

				addComp(MakeComponentUtil.makeLabel());
			}
		});
		bar.addSeparator();
		bar.add(new AbstractAction("add JButton") {
			@Override
			public void actionPerformed(ActionEvent e) {

				addComp(MakeComponentUtil.makeButton());
			}
		});
		bar.addSeparator();
		bar.add(new AbstractAction("add JCheckBox") {
			@Override
			public void actionPerformed(ActionEvent e) {

				addComp(MakeComponentUtil.makeCheckBox());
			}
		});
		return bar;
	}

	public static void main(String... args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		JFrame frame = new JFrame("@title@");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MyPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

final class MakeComponentUtil {
	private MakeComponentUtil() {
		/* Singleton */ }

	public static JComponent makeLabel() {

		JLabel label = new JLabel("Height: 50") {
			@Override
			public Dimension getPreferredSize() {

				return new Dimension(0, 50);
			}
		};
		label.setOpaque(true);
		label.setBackground(Color.YELLOW.brighter());
		return label;
	}

	public static JComponent makeButton() {

		return new JButton(new AbstractAction("Beep Test") {
			@Override
			public void actionPerformed(ActionEvent e) {

				Toolkit.getDefaultToolkit().beep();
			}
		});
	}

	public static JComponent makeCheckBox() {

		return new JCheckBox("bbbbbbbbbbbbbbbbbbbb", true);
	}
}
