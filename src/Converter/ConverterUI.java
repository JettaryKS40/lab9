package Converter;

/** This class is a GUI that give the user to use the converter
 *  to convert unit, which can use freely and have a standard unit
 *  to convert init.
 *  
 *  @author Keetawat Srichompoo - 5810545840
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterUI extends JFrame{
	
	//Main class: Start the GUI and run the program.
	
	public static void main(String[] args) {

		UnitConverter uc = new UnitConverter();
		ConverterPanel start = new ConverterPanel ( uc );
		start.run();

	}

}

class ConverterPanel extends JFrame {
	// Add a component variables such as button or text to the GUI program
	
		private UnitConverter unitconverter ;
		private JButton convertButton ;
		private JButton clearButton ;
		private JComboBox unit1 ;
		private JComboBox unit2 ;
		private JLabel equal = new JLabel ( " = " ) ;
		private JTextField input = new JTextField ( 10 ) ;	
		private JTextField result = new JTextField ( 10 ) ;
		private JRadioButton JRadio1 = new JRadioButton ( "Left->Right" ) ;
		private JRadioButton JRadio2 = new JRadioButton ( "Right->Left" ) ;

		public ConverterPanel ( UnitConverter uc ) {
					
			/**  Constructor set the title of the GUI and set the program to run and exit properly.
			 *  and run initComponent to run all the components in this programs.
			 *  
			 *  Set the JRadioButton and set the textfield to the default mode.
			 *  [Default mode: Convert Left to Right and Set the result textfield to uneditable] 
			 */
			
			this.unitconverter = uc ;
			this.setTitle ( " Length Converter " ) ;
			this.setDefaultCloseOperation ( EXIT_ON_CLOSE ) ;

			JRadio1.setSelected ( true ) ;
			result.setEditable ( false ) ;

			initComponents() ;
		}

		private void initComponents () {

			/*
			 * edit the componenets and set variable to use
			 */
			
			convertButton = new JButton ( "Convert!" ) ;
			clearButton = new JButton ( "Clear" ) ;

			unit1 = new JComboBox ( Length.values() ) ;
			unit2 = new JComboBox ( Length.values() ) ;

			/*
			 *  Add a Container to use for a programs and set the layouts 
			 */
			
			Container contents = new Container () ;
			contents.setLayout(new FlowLayout());

			Container contents2 = new Container () ;
			contents2.setLayout(new FlowLayout());

			this.setLayout(new GridLayout(2,0));

			this.add(contents);
			this.add(contents2);

			/*
			 *  add all the components to the Containers. 
			 */
			
			contents.add(input);
			contents.add(unit1);
			contents.add(equal);
			contents.add(result);
			contents.add(unit2);
			contents.add(convertButton);
			contents.add(clearButton);
			contents2.add(JRadio1);
			contents2.add(JRadio2);


			/** Make all the button, radio button and textfield use a function of itself. 
			 *  by add a actionListioner.
			 */
			
			ActionListener radio1Listener = new RadioButtonListener();
			ActionListener radio2Listener = new RadioButtonListener2();
			JRadio1.addActionListener(radio1Listener);
			JRadio2.addActionListener(radio2Listener);

			ActionListener convertListener = new ConvertButtonListener();
			input.addActionListener( convertListener );
			convertButton.addActionListener( convertListener );

			ActionListener clearListener = new ClearButtonListener();
			clearButton.addActionListener( clearListener );
			
			/*
			 * Make all the textfield auto-detected the user input.
			 */
			
			input.getDocument().addDocumentListener(new inputListener());
			result.getDocument().addDocumentListener(new resultListener());

			/*
			 * Set the program size into a properly view.
			 */
			
			this.pack();
		}

		public void run() {
			
			//Set the program to be visible to user.
			setVisible(true) ;
			
		}

		class inputListener implements DocumentListener {
			
			/** Default param.
			 * Check when the user change, remove or insert the text the JTextfield. 
			 * this used by a JTextField input.
			 */
			
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
				
				/**  Check if the Radio button is check or not
				 *  if is selected then make it to convert the units right away
				 *  by checking the length of the String then get the Units then starting convert
				 *  and return the text String and set it to the result TextField. 
				 *   
				 *  if the user didn't input as a number then catch the error exception
				 */
				
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

					// if the user remove the text then the result TextField should be remove too.
					else { result.setText(""); }
				}
			}
		}

		/** This give the JTextField to check that after the user input the text inside the JTextField
		 *  then it should check the method right away. this class gives the JTextField to check the text 
		 *  inside of it.
		 */
		
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
				
				/**
				 *  Check the JRadioButton so that it should not be select both of it.
				 *  as a default this JRadio1 (Left->Right) will be selected and the JRadio2 (Right->Left) be unselected.
				 *  if the user uncheck the JRadio1 then the JRadio2 will be selected.  
				 *  this change the JTextField too [ JTextField 'Result' will be uneditable]
				 *  if the JRadio2 is selected then the JTextField 'input' will be uneditable. 
				 */
				
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
				
				//when execute, set all the components to the Default mode
				//[Set all JTextField and Radio button the Default]
				
				JRadio1.setSelected(true);
				JRadio2.setSelected(false);
				 
				input.setText("");
				result.setText("");

			}
		}

		/** This give the JRadio button to check by user if the user want to convert from
		 *  Left to Right or Right to Left inside.
		 */
		
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
}