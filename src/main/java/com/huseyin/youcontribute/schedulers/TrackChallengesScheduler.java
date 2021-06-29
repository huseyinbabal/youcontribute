package com.huseyin.youcontribute.schedulers;

import java.util.Arrays;

import com.huseyin.youcontribute.clients.OneSignalClient;
import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.IssueChallenge;
import com.huseyin.youcontribute.models.IssueChallengeStatus;
import com.huseyin.youcontribute.models.Repository;
import com.huseyin.youcontribute.service.GithubClient;
import com.huseyin.youcontribute.service.IssueChallengeService;
import com.huseyin.youcontribute.service.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class TrackChallengesScheduler {

    private final IssueChallengeService issueChallengeService;

    private final GithubClient githubClient;

    @Scheduled(fixedRateString = "${application.challenge-tracking-frequency}")
    public void challengeIssuesScheduler() {
        log.info("Track challenge scheduler started");
        this.issueChallengeService.list()
            .stream()
            .filter(issueChallenge -> IssueChallengeStatus.ACCEPTED.equals(issueChallenge.getStatus()))
            .forEach(issueChallenge -> {
                Repository repository = issueChallenge.getIssue().getRepository();
                Arrays.stream(this.githubClient
                    .listPullRequests(repository.getOrganization(), repository.getRepository()))
                    .filter(pull -> "huseyinbabal".equals(pull.getUser().getLogin()) && "closed".equals(pull.getState()))
                    .findFirst()
                    .ifPresent(pullResponse -> {
                        System.out.println("Issue resolved!");
                    });
            });
        log.info("Track challenge scheduler finished");
    }
}
