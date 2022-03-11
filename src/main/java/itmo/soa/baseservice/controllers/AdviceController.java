package itmo.soa.baseservice.controllers;

import itmo.soa.baseservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NoSuchPrizeException.class)
    public ResponseEntity<String> handleNoPrize(NoSuchPrizeException ex) {
        return new ResponseEntity<>( ex.getCustomMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchPromotionException.class)
    public ResponseEntity<String> handleNoPromo(NoSuchPromotionException ex) {
        return new ResponseEntity<>( ex.getCustomMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchParticipantException.class)
    public ResponseEntity<String> handleNoParticipant(NoSuchParticipantException ex) {
        return new ResponseEntity<>( ex.getCustomMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImpossibleRaffleException.class)
    public ResponseEntity<String> handleImpossible(ImpossibleRaffleException ex) {
        return new ResponseEntity<>( ex.getCustomMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoRequiredParamException.class)
    public ResponseEntity<String> handleImpossible(NoRequiredParamException ex) {
        return new ResponseEntity<>( ex.getCustomMessage(), HttpStatus.BAD_REQUEST);
    }
}
