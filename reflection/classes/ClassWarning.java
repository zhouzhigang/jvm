import java.lang.reflect.Method;

public class ClassWarning {
    void m() {
        try {
            Class c = ClassWarning.class;
            @SuppressWarnings("unchecked")
            Method m = c.getMethod("m"); // warning
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    void m2() {
        try {
            // declare the Class with an appropriate generic type
            Class<?> c= ClassWarning.class;
            Method m = c.getMethod("m2");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
