package cn.itcast.hotel.service.impl;

import cn.itcast.hotel.mapper.HotelMapper;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.pojo.PageResult;
import cn.itcast.hotel.pojo.RequestParams;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public void insertById(Long id) {
        // 根据ID查询酒店数据
        Hotel hotel = getById(id);
        HotelDoc hotelDoc = new HotelDoc(hotel);
        // 1.准备request
        IndexRequest request = new IndexRequest("hotel").id(hotel.getId().toString());
        // 2.DSL
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        // 3.准备发送请求
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        // 1.准备request
        DeleteRequest request = new DeleteRequest("hotel", id.toString());
        // 2.准备发送请求
        try {
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PageResult search(RequestParams params) {
        try {
            // try...catch: ctrl+alt+t
            // 1.准备request
            SearchRequest request = new SearchRequest("hotel");
            // 2.准备dsl
            // 构建query
            buildBasicQuery(params, request);
            // 分页
            int page = params.getPage();
            int size = params.getSize();
            request.source().from((page - 1) * size).size(size);

            // 排序  附近的酒店 距离
            String location = params.getLocation();
            if (StringUtils.isNotBlank(location)) {
                GeoPoint geoPoint = new GeoPoint(location);
                request.source().sort(SortBuilders
                        .geoDistanceSort("location", geoPoint)
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.KILOMETERS));
            }

            // 3.发送请求，得到响应
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4.解析响应
            return handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return new PageResult();
        }
    }

    /**
     * 条件查询
     *
     * @param params  城市、星级、价格等，分页
     * @param request
     */
    private void buildBasicQuery(RequestParams params, SearchRequest request) {
        // 构建booleanQuery
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        // 关键字搜索must
        String key = params.getKey();
        if (StringUtils.isBlank(key)) {
            boolQuery.must(QueryBuilders.matchAllQuery());
        } else {
            boolQuery.must(QueryBuilders.matchQuery("all", key));
        }

        // 条件过滤 city, star, price...
        String city = params.getCity();
        if (StringUtils.isNotBlank(city)) {
            boolQuery.filter(QueryBuilders.termQuery("city", city)); // filter不参与算分
        }
        // 品牌
        String brand = params.getBrand();
        if (StringUtils.isNotBlank(brand)) {
            boolQuery.filter(QueryBuilders.termQuery("brand", brand)); // filter不参与算分
        }
        // 星级
        String starName = params.getStarName();
        if (StringUtils.isNotBlank(starName)) {
            boolQuery.filter(QueryBuilders.termQuery("starName", starName)); // filter不参与算分
        }
        // 价格
        String minPrice = params.getMinPrice();
        String maxPrice = params.getMaxPrice();
        if (StringUtils.isNotBlank(minPrice) && StringUtils.isNotBlank(maxPrice)) {
            // gte >= , lte <=
            boolQuery.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        }

        // 算分控制
        FunctionScoreQueryBuilder functionScoreQuery =
                QueryBuilders.functionScoreQuery(
                        // 原始查询，相关性算分的查询
                        boolQuery,
                        // function score的数组
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                                // 其中的一个function score元素
                                new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                        // 过滤条件
                                        QueryBuilders.termQuery("isAD", true),
                                        // 算分函数，如 算分时*10
                                        ScoreFunctionBuilders.weightFactorFunction(10))
                });

        request.source().query(functionScoreQuery);
    }

    /**
     * 关键字搜索
     *
     * @param params 关键字，分页
     * @return
     */
    //@Override
    public PageResult searchAll(RequestParams params) {
        try {
            // try...catch: ctrl+alt+t
            // 1.准备request
            SearchRequest request = new SearchRequest("hotel");
            // 2.准备dsl
            String key = params.getKey();
            if (StringUtils.isBlank(key)) {
                request.source().query(QueryBuilders.matchAllQuery());
            } else {
                request.source().query(QueryBuilders.matchQuery("all", key));
            }
            // 分页
            int page = params.getPage();
            int size = params.getSize();
            request.source().from((page - 1) * size).size(size);
            // 3.发送请求，得到响应
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            // 4.解析响应
            return handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return new PageResult();
        }
    }

    private PageResult handleResponse(SearchResponse response) {
        // 4.解析响应
        SearchHits searchHits = response.getHits();
        // 获取总条数
        long total = searchHits.getTotalHits().value;
        System.out.println("共搜索到：" + total + "条");
        SearchHit[] hits = searchHits.getHits();
        List<HotelDoc> hotelDocs = new ArrayList<>();
        for (SearchHit hit : hits) {
            // 获取文档source
            String json = hit.getSourceAsString();
            // 反序列化
            HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
            // 获取排序值 距离值
            Object[] sortValues = hit.getSortValues();
            if (sortValues.length > 0) {
                Object sortValue = sortValues[0];
                hotelDoc.setDistance(sortValue);
            }
            hotelDocs.add(hotelDoc);
        }
        return new PageResult(total, hotelDocs);
    }
}
