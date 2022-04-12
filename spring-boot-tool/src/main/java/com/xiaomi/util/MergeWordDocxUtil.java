package com.xiaomi.util;

import java.io.File;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

public class MergeWordDocxUtil {

    public static boolean merge(String docPath, String desPath) {
        File[] files = getSplitFiles(docPath);
        if (files == null || files.length == 0) {
            return false;
        }

        System.out.println(docPath);
        Document document = new Document(docPath + "test0.docx");


        for (int i = 1; i < files.length; i++) {
            document.insertTextFromFile(docPath + "test" + i + ".docx", FileFormat.Docx_2013);
        }
        //第四步：对合并的doc进行保存
        document.saveToFile(desPath);
        return true;
    }

    // 取得某一路径下所有的pdf
    private static File[] getSplitFiles(String path) {
        File f = new File(path);
        File[] fs = f.listFiles();
        if (fs == null) {
            return null;
        }
        return fs;
    }

}