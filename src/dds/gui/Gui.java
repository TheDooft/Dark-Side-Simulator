package dds.gui;

public class Gui {

	private MainWindow mainWindow;

	public Gui() {
		mainWindow = new MainWindow();
	}
	
	public void start() {
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
		
	}
	
}
