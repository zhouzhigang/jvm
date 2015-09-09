import java.lang.reflect.Field;

/**
 * IllegalAccessException when Modifying Final Fields.
 */
public class FieldTroubleToo {

    // final fields
    public final boolean b = true;

    public static void main(String[] args) {
        FieldTroubleToo ft = new FieldTroubleToo();
        try {
            Class<?> c = ft.getClass();
            Field f = c.getDeclaredField("b");
            // f.setAccessible(true); // solution
            f.setBoolean(ft, Boolean.FALSE); // IllegalAccessException
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
