import com.atsanstwy27.springcloud.GatewayRateLimiter9527;
import com.atsanstwy27.springcloud.entities.Payment;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MockServerContainer;

import static org.mockserver.model.HttpResponse.response;

/**
 * @author Sanstwy27
 * @create 8/15/2020
 * @ref https://piotrminkowski.com/2019/11/15/rate-limiting-in-spring-cloud-gateway-with-redis/
 */
@SpringBootTest(classes = GatewayRateLimiter9527.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class GatewayRateLimiterTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayRateLimiterTest.class);

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @ClassRule
    public static MockServerContainer mockServer = new MockServerContainer();
    @ClassRule
    public static GenericContainer redis = new GenericContainer("redis:latest").withExposedPorts(6379);

    @Autowired
    TestRestTemplate template;

    @BeforeClass
    public static void init() {
        System.setProperty("spring.cloud.gateway.routes[0].id", "account-service");
        //System.setProperty("spring.cloud.gateway.routes[0].uri", "http://localhost:" + mockServer.getServerPort());
        System.setProperty("spring.cloud.gateway.routes[0].uri", mockServer.getEndpoint());
        System.setProperty("spring.cloud.gateway.routes[0].predicates[0]", "Path=/payment/get/**");
        //System.setProperty("spring.cloud.gateway.routes[0].filters[0]", "RewritePath=/account/(?<path>.*), /$\\{path}");
        System.setProperty("spring.cloud.gateway.routes[0].filters[0].name", "RequestRateLimiter");
        System.setProperty("spring.cloud.gateway.routes[0].filters[0].args.redis-rate-limiter.replenishRate", "10");
        System.setProperty("spring.cloud.gateway.routes[0].filters[0].args.redis-rate-limiter.burstCapacity", "20");
        System.setProperty("spring.redis.host", "localhost");
        System.setProperty("spring.redis.port", "" + redis.getMappedPort(6379));
        new MockServerClient(mockServer.getContainerIpAddress(), mockServer.getServerPort())
                .when(HttpRequest.request()
                        .withPath("/payment/get/2"))
                .respond(response()
                        .withBody("{\"id\":2,\"serial\":\"1234567890\"}")
                        .withHeader("Content-Type", "application/json"));
    }

    @Test
    @BenchmarkOptions(warmupRounds = 0, concurrency = 6, benchmarkRounds = 600)
    public void testAccountService() {
        ResponseEntity<Payment> r = template.exchange("/payment/get/{id}", HttpMethod.GET, null, Payment.class, 2);
        LOGGER.info("Received: status->{}, payload->{}, remaining->{}", r.getStatusCodeValue(), r.getBody(), r.getHeaders().get("X-RateLimit-Remaining"));
    }
}
