package dss.gui;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import dss.model.DataModel;

public class Gui {

	private MainWindow mainWindow;

	public Gui(DataModel model) {
		setLookAndFeel();
		mainWindow = new MainWindow(model);

	}

	public void start() {
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}

	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

	}

}
