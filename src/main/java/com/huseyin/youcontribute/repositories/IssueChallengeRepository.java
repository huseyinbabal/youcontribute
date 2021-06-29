package com.huseyin.youcontribute.repositories;

import java.util.List;
import java.util.Optional;

import com.huseyin.youcontribute.models.IssueChallenge;
import com.huseyin.youcontribute.models.IssueChallengeStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IssueChallengeRepository extends PagingAndSortingRepository<IssueChallenge, Integer> {

    Optional<IssueChallenge> findByStatusIn(List<IssueChallengeStatus> status);

    @Override
    List<IssueChallenge> findAll();
}
