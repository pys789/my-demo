package cn.pys;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
class QuartzDemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testGetDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}
