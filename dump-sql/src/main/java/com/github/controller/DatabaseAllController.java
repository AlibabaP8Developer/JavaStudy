package com.github.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.constant.DbConstant.*;
import static com.github.constant.DbConstant.DATABASE_NAME;

/**
 * 全库备份
 * 自动定时备份
 */
@RestController
@RequestMapping("")
public class DatabaseAllController extends TimerTask {

    @GetMapping("test")
    public String test() {
        try {
            var path = "";
            var property = System.getProperty("os.name");
            if (property.equals("Mac OS X")) {
                path = MAC_PATH;
            } else if (property.toLowerCase().indexOf("win") > 0) {
                path = WINDOWS_PATH;
            } else {
                path = LINUX_PATH;
            }
            DatabaseAllController.exportDatabaseTool(HOST, USERNAME, PASSWORD, path, FILE_NAME_ALL, DATABASE_NAME);
            return "导出数据库成功";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "导出数据库失败";
        }
    }

    /**
     * 数据库备份
     */
    public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath,
                                             String fileName, String databaseName) throws InterruptedException {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            StringBuilder stringBuilder = new StringBuilder();

            var system = "";
            var property = System.getProperty("os.name");
            if ("Mac OS X".equals(property)) {
                system = "/usr/local/mysql/bin/mysqldump";
            } else {
                system = "mysqldump";
            }
            stringBuilder.append(system).append(" --opt").append(" -h").append(hostIP);
            stringBuilder.append(" --user=").append(userName).append(" --password=").append(password)
                    .append(" --lock-all-tables=true");
            stringBuilder.append(" --result-file=").append(savePath + fileName).append(" --default-character-set=utf8 ")
                    .append(databaseName);

            if (StringUtils.isNotBlank(TABLE_NAME)) {
                stringBuilder.append(" --tables ");
                String[] tableName = TABLE_NAME.split(",");
                for (int i = 0; i < tableName.length; i++) {
                    stringBuilder.append(tableName[i]);
                    stringBuilder.append(" ");
                }
            }

            System.out.println("stringBuilder.append(--tables)" + stringBuilder.toString());
            Process process = Runtime.getRuntime().exec(stringBuilder.toString());

            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();

            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                System.out.println("导出数据库完成...");
                return true;
            }
            System.out.println("导出数据库完成...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 数据库恢复
     */
    public static void recover(String path) throws IOException {
        String system = "";
        String property = System.getProperty("os.name");
        if ("Mac OS X".equals(property)) {
            system = "/usr/local/mysql/bin/mysql";
        } else {
            system = "mysql";
        }

        Runtime runtime = Runtime.getRuntime();
        // 恢复 到数据库的账户信息
//        Process process = runtime.exec("mysql -h 192.168.25.129 -u root -p123456 --default-character-set=utf8 my");
        Process process = runtime.exec(system + " -h" + HOST + " -u" + USERNAME + " -p" + PASSWORD + " --default-character-set=utf8 " + DATABASE_NAME + "");
        OutputStream outputStream = process.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str = null;
        StringBuffer sb = new StringBuffer();
        while ((str = br.readLine()) != null) {
            sb.append(str + "\r\n");
        }
        str = sb.toString();
        System.out.println(str);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8");
        writer.write(str);
        writer.flush();
        outputStream.close();
        br.close();
        writer.close();
    }

    /**
     * 定时方法
     */
    public void run() {
        Timer timer = new Timer(true);
        timer.schedule(

                new java.util.TimerTask() {
                    public void run() {
                        try {
                            databaseBackup();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }, 0, 60 * 60 * 1000);
        // 1 * 60 * 1000
    }

    /**
     * 数据库备份恢复操作
     */
    public void databaseBackup() throws IOException {
        try {
            var path = "";
            var property = System.getProperty("os.name");
            if (property.equals("Mac OS X")) {
                path = MAC_PATH;
            } else if (property.toLowerCase().indexOf("win") > 0) {
                path = WINDOWS_PATH;
            } else {
                path = LINUX_PATH;
            }
            // 备份
            exportDatabaseTool(HOST, USERNAME, PASSWORD, path, FILE_NAME_ALL, DATABASE_NAME);
            // 恢复
//            recover("D:/backupDatabase/qinmei.sql");
            recover(path + FILE_NAME_ALL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}