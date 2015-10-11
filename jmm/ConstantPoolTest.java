/**
 * Test difference behavior about constant pool(String.intern()) in Jdk6 and Jdk7.
 */
public class ConstantPoolTest {
    public static void main(String[] args) {
        String str1 = new StringBuilder("Computer").append("Software").toString();
        System.out.println(str1.intern() == str1); // true in jdk7 and latter
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2); // false("java" is not first appears)
    }
}
