package me.maru.seeTogether.api.v1.payment.update;

import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateRequest;
import me.maru.seeTogether.api.v1.payment.create.CardInfoCreateResponse;
import me.maru.seeTogether.service.payment.update.CardInfoUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class CardInfoUpdateController {

    private final Logger logger = LoggerFactory.getLogger(CardInfoUpdateController.class);
    private final CardInfoUpdater cardInfoUpdater;

    @Autowired
    public CardInfoUpdateController(final CardInfoUpdater cardInfoUpdater) {
        this.cardInfoUpdater = cardInfoUpdater;
    }

    @PutMapping(value = "/card")
    public ResponseEntity<CardInfoCreateResponse> create(@Valid @RequestBody final CardInfoCreateRequest cardInfoCreateRequest){
        logger.info("api/v1/card update request : {}", cardInfoCreateRequest.toString());
        final var cardCreateResponse = cardInfoUpdater.update(cardInfoCreateRequest);
        logger.info("api/v1/card update response : {}", cardCreateResponse.toString());
        return ResponseEntity.status(201).body(cardCreateResponse);
    }
}
