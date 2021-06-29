package com.huseyin.youcontribute.repositories;

import java.util.List;
import java.util.Optional;

import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {

    List<Issue> findAll();

    List<Issue> findByRepository(Repository repository);

    @Query(value = "select * from issue where id not in (select issue_id from issue_challenge) order by rand() limit 1", nativeQuery = true)
    Optional<Issue> findRandomIssue();

    Optional<Issue> findByGithubIssueId(Long githubIssueId);
}
