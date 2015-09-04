class Cls {
    // private constructor
    private Cls() {}
}
public class ClassTrouble {

    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("Cls");
            // IllegalAccessException: modifiers private
            c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
