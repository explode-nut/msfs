package client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShellTest {
    @Test
    void lexicalAnalyzeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> clazz = Class.forName("client.Shell");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method lexicalAnalyze = clazz.getDeclaredMethod("lexicalAnalyze", String.class);
        lexicalAnalyze.setAccessible(true);

        // 测试常规
        Pair invoke = (Pair) lexicalAnalyze.invoke(instance, "cd /");
        Assertions.assertEquals("cd", invoke.command());
        Assertions.assertEquals("/", invoke.parms().getFirst());
        invoke = (Pair) lexicalAnalyze.invoke(instance, "cd /a/b/");
        Assertions.assertEquals("cd", invoke.command());
        Assertions.assertEquals("/a/b/", invoke.parms().getFirst());
        // 测试开头有空格或其他空字符
        invoke = (Pair) lexicalAnalyze.invoke(instance, " cd /a/b");
        Assertions.assertEquals("cd", invoke.command());
        Assertions.assertEquals("/a/b", invoke.parms().getFirst());
        invoke = (Pair) lexicalAnalyze.invoke(instance, "\r cd /a/b");
        Assertions.assertEquals("cd", invoke.command());
        Assertions.assertEquals("/a/b", invoke.parms().getFirst());
        invoke = (Pair) lexicalAnalyze.invoke(instance, "\n cd /a/b");
        Assertions.assertEquals("cd", invoke.command());
        Assertions.assertEquals("/a/b", invoke.parms().getFirst());
        invoke = (Pair) lexicalAnalyze.invoke(instance, "\t cd /a/b");
        Assertions.assertEquals("cd", invoke.command());
        Assertions.assertEquals("/a/b", invoke.parms().getFirst());
        // 测试选项
        invoke = (Pair) lexicalAnalyze.invoke(instance, "ls -l");
        Assertions.assertEquals("ls", invoke.command());
        Assertions.assertEquals("-l", invoke.option().getFirst());
        // 测试参数间有多个空格
        invoke = (Pair) lexicalAnalyze.invoke(instance, "cd     /a/b/");
        Assertions.assertEquals("cd", invoke.command());
        Assertions.assertEquals("/a/b/", invoke.parms().getFirst());
        invoke = (Pair) lexicalAnalyze.invoke(instance, "ls     -l");
        Assertions.assertEquals("ls", invoke.command());
        Assertions.assertEquals("-l", invoke.option().getFirst());
    }
}
