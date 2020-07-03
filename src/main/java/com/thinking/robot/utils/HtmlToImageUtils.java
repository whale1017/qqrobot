package com.thinking.robot.utils;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.image.BufferedImage;

public class HtmlToImageUtils {
    
    public static BufferedImage html2Img(String htmText){
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        try {
            imageGenerator.loadHtml(htmText);
            BufferedImage image = imageGenerator.getBufferedImage();
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("将HTML文件转换成图片异常");
        }
    }
}
