import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import static java.lang.System.out;

enum Spy { BLACK, WHITE }

/**
 * Retriving and Parsing Field Modifiers.
 *
 * Usage examples:
 * java FieldModifierSpy FieldModifierSpy volatile
 * java FieldModifierSpy Spy public
 * java FieldModifierSpy FieldModifierSpy\$Inner final
 * java FieldModifierSpy Spy private static final
 */
public class FieldModifierSpy {
    // fileds used for test(different modifier)
    volatile int share;
    int instance;
    class Inner{};

    public static void getFieldModifiers(String className, String[] modifierNames) {
        try {
            Class<?> c = Class.forName(className);
            int searchMods = 0x0;
            for (int i = 0; i < modifierNames.length; i++) {
                searchMods |= modifierFromString(modifierNames[i]);
            }

            Field[] flds = c.getDeclaredFields();
            out.format("Fields in Class '%s' containing modifiers: %s%n",
                        c.getName(),
                        Modifier.toString(searchMods));
            boolean found = false;
            for (Field f : flds) {
                int foundMods = f.getModifiers();
                // Require all of the requested modifiers to be present
                if ((foundMods & searchMods) == searchMods) {
                    out.format("%-8s [ synthetic=%-5b enum_constant=%-5b]%n",
                            f.getName(), f.isSynthetic(),
                            f.isEnumConstant());
                    found = true;
                }
            }

            if (!found) {
                out.format("No matching fields%n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int modifierFromString(String s) {
        int m = 0x0;
        if ("public".equals(s))         m |= Modifier.PUBLIC;
        else if ("protected".equals(s)) m |= Modifier.PROTECTED;
        else if ("private".equals(s))   m |= Modifier.PRIVATE;
        else if ("static".equals(s))    m |= Modifier.STATIC;
        else if ("final".equals(s))     m |= Modifier.FINAL;
        else if ("transient".equals(s)) m |= Modifier.TRANSIENT;
        else if ("volatile".equals(s))  m |= Modifier.VOLATILE;
        return m;
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            FieldModifierSpy.getFieldModifiers(args[0], Arrays.copyOfRange(args, 1, args.length));
        } else {
            out.format("==== Usage: ====%n    java FieldModifierSpy 'classname' 'modifierNames'%n");
            out.format("    modifierNames(split by space): public protected private transient volatile static final%n");
            String className = "FieldModifierSpy";
            String[] modifierNames = {"volatile"};
            out.format("==== Example: ====%n    java FieldModifierSpy %s %s%n", className, modifierNames[0]);
            out.format("==== Example Result: ====%n");
            FieldModifierSpy.getFieldModifiers(className, modifierNames);
        }
    }
}
