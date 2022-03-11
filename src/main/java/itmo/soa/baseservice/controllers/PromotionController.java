package itmo.soa.baseservice.controllers;

import itmo.soa.baseservice.entities.Result;
import itmo.soa.baseservice.entities.dto.requestDto.RequestParticipantDTO;
import itmo.soa.baseservice.entities.dto.requestDto.RequestPrizeDTO;
import itmo.soa.baseservice.entities.dto.requestDto.RequestPromotionDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PromoDetailDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PromotionDTO;
import itmo.soa.baseservice.services.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("promo")
public class PromotionController {

    final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<List<PromotionDTO>> getAllPromotions() {
        return promotionService.getAllPromotions().map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("{id}")
    public ResponseEntity<PromoDetailDTO> getPromotion(@PathVariable("id") Long id) {
        return promotionService.getPromotion(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<PromotionDTO> addNewPromotion(@RequestBody RequestPromotionDTO requestDto) {
        return promotionService.addNewPromotion(requestDto).map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionDTO> updateWorker(@PathVariable("id") Long id,
                                                  @RequestBody RequestPromotionDTO dto){
        return promotionService.updatePromotion(id, dto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable("id") Long id){
        return promotionService.deletePromotion(id).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{id}/participant")
    public ResponseEntity<Long> addNewParticipant(@PathVariable("id") Long id,
                                                  @RequestBody RequestParticipantDTO requestDto) {
        return promotionService.addNewParticipant(id, requestDto).map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{promoId}/participant/{participantId}")
    public ResponseEntity<String> deleteWorker(@PathVariable("promoId") Long promoId,
                                               @PathVariable("participantId") Long participantId){
        return promotionService.deleteParticipant(promoId, participantId).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{id}/prize")
    public ResponseEntity<Long> addNewPrize(@PathVariable("id") Long id,
                                                  @RequestBody RequestPrizeDTO requestDto) {
        return promotionService.addNewPrize(id, requestDto).map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{promoId}/prize/{prizeId}")
    public ResponseEntity<String> deletePrize(@PathVariable("promoId") Long promoId,
                                               @PathVariable("prizeId") Long prizeId){
        return promotionService.deletePrize(promoId, prizeId).map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{id}/raffle")
    public ResponseEntity<List<Result>> addNewPrize(@PathVariable("id") Long id) {
        return promotionService.makeRaffle(id).map(e -> new ResponseEntity<>(e, HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
