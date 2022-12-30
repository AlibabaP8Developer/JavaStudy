package com.github;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelIndexTest {
    @Autowired
    private RestHighLevelClient client;

    @BeforeEach
    public void testInit() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://139.198.181.54:9200/")
        ));
    }

    @Test
    public void test() throws IOException {
        // 1.创建request对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        // 2.准备请求的参数：dsl语句
        request.source("""
                
                """ , XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }

}
