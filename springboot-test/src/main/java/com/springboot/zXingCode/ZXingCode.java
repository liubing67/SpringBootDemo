package com.springboot.zXingCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.catalina.connector.Request;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class ZXingCode {

    private static final int QRCOLOR = 0xFF000000; // 默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF; // 背景颜色

    private static int WIDTH = 200; // 二维码宽
    private static  int HEIGHT = 200; // 二维码高

    // 用于设置QR二维码参数
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;

        {
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.CHARACTER_SET, "utf-8");// 设置编码方式
            put(EncodeHintType.MARGIN, 0);
        }
    };


    // 生成带logo的二维码图片
    public static void drawLogoQRCode(File logoFile, File codeFile, String qrUrl,int qrCodeWidth,int qrCodeHeight ) {
        try {
            if (qrCodeWidth!=0){
                WIDTH=qrCodeWidth;
            }
            if (qrCodeHeight!=0){
                HEIGHT=qrCodeHeight;
            }
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm = multiFormatWriter.encode(qrUrl, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }

            int width = image.getWidth();
            int height = image.getHeight();
            if (Objects.nonNull(logoFile) && logoFile.exists()) {
                // 构建绘图对象
                Graphics2D g = image.createGraphics();
                // 读取Logo图片
                BufferedImage logo = ImageIO.read(logoFile);
                // 开始绘制logo图片
                g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
                g.dispose();
                logo.flush();
            }

            image.flush();

            ImageIO.write(image, "png", codeFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 合并图片
     * @param bigPath
     * @param smallPath
     * @param outPath 合成输出地址
     * @param x
     * @param y
     * @throws IOException
     */
    public static void mergeImage(String bigPath, String outPath,String smallPath, String x, String y) throws IOException {

        try {
            BufferedImage small;
            BufferedImage big = ImageIO.read(new File(bigPath));
            if (smallPath.contains("http")) {

                URL url = new URL(smallPath);
                small = ImageIO.read(url);
            } else {
                small = ImageIO.read(new File(smallPath));
            }

            Graphics2D g = big.createGraphics();

//            float fx = Float.parseFloat(x);
//            float fy = Float.parseFloat(y);
//            int x_i = (int) fx;
//            int y_i = (int) fy;
            int x_i = big.getWidth() / 2;
            int y_i = big.getHeight() / 2;
            g.drawImage(small, x_i - small.getWidth() / 2, y_i - small.getHeight() / 2, small.getWidth(), small.getHeight(), null);
            g.dispose();
            ImageIO.write(big, "png", new File(outPath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws WriterException {
        File logoFile = new File("C:\\Users\\liubing\\Desktop\\qr_logo.png");
        File QrCodeFile = new File("C:\\Users\\liubing\\Desktop\\3.jpg");
        String url = "https://www.baidu.com/";
        String note = "访问百度连接";
        drawLogoQRCode(logoFile, QrCodeFile, url,0,0);

        try {
            mergeImage("C:\\Users\\liubing\\Desktop\\qr_background.png", "","C:\\Users\\liubing\\Desktop\\3.jpg", "0", "0");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpServletRequest request=new Request();

        String ctxPath = request.getSession().getServletContext().getRealPath(File.separator)+ File.separator + "resources" + File.separator + File.separator + "template" + File.separator;
        System.out.println(ctxPath);

    }
}