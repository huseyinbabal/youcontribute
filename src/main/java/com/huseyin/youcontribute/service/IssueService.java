package com.huseyin.youcontribute.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.Repository;
import com.huseyin.youcontribute.repositories.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;

    private final RepositoryService repositoryService;

    @Transactional
    public void saveAll(List<Issue> issues) {
        this.issueRepository.saveAll(issues);
    }

    public List<Issue> list(Integer repositoryId) {
        Repository repository = this.repositoryService.findById(repositoryId);
        return this.issueRepository.findByRepository(repository);
    }

    public Issue findRandomIssue() {
        return this.issueRepository.findRandomIssue()
            .orElseThrow(() -> new EntityNotFoundException("No issues found."));
    }

}
