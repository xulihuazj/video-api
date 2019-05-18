package com.yinfeixing.utils.gifcode;

import com.yinfeixing.utils.datastru.TwoTuple;
import com.yinfeixing.utils.log.LogHelper;
import com.yinfeixing.utils.security.SecurityCode;

import javax.servlet.ServletException;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author mazy
 */
public class RandomCodeImageUtil {

    public static TwoTuple<BufferedImage, String> getGif() throws ServletException, IOException {
        return createImge(4, 0, 0);
    }

    public static TwoTuple<BufferedImage, String> getGif(int length, int indentation, float fontSize) throws ServletException, IOException {
        return createImge(length, indentation, fontSize);
    }

    private static TwoTuple<BufferedImage, String> createImge(int length, int indentation, float fontSize) {
        int width = 200;
        int height = 100;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // 创建BufferedImage类的对象
        Graphics g = image.getGraphics(); // 创建Graphics类的对象
        Graphics2D g2d = (Graphics2D) g; // 通过Graphics类的对象创建一个Graphics2D类的对象
        Random random = new Random(); // 实例化一个Random对象
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = environment.getAvailableFontFamilyNames();//获得系统字体
        String font = fonts[0];
        for (String s : fonts) {
            if ("宋体".equals(s)) {
                font = "宋体";
                break;
            }
        }
        LogHelper.debug("生成图片验证码时使用的字体:" + font);
        Font mFont = new Font(font, Font.BOLD, 60); // 通过Font构造字体
        g.setColor(Color.LIGHT_GRAY); // 改变图形的当前颜色为随机生成的颜色
        g.fillRect(0, 0, width, height); // 绘制一个填色矩形
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
        Color co = new
                Color(50 + random.nextInt(110), 50 + random.nextInt(110), 50 + random.nextInt(110));
        // 画一条折线
        BasicStroke bs = new BasicStroke(0.1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL); // 创建一个供画笔选择线条粗细的对象
        g2d.setStroke(bs); // 改变线条的粗细
        g.setColor(Color.DARK_GRAY); // 设置当前颜色为预订义颜色中的深灰色
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];
        for (int j = 0; j < 3; j++) {
            xPoints[j] = random.nextInt(width - 1);
            yPoints[j] = random.nextInt(height - 1);
        }
        g.drawPolyline(xPoints, yPoints, 1);
        // 生成并输出随机的验证文字
        g.setFont(mFont);
        g.setColor(Color.white);
        for (int i = -2; i < 30; i++) {
            g.drawString(String.valueOf("|"), 8 * i, 40);
            g.drawString(String.valueOf("|"), 8 * i, 90);
        }
        g.setColor(Color.pink);
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(300);
            int yl = random.nextInt(300);
            Color color = new Color(50 + random.nextInt(110), 50 + random.nextInt(110), 50 + random.nextInt(110));
            g.setColor(color);
            g.drawLine(x, y, x + xl, y + yl);
        }
        String sRand = "";
        int itmp = 0;
        sRand = SecurityCode.getSecurityCode(length, SecurityCode.SecurityCodeLevel.Medium, false);
        LogHelper.debug("图片验证码字符串：" + sRand);
        for (int i = 0; i < sRand.length(); i++) {
            char ctmp = sRand.toCharArray()[i];

            Color color = new Color(50 + random.nextInt(110), 50 + random.nextInt(110), 50 + random.nextInt(110));
            g.setColor(color);
            /**** 随机缩放文字并将文字旋转指定角度 **/
            // 将文字旋转指定角度
            Graphics2D g2d_word = (Graphics2D) g;
            AffineTransform trans = new AffineTransform();
            trans.rotate(random.nextInt(45) * 2.3 / 180, 20 * i + 10, 20);
            // 缩放文字
            float scaleSize = random.nextFloat() + 0.8f;
            if (fontSize > 0) {
                scaleSize = fontSize;
            }
            if (scaleSize > 1.2f) {
                scaleSize = 1.2f;
            }
            trans.scale(scaleSize, scaleSize);
            g2d_word.setTransform(trans);
            g.drawString(String.valueOf(ctmp), 30 * i + (10 + indentation), 40);
        }
        g.dispose();
        return new TwoTuple<>(image, sRand);
    }

}
