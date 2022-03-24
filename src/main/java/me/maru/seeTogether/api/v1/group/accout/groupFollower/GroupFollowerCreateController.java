package me.maru.seeTogether.api.v1.group.accout.groupFollower;

import me.maru.seeTogether.service.group.accout.groupFollower.create.GroupFollowerCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class GroupFollowerCreateController {

    private final Logger logger = LoggerFactory.getLogger(GroupFollowerCreateController.class);

    @Autowired
    private final GroupFollowerCreator groupFollowerCreator;

    public GroupFollowerCreateController(final GroupFollowerCreator groupFollowerCreator) {
        this.groupFollowerCreator = groupFollowerCreator;
    }

    @PostMapping(value = "/groupFollower")
    public ResponseEntity<GroupFollowerCreateResponse> create(final GroupFollowerCreateRequest groupFollowerCreateRequest){
        logger.info("api/v1/groupFollower request : {}", groupFollowerCreateRequest.toString());
        final var groupFollowerCreateResponse = groupFollowerCreator.create(groupFollowerCreateRequest);
        logger.info("api/v1/groupFollower response : {}", groupFollowerCreateResponse.toString());
        return ResponseEntity.status(201).body(groupFollowerCreateResponse);
    }
}
