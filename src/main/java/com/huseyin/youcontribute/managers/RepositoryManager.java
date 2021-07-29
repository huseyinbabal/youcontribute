package com.huseyin.youcontribute.managers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.huseyin.youcontribute.config.ApplicationProperties;
import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.Repository;
import com.huseyin.youcontribute.service.GithubClient;
import com.huseyin.youcontribute.service.IssueService;
import com.huseyin.youcontribute.service.RepositoryService;
import com.huseyin.youcontribute.service.models.GithubIssueResponse;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RepositoryManager {

    private final RepositoryService repositoryService;

    private final IssueService issueService;

    private final GithubClient githubClient;

    private final ApplicationProperties applicationProperties;

    public void importRepository(String organization, String repository) {
        this.repositoryService.create(organization, repository);
    }

    @Async
    public void importIssues(Repository repository) {
        int schedulerFrequencyInMinutes = applicationProperties.getImportFrequency() / 60000;
        LocalDate since = LocalDate.ofInstant(Instant.now().minus(schedulerFrequencyInMinutes, ChronoUnit.MINUTES),
            ZoneOffset.UTC);
        GithubIssueResponse[] githubIssueResponses = this.githubClient.listIssues(
            repository.getOrganization(), repository.getRepository(), since);
        List<Issue> issues = Arrays.stream(githubIssueResponses).filter(githubIssue -> Objects.isNull(githubIssue.getPullRequest())).map(githubIssue -> Issue.builder().repository(repository).githubIssueNumber(githubIssue.getNumber()).githubIssueId(githubIssue.getId()).url(githubIssue.getHtmlUrl()).title(githubIssue.getTitle()).body(githubIssue.getBody()).build()).collect(
            Collectors.toList());
        this.issueService.saveAll(issues);
    }
}
