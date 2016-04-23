package Converter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterUI extends JFrame{

	private JComboBox unit1;
	private JButton convertButton;
	private JButton clearButton;
	private UnitConverter unitconverter;
	private JComboBox unit2;

	private JTextField input = new JTextField(10);
	private JLabel equal = new JLabel(" = ");
	private JTextField result = new JTextField(10);

	private JRadioButton JRadio1 = new JRadioButton("Left->Right");
	private JRadioButton JRadio2 = new JRadioButton("Right->Left");

	public ConverterUI ( UnitConverter uc ) {
		this.unitconverter = uc ;
		this.setTitle ( " Length Converter " );
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		initComponents();
	}

	private void initComponents () {

		convertButton = new JButton("Convert!");
		clearButton = new JButton("Clear");

		unit1 = new JComboBox(Length.values());

		unit2 = new JComboBox(Length.values());

		Container contents = new Container () ;
		contents.setLayout(new FlowLayout());

		Container contents2 = new Container () ;
		contents2.setLayout(new FlowLayout());

		this.setLayout(new GridLayout(2,0));

		this.add(contents);
		this.add(contents2);

		contents.add(input);
		contents.add(unit1);
		contents.add(equal);
		contents.add(result);
		contents.add(unit2);
		contents.add(convertButton);
		contents.add(clearButton);	

		contents2.add(JRadio1);
		contents2.add(JRadio2);



		ActionListener radio1Listener = new RadioButtonListener();
		ActionListener radio2Listener = new RadioButtonListener2();
		JRadio1.addActionListener(radio1Listener);
		JRadio2.addActionListener(radio2Listener);

		ActionListener convertListener = new ConvertButtonListener();
		input.addActionListener( convertListener );
		convertButton.addActionListener( convertListener );

		ActionListener clearListener = new ClearButtonListener();
		clearButton.addActionListener( clearListener );

		JRadio1.setSelected(true);
		result.setEditable(false);

		input.getDocument().addDocumentListener(new inputListener());
		result.getDocument().addDocumentListener(new resultListener());

		pack();
	}

	public void run() {
		setVisible(true) ;
	}

	class inputListener implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			changed();
		}
		public void removeUpdate(DocumentEvent e) {
			changed();
		}
		public void insertUpdate(DocumentEvent e) {
			changed();
		}

		public void changed() {
			if(JRadio1.isSelected()){
				if(!input.getText().equals("")) {
					String inputString = input.getText();
					if( inputString.length() > 0 ) {
						try {
							double value = Double.valueOf( inputString );
							String text = "" + unitconverter.convert(value, (Length)unit1.getSelectedItem(), (Length)unit2.getSelectedItem());
							result.setText(text);

						} catch (NumberFormatException exception) {
							System.out.println("Input was not a number");
						}
					}
				}

				else { result.setText(""); }
			}
		}
	}

	class resultListener implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			changed();
		}
		public void removeUpdate(DocumentEvent e) {
			changed();
		}
		public void insertUpdate(DocumentEvent e) {
			changed();
		}

		public void changed() {
			if(JRadio2.isSelected()){
				if(!result.getText().equals("")) {
					String resultString = result.getText();
					if( resultString.length() > 0 ) {
						try {
							double value = Double.valueOf( resultString );
							String text = "" + unitconverter.convert(value, (Length)unit1.getSelectedItem(), (Length)unit2.getSelectedItem());
							input.setText(text);

						} catch (NumberFormatException exception) {
							System.out.println("Input was not a number");
						}
					}
				}

				else { input.setText(""); }
			}
		}
	}

	class RadioButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent evt ) {
			if(JRadio2.isSelected()){
				JRadio1.setSelected(true);
				JRadio2.setSelected(false);
				input.setEditable(true);
				result.setEditable(false);
			}
		}
	}

	class RadioButtonListener2 implements ActionListener {
		public void actionPerformed ( ActionEvent evt ) {
			if(JRadio1.isSelected()){
				JRadio2.setSelected(true);
				JRadio1.setSelected(false);
				input.setEditable(false);
				result.setEditable(true);
			}
		}
	}

	class ClearButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent evt ) {

			input.setText("");
			result.setText("");

		}
	}

	class ConvertButtonListener implements ActionListener {
		public void actionPerformed ( ActionEvent evt ) {
			String inputString = input.getText().trim();
			String resultString = result.getText().trim();

			if( JRadio1.isSelected() ) {
				if( inputString.length() > 0 ) {
					try {
						double value = Double.valueOf( inputString );
						String text = "" + unitconverter.convert(value, (Length)unit1.getSelectedItem(), (Length)unit2.getSelectedItem());
						result.setText(text);

					} catch (NumberFormatException exception) {
						System.out.println("Input was not a number");
					}
				}
			}

			else if ( JRadio2.isSelected() ) {
				if( resultString.length() > 0 ) {
					try {
						double value = Double.valueOf( resultString );
						String text = "" + unitconverter.convert(value, (Length)unit1.getSelectedItem(), (Length)unit2.getSelectedItem());
						input.setText(text);

					} catch (NumberFormatException exception) {
						System.out.println("Input was not a number");
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		UnitConverter uc = new UnitConverter();
		ConverterUI start = new ConverterUI ( uc );
		start.run();

	}
}




