package com.xiaomi.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.widget.PdfPageCollection;

public class PdfToWordUtil {
    // 涉及到的路径
    // 1、pdf所在的路径，真实测试种是从外部引入的

    // 2、如果是大文件，需要进行切分，保存的子pdf路径
    String splitPath = "./split/";

    // 3、如果是大文件，需要对子pdf文件一个一个进行转化
    String docPath = "./doc/";

    public Map<String, Object> pdftoword(String srcPath, String docPath, String splitPath) {
        this.splitPath = splitPath;
        this.docPath = docPath;
        // 4、最终生成的doc所在的目录，默认是和引入的一个地方，开源时对外提供下载的接口。
        String desPath = srcPath.substring(0, srcPath.length() - 4) + ".docx";
        boolean result = false;
        try {
            // 0、判断输入的是否是pdf文件
            //第一步：判断输入的是否合法
            boolean flag = isPDFFile(srcPath);
            //第二步：在输入的路径下新建文件夹
            boolean flag1 = create();

            if (flag && flag1) {
                // 1、加载pdf
                PdfDocument pdf = new PdfDocument();
                pdf.loadFromFile(srcPath);
                PdfPageCollection num = pdf.getPages();

                // 2、如果pdf的页数小于31，那么直接进行转化
                if (num.getCount() <= 20) {
                    pdf.saveToFile(desPath, com.spire.pdf.FileFormat.DOCX);
                }
                // 3、否则输入的页数比较多，就开始进行切分再转化
                else {
                    // 第一步：将其进行切分,每页一张pdf
                    pdf.split(splitPath + "test{0}.pdf", 0);

                    // 第二步：将切分的pdf，一个一个进行转换
                    File[] fs = getSplitFiles(splitPath);
                    for (int i = 0; i < fs.length; i++) {
                        PdfDocument sonpdf = new PdfDocument();
                        sonpdf.loadFromFile(fs[i].getAbsolutePath());
                        String test = fs[i].getName().substring(0, fs[i].getName().length() - 4) + ".docx";
                        sonpdf.saveToFile(docPath + test, FileFormat.DOCX);
                        sonpdf.close();
                    }
                    //第三步：对转化的doc文档进行合并，合并成一个大的word
                    try {
                        pdf.close();
                        result = MergeWordDocxUtil.merge(docPath, desPath);
                        System.out.println(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("输入的不是pdf文件");
                Map<String, Object> map = new HashMap<>();
                map.put("result", "输入的不是pdf文件");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4、把刚刚缓存的split和doc删除
            if (result == true) {
                this.clearFiles(splitPath);
                this.clearFiles(docPath);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        map.put("desPath", desPath);
        map.put("docPath", docPath);
        return map;
    }


    private boolean create() {
        File f = new File(splitPath);
        File f1 = new File(docPath);
        if (!f.exists()) f.mkdirs();
        if (!f.exists()) f1.mkdirs();
        return true;
    }

    // 判断是否是pdf文件
    public static boolean isPDFFile(String srcPath2) {
        File file = new File(srcPath2);
        String filename = file.getName();
        if (filename.endsWith(".pdf")) {
            return true;
        }
        return false;
    }

    // 取得某一路径下所有的pdf
    private File[] getSplitFiles(String path) {
        File f = new File(path);
        File[] fs = f.listFiles();
        if (fs == null) {
            return null;
        }
        return fs;
    }

    public void clearFiles(String workspaceRootPath) {
        File file = new File(workspaceRootPath);
        if (file.exists()) {
            deleteFile(file);
        }
    }

    public void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
        file.delete();
    }
}