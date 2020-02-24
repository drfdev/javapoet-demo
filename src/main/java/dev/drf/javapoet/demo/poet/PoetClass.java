package dev.drf.javapoet.demo.poet;

import javax.tools.JavaFileObject;

public interface PoetClass {
    String getClassName();
    String getClassPath();
    JavaFileObject getJavaObject();
}
