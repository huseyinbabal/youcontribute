package com.huseyin.youcontribute.controllers;

import java.util.List;

import com.huseyin.youcontribute.controllers.requests.UpdateChallengeStatusRequest;
import com.huseyin.youcontribute.controllers.resources.IssueChallengeResource;
import com.huseyin.youcontribute.controllers.resources.IssueResource;
import com.huseyin.youcontribute.models.IssueChallenge;
import com.huseyin.youcontribute.service.IssueChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenges")
public class IssueChallengeController {

    private final IssueChallengeService issueChallengeService;

    public IssueChallengeController(
        IssueChallengeService issueChallengeService) {
        this.issueChallengeService = issueChallengeService;
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable("id") Integer id, @RequestBody UpdateChallengeStatusRequest request) {
        this.issueChallengeService.updateStatus(id, request.getStatus());
    }

    @GetMapping
    public List<IssueChallengeResource> list() {
        return IssueChallengeResource.createFor(this.issueChallengeService.list());
    }
}
