package com.github;

import com.github.controller.DatabaseAllController;
import org.junit.Test;

import static com.github.constant.DbConstant.*;

public class DbTest {

    @Test
    public void test() {
        try {
            String path = "";
            String property = System.getProperty("os.name");
            if (property.equals("Mac OS X")) {
                path = MAC_PATH;
            } else if (property.toLowerCase().indexOf("win") > 0) {
                path = WINDOWS_PATH;
            } else {
                path = LINUX_PATH;
            }
            new DatabaseAllController().exportDatabaseTool(HOST, USERNAME, PASSWORD, path, FILE_NAME_ALL, DATABASE_NAME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
