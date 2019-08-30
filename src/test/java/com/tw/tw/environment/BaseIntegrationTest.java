package com.tw.tw.environment;

import lombok.Getter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * Base class for simplify and make more meaningful tests inside the module.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest extends BaseTransactionalTest {
    @Getter
    @LocalServerPort
    private Long port;
}
