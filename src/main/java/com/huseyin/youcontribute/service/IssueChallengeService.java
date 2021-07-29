package com.huseyin.youcontribute.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.IssueChallenge;
import com.huseyin.youcontribute.models.IssueChallengeStatus;
import com.huseyin.youcontribute.repositories.IssueChallengeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IssueChallengeService {

    private final IssueChallengeRepository issueChallengeRepository;

    @Transactional
    public IssueChallenge create(Issue issue) {
        IssueChallenge challenge = IssueChallenge.builder().issue(issue).status(IssueChallengeStatus.PENDING)
            .build();
        return this.issueChallengeRepository.save(challenge);
    }

    public Boolean hasOngoingChallenge() {
        return this.issueChallengeRepository.findByStatusIn(IssueChallengeStatus.ongoingStatuses()).isPresent();
    }

    public void updateStatus(Integer id, IssueChallengeStatus status) {
        IssueChallenge issueChallenge = this.issueChallengeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Issue Challenge " + id + " not found"));
        issueChallenge.setStatus(status);
        this.issueChallengeRepository.save(issueChallenge);
    }

    public List<IssueChallenge> list() {
        return this.issueChallengeRepository.findAll();
    }

}
