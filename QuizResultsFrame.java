

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * QuizResultsFrame shows the results of a quiz: right, wrong, and percent.
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class QuizResultsFrame extends JFrame
{

	private JPanel	contentPane;
	private Quiz finishedQuiz;

	/**
	 * Create the frame.
	 */
	public QuizResultsFrame(Quiz finishedQuiz)
	{
		this.finishedQuiz = finishedQuiz;
		
		setTitle("Quiz Results");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 174, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblRight = new JLabel("Right");
		lblRight.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRight = new GridBagConstraints();
		gbc_lblRight.ipadx = 10;
		gbc_lblRight.insets = new Insets(0, 0, 5, 5);
		gbc_lblRight.gridx = 0;
		gbc_lblRight.gridy = 0;
		contentPane.add(lblRight, gbc_lblRight);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.VERTICAL;
		gbc_separator.gridheight = 2;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 0;
		contentPane.add(separator, gbc_separator);
		
		JLabel lblNewLabel = new JLabel("Wrong");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.ipadx = 10;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridheight = 2;
		gbc_separator_1.fill = GridBagConstraints.VERTICAL;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 3;
		gbc_separator_1.gridy = 0;
		contentPane.add(separator_1, gbc_separator_1);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.ipadx = 10;
		gbc_lblScore.insets = new Insets(0, 0, 5, 0);
		gbc_lblScore.gridx = 4;
		gbc_lblScore.gridy = 0;
		contentPane.add(lblScore, gbc_lblScore);
		
		JLabel lblRightCount = new JLabel("<right_score>");
		GridBagConstraints gbc_lblRightCount = new GridBagConstraints();
		gbc_lblRightCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblRightCount.gridx = 0;
		gbc_lblRightCount.gridy = 1;
		contentPane.add(lblRightCount, gbc_lblRightCount);
		
		JLabel lblWrongCount = new JLabel("<wrong_score>");
		GridBagConstraints gbc_lblWrongCount = new GridBagConstraints();
		gbc_lblWrongCount.insets = new Insets(0, 0, 5, 5);
		gbc_lblWrongCount.gridx = 2;
		gbc_lblWrongCount.gridy = 1;
		contentPane.add(lblWrongCount, gbc_lblWrongCount);
		
		JLabel lblPercent = new JLabel("<%>");
		GridBagConstraints gbc_lblPercent = new GridBagConstraints();
		gbc_lblPercent.insets = new Insets(0, 0, 5, 0);
		gbc_lblPercent.gridx = 4;
		gbc_lblPercent.gridy = 1;
		contentPane.add(lblPercent, gbc_lblPercent);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 5;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		contentPane.add(panel, gbc_panel);
		
		JButton button = new JButton("Finish");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressedFinish();
			}
		});
		panel.add(button);
		
		lblRightCount.setText(String.valueOf(finishedQuiz.getRight()));
		lblWrongCount.setText(String.valueOf(finishedQuiz.getWrong()));
		lblPercent.setText(String.format("%d%%", finishedQuiz.getScore()));
	}

	/**
	 * Called when the finish button is pressed
	 */
	protected void pressedFinish()
	{
		MainFrame m = new MainFrame();
		m.setVisible(true);
		dispose();
	}

}
