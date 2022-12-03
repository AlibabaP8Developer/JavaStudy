package com.github;

import com.github.controller.DatabaseAllController;
import org.junit.Test;

import static com.github.constant.DbConstant.*;

public class DbTest {

    @Test
    public void test() {
        try {
            new DatabaseAllController().exportDatabaseTool(HOST, USERNAME, PASSWORD, PATH, FILE_NAME, DATABASE_NAME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
