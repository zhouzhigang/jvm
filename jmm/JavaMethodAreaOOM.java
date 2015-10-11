import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * OutOfMemory on Java Method Area by using CGLib to generate class dynamically.
 * Using GeneratedConstructorAccessor and DynamicProxy need a lot of code.
 *
 * wget http://central.maven.org/maven2/cglib/cglib/3.2.0/cglib-3.2.0.jar
 * java -cp cglib-3.2.0.jar JavaMethodAreaOOM
 * java -XX:PermSize=10m -XX:MaxPermSize=10m -cp cglib-3.2.0.jar JavaMethodAreaOOM
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {
    }
}
