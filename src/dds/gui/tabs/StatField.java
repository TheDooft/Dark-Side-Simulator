package dds.gui.tabs;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dds.model.Stat;

public class StatField extends JPanel {

	private static final long serialVersionUID = -4455285130313141604L;
	private final Stat stat;
	private JTextField statField;

	public StatField(final Stat stat) {
		this.stat = stat;
		add(new JLabel(stat.getName() + ":"));
		statField = new JTextField(Integer.toString(stat.getValue()));
		statField.setColumns(5);
		statField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				int statValue = Integer.parseInt(statField.getText());
				StatField.this.stat.setValue(statValue);
			}
		});
		
		add(statField);
	}

}
