package dev.drf.javapoet.demo.poet;

import org.junit.Test;

import javax.tools.JavaFileObject;

import java.io.IOException;

import static org.junit.Assert.assertFalse;

public class PoetValueKeeperTest {
    @Test
    public void testClassGenerator() throws IOException {
        PoetValueKeeper poetValueKeeper = new PoetValueKeeper();

        JavaFileObject javaFileObject = poetValueKeeper.getJavaObject();
        String javaText = javaFileObject.getCharContent(false).toString();

        assertFalse(javaText.isEmpty());
        System.out.println(javaText);
    }
}
