package dds.gui.tabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import dds.model.DataModel;

public class ResultsTab extends JPanel {

	private static final long serialVersionUID = 2525104157488484823L;

	public ResultsTab(final DataModel model) {
		
		JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.generate();
			}
		});
		
		add(generateButton);
		
		
	}
}
