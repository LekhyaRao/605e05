/**
 * Inheritance example showing constructor calls
 *
 * - Notice that when calling an overloaded constructor, the parent default
 * constructor is still called (equivalent to having a super() call before
 * anything in the child's constructor)
 * - How could we the child default constructor ALSO be called?
 *
 */
public class Child extends Parent {

    public Child() {
        System.out.println("Child Constructor");
    }

    public Child(int number ) {
        this();
        System.out.println("Child int Constructor");
    }

    public void hi() {
        System.out.println("Hi from Child");
    }

    public static void main ( String [] args ) {

        new Child( 2 );
    }
}