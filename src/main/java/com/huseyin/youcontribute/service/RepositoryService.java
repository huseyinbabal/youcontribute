package com.huseyin.youcontribute.service;

import com.huseyin.youcontribute.exceptions.DuplicatedRepositoryException;
import com.huseyin.youcontribute.models.Repository;
import com.huseyin.youcontribute.repositories.RepositoryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        this.validate(organization, repository);
        Repository r = Repository.builder().organization(organization).repository(repository).build();
        this.repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return repositoryRepository.findAll();
    }

    private void validate(String organization, String repository) {
        this.repositoryRepository.findByOrganizationAndRepository(organization, repository)
            .ifPresent((r) -> {
                throw new DuplicatedRepositoryException(organization, repository);
            });
    }

    public Repository findById(Integer repositoryId) {
        return this.repositoryRepository.findById(repositoryId).orElseThrow(
            () -> new EntityNotFoundException(String.format("Repository: %d is not found", repositoryId)));
    }

}
