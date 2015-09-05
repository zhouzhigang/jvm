import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static java.lang.System.out;

/**
 * Examining Class Modifiers and Types.
 * Print out the Class, Modifiers, Type Parameters, Interfacess, Inheritance Path and Annotations in std out.
 *
 * Usages examples:
 *  java ClassDeclarationSpy java.util.concurrent.ConcurrentNavigableMap
 *  java ClassDeclarationSpy "[Ljava.lang.String;"
 *  java ClassDeclarationSpy java.io.InterruptedIOException
 *  java ClassDeclarationSpy java.security.Identity
 */
public class ClassDeclarationSpy {

    public static void main(String args[]) {
        if (args.length >= 1) {
            ClassDeclarationSpy.getClassDeclaration(args[0]);
        } else {
            out.format("==== Usage: ====%n    java ClassDeclarationSpy 'classname'%n");
            out.format("==== Example: ====%n    java ClassDeclarationSpy 'java.lang.String'%n");
            out.format("==== Example Result: ====%n");
            ClassDeclarationSpy.getClassDeclaration("java.lang.String");
        }
    }

    /**
     * Get class declaration by classNam(include package).
     */
    public static void getClassDeclaration(String className) {
        try {
            Class<?> c = Class.forName(className);
            // get classes
            out.format("Class: %n   %s%n%n", c.getCanonicalName());
            // get modifiers
            out.format("Modifiers:%n    %s%n%n",
                        Modifier.toString(c.getModifiers()));
            
            // get tye parameters
            out.format("Type Parameters:%n");
            TypeVariable<?>[] tv = c.getTypeParameters();
            if (tv.length != 0) {
                out.format("    ");
                for (TypeVariable<?> t : tv) {
                    out.format("%s ", t.getName());
                }
                out.format("%n%n");
            } else {
                out.format("  -- No Type Parameters --%n%n");
            }

            // get interfaces
            out.format("Implemented Interfaces:%n");
            Type[] intfs = c.getGenericInterfaces();
            if (intfs.length != 0) {
                for (Type intf : intfs) {
                    out.format("    %s%n", intf.toString());
                }
                out.format("%n");
            } else {
                out.format("    -- No Implemented Interfaces --%n%n");
            }

            // get inheritance path
            out.format("Inheritance Path%n");
            List<Class<?>> l = new ArrayList<Class<?>>();
            printAncestor(c, l);
            if (l.size() != 0) {
                for (Class<?> cl : l) {
                    out.format("    %s%n", cl.getCanonicalName());
                }
                out.format("%n");
            } else {
                out.format("    -- No Super Classes --%n%n");
            }

            // get annotations
            out.format("Annotations:%n");
            Annotation[] ann = c.getAnnotations();
            if (ann.length != 0) {
                for (Annotation a : ann) {
                    out.format("    %s%n", a.toString());
                }
                out.format("%n");
            } else {
                out.format("    -- No Annotations --%n%n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get ancestor recursively.
     */
    private static void printAncestor(Class<?> c, List<Class<?>> l) {
        Class<?> ancestor = c.getSuperclass();
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);
        }
    }
}
