package me.maru.seeTogether.service.group.accout.groupFollower.create;

import me.maru.seeTogether.api.v1.group.accout.groupFollower.GroupFollowerCreateRequest;
import me.maru.seeTogether.api.v1.group.accout.groupFollower.GroupFollowerCreateResponse;

public interface GroupFollowerCreator {
    GroupFollowerCreateResponse create(GroupFollowerCreateRequest groupFollowerCreateRequest);
}
