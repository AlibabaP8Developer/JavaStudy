package cn.itcast.hotel;

import cn.hutool.json.JSONUtil;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelDocumentTest {
    @Autowired
    private IHotelService hotelService;

    @Autowired
    private RestHighLevelClient client;

    @BeforeEach
    public void testInit() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://139.198.181.54:9200")
        ));
    }

    /**
     * 新增文档
     *
     * @throws IOException
     */
    @Test
    public void testAddDocuemt() throws IOException {
        Hotel hotel = hotelService.getById(60214L);
        // 转为文档类型
        HotelDoc hotelDoc = new HotelDoc(hotel);
        // 1.准备request对象
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());

        // 2.准备json对象
        request.source(JSONUtil.toJsonStr(hotelDoc), XContentType.JSON);
        // 3.发送请求
        client.index(request, RequestOptions.DEFAULT);
    }

    /**
     * 查询文档
     *
     * @throws IOException
     */
    @Test
    public void testGetDocuemtById() throws IOException {
        // 1.准备request
        GetRequest request = new GetRequest("hotel", "60214");
        // 2.发送请求
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 3.解析响应结果
        String json = response.getSourceAsString();
        HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
        System.out.println("查询文档结果：" + hotelDoc);
    }

    /**
     * 修改文档
     *
     * @throws IOException
     */
    @Test
    public void testUpdateDocuemtById() throws IOException {
        // 1.准备request
        UpdateRequest request = new UpdateRequest("hotel", "60214");
        // 2.准备请求参数
        request.doc(
                "price", "998",
                "city", "金陵"
        );

        // 3.发送请求
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        int status = response.status().getStatus();
        System.out.println("更新文档：" + status);
    }

    /**
     * 删除文档
     *
     * @throws IOException
     */
    @Test
    public void testDeleteDocuemtById() throws IOException {
        // 1.准备request
        DeleteRequest request = new DeleteRequest("hotel", "60214");
        // 2.发送请求
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        int status = response.status().getStatus();
        System.out.println("删除文档：" + status);
    }


    /**
     * 批量新增文档
     *
     * @throws IOException
     */
    @Test
    public void testBulkRequest() throws IOException {
        List<Hotel> list = hotelService.list();
        // 1.准备request
        BulkRequest request = new BulkRequest();
        // 2.准备参数，添加多个新增的request
        for (Hotel hotel : list) {
            // 转为文档类型
            HotelDoc hotelDoc = new HotelDoc(hotel);
            String jsonStr = JSONUtil.toJsonStr(hotelDoc);
            // 创建新增文档的request对象
            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source(jsonStr, XContentType.JSON));
        }
        // 3.发送请求
        client.bulk(request, RequestOptions.DEFAULT);
    }

    @AfterEach
    public void tearDown() throws IOException {
        this.client.close();
    }

}
