package com.huseyin.youcontribute.controllers.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.huseyin.youcontribute.models.Issue;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueResource {

    private Integer id;

    private Long githubIssueId;

    private Long githubIssueNumber;

    private String title;

    private String body;

    private String url;

    public static IssueResource createFor(Issue issue) {
        return IssueResource.builder()
            .id(issue.getId())
            .title(issue.getTitle())
            .body(issue.getBody())
            .githubIssueId(issue.getGithubIssueId())
            .url(issue.getUrl())
            .build();
    }

    public static List<IssueResource> createFor(List<Issue> issues) {
        return issues.stream().map(IssueResource::createFor).collect(Collectors.toList());
    }

}
