package com.huseyin.youcontribute.repositories;

import java.util.List;

import com.huseyin.youcontribute.models.Issue;
import com.huseyin.youcontribute.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Integer> {

    List<Issue> findAll();

    List<Issue> findByRepository(Repository repository);
}
