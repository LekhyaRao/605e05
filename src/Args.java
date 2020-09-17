import java.util.Vector;

/**
 * Take a look at this code, slightly modified from last lectures's code. If
 * arguments are passed by value, why is anInt changing?
 *
 * - What about the Strings as arguments, why these are the original values
 * not changed if String is an Object (line 23)?
 */
public class  Args {

	int    anInt   = -1;
	String aString = "a";
	int[]  anArray = { 4, 2 };

	public void print() {
		System.out.println("anInt: " + anInt);
	}
	public void testArgs(Args arg )      {
		arg.anInt += 1;
	}

	public void testString(String arg )      {
		arg = "b";
	}
	public void testArray(int[] arg )      {
		arg[1] = 3;
	}
	public void testInt(int arg )      {
		arg = 42;
	}

	public void testArgs()      {
		System.out.println("1.	" + aString);
		testArgs();
		System.out.println("2.	" + aString);
	}
	public void testString()	{
		System.out.println("1.	" + aString);
		testString(aString);
		System.out.println("2.	" + aString);
	}
	public void testArray()	{
		System.out.println("3.	" + anArray[0] + ", " + anArray[1]);
		testArray(anArray);
		System.out.println("4.	" + anArray[0] + ", " + anArray[1]);
	}
	public void testInt()	{
		System.out.println("5.	" + anInt );
		testInt(anInt);
		System.out.println("6.	" + anInt );
	}

	public static void main(String args[] )       {
		new Args().testString();
		new Args().testArray();
		new Args().testInt();

		Args changingOrNot = new Args();
		Args worker = new Args();
		changingOrNot.print();
		worker.testArgs(changingOrNot);
		changingOrNot.print();

	}
}
