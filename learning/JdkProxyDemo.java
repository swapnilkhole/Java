package learning;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyDemo {

    interface Perfect {
        void originalMethod(String s);
    }

    static class Original implements Perfect {
        public void originalMethod(String s) {
            System.out.println(s);
        }
    }

    static class Handler implements InvocationHandler {
        private final Perfect original;

        public Handler(Perfect original) {
            this.original = original;
        }

        public Object invoke(Object proxy, Method method, Object[] args)
                throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
            System.out.println("BEFORE");
            method.invoke(original, args);
            System.out.println("AFTER");
            return null;
        }
    }

    public static void main(String[] args){
        Original original = new Original();
        Handler handler = new Handler(original);
        Perfect f = (Perfect) Proxy.newProxyInstance(Perfect.class.getClassLoader(),
                new Class[] { Perfect.class },
                handler);
        f.originalMethod("Hallo");
    }

}
