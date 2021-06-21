package com.huseyin.youcontribute.controllers;

import java.util.List;

import com.huseyin.youcontribute.controllers.requests.CreateRepositoryRequest;
import com.huseyin.youcontribute.controllers.resources.IssueResource;
import com.huseyin.youcontribute.service.IssueService;
import com.huseyin.youcontribute.service.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<IssueResource> list(@RequestParam("repository_id") Integer repositoryId) {
        return IssueResource.createFor(this.issueService.list(repositoryId));
    }
}
