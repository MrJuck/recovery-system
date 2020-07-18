package com.juck.recovery.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class ImageVerificationUtil {
    private static final int WEIGHT = 100;
    private static final int HEIGHT = 40;
    private static final Random RANDOM = new Random();
    private static final String[] FONT_NAMES = {"Georgia"};
    private static final String CODES = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    private static String text;

    private ImageVerificationUtil() {
    }

    public static void output(BufferedImage image, OutputStream out) throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    /**
     * 获取随机的颜色
     *
     * @return
     */
    private static Color randomColor() {
        int r = RANDOM.nextInt(225);  //这里为什么是225，因为当r，g，b都为255时，即为白色，为了好辨认，需要颜色深一点。
        int g = RANDOM.nextInt(225);
        int b = RANDOM.nextInt(225);
        return new Color(r, g, b);            //返回一个随机颜色
    }

    /**
     * 获取随机字体
     *
     * @return
     */
    private static Font randomFont() {
        int index = RANDOM.nextInt(FONT_NAMES.length);  //获取随机的字体
        String fontName = FONT_NAMES[index];
        int style = RANDOM.nextInt(4);         //随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体
        int size = RANDOM.nextInt(10) + 24;    //随机获取字体的大小
        return new Font(fontName, style, size);   //返回一个随机的字体
    }

    /**
     * 获取随机字符
     *
     * @return
     */
    private static char randomChar() {
        int index = RANDOM.nextInt(CODES.length());
        return CODES.charAt(index);
    }

    /**
     * 画干扰线，验证码干扰线用来防止计算机解析图片
     *
     * @param image
     */
    private static void drawLine(BufferedImage image) {
        int num = RANDOM.nextInt(10); //定义干扰线的数量
        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x1 = RANDOM.nextInt(WEIGHT);
            int y1 = RANDOM.nextInt(HEIGHT);
            int x2 = RANDOM.nextInt(WEIGHT);
            int y2 = RANDOM.nextInt(HEIGHT);
            g.setColor(randomColor());
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 创建图片的方法
     *
     * @return
     */
    private static BufferedImage createImage() {
        //创建图片缓冲区
        BufferedImage image = new BufferedImage(WEIGHT, HEIGHT, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D g = (Graphics2D) image.getGraphics();
        //设置背景色随机
        g.setColor(new Color(255, 255, RANDOM.nextInt(245) + 10));
        g.fillRect(0, 0, WEIGHT, HEIGHT);
        //返回一个图片
        return image;
    }

    /**
     * 获取验证码图片的方法
     *
     * @return
     */
    public static BufferedImage getImage() {
        BufferedImage image = createImage();
        Graphics2D g = (Graphics2D) image.getGraphics(); //获取画笔
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String s = randomChar() + "";
            sb.append(s);
            float x = i * 1.0F * WEIGHT / 4;
            g.setFont(randomFont());
            g.setColor(randomColor());
            g.drawString(s, x, HEIGHT - 5);
        }
        text = sb.toString();
        drawLine(image);
        return image;
    }

    /**
     * 获取验证码文本的方法
     *
     * @return
     */
    public static String getText() {
        return text;
    }
}
