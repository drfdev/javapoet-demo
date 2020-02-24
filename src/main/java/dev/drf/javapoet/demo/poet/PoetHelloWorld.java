package dev.drf.javapoet.demo.poet;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import javax.tools.JavaFileObject;

public final class PoetHelloWorld implements PoetClass {
    private static final String CLASS_NAME = "PoetHelloWorldImpl";
    private static final String CLASS_PATH = "dev.drf.javapoet.demo.poet";

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public String getClassPath() {
        return CLASS_PATH;
    }

    @Override
    public JavaFileObject getJavaObject() {
        TypeSpec type = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(
                        FieldSpec.builder(String.class, "value", Modifier.PUBLIC, Modifier.FINAL)
                                .build()
                )
                .addMethod(
                        MethodSpec.constructorBuilder()
                                .addModifiers(Modifier.PUBLIC)
                                .addStatement("this.$N = $S", "value", "Hello World")
                                .build()
                )
                .addMethod(
                        MethodSpec.constructorBuilder()
                                .addModifiers(Modifier.PUBLIC)
                                .addParameter(
                                        ParameterSpec.builder(String.class, "value").build()
                                )
                                .addStatement("this.$N = $N", "value", "value")
                                .build()
                )
                .addMethod(
                        MethodSpec.methodBuilder("toString")
                                .addModifiers(Modifier.PUBLIC)
                                .addAnnotation(Override.class)
                                .returns(String.class)
                                .beginControlFlow("if ($N == null)", "value")
                                .addStatement("return $S", "Value is null, Hello from NULL!!")
                                .endControlFlow()
                                .addStatement("return $N", "value")
                                .build()
                )
                .build();
        JavaFile javaFile = JavaFile
                .builder(CLASS_PATH, type)
                .build();
        return javaFile.toJavaFileObject();
    }
}
