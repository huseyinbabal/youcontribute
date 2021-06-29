package com.huseyin.youcontribute.schedulers;

import com.huseyin.youcontribute.clients.OneSignalClient;
import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.IssueChallenge;
import com.huseyin.youcontribute.service.IssueChallengeService;
import com.huseyin.youcontribute.service.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ChallengeIssuesScheduler {

    private final IssueService issueService;

    private final IssueChallengeService issueChallengeService;

    private final OneSignalClient oneSignalClient;

    @Scheduled(fixedRateString = "${application.challenge-frequency}")
    public void challengeIssuesScheduler() {
        log.info("Challenge issue scheduler started");
        if (this.issueChallengeService.hasOngoingChallenge()) {
            log.warn("There is already an ongoing challenge, skipping...");
            return;
        }
        Issue randomIssue = this.issueService.findRandomIssue();
        log.info("Found a random issue {}", randomIssue.getId());
        IssueChallenge issueChallenge = issueChallengeService.create(randomIssue);
        oneSignalClient.sendNotification(issueChallenge.getId(), randomIssue.getTitle());
        log.info("Challenge issue scheduler finished");
    }
}
