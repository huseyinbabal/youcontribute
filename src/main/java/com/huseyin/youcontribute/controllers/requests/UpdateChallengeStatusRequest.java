package com.huseyin.youcontribute.controllers.requests;

import com.huseyin.youcontribute.models.IssueChallengeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeStatusRequest {

    private IssueChallengeStatus status;
}
