import java.lang.reflect.Field;
import java.util.List;

/**
 * Obtaining Field Types.
 *
 * Usage examples:
 * java FieldSpy FieldSpy b
 * java FieldSpy FieldSpy name
 * java FieldSpy FieldSpy list
 * java FieldSpy FieldSpy val
 */
public class FieldSpy<T> {

    // some fields used for test
    public boolean[][] b = {{false, false}, {true, true}};
    public String name = "Alice";
    public List<Integer> list;
    public T val;

    public static void getFields(String className, String fieldName) {
        try {
            Class<?> c = Class.forName(className);
            // get field by fieldName
            Field f = c.getField(fieldName);
            System.out.format("Type: %s%n", f.getType());
            System.out.format("GenericType: %s%n", f.getGenericType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        if (args.length >= 2) {
            FieldSpy.getFields(args[0], args[1]);
        } else {
            System.out.format("==== Usage: ====%n    java FieldSpy 'classname' 'fieldName'%n");
            System.out.format("==== Example: ====%n    java FieldSpy FieldSpy list%n");
            System.out.format("==== Example Result: ====%n");
            FieldSpy.getFields("FieldSpy", "list");
        }
    }
}
