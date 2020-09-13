/*
CMSC350 
 23 Feb 2020
 Shaun Reid
 
 The Main class creates the GUI and receives all data from the GUI*/

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Main extends JFrame implements ActionListener{

	static String originalList = "";
	static String[] parsedArray = null;
	static Integer[] intArray = null;
	static Fraction[] fracArray = null;
	static Node root = null;
	static BST bstTree = new BST();
	
	//Create two labels
	static JLabel originalListLbl = new JLabel("Original List", JLabel.CENTER);
	static JLabel sortedListLbl = new JLabel("Sorted List", JLabel.CENTER);
	
	//Create two textfields
	static JTextField originalListTxt = new JTextField(25);
	static JTextField sortedListTxt = new JTextField(25);
	
	//Create button
	static JButton performSortBtn = new JButton("Perform Sort");
	
	//Create 4 radio buttons
	static JRadioButton ascendingRadioBtn = new JRadioButton("Ascending", false);
	static JRadioButton descendingRadioBtn = new JRadioButton("Descending", false);
	static JRadioButton integerRadioBtn = new JRadioButton("Integer", false);
	static JRadioButton fractionRadioBtn = new JRadioButton("Fraction", false);
	
	public static void main(String[] args) {
		
		buildGUI();
	}
	
	//Build GUI
	public static void buildGUI() {
		
		//Create original list panel
		JPanel originalListPanel = new JPanel(new FlowLayout());
		originalListPanel.add(originalListLbl);
		originalListPanel.add(originalListTxt);
		
		//Create sorted list panel
		JPanel sortedListPanel = new JPanel(new FlowLayout());
		sortedListPanel.add(sortedListLbl);
		sortedListPanel.add(sortedListTxt);
		
		//Create perform sort button panel
		JPanel performSortBtnPanel = new JPanel(new FlowLayout());
		performSortBtnPanel.add(performSortBtn);
				
		//Create Sort order panel has a border and a label
		JPanel sortOrderPanel = new JPanel(new GridLayout(2, 1));
		sortOrderPanel.setBorder(BorderFactory.createTitledBorder("Sort Order"));
		//sortOrderPanel.setSize(200, 50);
		sortOrderPanel.add(ascendingRadioBtn);
		sortOrderPanel.add(descendingRadioBtn);
		
		//Create Numeric type panel has a border and label
		JPanel numericTypePanel = new JPanel(new GridLayout(2, 1));
		//numericTypePanel.setSize(200, 50);
		numericTypePanel.setBorder(BorderFactory.createTitledBorder("Numeric Type"));
		numericTypePanel.add(integerRadioBtn);
		numericTypePanel.add(fractionRadioBtn);
		
		//Create operation panel. Contains 2 panels
		JPanel operationPanel = new JPanel(new GridLayout(1, 2));
		operationPanel.add(sortOrderPanel);
		operationPanel.add(numericTypePanel);
		
		//Create frame
		final int WIDTH = 450;
		final int HEIGHT = 300;
		
		JFrame genFrame = new JFrame();
		genFrame.setTitle("Binary Search Tree Sort");
		genFrame.setSize(WIDTH, HEIGHT);
		genFrame.setLocationRelativeTo(null);
		genFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		genFrame.setVisible(true);
		genFrame.setForeground(Color.lightGray);
		
		genFrame.setLayout(new GridLayout(4, 1));
		genFrame.add(originalListPanel);
		genFrame.add(sortedListPanel);
		genFrame.add(performSortBtnPanel);
		genFrame.add(operationPanel);
		
		performSortBtn.setSize(30, 10);
		
		performSortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				getOriginalList();
				
				Parser parseStr = new Parser();
				parsedArray = parseStr.parser(originalList);
				
				try {
				if(integerBtnClk() == true && ascendingBtnClk() == true) {
					intArray = convertToInt(parsedArray);
					for(int i = 0; i < intArray.length; i++) {
						root = bstTree.insert(root, intArray[i]);
					}
					bstTree.inorderWalk(root);
					sortedListTxt.setText(bstTree.returnedStr);
				} else if(integerBtnClk() == true && descendingBtnClk() == true) {
					intArray = convertToInt(parsedArray);
					for(int i = 0; i < intArray.length; i++) {
						root = bstTree.insert(root, intArray[i]);
					}
					bstTree.inorderWalk(root);
					
					sortedListTxt.setText(reverseOrder(bstTree.returnedStr));
				} else if(fractionBtnClk() == true && ascendingBtnClk() == true) {
					fracArray = convertToFrac(parsedArray);
					for(int i = 0; i < fracArray.length; i++) {
						root = bstTree.insert(root, fracArray[i]);
					}
					bstTree.inorderWalk(root);
					sortedListTxt.setText(bstTree.returnedStr);
				} else if(fractionBtnClk() == true && descendingBtnClk() == true) {
					fracArray = convertToFrac(parsedArray);
					for(int i = 0; i < fracArray.length; i++) {
						root = bstTree.insert(root, fracArray[i]);
					}
					bstTree.inorderWalk(root);
					sortedListTxt.setText(reverseOrder(bstTree.returnedStr));
				}
				} catch (NumberFormatException a) {
					JOptionPane nonNumericInput = new JOptionPane("Non numeric input ", JOptionPane.ERROR_MESSAGE);
					JDialog dialog = nonNumericInput.createDialog("Message");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				} catch (IllegalArgumentException b) {
					JOptionPane divideByZero = new JOptionPane("Divided By Zero ", JOptionPane.ERROR_MESSAGE);
					JDialog dialog = divideByZero.createDialog("Message");
					dialog.setAlwaysOnTop(true);
					dialog.setVisible(true);
				}
				
				
			}
		});
		
		//Groups sort order radio buttons
		ButtonGroup sortOrder = new ButtonGroup();
		sortOrder.add(ascendingRadioBtn);
		sortOrder.add(descendingRadioBtn);
		
		//Groups numeric type radio buttons
		ButtonGroup numericType = new ButtonGroup();
		numericType.add(integerRadioBtn);
		numericType.add(fractionRadioBtn);
		

	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	//Gets text from the original list textfield
	public static String getOriginalList() {
		originalList = originalListTxt.getText();
		return originalList;
	}
	
	//Returns the state of the ascending order radio button
	public static boolean ascendingBtnClk() {
		if(ascendingRadioBtn.isSelected() == true) {
			return true;
		} else {
			return false;
		}
	
	}
	
	//Returns the state of the descending order radio button
	public static boolean descendingBtnClk() {
		if(descendingRadioBtn.isSelected() == true) {
			return true;
		} else {
			return false;
		}
	}
	
	//Returns the state of the integer radio button
	public static boolean integerBtnClk() {
		if(integerRadioBtn.isSelected() == true) {
			return true;
		} else {
			return false;
		}
	}
	
	//Returns the state of the fraction radio button
	public static boolean fractionBtnClk() {
		if(fractionRadioBtn.isSelected() == true) {
			return true;
		} else {
			return false;
		}
	}
	
	//Converts a string array to an Integer array
	public static Integer[] convertToInt(String[] str) {
		Integer[] array = new Integer[str.length];
		for(int i = 0; i < str.length; i++) {
			
			array[i] = Integer.valueOf(str[i]);
		}
		return array;
	}
	
	public static Fraction[] convertToFrac(String[] str) {
		Fraction[] fracArray = new Fraction[str.length];
		//Fraction fracObj = new Fraction();
		for(int i = 0; i < str.length; i++) {
			
			fracArray[i] = new Fraction(str[i]);
			
		}
		return fracArray;
	}
	
	
	//Reverses the order of the string
	public static String reverseOrder(String str) {
		String[] tokens = null;
		StringBuilder buildString = new StringBuilder();
		String reversedStr = new String();
		
		tokens = str.split("\\s");
		for(int i = tokens.length-1; i >= 0; i--) {
			buildString.append(tokens[i] + " ");
		}
		
		reversedStr = buildString.toString();
		
		return reversedStr;
	}

}
