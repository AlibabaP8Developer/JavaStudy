package cn.itcast.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static cn.itcast.hotel.constants.HotelConstants.MAPPING_TEMPLATE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelIndexTest {
    @Autowired
    private RestHighLevelClient client;

    @BeforeEach
    public void testInit() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://139.198.181.54:9200")
        ));
    }

    /**
     * 创建索引库
     * @throws IOException
     */
    @Test
    public void testCreateHotelIndex() throws IOException {
        // 1.创建request对象
        CreateIndexRequest request = new CreateIndexRequest("heima");
        // 2.准备请求的参数：dsl语句
        request.source(MAPPING_TEMPLATE, XContentType.JSON);
        // 3.发送请求
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引库
     * @throws IOException
     */
    @Test
    public void testDeleteHotelIndex() throws IOException {
        // 1.创建request对象
        DeleteIndexRequest request = new DeleteIndexRequest("heima");
        // 3.发送请求
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    /**
     * 判断索引库是否存在
     * @throws IOException
     */
    @Test
    public void testExistsHotelIndex() throws IOException {
        // 1.创建request对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        // 3.发送请求
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists ? "索引库已经存在" : "索引库不存在");
    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }

}
