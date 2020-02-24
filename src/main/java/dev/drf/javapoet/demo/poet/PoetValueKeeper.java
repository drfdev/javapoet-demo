package dev.drf.javapoet.demo.poet;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import javax.tools.JavaFileObject;
import java.util.ArrayList;
import java.util.List;

public class PoetValueKeeper implements PoetClass {
    private static final String CLASS_NAME = "ValueKeeper";
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
        TypeVariableName typeVariable = TypeVariableName.get("T");

        TypeSpec type = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addTypeVariable(typeVariable)
                .addField(
                        FieldSpec.builder(ParameterizedTypeName.get(
                                ClassName.get(List.class), typeVariable), "values")
                                .addModifiers(Modifier.PRIVATE)
                                .initializer("new $T<>()", ArrayList.class)
                                .build()
                )
                .addMethod(
                        MethodSpec.methodBuilder("addValue")
                                .addModifiers(Modifier.PUBLIC)
                                .returns(void.class)
                                .addParameter(typeVariable, "val")
                                .addStatement("this.values.add($N)", "val")
                                .build()
                )
                .addMethod(
                        MethodSpec.methodBuilder("valueToStdout")
                                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                                .returns(void.class)
                                .addStatement("System.out.println($S)", "Values:")
                                .addStatement(
                                        CodeBlock.builder()
                                                .add("$N.forEach(System.out::println)", "values")
                                                .build()
                                )
                                .build()
                )
                .build();

        JavaFile javaFile = JavaFile
                .builder(CLASS_PATH, type)
                .build();

        return javaFile.toJavaFileObject();
    }
}
