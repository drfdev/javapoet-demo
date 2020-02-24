package dev.drf.javapoet.demo;

import dev.drf.javapoet.demo.poet.PoetClass;
import dev.drf.javapoet.demo.poet.PoetHelloWorld;
import dev.drf.javapoet.demo.poet.PoetValueKeeper;

import javax.tools.JavaFileObject;
import java.lang.reflect.Method;

public class DemoMain {
    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();

        try {
            String java = """
                    package  dev.drf.javapoet.demo;

                    public class HelloWorld {
                        public String hello() {
                            return "Hello World!";
                        }
                    }
                    """;
            String className = "dev.drf.javapoet.demo.HelloWorld";


            Class<?> helloWorldClass = generator.compileClass(className, java);

            Object obj = helloWorldClass.newInstance();
            Object result = helloWorldClass.getMethod("hello").invoke(obj);

            System.out.println(result.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /* JAVAPOET */
        try {
            PoetHelloWorld poetHelloWorld = new PoetHelloWorld();
            JavaFileObject javaFileObject = poetHelloWorld.getJavaObject();

            String javaText = javaFileObject.getCharContent(false).toString();
//            System.out.println(javaText);

            Class<?> poetHelloWorldClass = generator.compileClass(getJavaFullName(poetHelloWorld), javaText);
            Object obj1 = poetHelloWorldClass.newInstance();
            Object obj2 = poetHelloWorldClass.getDeclaredConstructor(String.class).newInstance("Test Hello");
            Object obj3 = poetHelloWorldClass.getDeclaredConstructor(String.class).newInstance(new Object[] {null});

            Method method = poetHelloWorldClass.getMethod("toString");

            System.out.println(method.invoke(obj1));
            System.out.println(method.invoke(obj2));
            System.out.println(method.invoke(obj3));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            PoetValueKeeper poetValueKeeper = new PoetValueKeeper();
            JavaFileObject javaFileObject = poetValueKeeper.getJavaObject();

            String javaText = javaFileObject.getCharContent(false).toString();
//            System.out.println(javaText);

            Class<?> poetValueKeeperClass = generator.compileClass(getJavaFullName(poetValueKeeper), javaText);
            Object obj = poetValueKeeperClass.newInstance();

            Method addValue = poetValueKeeperClass.getMethod("addValue", Object.class);
            Method valueToStdout = poetValueKeeperClass.getMethod("valueToStdout");

            addValue.invoke(obj, "String-1");
            addValue.invoke(obj, "String-2");

            valueToStdout.invoke(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getJavaFullName(PoetClass poet) {
        return poet.getClassPath() + "." + poet.getClassName();
    }
}
