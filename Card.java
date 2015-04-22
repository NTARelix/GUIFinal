/**
 * Card class contains a 3 sided flashcard for studying Japanese language.
 * One side contains the Kanji, another side contains the English translation,
 * and the third side contains the Japanese reading of the kanji.
 * @author Kevin
 *
 */
public class Card
{
	private String kanji;
	private String english;
	private String reading;
	
	/**
	 * Constructor
	 * @param kanji
	 * @param english
	 * @param reading
	 */
	public Card(String kanji, String english, String reading)
	{
		setKanji(kanji);
		setEnglish(english);
		setReading(reading);
	}

	/**
	 * @return kanji
	 */
	public String getKanji()
	{
		return kanji;
	}

	/**
	 * @param kanji
	 */
	public void setKanji(String kanji)
	{
		this.kanji = kanji;
	}

	/**
	 * @return English
	 */
	public String getEnglish()
	{
		return english;
	}

	/**
	 * @param english
	 */
	public void setEnglish(String english)
	{
		this.english = english;
	}

	/**
	 * @return reading
	 */
	public String getReading()
	{
		return reading;
	}

	/**
	 * @param reading
	 */
	public void setReading(String reading)
	{
		this.reading = reading;
	}
	
	/**
	 * @return String representation of card (English)
	 */
	public String toString()
	{
		return getEnglish();
	}
	
	/**
	 * @param other object to compare equality (Another card)
	 * @return true if objects equal, false otherwise
	 */
	public boolean equals(Object other)
	{
		return getEnglish() == ((Card) other).getEnglish();
	}
	
	/**
	 * @param t quiz answer type requested
	 * @return object's String value of QuizAnswerType
	 */
	public String getText(QuizAnswerType t)
	{
		switch(t)
		{
		case ENGLISH:
			return getEnglish();
		case KANJI:
			return getKanji();
		case READING:
			return getReading();
		default:
			return null;
		}
	}
}
