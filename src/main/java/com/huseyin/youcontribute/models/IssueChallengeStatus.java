package com.huseyin.youcontribute.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IssueChallengeStatus {
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    COMPLETED("COMPLETED");

    private String value;

    public static List<IssueChallengeStatus> ongoingStatuses() {
        return Arrays.stream(IssueChallengeStatus.values()).filter(
            status -> PENDING.equals(status) || ACCEPTED.equals(status))
            .collect(Collectors.toList());
    }
}
