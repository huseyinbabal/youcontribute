package com.huseyin.youcontribute.repositories;

import com.huseyin.youcontribute.models.Repository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(initializers = RepositoryRepositoryTestIT.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@Testcontainers
public class RepositoryRepositoryTestIT {

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7");

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_57_IMAGE);

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Test
    public void it_should_find_all_repositories() throws Exception {
        //given
        Repository repository1 = Repository.builder().repository("repo1").organization("org1").build();
        Repository repository2 = Repository.builder().repository("repo2").organization("org2").build();

        this.repositoryRepository.saveAll(Arrays.asList(repository1, repository2));
        this.testEntityManager.flush();

        //when
        List<Repository> repos = this.repositoryRepository.findAll();

        //then
        then(repos.size()).isEqualTo(2);
        Repository repo1 = repos.get(0);
        Repository repo2 = repos.get(1);
        then(repo1.getRepository()).isEqualTo("repo1");
        then(repo1.getOrganization()).isEqualTo("org1");
        then(repo2.getRepository()).isEqualTo("repo2");
        then(repo2.getOrganization()).isEqualTo("org2");
    }


    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("spring.datasource.url=" + mysql.getJdbcUrl(),
                    "spring.datasource.username=" + mysql.getUsername(),
                    "spring.datasource.password=" + mysql.getPassword())
                    .applyTo(applicationContext.getEnvironment());
        }
    }


}