package com.thinking.robot.utils;


import com.thinking.robot.utils.compiler.CompilerExecutor;
import org.junit.Test;

public class CompilerTest {
    private static final String source = "public class CompilerMain extends AbstractCompilerClass {\n" +
            "    @Override\n" +
            "    public Object solution(Object... args) {\n" +
            "        return \"测试运行\";\n" +
            "    }\n" +
            "}";
    
    @Test
    public void test(){
        try {
            System.out.println(CompilerExecutor.run(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
