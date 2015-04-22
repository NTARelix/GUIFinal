import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

/**
 * QuizOptions allows various quiz options to be selected. Options include
 * shuffling the cards before quizzing and choosing the order of card data.
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class QuizOptions extends JFrame
{

	private JPanel	contentPane;
	private JCheckBox	chckbxShuffle;
	private JButton	btnUp;
	private JButton	btnDown;
	private JList<String>	list;
	private Deck deck;

	/**
	 * Create the frame.
	 * @param quizDeck 
	 */
	public QuizOptions(Deck quizDeck)
	{
		deck = quizDeck;
		
		setTitle("Quiz Options");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 219, 197);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		chckbxShuffle = new JCheckBox("Shuffle");
		GridBagConstraints gbc_chckbxShuffle = new GridBagConstraints();
		gbc_chckbxShuffle.gridwidth = 2;
		gbc_chckbxShuffle.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxShuffle.gridx = 0;
		gbc_chckbxShuffle.gridy = 0;
		contentPane.add(chckbxShuffle, gbc_chckbxShuffle);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JLabel lblOrder = new JLabel("Order");
		GridBagConstraints gbc_lblOrder = new GridBagConstraints();
		gbc_lblOrder.gridwidth = 2;
		gbc_lblOrder.insets = new Insets(0, 0, 5, 0);
		gbc_lblOrder.gridx = 0;
		gbc_lblOrder.gridy = 2;
		contentPane.add(lblOrder, gbc_lblOrder);
		
		list = new JList<String>();
		list.setVisibleRowCount(3);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())
					selectedLanguage();
			}
		});
		list.setModel(new AbstractListModel<String>() {
			String[] values = new String[] {"Kanji", "English", "Reading"};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 2;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 3;
		contentPane.add(list, gbc_list);
		
		btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedUp();
			}
		});
		btnUp.setEnabled(false);
		GridBagConstraints gbc_btnUp = new GridBagConstraints();
		gbc_btnUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUp.insets = new Insets(0, 0, 5, 0);
		gbc_btnUp.gridx = 1;
		gbc_btnUp.gridy = 3;
		contentPane.add(btnUp, gbc_btnUp);
		
		btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedDown();
			}
		});
		btnDown.setEnabled(false);
		GridBagConstraints gbc_btnDown = new GridBagConstraints();
		gbc_btnDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDown.insets = new Insets(0, 0, 5, 0);
		gbc_btnDown.gridx = 1;
		gbc_btnDown.gridy = 4;
		contentPane.add(btnDown, gbc_btnDown);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridwidth = 2;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		contentPane.add(panel, gbc_panel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedCancel();
			}
		});
		btnCancel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnCancel);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedStart();
			}
		});
		btnStart.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnStart);
	}

	/**
	 * Called of the card data has been selected
	 */
	protected void selectedLanguage()
	{
		int index = list.getMinSelectionIndex();
		if (index == 0)
		{
			btnUp.setEnabled(false);
			btnDown.setEnabled(true);
		}
		else if (index == 2)
		{
			btnUp.setEnabled(true);
			btnDown.setEnabled(false);
		}
		else if (index == 1)
		{
			btnUp.setEnabled(true);
			btnDown.setEnabled(true);
		}
		else
		{
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
		}
	}

	/**
	 * Called when the down button has been pressed
	 */
	protected void pressedDown()
	{
		list.setModel(new AbstractListModel<String>()
		{
			String[] values = getDownValues();
			@Override
			public String getElementAt(int i)
			{
				return values[i];
			}

			@Override
			public int getSize()
			{
				return values.length;
			}
		});
		selectedLanguage();
	}
	
	/**
	 * @return modified version of the card data list after pressing down
	 */
	protected String[] getDownValues()
	{
		int index = list.getMinSelectionIndex();
		ListModel<String> model = list.getModel();
		String[] values = {"","",""};
		if (index == 0)
		{
			values[0] = model.getElementAt(1);
			values[1] = model.getElementAt(0);
			values[2] = model.getElementAt(2);
		}
		else if (index == 1)
		{
			values[0] = model.getElementAt(0);
			values[1] = model.getElementAt(2);
			values[2] = model.getElementAt(1);
		}
		return values;
	}

	/**
	 * Called when the up button has been pressed
	 */
	protected void pressedUp()
	{
		list.setModel(new AbstractListModel<String>()
		{
			String[] values = getUpValues();
			@Override
			public String getElementAt(int i)
			{
				return values[i];
			}

			@Override
			public int getSize()
			{
				return values.length;
			}
		});
		selectedLanguage();
	}
	
	/**
	 * @return modified version of the card data list after pressing up
	 */
	protected String[] getUpValues()
	{
		int index = list.getMinSelectionIndex();
		ListModel<String> model = list.getModel();
		String[] values = {"A","B","C"};
		if (index == 1)
		{
			values[0] = model.getElementAt(1);
			values[1] = model.getElementAt(0);
			values[2] = model.getElementAt(2);
		}
		else if (index == 2)
		{
			values[0] = model.getElementAt(0);
			values[1] = model.getElementAt(2);
			values[2] = model.getElementAt(1);
		}
		return values;
	}

	/**
	 * Called when the start button has been pressed
	 */
	protected void pressedStart()
	{
		boolean shuffle = chckbxShuffle.isSelected();
		QuizAnswerType q = convertStringToQAT(list.getModel().getElementAt(0));
		QuizAnswerType first = convertStringToQAT(list.getModel().getElementAt(1));
		QuizAnswerType second = convertStringToQAT(list.getModel().getElementAt(2));
		Quiz qz = new Quiz(q, first, second, deck);
		if (shuffle)
			qz.shuffle();
		QuizFrame qzf = new QuizFrame(qz);
		qzf.setVisible(true);
		dispose();
	}
	
	/**
	 * Converts a string to a Quiz answer type
	 * @param s string to convert
	 * @return corresponding quiz answer type
	 */
	protected static QuizAnswerType convertStringToQAT(String s)
	{
		if (s.equals("Kanji"))
			return QuizAnswerType.KANJI;
		else if (s.equals("English"))
			return QuizAnswerType.ENGLISH;
		else
			return QuizAnswerType.READING;
	}

	/**
	 * Called when the cancel button has been pressed
	 */
	protected void pressedCancel()
	{
		MainFrame f = new MainFrame();
		f.setVisible(true);
		dispose();
	}

}
