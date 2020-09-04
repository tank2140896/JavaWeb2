package com.javaweb.config.barcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.javaweb.base.BaseInject;

@RestController
public class BarCodeController extends BaseInject {
	
   /**
    * 生成code128条形码
    * @param height        条形码的高度
    * @param width         条形码的宽度
    * @param message       要生成的文本
    * @param withQuietZone 是否两边留白
    * @param hideText      隐藏可读文本
    * @return              图片对应的字节码
    */
    public static byte[] generateBarCode128(String message, Double height, Double width, boolean withQuietZone, boolean hideText) {
        Code128Bean bean = new Code128Bean();
        // 分辨率
        int dpi = 512;
        // 设置两侧是否留白
        bean.doQuietZone(withQuietZone);

        // 设置条形码高度和宽度
        bean.setBarHeight((double) ObjectUtils.defaultIfNull(height, 9.0D));
        if (width != null) {
            bean.setModuleWidth(width);
        }
        // 设置文本位置（包括是否显示）
        if (hideText) {
            bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        }
        // 设置图片类型
        String format = "image/png";
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
        // 生产条形码
        bean.generateBarcode(canvas, message);
        try {
            canvas.finish();
        } catch (IOException e) {
        	//do nothing
        }
        return ous.toByteArray();
    }

    //条形码宽度
    private static final int WIDTH = 325;

    //条形码高度
    private static final int HEIGHT = 50;

    //加文字条形码
    private static final int WORDHEIGHT = 75;

    //设置条形码参数
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        private static final long serialVersionUID = 1L;
        {
            
            put(EncodeHintType.CHARACTER_SET, "utf-8");//设置编码方式
        }
    };

    //生成图片缓冲
    public static BufferedImage getBarCode(String vaNumber){
        try {
            Code128Writer writer = new Code128Writer();
            // 编码内容, 编码类型, 宽度, 高度, 设置参数
            BitMatrix bitMatrix = writer.encode(vaNumber, BarcodeFormat.CODE_128, WIDTH, HEIGHT, hints);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    //把带logo的条形码下面加上文字
    public static BufferedImage insertWords(BufferedImage image, String words){
        //新的图片，把带logo的二维码下面加上文字
        if (StringUtils.isNotEmpty(words)) {
            BufferedImage outImage = new BufferedImage(WIDTH, WORDHEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = outImage.createGraphics();
            //抗锯齿
            setGraphics2D(g2d);
            //设置白色
            setColorWhite(g2d);
            //画条形码到新的面板
            g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            //画文字到新的面板
            Color color=new Color(0, 0, 0);
            g2d.setColor(color);
            //字体、字型、字号
            g2d.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            //文字长度
            int strWidth = g2d.getFontMetrics().stringWidth(words);
            //总长度减去文字长度的一半  （居中显示）
            int wordStartX=(WIDTH - strWidth) / 2;
            //height + (outImage.getHeight() - height) / 2 + 12
            int wordStartY=HEIGHT+20;
            //画文字
            g2d.drawString(words, wordStartX, wordStartY);
            g2d.dispose();
            outImage.flush();
            return outImage;
        }
        return null;
    }

    //设置 Graphics2D属性（抗锯齿）
    private static void setGraphics2D(Graphics2D g2d){
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        Stroke s = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(s);
    }

    //设置背景为白色
    private static void setColorWhite(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        //填充整个屏幕
        g2d.fillRect(0,0,600,600);
        //设置笔刷
        g2d.setColor(Color.BLACK);
    }

    public static void main(String[] args) throws IOException {
        BufferedImage image = insertWords(getBarCode("123456asdasd789"), "这是条形码的文字说明");
        ImageIO.write(image, "jpg", new File("D://abc.jpg"));
    }
	
    @GetMapping("/getBarCode")
    public void getBarCode(String code,HttpServletResponse response) throws Exception{
        BufferedImage image = BarCodeController.insertWords(BarCodeController.getBarCode(code),code);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

}
