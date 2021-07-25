package cn.pys;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class QueryDoc {
    RestHighLevelClient esClient = null;
    SearchRequest request = null;

    @Before
    public void before() {
        esClient = new RestHighLevelClient(RestClient
                .builder(new HttpHost("localhost", 9200, "http")));
        request = new SearchRequest();
        request.indices("student");
    }

    @Test
    public void queryALL() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
    }

    @Test
    public void termQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("age", 30));
        request.source(builder);
    }

    @Test
    public void termManyQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termsQuery("name","zhangsan","lisi"));
        request.source(builder);
    }

    @Test
    public void pageQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        builder.from(0);
        builder.size(2);
        request.source(builder);
    }

    @Test
    public void filterQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());

        String[] includes = new String[]{"name", "age"};
        String[] excludes = new String[]{};
        builder.fetchSource(includes, excludes);
        request.source(builder);
    }

    @Test
    public void boolQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name","zhangsan"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("age",30));
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("age",40));
        boolQueryBuilder.should(QueryBuilders.matchQuery("sex","男"));

        builder.query(boolQueryBuilder);
        request.source(builder);
    }

    @Test
    public void rangeQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.rangeQuery("age").gte(30).lte(35));
        request.source(builder);
    }

    @Test
    public void fuzzyQuery() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.fuzzyQuery("name","zhangsan").fuzziness(Fuzziness.ONE));
        request.source(builder);
    }


    @Test
    public void highLight() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("name", "zhangsan"));

        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");
        builder.highlighter(highlightBuilder);
        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            //打印高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields);
        }

    }

    @Test
    public void aggsQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.aggregation(AggregationBuilders.stats("age_stats").field("age"));
        builder.size(0);
        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    public void groupByQuery() throws IOException {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("age_groupBy")
                // 可以在这里添加子聚合
                .field("age").subAggregation(AggregationBuilders.sum("sum_age").field("age"));
        builder.aggregation(aggregationBuilder);
        builder.size(0);
        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }
    @After
    public void after() throws IOException {
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        esClient.close();
    }
}
