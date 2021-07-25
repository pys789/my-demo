package cn.pys;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class DocTest {
    RestHighLevelClient esClient = null;

    @Before
    public void before() {
        esClient = new RestHighLevelClient(RestClient
                .builder(new HttpHost("localhost", 9200, "http")));
    }

    @Test
    public void addDoc() throws IOException {
        IndexRequest request = new IndexRequest("user");
        request.id("1001");

        User user = new User();
        user.setName("zhangsan");
        user.setAge(30);
        user.setSex("男");

        ObjectMapper objectMapper = new ObjectMapper();
        request.source(objectMapper.writeValueAsString(user), XContentType.JSON);
        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    @Test
    public void updateDoc() throws IOException {
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");
        request.doc(XContentType.JSON, "sex", "女");
        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    @Test
    public void getDoc() throws IOException {
        GetRequest request = new GetRequest();
        request.index("user").id("1001");
        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
    }

    @Test
    public void deleteDoc() throws IOException {
        DeleteRequest request = new DeleteRequest();
        request.index("user").id("1001");
        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }


    @Test
    public void batchAddDoc() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        for (int i = 1; i < 6; i++) {
            IndexRequest request = new IndexRequest("user").id((1000 + i) + "");
            User user = new User();
            user.setName("zhangsan" + i);
            user.setAge(30);
            user.setSex("男");
            request.source(objectMapper.writeValueAsString(user), XContentType.JSON);
            bulkRequest.add(request);
        }
        BulkResponse bulkResponse = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.getTook());
    }

    @After
    public void after() throws IOException {
        esClient.close();
    }
}
