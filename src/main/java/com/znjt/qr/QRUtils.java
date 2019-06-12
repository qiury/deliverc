package com.znjt.qr;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.HybridBinarizer;
import org.apache.ibatis.io.Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created on 2019-05-29 22:07
 * ClassName:QRUtils
 * Package:com.znjt.qr
 * Company FREEDOM
 * Description:
 * Depart Tech
 *
 * @author qiury_zhenxin@126.com
 */
public class QRUtils {
    public static void main(String[] args) throws IOException, NotFoundException, FormatException, ChecksumException {
        File resourceAsStream = Resources.getResourceAsFile("qr.png");
        BufferedImage img = ImageIO.read(resourceAsStream);
        decoderQRImage(img);
    }
    public static void decoderQRImage(BufferedImage bi) throws NotFoundException, FormatException, ChecksumException {
        LuminanceSource source = new BufferedImageLuminanceSource(bi);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        DetectorResult result;

        // 解码设置编码方式为：utf-8，
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        //result = new MultiFormatReader().decode(bitmap, hints);
        result = QRDecoder.decodeQR(bitmap,hints);
        ResultPoint[] points = result.getPoints();
    }
}
