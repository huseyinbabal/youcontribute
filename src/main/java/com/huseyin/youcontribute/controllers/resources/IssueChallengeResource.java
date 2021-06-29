package com.huseyin.youcontribute.controllers.resources;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.IssueChallenge;
import com.huseyin.youcontribute.models.IssueChallengeStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IssueChallengeResource {

    private Integer id;

    private Integer issueId;

    private Integer repositoryId;

    private String repositoryTitle;

    private String issueTitle;

    private Date creationDate;

    private IssueChallengeStatus status;

    public static IssueChallengeResource createFor(IssueChallenge issueChallenge) {
        Issue issue = issueChallenge.getIssue();
        return IssueChallengeResource.builder()
            .id(issueChallenge.getId())
            .issueId(issue.getId())
            .repositoryId(issue.getRepository().getId())
            .issueTitle(issue.getTitle())
            .repositoryTitle(issue.getRepository().getRepository())
            .creationDate(issueChallenge.getCreatedAt())
            .status(issueChallenge.getStatus())
            .build();
    }

    public static List<IssueChallengeResource> createFor(List<IssueChallenge> issueChallenges) {
        return issueChallenges.stream().map(IssueChallengeResource::createFor).collect(Collectors.toList());
    }

}
