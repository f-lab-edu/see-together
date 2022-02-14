package me.maru.seeTogether.api.v1.payment.query;

import me.maru.seeTogether.service.payment.query.CardInfoReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CardInfoQueryController {

    private final Logger logger = LoggerFactory.getLogger(CardInfoQueryController.class);
    private final CardInfoReader cardInfoReader;

    @Autowired
    public CardInfoQueryController(final CardInfoReader cardInfoReader) {
        this.cardInfoReader = cardInfoReader;
    }

    @GetMapping("/card")
    public ResponseEntity<CardInfoQueryResponse> getCardInfo(){
        logger.info("api/v1/card update request");
        final var cardInfoQueryResponse = cardInfoReader.getCardInfo();
        logger.info("api/v1/card update response : '{}'", cardInfoQueryResponse.toString());
        return ResponseEntity.ok(cardInfoQueryResponse);
    }
}
