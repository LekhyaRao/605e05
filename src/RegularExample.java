import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Integer;
/*
 */
public class RegularExample
{
	static String delimiter = " ";	// not set
	static String fileName = null;	// not set
	static Scanner input = null;	// not set

	static String[] allPatternsToTest = {
			".",
			"a word which is one character long",
			"^.$",
			"a000",
			"starts with 'a' followed by one digit or more digits",
			"^a\\d+$",
			"avenious",
			"a word with the vowels 'aeiou' in order and each vowel can appear only once",
			"^[^aeiou]*a[^aeiou]*e[^aeiou]*i[^aeiou]*o[^aeiou]*u[^aeiou]*$",
			"a222",
			"starts with 'a' followed by 3 digits",
			"^a\\d{3}?$",
			"a123",
			"starts with 'a' followed by 3 or more digits",
			"^a\\d{3,}?$",
			"a88",
			"starts with 'a' followed by 2 digits in the range between 8 and 9 only",
			"^a[89]{2}$",
			"world",
			"includes only lower case characters, but not the character 'h', 'p', and 'b'",
			"^[a-z&&[^hpb]]+$",
	};

	public static void printMe() {
		System.out.println("delimiter 	= " + delimiter );
		System.out.println("fileName 	= " + fileName );
	}

	public static void parseArgs( String args[] ) {
		int index = 0;
		while ( index < args.length )	{
			if ( args[index].startsWith("-d") )	{
				delimiter = args[++index];
			}
			if ( args[index].equals("-input") )	{
				fileName = args[++index];
			}
			index ++;
		}
	}

	public static void doTheWork(String explanation, boolean result)  {
		System.out.println(explanation + ": " +  result );
	}

	public static void processStatic()	{
		if ( fileName == null )	{
			int index = 0;
			while  ( index < allPatternsToTest.length )	{
				System.out.println("Word to test: -" + allPatternsToTest[index] + "=");
				if  ( Pattern.matches(allPatternsToTest[index + 2 ], allPatternsToTest[index] ) )	{
					System.out.println("This regular expression " + allPatternsToTest[index + 2 ]
							// System.out.println("This regular expression " + "\"XX\""
							+ " matches the following input: -" + allPatternsToTest[index] + "=");
					System.out.println(" 	verbal explanation: " + allPatternsToTest[index + 1 ]);
				}
				index += 3;
			}
		}
	}

	public static void readData()  {
		while ( input.hasNext()	)	{
			String nextInput = input.next();
			if (! nextInput.equals("\n") )	{
				int index = 0;
				System.out.println("-------------------------------------------------------");
				System.out.println("Input: -" + nextInput + "=");
				while  ( index < allPatternsToTest.length )	{
					if  ( Pattern.matches(allPatternsToTest[index + 2], nextInput) )	{
						System.out.println("This regular expression " + "\"XX\""
								// System.out.println("This regular expression " + allPatternsToTest[index + 2 ]
								+ " matches the following input: -" + nextInput + "=");
						System.out.println(" 	verbal explanation " + allPatternsToTest[index + 1 ]);
					}
					index += 3;
				}
			}
		}
	}

	public static void createScanner()  {
		if ( fileName == null )
			input = new Scanner(System.in);
		else	{
			try {
				input = new Scanner(new File(fileName));
				if ( delimiter != null )
					input.useDelimiter(delimiter);
			} catch (FileNotFoundException e) {
				System.out.println("Can't find that file! Try Again.");
				System.exit(1);
			}
		}
	}

	public static void closeScanner()  {
		input.close();
	}

	public static void main(String[] args)
	{
		parseArgs(args);
		processStatic();
		createScanner();
		readData();
		closeScanner();
	}
}
