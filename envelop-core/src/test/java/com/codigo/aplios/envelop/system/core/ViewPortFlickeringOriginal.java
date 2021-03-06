package com.codigo.aplios.envelop.system.core;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.RepaintManager;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableModel;

public class ViewPortFlickeringOriginal {
	
	interface sds
	{
		
	}

	private JFrame frame = new JFrame("Table");
	private JViewport viewport = new JViewport();
	private Rectangle RECT = new Rectangle();
	private Rectangle RECT1 = new Rectangle();
	private JTable table = new JTable(1000000, 3);
	private javax.swing.Timer timer;
	private int count = 0;
	private GradientViewPortOriginal tableViewPort;
	private static boolean loggerOpacity;
	private JPanel panel = new JPanel();
	private static JButton button;

	public ViewPortFlickeringOriginal() {
		table.setRowHeight(20);
		tableViewPort = new GradientViewPortOriginal(table);
		viewport = tableViewPort.getViewport();
		viewport.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				if (tableViewPort.bolStart) {
					RECT = table.getCellRect(0, 0, true);
					RECT1 = table.getCellRect(table.getRowCount() - 1, 0, true);
					Rectangle viewRect = viewport.getViewRect();
					if (viewRect.intersects(RECT)) {
						tableViewPort.paintBackGround(new Color(250, 150, 150));
					}
					else
						if (viewRect.intersects(RECT1)) {
							tableViewPort.paintBackGround(new Color(150, 250, 150));
						}
						else {

							tableViewPort.paintBackGround(new Color(150, 150, 250));
						}
				}
			}
		});
		frame.add(tableViewPort);
		button = new JButton("Change Opacity for Java6 / Win7");
		button.setBounds(100, 100, 50, 50);
		button.setVisible(true);
		panel.add(button);
		loggerOpacity = true;
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				count = 0;
				timer.restart();
				table.removeAll();
				// Object src = evt.getSource();
				// if (src == button && loggerOpacity) {
				// AWTUtilities.setWindowOpacity(frame, 0.80f);
				// }
			}
		});
		frame.add(panel, BorderLayout.SOUTH);
		frame.setPreferredSize(new Dimension(600, 300));
		frame.pack();
		frame.setLocation(50, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RepaintManager.setCurrentManager(new RepaintManager() {

			@Override
			public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {

				Container con = c.getParent();
				while (con instanceof JComponent) {
					if (!con.isVisible()) {
						return;
					}
					if (con instanceof GradientViewPortOriginal) {
						c = (JComponent) con;
						x = 0;
						y = 0;
						w = con.getWidth();
						h = con.getHeight();
					}
					con = con.getParent();
				}
				super.addDirtyRegion(c, x, y, w, h);
			}
		});
		frame.setVisible(true);
		start();
	}

	private void start() {

		timer = new javax.swing.Timer(100, updateCol());
		timer.start();
	}

	public Action updateCol() {

		return new AbstractAction("text load action") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				TableModel model = table.getModel();
				int cols = model.getColumnCount();
				int row = 0;
				for (int j = 0; j < cols; j++) {
					row = count;
					table.changeSelection(row, 0, false, false);
					timer.setDelay(10);
					Object value = "row " + (count + 1) + " item " + (j + 1);
					model.setValueAt(value, count, j);
				}
				count++;
				if (count >= table.getRowCount()) {
					timer.stop();
					table.changeSelection(0, 0, false, false);
					java.awt.EventQueue.invokeLater(new Runnable() {

						@Override
						public void run() {

							table.clearSelection();
							tableViewPort.bolStart = true;
						}
					});
				}
			}
		};
	}

	public static void main(String[] args) {

		try {
			// Significantly improves the look of the output in
			// terms of the file names returned by FileSystemView!
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception weTried) {
		}

		java.awt.EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				ViewPortFlickeringOriginal viewPortFlickering = new ViewPortFlickeringOriginal();
			}
		});
	}
}

class GradientViewPortOriginal extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private final int h = 50;
	private BufferedImage img = null;
	private BufferedImage shadow = new BufferedImage(1, h, BufferedImage.TYPE_INT_ARGB);
	private JViewport viewPort;
	public boolean bolStart = false;

	// -----------------------------------------------------------------------------------------------------------------

	public GradientViewPortOriginal(JComponent com) {
		super(com);
		viewPort = this.getViewport();
		viewPort.setScrollMode(JViewport.BLIT_SCROLL_MODE);
		viewPort.setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
		viewPort.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		paintBackGround(new Color(250, 150, 150));
	}

	// -----------------------------------------------------------------------------------------------------------------

	public void paintBackGround(Color g) {

		Graphics2D g2 = shadow.createGraphics();
		g2.setPaint(g);
		g2.fillRect(0, 0, 1, h);
		g2.setComposite(AlphaComposite.DstIn);
		g2.setPaint(new GradientPaint(0, 0, new Color(0, 0, 0, 0f), 0, h, new Color(0.1f, 0.8f, 0.8f, 0.5f)));
		g2.fillRect(0, 0, 1, h);
		g2.dispose();
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void paint(Graphics g) {

		if (img == null || img.getWidth() != getWidth() || img.getHeight() != getHeight()) {
			img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		}
		Graphics2D g2 = img.createGraphics();
		super.paint(g2);
		Rectangle bounds = getViewport().getVisibleRect();
		g2.scale(bounds.getWidth(), -1);
		int y = (getColumnHeader() == null) ? 0 : getColumnHeader().getHeight();
		g2.drawImage(shadow, bounds.x, -bounds.y - y - h, null);
		g2.scale(1, -1);
		g2.drawImage(shadow, bounds.x, bounds.y + bounds.height - h + y, null);
		g2.dispose();
		g.drawImage(img, 0, 0, null);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
