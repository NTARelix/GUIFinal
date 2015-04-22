import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Deck class holds cards and contains a name for organizing multiple decks.
 * 
 * @author Kevin
 */
@SuppressWarnings("serial")
public class Deck extends LinkedList<Card>
{
	public static String DECK_DIR = "decks";
	
	private String name;
	
	/**
	 * Constructor
	 * @param f File from which deck should be loaded
	 */
	public Deck(File f)
	{
		super();
		setName(null);
		load(f);
	}
	
	/**
	 * Constructor
	 * @param name Name of deck
	 */
	public Deck(String name)
	{
		super();
		setName(name);
	}

	/**
	 * @return deck name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name Deck name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * return String representation of deck
	 */
	@Override
	public String toString()
	{
		return String.format("Deck(name=\"%s\", size=%d)", getName(), size());
	}
	
	/**
	 * Loads data from file into deck
	 * @param f File to load from
	 */
	protected void load(File f)
	{
		String name = f.getName().replaceFirst("[.][^.]+$", "");
		try
		{
			Scanner sc = new Scanner(f, "UTF-8");
			while (sc.hasNext())
			{
				String kanji = sc.nextLine();
				String english = sc.nextLine();
				String reading = sc.nextLine();
				Card c = new Card(kanji, english, reading);
				add(c);
			}
			sc.close();
			setName(name);
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Failed to load deck: " + name);
		}
	}
	
	/**
	 * Saves deck to file. Name of file is name of deck with .deck extension
	 * @throws IOException 
	 */
	public void save() throws IOException
	{
		Path p = FileSystems.getDefault().getPath(getDeckPath().toString(), getName() + ".deck");
		File f = p.toFile();
		try
		{
			Writer out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			for (Card c : this)
			{
				out.write(c.getKanji() + "\n");
				out.write(c.getEnglish() + "\n");
				out.write(c.getReading() + "\n");
			}
			out.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Failed to save deck");
		}
	}
	
	/**
	 * Gets file object associated with deck
	 * @return
	 */
	public File getFile()
	{
		Path p = FileSystems.getDefault().getPath(getDeckPath().toString(), getName() + ".deck");
		File f = p.toFile();
		return f;
	}
	
	/**
	 * @return Path to directory holding deck files
	 */
	public static Path getDeckPath()
	{
		Path p = FileSystems.getDefault().getPath(System.getProperty("user.home"), DECK_DIR);
		if (Files.notExists(p))
			p.toFile().mkdir();
		return p;
	}
	
	/**
	 * Loads all decks in deck directory into a list of deck objects
	 * @return Linked list of decks
	 */
	public static List<Deck> loadDecks()
	{
		List<Deck> decks = new LinkedList<Deck>();
		Path deckPath = Deck.getDeckPath();
		FilenameFilter filter = new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String filename)
			{
				return filename.endsWith(".deck");
			}
		};
		File[] deckFiles = deckPath.toFile().listFiles(filter);
		for (File deckFile : deckFiles)
		{
			Deck deck = new Deck(deckFile);
			if (deck != null)
				decks.add(deck);
		}
		return decks;
	}
}
