package me.maru.seeTogether.api.v1.group.accout.groupFollower;

import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
public class GroupFollowerCreateRequest {
    @NotNull
    private Long userId;
    @NotNull
    private Long ottProductId;
}
