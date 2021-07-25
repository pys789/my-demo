package cn.pys;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class IndexTest {
    RestHighLevelClient esClient = null;

    @Before
    public void before() {
        esClient = new RestHighLevelClient(RestClient
                .builder(new HttpHost("localhost", 9200, "http")));
    }

    @Test
    public void createIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("user2");
        CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("操作状态:" + response.isAcknowledged());
    }

    @Test
    public void getIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("user");
        GetIndexResponse response = esClient.indices().get(request, RequestOptions.DEFAULT);
        System.out.println("mappings:" + response.getMappings());
        System.out.println("settings:" + response.getSettings());
        System.out.println("aliases:" + response.getAliases());
    }

    @Test
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("user2");
        AcknowledgedResponse response = esClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("操作结果:" + response.isAcknowledged());
    }

    @After
    public void after() throws IOException {
        esClient.close();
    }


}
