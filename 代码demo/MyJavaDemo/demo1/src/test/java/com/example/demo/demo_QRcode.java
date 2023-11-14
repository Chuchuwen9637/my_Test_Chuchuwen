package com.example.demo;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class demo_QRcode {
    public static void main(String[] args) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String textToEncode = "http://tomxlysplay.com.cn/#/";
        BitMatrix bitMatrix = qrCodeWriter.encode(textToEncode, BarcodeFormat.QR_CODE, 200, 200);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int color = bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB();
                image.setRGB(x, y, color);
            }
        }
        File outputFile = new File("C:\\Users\\zhou\\Desktop\\demo\\baidu.png");
        ImageIO.write(image, "png", outputFile);
    }
}
