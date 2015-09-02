import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Field;

/**
 * Retrieving Class Object.
 */
public class RetrievingClass {

    enum E {A, B}

    /**
     * Retrieving class by getClass() method of an object.
     */
    public static void getClassTest() {
        System.out.println("==== 1. Object.getClass Test ====");
        // common object
        Class<?> c1 = System.console().getClass();
        System.out.println(c1.getName());
        // enum instance
        Class<?> c2 = E.A.getClass();
        System.out.println(c2.getName());
        // array object
        byte[] bytes = new byte[1024];
        Class<?> c3 = bytes.getClass();
        System.out.println(c3.getName());
        // collection object
        Set<String> s = new HashSet<String>();
        Class<?> c4 = s.getClass();
        System.out.println(c4.getName());
    }
 
    /**
     * This.class Syntax Test.
     */
    public static void dotClassTest() {
        System.out.println("==== 2. The .class Syntax Test ====");
        // comile-time error
        // boolean b;
        // Class c = b.getClass(); // primitive type that can't be dereferenced
        // correct primitive type
        Class<?> c1 = boolean.class;
        System.out.println(c1.getName());
        // A reference type
        Class<?> c2 = java.io.PrintStream.class;
        System.out.println(c2.getName());
        // multi-dimensional array of a given type
        Class<?> c3 = int[][][].class;
        System.out.println(c3.getName());
        Class<?> c4 = String[][].class;
        System.out.println(c4.getName());

    }
    
    /**
     * Class.forName() Test.
     * Must throw or catch ClassNotFoundException.
     */
    public static void forNameTest() {
        System.out.println("==== 3. Class.forName() Test ====");
        try {
            // fully-qulified name of a class
            Class<?> c1 = Class.forName("java.util.Set");
            System.out.println(c1.getName());
            // double[].class
            Class<?> cDoubleArray = Class.forName("[D");
            System.out.println(cDoubleArray.getName());
            // two-dimensional array of String
            Class<?> cStringArray = Class.forName("[[Ljava.lang.String;");
            System.out.println(cStringArray.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * TYPE Field for Primitive Type Wrappers Test.
     */
    public static void wrapperFieldTypeTest() {
        System.out.println("==== 4. TYPE Field for Primitive Type Wrappers Test =====");
        // Double Wrapper
        Class<?> c1 = Double.TYPE;
        System.out.println(c1.getName());
        // Void type, identical to void.class
        Class<?> c2 = Void.TYPE;
        System.out.println(c2.getName());
    }

    /**
     * Methods that return classes Test.
     */
    public static void returnClassesMethodTest() {
        System.out.println("==== 5. Methods that Return Classes Test ====");
        // Class.getSuperclass()
        Class<?> c1 = javax.swing.JButton.class.getSuperclass();
        System.out.println(c1.getName());
        // Class.getClasses() return all the public classes, interfaces, and enums
        Class<?>[] c2 = Character.class.getClasses();
        System.out.println("All public classes, interfaces, enums count: " + c2.length);
        // Class.getDeclaredClasses() reurn all classes, interfaces, and enums
        Class<?>[] c3 = Character.class.getDeclaredClasses();
        System.out.println("All classes, interfaces, enums count: " + c3.length);
        // Class.getDeclaringClass()
        try {
            Field f = System.class.getField("out");
            Class<?> c4 = f.getDeclaringClass();
            System.out.println(c4.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RetrievingClass.getClassTest();
        RetrievingClass.dotClassTest();
        RetrievingClass.forNameTest();
        RetrievingClass.wrapperFieldTypeTest();
        RetrievingClass.returnClassesMethodTest();
    }
}
