package com.huseyin.youcontribute.service;

import java.time.LocalDate;
import java.util.Date;

import com.huseyin.youcontribute.config.GithubProperties;
import com.huseyin.youcontribute.service.models.GithubIssueResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubClient {

    private final RestTemplate restTemplate;

    private final GithubProperties githubProperties;

    public GithubClient(RestTemplate restTemplate,
        GithubProperties githubProperties) {
        this.restTemplate = restTemplate;
        this.githubProperties = githubProperties;
    }

    public GithubIssueResponse[] listIssues(String owner, String repository, LocalDate since) {
        String issuesUrl = String.format("%s/repos/%s/%s/issues?since=%s", this.githubProperties.getApiUrl(),
            owner, repository, since.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  "token " + this.githubProperties.getToken());
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<GithubIssueResponse[]> response = this.restTemplate.exchange(issuesUrl, HttpMethod.GET,
            request, GithubIssueResponse[].class);
        return response.getBody();
    }
}
