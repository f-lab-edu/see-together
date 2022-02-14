package me.maru.seeTogether.api.v1.payment.create;

import me.maru.seeTogether.service.payment.create.CardInfoCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1")
public class CardInfoCreateController {

    private final Logger logger = LoggerFactory.getLogger(CardInfoCreateController.class);
    private final CardInfoCreator cardInfoCreator;

    @Autowired
    public CardInfoCreateController(final CardInfoCreator cardInfoCreator) {
        this.cardInfoCreator = cardInfoCreator;
    }

    @PostMapping(value = "/card")
    public ResponseEntity<CardInfoCreateResponse> create(@Valid @RequestBody final CardInfoCreateRequest cardInfoCreateRequest){
        logger.info("api/v1/card request : {}", cardInfoCreateRequest.toString());
        final var cardCreateResponse = cardInfoCreator.create(cardInfoCreateRequest);
        logger.info("api/v1/card response : {}", cardCreateResponse.toString());
        return ResponseEntity.status(201).body(cardCreateResponse);
    }
}
