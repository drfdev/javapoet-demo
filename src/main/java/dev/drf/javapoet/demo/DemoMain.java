package dev.drf.javapoet.demo;

public class DemoMain {
    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();

        String java = """
                package  dev.drf.javapoet.demo;

                public class HelloWorld {
                    public String hello() {
                        return "Hello World!";
                    }
                }
                """;
        String className = "dev.drf.javapoet.demo.HelloWorld";

        try {
            Class<?> helloWorldClass = generator.compileClass(className, java);

            Object obj = helloWorldClass.newInstance();
            Object result = helloWorldClass.getMethod("hello").invoke(obj);

            System.out.println(result.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
