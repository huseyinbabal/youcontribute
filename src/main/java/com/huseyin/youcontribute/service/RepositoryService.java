package com.huseyin.youcontribute.service;

import com.huseyin.youcontribute.models.Repository;
import com.huseyin.youcontribute.repositories.RepositoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryService(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @Transactional
    public void create(String organization, String repository) {
        Repository r = Repository.builder().organization(organization).repository(repository).build();
        this.repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return repositoryRepository.findAll();
    }
}
