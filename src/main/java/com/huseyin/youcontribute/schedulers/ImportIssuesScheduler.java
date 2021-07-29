package com.huseyin.youcontribute.schedulers;

import java.util.List;

import com.huseyin.youcontribute.managers.RepositoryManager;
import com.huseyin.youcontribute.models.Repository;
import com.huseyin.youcontribute.service.RepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ImportIssuesScheduler {

    private final RepositoryService repositoryService;

    private final RepositoryManager repositoryManager;

    @Scheduled(fixedRateString = "${application.import-frequency}", initialDelay = 60000)
    public void importIssuesScheduler() {
        log.info("Import scheduler started");
        List<Repository> repos = this.repositoryService.list();
        repos.forEach(this.repositoryManager::importIssues);
        log.info("Import scheduler finished");
    }
}
