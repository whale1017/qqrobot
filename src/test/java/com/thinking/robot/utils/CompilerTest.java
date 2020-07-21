package com.thinking.robot.utils;


import com.thinking.robot.utils.compiler.CompilerExecutor;
import org.junit.Test;

public class CompilerTest {
    private static final String source = "public class Main {\n" +
            "    public static Object main() {\n" +
            "        return \"动态编译测试通过\";\n" +
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
