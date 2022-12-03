package com.github.controller;

import com.github.util.CompressUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.github.constant.DbConstant.FILE_NAME;
import static com.github.constant.DbConstant.PATH;

/**
 * 备份指定数据
 */
@Controller
@RequestMapping()
public class ExportDataController {

    @Value("${config.osxDir}")
    private String osXDir;
    @Value("${config.linuxDir}")
    private String linuxDir;
    @Value("${config.windowsDir}")
    private String windowsDir;

    private static Connection conn = null;
    private static Statement sm = null;

    private static String insert = "INSERT INTO";//插入sql
    private static String values = "VALUES";//values关键字
    private static List<String> insertList = new ArrayList<String>();//全局存放insertsql文件的数据

    @GetMapping("index")
    public String page() {
        return "index";
    }

    @PostMapping("exportSQLPage")
    public void test(String host, String dbName,
                       String username,
                       String password,
                       String tableName,
                       String where,
                       @RequestParam(value = "port", defaultValue = "3306") String port,
                       HttpServletResponse response) {

        List<String> listSQL = new ArrayList<String>();
        connectSQL("com.mysql.jdbc.Driver",
                "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC",
                username, password);//连接数据库
        listSQL = createSQL("select * from " + tableName +" "+ where);//创建查询语句
        try {
            String[] tableNames = {tableName};
            executeSQL(conn, sm, listSQL, tableNames, dbName);//执行sql并拼装
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String file = createFile();//创建文件
        String zipFile = null;
        try {
            zipFile = CompressUtil.zipFile(new File(file), "zip");
            response.setContentType("APPLICATION/OCTET-STREAM");
            String fileName = "zip-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".zip";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream out = response.getOutputStream();
            File ftp = ResourceUtils.getFile(zipFile);
            InputStream in = new FileInputStream(ftp);

            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }

            File zip = new File(zipFile);
            if (zip.exists()) {
                if (zip.isFile()) {
                    zip.delete();
                }
            }
            File desPath1 = new File(file);
            if (desPath1.exists()) {
                if (desPath1.isFile()) {
                    desPath1.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            insertList.clear();
        }
    }

    @GetMapping("exportSQL")
    @ResponseBody
    public String exportSQL(String host, String dbName,
                       String username,
                       String password,
                       String tableName,
                       String where,
                       @RequestParam(value = "port", defaultValue = "3306") String port) {

        List<String> listSQL = new ArrayList<String>();
        connectSQL("com.mysql.jdbc.Driver",
                "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useSSL=false&serverTimezone=UTC",
                username, password);//连接数据库
        listSQL = createSQL("select * from " + tableName +" "+ where);//创建查询语句
        try {
            String[] tableNames = {tableName};
            executeSQL(conn, sm, listSQL, tableNames, dbName);//执行sql并拼装
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createFile();//创建文件
        insertList.clear();
        return "导出数据成功！";
    }

    /**
     * 创建insertsql.txt并导出数据
     */
    public String createFile() {
        String path = "";
        String property = System.getProperty("os.name");
        if (property.equals("Mac OS X")) {
            path = osXDir;
        } else if (property.toLowerCase().indexOf("win") > 0) {
            path = windowsDir;
        } else {
            path = linuxDir;
        }

        String filePath = path + FILE_NAME;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("创建文件名失败！！");
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            if (insertList.size() > 0) {
                for (int i = 0; i < insertList.size(); i++) {
                    bw.append(insertList.get(i));
                    bw.append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }

    /**
     * 拼装查询语句
     *
     * @return返回 select集合
     */
    public static List<String> createSQL(String sql) {
        List<String> listSQL = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();
        sb.append(sql);
        listSQL.add(sb.toString());
//        for (int i = 0; i < table.length; i++) {
//            StringBuffer sb = new StringBuffer();
//
//            sb.append("select * from t_dept where did in ('1','3')");
//            sb.append(select).append(" ").append(schema).append(".").append(table[i]);
//            if (term != null) {
//                sb.append(" ").append(term);
//            }
//            listSQL.add(sb.toString());
//        }
        return listSQL;
    }

    /**
     * 连接数据库创建statement对象
     * *@paramdriver
     * *@paramurl
     * *@paramUserName
     * *@paramPassword
     */
    public static void connectSQL(String driver, String url, String UserName, String Password) {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, UserName, Password);
            sm = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行sql并返回插入sql
     *
     * @paramconn
     * @paramsm
     * @paramlistSQL *
     * @throwsSQLException
     */
    public static void executeSQL(Connection conn, Statement sm, List listSQL, String[] tableName, String dbName) throws SQLException {
        List<String> insertSQL = new ArrayList<String>();
        ResultSet rs = null;
        try {
            rs = getColumnNameAndColumeValue(sm, listSQL, rs, tableName, dbName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            sm.close();
            conn.close();
        }
    }

    /**
     * 获取列名和列值
     *
     * @return
     * @paramsm
     * @paramlistSQL
     * @paramrs
     * @throwsSQLException
     */
    public static ResultSet getColumnNameAndColumeValue(Statement sm, List listSQL, ResultSet rs, String[] tableName, String dbName) throws SQLException {
        if (listSQL.size() > 0) {
            for (int j = 0; j < listSQL.size(); j++) {
                String sql = String.valueOf(listSQL.get(j));
                rs = sm.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()) {
                    StringBuffer ColumnName = new StringBuffer();
                    StringBuffer ColumnValue = new StringBuffer();
                    for (int i = 1; i <= columnCount; i++) {
                        String value = null;
                        if (rs.getString(i) != null) {
                            value = rs.getString(i).trim();
                        }
                        if (null != value && "".equals(value)) {
                            value = "";
                        }
                        if (i == 1 || i == columnCount) {
                            if (i == columnCount) {
                                ColumnName.append(",");
                            }
                            ColumnName.append(rsmd.getColumnName(i));
                            if (i == 1) {
                                if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {
                                    ColumnValue.append("'").append(value).append("',");
                                } else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i) || Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i) || Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
                                    ColumnValue.append(value).append(",");
                                } else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
                                    ColumnValue.append("'").append(value).append("',");
                                } else {
                                    ColumnValue.append(value).append(",");

                                }
                            } else {
                                if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {
                                    ColumnValue.append("'").append(value).append("',");
                                } else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i) || Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i) || Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
                                    ColumnValue.append(value);
                                } else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
                                    ColumnValue.append("'").append(value).append("',");
                                } else {
                                    ColumnValue.append(value);

                                }
                            }

                        } else {
                            ColumnName.append("," + rsmd.getColumnName(i));
                            if (Types.CHAR == rsmd.getColumnType(i) || Types.VARCHAR == rsmd.getColumnType(i) || Types.LONGVARCHAR == rsmd.getColumnType(i)) {
                                if (null == value) {
                                    ColumnValue.append(value).append(",");
                                } else {
                                    ColumnValue.append("'").append(value).append("'").append(",");
                                }
                            } else if (Types.SMALLINT == rsmd.getColumnType(i) || Types.INTEGER == rsmd.getColumnType(i) || Types.BIGINT == rsmd.getColumnType(i) || Types.FLOAT == rsmd.getColumnType(i) || Types.DOUBLE == rsmd.getColumnType(i) || Types.NUMERIC == rsmd.getColumnType(i) || Types.DECIMAL == rsmd.getColumnType(i) || Types.TINYINT == rsmd.getColumnType(i)) {
                                ColumnValue.append(value).append(",");
                            } else if (Types.DATE == rsmd.getColumnType(i) || Types.TIME == rsmd.getColumnType(i) || Types.TIMESTAMP == rsmd.getColumnType(i)) {
                                ColumnValue.append("'").append(value).append("',");
                            } else {
                                ColumnValue.append(value).append(",");
                            }
                        }
                    }
                    System.out.println(ColumnName.toString());
                    System.out.println(ColumnValue.toString());
                    insertSQL(ColumnName, ColumnValue, tableName[j], dbName);
                }
            }
        }
        return rs;
    }

    /**
     * 拼装insertsql放到全局list里面
     *
     * @paramColumnName
     * @paramColumnValue
     */
    public static void insertSQL(StringBuffer ColumnName, StringBuffer ColumnValue, String tableName, String dbName) {
        StringBuffer insertSQL = new StringBuffer();
        insertSQL.append(insert).append(" ").append(dbName).append(".")
                .append(tableName).append("(").append(ColumnName.toString()).append(")").append(values).append("(").append(ColumnValue.toString()).append(");");
        insertList.add(insertSQL.toString());
        System.out.println(insertSQL.toString());

    }

}
