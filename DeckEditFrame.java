import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JComboBox;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;


@SuppressWarnings("serial")
public class DeckEditFrame extends JFrame
{
	Deck deck;
	
	private JPanel	contentPane;
	private JTextField fieldKanji;
	private JTextField fieldEnglish;
	private JTextField fieldReading;
	private JButton	btnCancel;
	private JButton	btnSave;
	private JButton	btnFinish;
	private JComboBox<String>	comboCardBox;
	private JLabel lblDeckName;
	private JTextField fieldDeckName;
	private JPanel panel_1;
	private JButton btnNew;
	private JButton btnDelete;
	private JButton btnEdit;

	/**
	 * Create the frame.
	 */
	public DeckEditFrame(Deck d)
	{
		deck = d;
		if (d.getName().length() == 0)
			setTitle("New Deck");
		else
			setTitle("Editing Deck: " + d.getName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 262);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblDeckName = new JLabel("Deck Name");
		GridBagConstraints gbc_lblDeckName = new GridBagConstraints();
		gbc_lblDeckName.anchor = GridBagConstraints.EAST;
		gbc_lblDeckName.insets = new Insets(0, 0, 5, 5);
		gbc_lblDeckName.gridx = 0;
		gbc_lblDeckName.gridy = 0;
		contentPane.add(lblDeckName, gbc_lblDeckName);
		
		fieldDeckName = new JTextField();
		fieldDeckName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				checkDeckName();
			}
		});
		GridBagConstraints gbc_fieldDeckName = new GridBagConstraints();
		gbc_fieldDeckName.gridwidth = 2;
		gbc_fieldDeckName.insets = new Insets(0, 0, 5, 0);
		gbc_fieldDeckName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDeckName.gridx = 1;
		gbc_fieldDeckName.gridy = 0;
		contentPane.add(fieldDeckName, gbc_fieldDeckName);
		fieldDeckName.setColumns(10);
		
		JLabel lblCards = new JLabel("Cards");
		GridBagConstraints gbc_lblCards = new GridBagConstraints();
		gbc_lblCards.insets = new Insets(0, 0, 5, 5);
		gbc_lblCards.anchor = GridBagConstraints.EAST;
		gbc_lblCards.gridx = 0;
		gbc_lblCards.gridy = 1;
		contentPane.add(lblCards, gbc_lblCards);
		
		comboCardBox = new JComboBox<String>();
		comboCardBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				changeCard();
			}
		});
		comboCardBox.setEnabled(false);
		GridBagConstraints gbc_comboCardBox = new GridBagConstraints();
		gbc_comboCardBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboCardBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboCardBox.gridx = 1;
		gbc_comboCardBox.gridy = 1;
		contentPane.add(comboCardBox, gbc_comboCardBox);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		
		btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedNew();
			}
		});
		panel_1.add(btnNew);
		
		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedDelete();
			}
		});
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedEdit();
			}
		});
		btnEdit.setEnabled(false);
		panel_1.add(btnEdit);
		panel_1.add(btnDelete);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 3;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		contentPane.add(separator, gbc_separator);
		
		JLabel lblKanji = new JLabel("Kanji");
		GridBagConstraints gbc_lblKanji = new GridBagConstraints();
		gbc_lblKanji.anchor = GridBagConstraints.EAST;
		gbc_lblKanji.insets = new Insets(0, 0, 5, 5);
		gbc_lblKanji.gridx = 0;
		gbc_lblKanji.gridy = 3;
		contentPane.add(lblKanji, gbc_lblKanji);
		
		fieldKanji = new JTextField();
		fieldKanji.setEnabled(false);
		GridBagConstraints gbc_fieldKanji = new GridBagConstraints();
		gbc_fieldKanji.gridwidth = 2;
		gbc_fieldKanji.insets = new Insets(0, 0, 5, 0);
		gbc_fieldKanji.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldKanji.gridx = 1;
		gbc_fieldKanji.gridy = 3;
		contentPane.add(fieldKanji, gbc_fieldKanji);
		fieldKanji.setColumns(10);
		
		JLabel lblEnglish = new JLabel("English");
		GridBagConstraints gbc_lblEnglish = new GridBagConstraints();
		gbc_lblEnglish.anchor = GridBagConstraints.EAST;
		gbc_lblEnglish.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnglish.gridx = 0;
		gbc_lblEnglish.gridy = 4;
		contentPane.add(lblEnglish, gbc_lblEnglish);
		
		fieldEnglish = new JTextField();
		fieldEnglish.setEnabled(false);
		GridBagConstraints gbc_fieldEnglish = new GridBagConstraints();
		gbc_fieldEnglish.gridwidth = 2;
		gbc_fieldEnglish.insets = new Insets(0, 0, 5, 0);
		gbc_fieldEnglish.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldEnglish.gridx = 1;
		gbc_fieldEnglish.gridy = 4;
		contentPane.add(fieldEnglish, gbc_fieldEnglish);
		fieldEnglish.setColumns(10);
		
		JLabel lblReading = new JLabel("Reading");
		GridBagConstraints gbc_lblReading = new GridBagConstraints();
		gbc_lblReading.anchor = GridBagConstraints.EAST;
		gbc_lblReading.insets = new Insets(0, 0, 5, 5);
		gbc_lblReading.gridx = 0;
		gbc_lblReading.gridy = 5;
		contentPane.add(lblReading, gbc_lblReading);
		
		fieldReading = new JTextField();
		fieldReading.setEnabled(false);
		GridBagConstraints gbc_fieldReading = new GridBagConstraints();
		gbc_fieldReading.insets = new Insets(0, 0, 5, 0);
		gbc_fieldReading.gridwidth = 2;
		gbc_fieldReading.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldReading.gridx = 1;
		gbc_fieldReading.gridy = 5;
		contentPane.add(fieldReading, gbc_fieldReading);
		fieldReading.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 7;
		contentPane.add(separator_1, gbc_separator_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 6;
		contentPane.add(panel, gbc_panel);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedCancel();
			}
		});
		btnCancel.setEnabled(false);
		panel.add(btnCancel);
		
		btnSave = new JButton("Save Card");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedSave();
			}
		});
		btnSave.setEnabled(false);
		panel.add(btnSave);
		
		btnFinish = new JButton("Finish Editing Deck");
		btnFinish.setEnabled(false);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					pressedFinish();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnFinish = new GridBagConstraints();
		gbc_btnFinish.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFinish.gridwidth = 3;
		gbc_btnFinish.gridx = 0;
		gbc_btnFinish.gridy = 8;
		contentPane.add(btnFinish, gbc_btnFinish);
		
		fieldDeckName.setText(deck.getName());
		checkDeckName();
		reload();
	}

	/**
	 * Called when edit button is pressed
	 */
	protected void pressedEdit()
	{
		editCard(true);
	}

	/**
	 * Called when deck name field must be verified
	 */
	private void checkDeckName()
	{
		boolean valid = fieldDeckName.getText().matches("\\w+");
		btnFinish.setEnabled(valid);
	}

	/**
	 * Called when delete button is pressed
	 */
	protected void pressedDelete()
	{
		int index = comboCardBox.getSelectedIndex();
		deck.remove(index);
		reload();
	}

	/**
	 * Called when new button is pressed
	 */
	protected void pressedNew()
	{
		comboCardBox.removeAllItems();
		changeCard();
		editCard(true);
	}

	/**
	 * Called when finish button is pressed
	 * @throws IOException 
	 */
	protected void pressedFinish() throws IOException
	{
		if (fieldDeckName.getText().length() > 0)
			deck.getFile().delete();
		deck.setName(fieldDeckName.getText());
		deck.save();
		MainFrame f = new MainFrame();
		f.setVisible(true);
		dispose();
	}

	/**
	 * Called when save button is pressed
	 */
	protected void pressedSave()
	{
		String kanji = fieldKanji.getText();
		String english = fieldEnglish.getText();
		String reading = fieldReading.getText();
		Card c = new Card(kanji, english, reading);
		int index = comboCardBox.getSelectedIndex();
		if (index == -1)
			deck.add(c);
		else
			deck.set(index, c);
		editCard(false);
		reload();
	}

	/**
	 * Called when cancel button is pressed
	 */
	protected void pressedCancel()
	{
		editCard(false);
		reload();
	}

	/**
	 * Called when selected card is changed
	 */
	protected void changeCard()
	{
		int index = comboCardBox.getSelectedIndex();
		if (index == -1)
		{
			fieldKanji.setText("");
			fieldEnglish.setText("");
			fieldReading.setText("");
		}
		else
		{
			fieldKanji.setText(deck.get(index).getKanji());
			fieldEnglish.setText(deck.get(index).getEnglish());
			fieldReading.setText(deck.get(index).getReading());
		}
	}

	/**
	 * Puts window components into edit-mode or not-edit-mode
	 */
	protected void editCard(boolean b)
	{
		fieldDeckName.setEnabled(!b);
		comboCardBox.setEnabled(!b);
		btnNew.setEnabled(!b);
		btnEdit.setEnabled(!b);
		btnDelete.setEnabled(!b);
		fieldKanji.setEnabled(b);
		fieldEnglish.setEnabled(b);
		fieldReading.setEnabled(b);
		btnCancel.setEnabled(b);
		btnSave.setEnabled(b);
		btnFinish.setEnabled(!b);
	}
	
	/**
	 * Reloads data from deck into components on screen
	 */
	protected void reload()
	{
		boolean notEmpty = deck.size() > 0;
		comboCardBox.setEnabled(notEmpty);
		btnDelete.setEnabled(notEmpty);
		btnEdit.setEnabled(notEmpty);
		populateCombo();
	}

	/**
	 * Populates the combobox with data from the deck
	 */
	private void populateCombo()
	{
		comboCardBox.removeAllItems();
		for (Card c : deck)
			comboCardBox.addItem(c.getEnglish());
	}
}
