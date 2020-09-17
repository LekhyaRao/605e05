import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
- write a high-level pseudocode of the program
- main() makes 2 calls: readBattleField() and play(). What is each method doing?
- describe what readHeightWidth() does?
- describe what createBattleField() does?
- describe what readBattleFieldScenario() does? (check questions in the method)
- describe what updateBattleField() does?
- how is the ocean represented?
- how are the boats represented?
- what are possible values for battleField?
- what are possible values for originalBattleField?
- removeAllPartsOfBoat(): what checks are done to verify if a boat's
  coordinates are contiguous in the ocean?

 */
public class BattleShip
{
	static String WIDTH  			= "width";
	static String HEIGHT 			= "height";
	static String WATER 			= "w";
	static int WATER_VALUE 			= -1;
	static int WATER_HIT_VALUE 		= -2;
	static int HIT_VALUE 			= -3;

	static int[][] battleField 		= null;		// This is the field shown to the player
	static int[][] originalBattleField 	= null;		// will be initialized once
	static int battleFieldWidth 		= 0;
	static int battleFieldHeight 		= 0;
	static Scanner battleFieldParser 	= null;
	static char hit 			= 'x';
	static String fileName 			= null;

	// what checks are done to verify if a boat's coordinates are contiguous
	// in the ocean?
	public static void removeAllPartsOfBoat(int column,int row)	{
		int boatId = originalBattleField[column][row];
		for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
			for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
				if ( originalBattleField[columns][rows] == boatId )
					battleField[columns][rows] = HIT_VALUE;
			}
		}
	}

	public static void printBattleField(int[][] battleField) {
		System.out.println();
		for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
			for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
				if ( battleField[columns][rows] == WATER_VALUE )
					System.out.print(  "w" + " " );
				else
					System.out.print(  battleField[columns][rows] + " " );
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printBattleFieldForPlayer(int[][] battleField) {
		System.out.println();
		System.out.println("x indicates a hit.");
		System.out.println("w indicates a miss, but you know now there is water.");
		System.out.println(". indicates boat or water.\n");
		System.out.print( "   " );
		for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
			System.out.print(  " " + columns );
		}
		System.out.println(" ---> columns");
		for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
			System.out.print(rows + ": " );
			for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
				if ( battleField[columns][rows] == WATER_HIT_VALUE )
					System.out.print(  " " + "w" );
				else if ( battleField[columns][rows] == HIT_VALUE )
					System.out.print(  " " + "x" );
				else
					System.out.print(  " " + "." );
			}
			System.out.println();
		}
		System.out.println();
	}

	public static boolean isThereAboatLeft() {
		boolean rValue = false;
		for ( int rows = 0; ! rValue && rows < battleFieldHeight; rows ++ )	{
			for ( int columns = 0; ! rValue && columns < battleFieldWidth; columns ++ )	{
				if  ( battleField[columns][rows] >= 0 )
					rValue = true;
			}
		}
		return rValue;
	}

	public static boolean allWater() {
		for ( int column = 0; column < battleFieldWidth; column ++ )	{
			for ( int row = 0; row < battleFieldWidth; row ++ )	{
				if (  battleField[column][row] != WATER_VALUE  )
					return false;
			}
		}
		return true;
	}

	public static void readHeightWidth() {
		for (int index = 0; index < 2; index ++ )	{
			if ( battleFieldParser.hasNextLine() )	{
				String[] oneDimension = battleFieldParser.nextLine().split("\\s+");
				if ( oneDimension[0].equals(WIDTH) )
					battleFieldWidth = Integer.parseInt(oneDimension[1]);
				else
					battleFieldHeight = Integer.parseInt(oneDimension[1]);
			}
		}
	}

	public static void createBattleField() {
		battleField             = new int[battleFieldWidth][battleFieldHeight];
		originalBattleField	= new int[battleFieldWidth][battleFieldHeight];
		for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
			for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
				battleField[columns][rows] = WATER_VALUE;
				originalBattleField[columns][rows] = WATER_VALUE;
			}
		}
	}

	public static void updateBattleField() {
		for ( int columns = 0; columns < battleFieldWidth; columns ++ )	{
			for ( int rows = 0; rows < battleFieldHeight; rows ++ )	{
				if ( originalBattleField[columns][rows] >= 0 )
					battleField[columns][rows] = 0;
			}
		}
	}

	public static void readBattleFieldScenario() {
		for ( int index = 0; index <   battleFieldHeight; index ++ )	{
			if ( battleFieldParser.hasNextLine() )	{
				String[] oneRow = battleFieldParser.nextLine().split("\\s+");
				for ( int xPosition = 1; xPosition < battleFieldWidth + 1; xPosition ++ )	{
					if (! oneRow[xPosition].equals(WATER) ) {
						// what does the line below do?
						String id = oneRow[xPosition].substring(1, oneRow[xPosition].length() );
						// are originalBattlefield[][] indices row,column or
						// column,row?
						originalBattleField[xPosition-1][index] = Integer.parseInt(id);
					}
				}
			}
		}
	}

	public static void readBattleField() {
		Scanner userInput = new Scanner(System.in);
		System.out.print("battleField file name: ");
		fileName = userInput.nextLine();

		if ( fileName.equals("exit")) {
			System.exit(0);
		}
		int column = 0;
		try {
			battleFieldParser = new Scanner(new File(fileName));
			readHeightWidth();
			createBattleField();
			readBattleFieldScenario();
			updateBattleField();

		} catch (FileNotFoundException e) {
			System.out.println("Can't find that file! Try Again.");
		}
	}

	public static int readOneIntValue(Scanner readUserInput, String text) {
		System.out.print(text);
		if ( readUserInput.hasNextInt()	)
			return readUserInput.nextInt();
		else	{
			System.out.println("Can't read next integer - RIP");
			System.exit(1);
		}
		return -1;
	}

	public static boolean checkRange(int minValue, int value, int maxValue, String errorMessage) {
		if ( ( minValue <= value) && ( value < maxValue ) )	{
			return true;
		} else	{
			System.out.println("Error: " + errorMessage );
			return false;
		}
	}

	public static void play() {
		Scanner readUserInput  = new Scanner( System.in);
		int row = 0;
		int column = 0;
		while ( isThereAboatLeft()	)	{
			printBattleFieldForPlayer(battleField);
			column = readOneIntValue(readUserInput,
					"column coordinate (0 <= column < " + battleFieldWidth + "): ");
			row	= readOneIntValue(readUserInput,
					"row	coordinate (0 <= row	< " + battleFieldHeight + "): ");
			if ( checkRange(0, column, battleFieldWidth, "Column out of range. " + column)	&&
					checkRange(0, row, battleFieldHeight, "Row out of range. " + row )          )
				if ( originalBattleField[column][row] == WATER_VALUE )	{
					battleField[column][row] = WATER_HIT_VALUE;
				} else {
					System.out.println("HIT");
					removeAllPartsOfBoat(column,row);
				}
		}
		printBattleFieldForPlayer(battleField);
	}

	public static void main(String[] args)
	{
		readBattleField();
		play();
	}
}
