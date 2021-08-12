package com.huseyin.youcontribute.service;

import com.huseyin.youcontribute.models.Repository;
import com.huseyin.youcontribute.repositories.RepositoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class RepositoryServiceTest {

    private RepositoryRepository repositoryRepository;

    private RepositoryService repositoryService;

    @BeforeAll
    public void setUp(){
        repositoryRepository = mock(RepositoryRepository.class);
        repositoryService = new RepositoryService(repositoryRepository);
    }

    @Test
    public void it_should_list_all_repository() {
        Repository repository1 = Repository.builder().repository("repo1").organization("org1").build();
        Repository repository2 = Repository.builder().repository("repo2").organization("org2").build();
        List<Repository> repos = List.of(repository1, repository2);
        when(repositoryRepository.findAll()).thenReturn(repos);

        List<Repository> repositories = repositoryService.list();

        assertEquals(repositories, repos);
        verify(repositoryRepository).findAll();
    }

    @Test
    public void it_should_create() {
        String organization = "org1";
        String repository = "repo1";
        Repository requestObject = Repository.builder().organization(organization).repository(repository).build();
        Repository storedValue = Repository.builder().organization(organization).repository(repository).id(100).build();
        when(repositoryRepository.save(requestObject)).thenReturn(storedValue);

        repositoryService.create(organization, repository);

        verify(repositoryRepository).save(requestObject);
    }
}

