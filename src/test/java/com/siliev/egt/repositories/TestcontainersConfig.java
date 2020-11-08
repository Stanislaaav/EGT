package com.siliev.egt.repositories;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class TestcontainersConfig {

    // use @Container when you want diferent container for every method
//    @Container
//    public static PostgreSQLContainer container = new PostgreSQLContainer()
//        .withUsername("stan")
//        .withPassword("pass")
//        .withDatabaseName("test");
//
//    @DynamicPropertySource
//    static void properties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.password", container::getPassword);
//        registry.add("spring.datasource.username", container::getUsername);
//    }

    // use when you want to share container
    static final PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:10-alpine")
            .withDatabaseName("test")
            .withUsername("duke")
            .withPassword("s3cret")
            .withReuse(true);

        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }
}
