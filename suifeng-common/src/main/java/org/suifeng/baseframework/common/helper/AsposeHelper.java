package org.suifeng.baseframework.common.helper;


import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AsposeHelper {

    private static InputStream license;

    /**
     * 由于使用了破解版的Aspose,需要破解，否者有水印
     * @return true,破解成功；fasle,破解失败
     */
    public static boolean isCrackSuccess() {
        boolean result = false;
        try {
            license = Thread.currentThread().getContextClassLoader().getResourceAsStream("aspose_license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用Aspose转换
     * @param sourcePath 待转换文件路径
     * @param targetPath 目标文件路径
     * @param saveFormatName 保存文件格式
     * @throws Exception
     */
    public static void convert(String sourcePath, String targetPath, String saveFormatName) throws Exception {
        if (!isCrackSuccess()) {
            throw new Exception("Aspose破解失败");
        }
        File parent = new File(targetPath).getParentFile();
        // 如果pdf保存路径不存在，则创建路径
        if(!parent.exists()) {
            parent.mkdirs();
        }
        long beginTime = System.currentTimeMillis();
        // 新建一个空白pdf文档
        File file = new File(targetPath);
        FileOutputStream os = new FileOutputStream(file);
        Document doc = new Document(sourcePath);
        // 全面支持DOC, DOCX, OOXML, RTF HTML,OpenDocument, PDF, EPUB, XPS, SWF 相互转换
        doc.save(os, SaveFormat.fromName(saveFormatName));
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时：" + ((endTime - beginTime) / 1000.0) + "秒");
    }

}
