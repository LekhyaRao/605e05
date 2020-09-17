/**
 * - Check the main() output
 * - Explain what is happening
 *
 * - can I call a B-specific method on obj?
 * - what does (B) represent in B obj2 = (B)obj;
 * - what is a method override?
 * - what is a field hiding?
 */

public class B extends A
{
    protected int value = 2;

    public B() { System.out.print("*"); }

    public void bMethod () {
        System.out.println("\nb exclusive");
    }

    public void methodOne() {
        System.out.print("B");
    }

    public static void main ( String [] args ) {
        A obj = new B();

//        //downcasting
//        B obj2 = (B) obj;
//        obj2.bMethod();

//        //field hiding
//        System.out.println(obj.value);
//        System.out.println(obj2.value);

    }
}
