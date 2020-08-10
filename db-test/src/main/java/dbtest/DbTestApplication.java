package dbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wqy
 * @version 1.0
 * @date 2020/8/10 17:17
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DbTestApplication {

    public static void main(String[] args){
        SpringApplication.run(DbTestApplication.class,args);
    }


}
