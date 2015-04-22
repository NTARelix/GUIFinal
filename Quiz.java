import java.util.LinkedList;
import java.util.Random;

/**
 * Quiz class holds cards from a deck and delivers data from those cards in
 * order. The display order of the card data can be specified as well.
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class Quiz extends LinkedList<Card>
{
	private int right;
	private int wrong;
	private int total;
	private QuizAnswerType answerType1;
	private QuizAnswerType answerType2;
	private QuizAnswerType questionType;
	
	/**
	 * Constructor
	 * @param q Card data to display as the question
	 * @param a1 Card data to display as the first answer
	 * @param a2 Card data to display as the second answer
	 * @param deck Deck containing cards to be quizzed on
	 */
	public Quiz(QuizAnswerType q, QuizAnswerType a1, QuizAnswerType a2, Deck deck)
	{
		setQuestionType(q);
		setAnswerType1(a1);
		setAnswerType2(a2);
		right = 0;
		wrong = 0;
		setTotal(deck.size());
		for (Card c : deck)
			if (!contains(c))
				add(c);
	}
	
	/**
	 * Shuffles the cards in the quiz
	 */
	public void shuffle()
	{
		//Fisher-yates shuffle algorithm
		for (int i=size()-1;i>1;i--)
		{
			int j = new Random().nextInt(i + 1);
			Card temp = get(j);
			set(j, get(i));
			set(i, temp);
		}
	}
	
	/**
	 * Current card is marked as Right and current card is incremented
	 * @return Card which has been marked
	 */
	public Card guessRight()
	{
		right++;
		return pop();
	}
	
	/**
	 * Current card is marked as Wrong and current card is incremented
	 * @return Card which has been marked
	 */
	public Card guessWrong()
	{
		wrong++;
		return pop();
	}
	
	/**
	 * @return number of right answers in quiz
	 */
	public int getRight()
	{
		return right;
	}
	
	/**
	 * @return number of wrong answers in quiz
	 */
	public int getWrong()
	{
		return wrong;
	}

	/**
	 * @return Type of card data to display for first answer
	 */
	public QuizAnswerType getAnswerType1()
	{
		return answerType1;
	}

	/**
	 * @param answerType1 Set type of card data to display for first question
	 */
	public void setAnswerType1(QuizAnswerType answerType1)
	{
		this.answerType1 = answerType1;
	}

	/**
	 * @return Type of card data to display for second answer
	 */
	public QuizAnswerType getAnswerType2()
	{
		return answerType2;
	}

	/**
	 * @param answerType2 Set type of card data to display for second answer
	 */
	public void setAnswerType2(QuizAnswerType answerType2)
	{
		this.answerType2 = answerType2;
	}

	/**
	 * @return Type of card data to display for question
	 */
	public QuizAnswerType getQuestionType()
	{
		return questionType;
	}

	/**
	 * @param t Card data type requested
	 * @return String representation of card data type
	 */
	public String getAnswerTypeString(QuizAnswerType t)
	{
		switch(t)
		{
		case ENGLISH:
			return "English";
		case KANJI:
			return "Kanji";
		case READING:
			return "Reading";
		default:
			return null;
		}
	}
	
	/**
	 * @return first answer type
	 */
	public String getAnwerType1String()
	{
		return getAnswerTypeString(getAnswerType1());
	}
	
	/**
	 * @return second answer type
	 */
	public String getAnwerType2String()
	{
		return getAnswerTypeString(getAnswerType2());
	}
	
	/**
	 * @param questionType
	 */
	public void setQuestionType(QuizAnswerType questionType)
	{
		this.questionType = questionType;
	}
	
	/**
	 * @return question type
	 */
	public String getQuestion(int i)
	{
		return get(i).getText(getQuestionType());
	}
	
	/**
	 * @param i index of card for which first answer should be received
	 * @return First answer to question of i-th card
	 */
	public String getAnswer1(int i)
	{
		return get(i).getText(getAnswerType1());
	}
	
	/**
	 * @param i index of card for which second answer should be received
	 * @return Second answer to question of i-th card
	 */
	public String getAnswer2(int i)
	{
		return get(i).getText(getAnswerType2());
	}

	/**
	 * @return total number of cards in quiz
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * @param total
	 */
	public void setTotal(int total)
	{
		this.total = total;
	}

	/**
	 * @return Percent received out of total number of cards
	 */
	public int getScore()
	{
		return getRight() * 100 / getTotal();
	}
}
