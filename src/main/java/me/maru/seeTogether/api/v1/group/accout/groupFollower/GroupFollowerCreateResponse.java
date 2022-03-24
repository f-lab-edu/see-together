package me.maru.seeTogether.api.v1.group.accout.groupFollower;

import lombok.Builder;

import java.time.LocalDateTime;

public class GroupFollowerCreateResponse {
    private final String productName;
    private final String ottId;
    private final String ottPassword;
    private final Integer currentParticipantsSize;
    private final LocalDateTime createdAt;

    @Builder
    public GroupFollowerCreateResponse(final String productName, final String ottId, final String ottPassword, final Integer currentParticipantsSize, final LocalDateTime createdAt) {
        this.productName = productName;
        this.ottId = ottId;
        this.ottPassword = ottPassword;
        this.currentParticipantsSize = currentParticipantsSize;
        this.createdAt = createdAt;
    }
}
