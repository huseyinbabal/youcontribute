package com.huseyin.youcontribute.service.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class GithubIssueResponse {
    public String url;
    public String repositoryUrl;
    public String labelsUrl;
    public String commentsUrl;
    public String eventsUrl;
    public String htmlUrl;
    public long id;
    public String nodeId;
    public int number;
    public String title;
    public User user;
    public List<Label> labels;
    public String state;
    public boolean locked;
    public Object assignee;
    public List<Assignee> assignees;
    public Object milestone;
    public int comments;
    public Date createdAt;
    public Date updatedAt;
    public Object closedAt;
    public String authorAssociation;
    public Object activeLockReason;
    public String body;
    public Object performedViaGithubApp;
    public Object pullRequest;

    public static class User{
        public String login;
        public long id;
        public String node_id;
        public String avatarUrl;
        public String gravatarId;
        public String url;
        public String htmlUrl;
        public String followersUrl;
        public String followingUrl;
        public String gistsUrl;
        public String starredUrl;
        public String subscriptionsUrl;
        public String organizationsUrl;
        public String reposUrl;
        public String eventsUrl;
        public String receivedEventsUrl;
        public String type;
        public boolean siteAdmin;
    }

    public static class Assignee {
        public String login;
        public long id;
        public String nodeId;
        public String avatarUrl;
        public String gravatarId;
        public String url;
        public String htmlUrl;
        public String followersUrl;
        public String followingUrl;
        public String gistsUrl;
        public String starredUrl;
        public String subscriptionsUrl;
        public String organizationsUrl;
        public String reposUrl;
        public String eventsUrl;
        public String receivedEventsUrl;
        public String type;
        public boolean siteAdmin;
    }

    public static class Label {
        public long id;
        public String nodeId;
        public String url;
        public String name;
        public String color;
        @JsonProperty("default")
        public boolean isDefault;
        public String description;
    }
}
