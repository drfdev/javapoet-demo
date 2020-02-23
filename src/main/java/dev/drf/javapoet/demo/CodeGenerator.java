package dev.drf.javapoet.demo;

import javax.tools.*;
import java.io.IOException;
import java.util.Arrays;

public final class CodeGenerator {

    public Class<?> compileClass(final String className, final String classCode) throws IOException, ClassNotFoundException {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        JavaFileObject compilationUnit = new StringJavaFileObject(className, classCode);
        SimpleJavaFileManager fileManager = new SimpleJavaFileManager(javaCompiler.getStandardFileManager(null, null, null));
        JavaCompiler.CompilationTask compilationTask = javaCompiler.getTask(null, fileManager, null, null, null, Arrays.asList(compilationUnit));
        compilationTask.call();

        CompiledClassLoader classLoader = new CompiledClassLoader(fileManager.getGeneratedOutputFiles());
        Class<?> generatedClass = classLoader.loadClass(className);

        return generatedClass;
    }
}
