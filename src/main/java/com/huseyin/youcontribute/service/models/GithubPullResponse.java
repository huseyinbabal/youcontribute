package com.huseyin.youcontribute.service.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class GithubPullResponse {

    private String state;

    private User user;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {

        private String login;

    }

}
