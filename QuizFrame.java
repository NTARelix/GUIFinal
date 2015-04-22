import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JSeparator;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.Color;

/**
 * QuizFrame displays a single card's data on screen while concealing some
 * data and allowing the rest to be revealed. Cycles through all cards in a
 * single quiz.
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class QuizFrame extends JFrame
{
	private Quiz quiz;
	
	private JPanel	contentPane;
	private JPanel	panelButtons;
	private JButton	btnWrong;
	private JButton	btnRight;
	private JLabel	lblAnswer2Type;
	private JLabel	lblAnswer2;
	private JButton	btnShowAnswer2;
	private JLabel	lblAnswer1Type;
	private JLabel	lblAnswer1;
	private JButton	btnShowAnswer1;
	private JLabel	lblQuestion;

	/**
	 * Create the frame.
	 */
	public QuizFrame(Quiz quiz)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblQuestion = new JLabel("<Question>");
		lblQuestion.setFont(new Font("SimSun", Font.PLAIN, 34));
		GridBagConstraints gbc_lblQuestion = new GridBagConstraints();
		gbc_lblQuestion.gridwidth = 2;
		gbc_lblQuestion.insets = new Insets(0, 0, 5, 0);
		gbc_lblQuestion.gridx = 0;
		gbc_lblQuestion.gridy = 0;
		contentPane.add(lblQuestion, gbc_lblQuestion);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		JPanel panelShowAnswer1 = new JPanel();
		GridBagConstraints gbc_panelShowAnswer1 = new GridBagConstraints();
		gbc_panelShowAnswer1.insets = new Insets(0, 0, 5, 5);
		gbc_panelShowAnswer1.fill = GridBagConstraints.BOTH;
		gbc_panelShowAnswer1.gridx = 0;
		gbc_panelShowAnswer1.gridy = 2;
		contentPane.add(panelShowAnswer1, gbc_panelShowAnswer1);
		GridBagLayout gbl_panelShowAnswer1 = new GridBagLayout();
		gbl_panelShowAnswer1.columnWidths = new int[]{0, 0};
		gbl_panelShowAnswer1.rowHeights = new int[]{0, 0, 0};
		gbl_panelShowAnswer1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelShowAnswer1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelShowAnswer1.setLayout(gbl_panelShowAnswer1);
		
		btnShowAnswer1 = new JButton("Show");
		btnShowAnswer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toggleState();
			}
		});
		GridBagConstraints gbc_btnShowAnswer1 = new GridBagConstraints();
		gbc_btnShowAnswer1.insets = new Insets(0, 0, 5, 0);
		gbc_btnShowAnswer1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnShowAnswer1.gridx = 0;
		gbc_btnShowAnswer1.gridy = 0;
		panelShowAnswer1.add(btnShowAnswer1, gbc_btnShowAnswer1);
		
		lblAnswer1Type = new JLabel("<Ans_Type_1>");
		GridBagConstraints gbc_lblAnswer1Type = new GridBagConstraints();
		gbc_lblAnswer1Type.gridx = 0;
		gbc_lblAnswer1Type.gridy = 1;
		panelShowAnswer1.add(lblAnswer1Type, gbc_lblAnswer1Type);
		
		lblAnswer1 = new JLabel("?");
		lblAnswer1.setFont(new Font("SimSun", Font.PLAIN, 34));
		GridBagConstraints gbc_lblAnswer1 = new GridBagConstraints();
		gbc_lblAnswer1.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnswer1.gridx = 1;
		gbc_lblAnswer1.gridy = 2;
		contentPane.add(lblAnswer1, gbc_lblAnswer1);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_1.gridwidth = 2;
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 3;
		contentPane.add(separator_1, gbc_separator_1);
		
		JPanel panelShowAnswer2 = new JPanel();
		GridBagConstraints gbc_panelShowAnswer2 = new GridBagConstraints();
		gbc_panelShowAnswer2.insets = new Insets(0, 0, 5, 5);
		gbc_panelShowAnswer2.fill = GridBagConstraints.BOTH;
		gbc_panelShowAnswer2.gridx = 0;
		gbc_panelShowAnswer2.gridy = 4;
		contentPane.add(panelShowAnswer2, gbc_panelShowAnswer2);
		GridBagLayout gbl_panelShowAnswer2 = new GridBagLayout();
		gbl_panelShowAnswer2.columnWidths = new int[]{0, 0};
		gbl_panelShowAnswer2.rowHeights = new int[]{0, 0, 0};
		gbl_panelShowAnswer2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelShowAnswer2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelShowAnswer2.setLayout(gbl_panelShowAnswer2);
		
		btnShowAnswer2 = new JButton("Show");
		btnShowAnswer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleState();
			}
		});
		btnShowAnswer2.setEnabled(false);
		GridBagConstraints gbc_btnShowAnswer2 = new GridBagConstraints();
		gbc_btnShowAnswer2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnShowAnswer2.insets = new Insets(0, 0, 5, 0);
		gbc_btnShowAnswer2.gridx = 0;
		gbc_btnShowAnswer2.gridy = 0;
		panelShowAnswer2.add(btnShowAnswer2, gbc_btnShowAnswer2);
		
		lblAnswer2Type = new JLabel("<Ans_Type_2>");
		GridBagConstraints gbc_lblAnswer2Type = new GridBagConstraints();
		gbc_lblAnswer2Type.gridx = 0;
		gbc_lblAnswer2Type.gridy = 1;
		panelShowAnswer2.add(lblAnswer2Type, gbc_lblAnswer2Type);
		
		lblAnswer2 = new JLabel("?");
		lblAnswer2.setFont(new Font("SimSun", Font.PLAIN, 34));
		GridBagConstraints gbc_lblAnswer2 = new GridBagConstraints();
		gbc_lblAnswer2.insets = new Insets(0, 0, 5, 0);
		gbc_lblAnswer2.gridx = 1;
		gbc_lblAnswer2.gridy = 4;
		contentPane.add(lblAnswer2, gbc_lblAnswer2);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 5;
		contentPane.add(separator_2, gbc_separator_2);
		
		panelButtons = new JPanel();
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.gridx = 1;
		gbc_panelButtons.gridy = 6;
		contentPane.add(panelButtons, gbc_panelButtons);
		
		btnWrong = new JButton("Wrong");
		btnWrong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedWrong();
			}
		});
		btnWrong.setEnabled(false);
		panelButtons.add(btnWrong);
		
		btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedRight();
			}
		});
		btnRight.setEnabled(false);
		panelButtons.add(btnRight);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnWrong, btnRight, btnShowAnswer1, btnShowAnswer2}));
		
		this.quiz = quiz;

		lblAnswer1Type.setText(quiz.getAnwerType1String());
		lblAnswer2Type.setText(quiz.getAnwerType2String());
		loadNextCard();
	}

	/**
	 * Called when the right button is pressed
	 */
	protected void pressedRight()
	{
		quiz.guessRight();
		toggleState();
	}

	/**
	 * Called when the wrong button is pressed
	 */
	protected void pressedWrong()
	{
		quiz.guessWrong();
		toggleState();
	}

	/**
	 * Changes the enabled state of the right and wrong buttons
	 * @param b true for enabled, false for disabled
	 */
	public void setRightWrongEnabled(boolean b)
	{
		btnRight.setEnabled(b);
		btnWrong.setEnabled(b);
	}
	
	/**
	 * Toggles the current card's state in the quiz:
	 *    Initial: Question visible
	 *    Second: First answer revealed
	 *    Third: Second answer revealed and answer buttons enabled
	 *    Fourth: Next card and back to state one, or to results if out of
	 *            cards 
	 */
	public void toggleState()
	{
		if (btnShowAnswer1.isEnabled())   
		{
			btnShowAnswer1.setEnabled(false);
			btnShowAnswer2.setEnabled(true);
			
			lblAnswer1.setText(quiz.getAnswer1(0));
		}
		else if (btnShowAnswer2.isEnabled())
		{
			btnShowAnswer2.setEnabled(false);
			setRightWrongEnabled(true);
			
			lblAnswer2.setText(quiz.getAnswer2(0));
		}
		else if (btnRight.isEnabled() && btnWrong.isEnabled())
		{
			loadNextCard();
		}
	}

	/**
	 * Puts next card's data into fields
	 */
	private void loadNextCard()
	{
		int ans = quiz.getRight() + quiz.getWrong();
		int tot = quiz.getTotal();
		this.setTitle(String.format("Quiz Question %d of %d", ans, tot));
		if (quiz.size() > 0)
		{
			btnShowAnswer1.setEnabled(true);
			btnShowAnswer2.setEnabled(false);
			setRightWrongEnabled(false);
			
			lblQuestion.setText(quiz.getQuestion(0));
			lblAnswer1.setText("?");
			lblAnswer2.setText("?");
		}
		else
		{
			QuizResultsFrame results = new QuizResultsFrame(quiz);
			results.setVisible(true);
			dispose();
		}
	}
}
