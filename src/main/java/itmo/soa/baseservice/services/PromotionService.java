package itmo.soa.baseservice.services;

import itmo.soa.baseservice.entities.Result;
import itmo.soa.baseservice.entities.dto.requestDto.RequestParticipantDTO;
import itmo.soa.baseservice.entities.dto.requestDto.RequestPrizeDTO;
import itmo.soa.baseservice.entities.dto.requestDto.RequestPromotionDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PromoDetailDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PromotionDTO;

import java.util.List;
import java.util.Optional;

public interface PromotionService {

    Optional<List<PromotionDTO>> getAllPromotions();

    Optional<PromoDetailDTO> getPromotion(Long id);

    Optional<PromotionDTO> addNewPromotion(RequestPromotionDTO dto);

    Optional<PromotionDTO> updatePromotion(Long id, RequestPromotionDTO dto);

    Optional<String> deletePromotion(Long id);

    Optional<Long> addNewParticipant(Long id, RequestParticipantDTO dto);

    Optional<String> deleteParticipant(Long promoId, Long participantId);

    Optional<Long> addNewPrize(Long id, RequestPrizeDTO dto);

    Optional<String> deletePrize(Long promoId, Long prizeId);

    Optional<List<Result>> makeRaffle(Long id);
}
