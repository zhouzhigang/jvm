import java.lang.reflect.Method;
import java.lang.reflect.Type;
import static java.lang.System.out;

/**
 * Enumerate all the declared mthods in a given class and retieve the return, parameterm and exception types for all the methods of the given name.
 *
 * Usage examples:
 * java MethodSpy java.lang.Class getConstructor
 * java MethodSpy java.lang.Class cast
 * java MethodSpy java.io.printStream format
 */
public class MethodSpy {

    private static final String fmt = "%24s: %s%n";

    // for the morbodly curious
    <E extends RuntimeException> void genericThrow() throws E {}

    public static void getMethodInfo(String className, String methodName) {
        try {
            Class<?> c = Class.forName(className);
            Method[] allMethods = c.getDeclaredMethods();
            for (Method m : allMethods) {
                if (!m.getName().equals(methodName)) {
                    continue;
                }
                out.format("%s%n", m.toGenericString());
                
                out.format(fmt, "Return Type", m.getReturnType());
                out.format(fmt, "GenericReturnType", m.getGenericReturnType());

                Class<?>[] pType = m.getParameterTypes();
                Type[] gpType = m.getGenericParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    out.format(fmt, "ParameterType", pType[i]);
                    out.format(fmt, "GenericParameterType", gpType[i]);
                }

                Class<?>[] xType = m.getExceptionTypes();
                Type[] gxType = m.getGenericExceptionTypes();
                for (int i = 0; i < xType.length; i++) {
                    out.format(fmt, "ExceptionType", xType[i]);
                    out.format(fmt, "GenericExceptionType", gxType[i]);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
                    
    public static void main(String[] args) {
        if (args.length >= 2) {
            MethodSpy.getMethodInfo(args[0], args[1]);
        } else {
            System.out.format("==== Usage: ====%n    java MethodSpy 'classname' 'methodName'%n");
            String className = "java.lang.Class";
            String methodName = "getConstructor";
            System.out.format("==== Example: ====%n    java MethodSpy %s %s%n", className, methodName);
            System.out.format("==== Example Result: ====%n");
            MethodSpy.getMethodInfo(className, methodName);
        }
    }
}
