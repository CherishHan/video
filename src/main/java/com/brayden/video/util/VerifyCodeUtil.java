package com.brayden.video.util;

import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class VerifyCodeUtil {

    private static Random random = new Random();

    /**
     *
     * @param showNum 展示多少个文字
     * @param checkNum 验证几个文字
     * @param height 图像高度
     * @param width  图像宽度
     * @param fontSize  文字大小
     * @param fontColor 文字颜色
     * @param defaultBackGroundImg  背景图片路径
     * @return
     */
    public static HashMap<String,Object> createImg(int showNum, int checkNum, int height, int width, int fontSize, Color fontColor, String defaultBackGroundImg){
        HashMap<String,Object> hashMap=new HashMap<>();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        // 读取本地图片，做背景图片
        //  URL resource = getClass().getResource("/system/login/img/" + (random.nextInt(4) + 1) + ".png");
       // URL resource = VerifyCodeUtil.class.getResource(defaultBackGroundImg);
        try {
            g.drawImage(ImageIO.read(ResourceUtils.getFile(defaultBackGroundImg)), 0, fontSize, width, height, null); //将背景图片从高度30开始
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.setColor(Color.WHITE);  //设置颜色
        g.drawRect(0, 0, width - 1, height - 1); //画边框

        g.setFont(new Font("宋体", Font.BOLD, fontSize)); //设置字体
        String[] target = new String[checkNum]; // 用于记录文字
        StringBuilder correctCheckCode=new StringBuilder(16);
        for (int i = 0; i < showNum; i++) {  //随机产生8个文字，坐标，颜色都不同
            g.setColor(VerifyCodeUtil.getRandColor(100, 160));
            String str = VerifyCodeUtil.getRandomChineseChar();
            int tempWidth=random.nextInt(width);
            int tempHeight=random.nextInt(height);
            tempWidth = tempWidth>width-fontSize*3?tempWidth-fontSize*3:tempWidth+fontSize;
            tempHeight = tempHeight>height-fontSize*3?(int)(tempHeight-fontSize*1.5):(int)(tempHeight+fontSize*1.5);
            if (i<checkNum) {
                target[i] = str; //记录文字
                correctCheckCode.append(",[").append(tempWidth).append(",").append(tempHeight).append("]");
            }
            g.drawString(str, tempWidth, tempHeight);

        }
        g.setColor(fontColor);
        //设置字体类型、字体大小、字体样式　
        g.drawString("请按顺序点击:" + Arrays.toString(target).replaceAll("[\\[\\]\\s]",""), 0, fontSize);
        //redisTemplate.opsForValue().set("XXX:" + MD5Utils.encrypt(id), x + ":" + y, 5, TimeUnit.MINUTES);
        //5.释放资源
        g.dispose();
        hashMap.put("BufferedImage",image);
        System.out.println(correctCheckCode);
        hashMap.put("correctCheckCode","["+correctCheckCode.substring(1)+"]");
        return hashMap;
    }

    /**
     *
     * @param correctCheckCode 正确的坐标码
     * @param imgCheckInfo 前端传到后台的坐标码
     * @return
     */
//    public static Boolean checkImg(String correctCheckCode, String imgCheckInfo) {
//        Boolean result=true;
//        if (StringUtils.isEmpty(imgCheckInfo)) {
//            return false;
//        }
//        JSONArray imgCheckInfos=JSONArray.parseArray(imgCheckInfo);
//        JSONArray correctCheckCodes=JSONArray.parseArray(correctCheckCode);
//        if (imgCheckInfos.size()!=3) {
//            return false;
//        }
//        for (int i=0;i<imgCheckInfos.size()&&result;i++){
//            Integer[] correctCheck=correctCheckCodes.getObject(i,Integer[].class);
//            Integer[] imgCheck=imgCheckInfos.getObject(i,Integer[].class);
//            int cx=correctCheck[0];
//            int cy=correctCheck[1];
//            int x=imgCheck[0];
//            int y=imgCheck[1];
//            result=cx- 22 < x && x < cx + 22 && cy - 22 < y && y < cy + 22;
//        }
//        return result;
//    }

    /**
     * 随机产生颜色
     */
    public static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 随机产生汉字
     */
    public static String getRandomChineseChar() {
        String str = null;
        int heightPos; // 定义高低位
        int lowPos;
        Random random = new Random();
        heightPos = (176 + Math.abs(random.nextInt(39))); //获取高位值
        lowPos = (161 + Math.abs(random.nextInt(93))); //获取低位值
        byte[] b = new byte[2];
        b[0] = (new Integer(heightPos).byteValue());
        b[1] = (new Integer(lowPos).byteValue());
        try {
            str = new String(b, "GBK"); //转成中文
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());;
        }
        return str;
    }

}
