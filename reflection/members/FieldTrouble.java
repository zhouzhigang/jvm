import java.lang.reflect.Field;

/**
 * IllegalArgumentExceptin due to Inconvertible Types.
 */
public class FieldTrouble {
    public Integer val;

    public static void main(String[] args) {
        FieldTrouble ft = new FieldTrouble();
        try {
            Class<?> c = ft.getClass();
            Field f = c.getDeclaredField("val");
            f.set(ft, new Integer(4)); // correct
            // compiler can't auto perform boxing
            f.setInt(ft, 2); // IllegalArgumentException
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
