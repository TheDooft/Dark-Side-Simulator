package dds.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 327992969446679713L;

	public MainWindow() {
		init();
	}

	private void init() {
		getContentPane().add(new JLabel("Hello world !"));
		setSize(800,600);
		setTitle("Dark Side Simulator");
	}
	
}
