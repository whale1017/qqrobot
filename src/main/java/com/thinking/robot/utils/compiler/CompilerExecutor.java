package com.thinking.robot.utils.compiler;

import com.itranswarp.compiler.JavaStringCompiler;

import java.util.Map;

public class CompilerExecutor {
    public static String run(String source, String...args) throws Exception {
    
        AbstractCompilerClass compiler = getInstance(source);
    
        Object result = compiler.solution((Object) args);
        return result.toString();
    }
    
    public static AbstractCompilerClass getInstance(String source) throws Exception {
        final Class<?> clazz = loadClass(source);
    
        // 创建实例
        AbstractCompilerClass instance = (AbstractCompilerClass) clazz.newInstance();
        return instance;
    }
    
    public static Class<?> loadClass(String source) throws Exception {
        // 声明类名
        String className = AbstractCompilerClass.CLASS_NAME;
        String packageName = AbstractCompilerClass.PACKAGE_NAME;
        
        // 声明包名：package top.fomeiherz;
        String prefix = String.format("package %s;", packageName);
        prefix += "import com.thinking.robot.utils.compiler.AbstractCompilerClass;";
        
        // 全类名：top.fomeiherz.Main
        String fullName = String.format("%s.%s", packageName, className);
        
        // 编译器
        JavaStringCompiler compiler = new JavaStringCompiler();
        // 编译：compiler.compile("Main.java", source)
        Map<String, byte[]> results;
        try {
            results = compiler.compile(className + ".java", prefix + source);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        // 加载内存中byte到Class<?>对象
        Class<?> clazz = compiler.loadClass(fullName, results);
        return clazz;
    }
    
    
}

