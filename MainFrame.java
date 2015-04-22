import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;

import java.awt.Insets;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * MainFrame is the main window that shows existing decks, allows creation of
 * new decks, allows deletion of existing decks, or allows quizzing of an
 * existing deck.
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private JPanel	contentPane;
	private List<Deck> decks;
	private JComboBox<String>	comboBox;
	private JButton	btnNew;
	private JButton	btnEdit;
	private JButton	btnDelete;
	private JButton	btnStartQuiz;
	private Border finishBorder;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setTitle("Japanese Flashcards");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDecks = new JLabel("Decks");
		lblDecks.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblDecks = new GridBagConstraints();
		gbc_lblDecks.insets = new Insets(0, 0, 5, 0);
		gbc_lblDecks.ipadx = 10;
		gbc_lblDecks.gridx = 0;
		gbc_lblDecks.gridy = 0;
		contentPane.add(lblDecks, gbc_lblDecks);
		
		comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedNew();
			}
		});
		panel.add(btnNew);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedEdit();
			}
		});
		btnEdit.setEnabled(false);
		panel.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedDelete();
			}
		});
		btnDelete.setEnabled(false);
		panel.add(btnDelete);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 3;
		contentPane.add(separator, gbc_separator);
		
		btnStartQuiz = new JButton("Start Quiz");
		btnStartQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedStartQuiz();
			}
		});
		btnStartQuiz.setEnabled(false);
		GridBagConstraints gbc_btnStartQuiz = new GridBagConstraints();
		gbc_btnStartQuiz.gridx = 0;
		gbc_btnStartQuiz.gridy = 4;
		contentPane.add(btnStartQuiz, gbc_btnStartQuiz);
		
		
		reload();
	}
	
	/**
	 * Called when delete button is pressed
	 */
	protected void pressedDelete()
	{
		int index = comboBox.getSelectedIndex();
		Deck d = decks.get(index);
		d.getFile().delete();
		reload();
	}

	/**
	 * Called when edit button is pressed
	 */
	protected void pressedEdit()
	{
		int index = comboBox.getSelectedIndex();
		Deck d = decks.get(index);
		DeckEditFrame def = new DeckEditFrame(d);
		def.setVisible(true);
		dispose();
	}

	/**
	 * Called when new button is pressed
	 */
	protected void pressedNew()
	{
		Deck d = new Deck("");
		DeckEditFrame def = new DeckEditFrame(d);
		def.setVisible(true);
		dispose();
	}

	/**
	 * Updates the data in the window components to match the data in the deck
	 * list
	 */
	private void reload()
	{
		comboBox.removeAllItems();
		decks = Deck.loadDecks();
		if (decks.size() > 0)
		{
			itemsSetEnabled(true);
			populateComboBox();
		}
		else
		{
			itemsSetEnabled(false);
		}	
	}

	/**
	 * Called when start quiz button is pressed
	 */
	protected void pressedStartQuiz()
	{
		int index = comboBox.getSelectedIndex();
		Deck quizDeck = (Deck) decks.get(index);
		QuizOptions op = new QuizOptions(quizDeck);
		op.setVisible(true);
		dispose();
	}

	/**
	 * Enables or disables components
	 * @param b enable if true, disable if false
	 */
	private void itemsSetEnabled(boolean b)
	{
		comboBox.setEnabled(b);
		btnDelete.setEnabled(b);
		btnEdit.setEnabled(b);
		btnStartQuiz.setEnabled(b);
	}

	/**
	 * Populates the combobox with deck names
	 */
	private void populateComboBox()
	{
		for (Deck d : decks)
			comboBox.addItem(d.getName());
	}
}
