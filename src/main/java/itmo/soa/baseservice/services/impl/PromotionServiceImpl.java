package itmo.soa.baseservice.services.impl;

import itmo.soa.baseservice.entities.Participant;
import itmo.soa.baseservice.entities.Prize;
import itmo.soa.baseservice.entities.Promotion;
import itmo.soa.baseservice.entities.Result;
import itmo.soa.baseservice.entities.dto.requestDto.RequestParticipantDTO;
import itmo.soa.baseservice.entities.dto.requestDto.RequestPrizeDTO;
import itmo.soa.baseservice.entities.dto.requestDto.RequestPromotionDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PromoDetailDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PromotionDTO;
import itmo.soa.baseservice.entities.dto.responseDto.ResultDTO;
import itmo.soa.baseservice.exception.ImpossibleRaffleException;
import itmo.soa.baseservice.exception.NoRequiredParamException;
import itmo.soa.baseservice.exception.NoSuchParticipantException;
import itmo.soa.baseservice.exception.NoSuchPromotionException;
import itmo.soa.baseservice.repositories.ParticipantRepository;
import itmo.soa.baseservice.repositories.PrizeRepository;
import itmo.soa.baseservice.repositories.PromotionRepository;
import itmo.soa.baseservice.repositories.ResultRepository;
import itmo.soa.baseservice.services.PromotionService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    final PromotionRepository promotionRepository;

    final ParticipantRepository participantRepository;

    final PrizeRepository prizeRepository;

    final ResultRepository resultRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository, ParticipantRepository participantRepository, PrizeRepository PrizeRepository, ResultRepository resultRepository) {
        this.promotionRepository = promotionRepository;
        this.participantRepository = participantRepository;
        this.prizeRepository = PrizeRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public Optional<List<PromotionDTO>> getAllPromotions() {
        List<Promotion> promotionList = (List<Promotion>) promotionRepository.findAll();
        if (promotionList.size() == 0) return Optional.empty();
        else return Optional.of(promotionList.stream()
                .map(promotion -> (PromotionDTO) promotion.mapToDto())
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<PromoDetailDTO> getPromotion(Long id) {
        Optional<Promotion> promoWrapper = promotionRepository.findById(id);
        if (promoWrapper.isPresent()) {
            Promotion promo = promoWrapper.get();
            PromoDetailDTO respDto = new PromoDetailDTO(promo.getId(), promo.getName(),
                    promo.getDescription(), promo.getPrizes(), promo.getParticipants());
            return Optional.of(respDto);
        } else return Optional.empty();
    }

    @SneakyThrows
    @Override
    public Optional<PromotionDTO> addNewPromotion(RequestPromotionDTO dto) {
        if (dto.getName() == null || dto.getName().equals("")) throw new NoRequiredParamException("name is required");
        Promotion newPromotion = new Promotion(dto.getName(), dto.getDescription());
        promotionRepository.save(newPromotion);
        return Optional.ofNullable((PromotionDTO) newPromotion.mapToDto());
    }

    @SneakyThrows
    @Override
    public Optional<PromotionDTO> updatePromotion(Long id, RequestPromotionDTO dto) {
        Optional<Promotion> promoWrapper = promotionRepository.findById(id);
        Promotion promotion;
        if (promoWrapper.isEmpty()) return Optional.empty();
        else {
            promotion = promoWrapper.get();

            if (dto.getName() == null || dto.getName().equals("")) throw new NoRequiredParamException("name is required");
            promotion.setName(dto.getName());
            promotion.setDescription(dto.getDescription());
        }
        return Optional.ofNullable((PromotionDTO) promotionRepository.save(promotion).mapToDto());
    }

    @Override
    public Optional<String> deletePromotion(Long id) {
        try {
            promotionRepository.deleteById(id);
            return Optional.of("success");
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> addNewParticipant(Long id, RequestParticipantDTO dto) {
        Participant newParticipant = new Participant(dto.getName());
        newParticipant = participantRepository.save(newParticipant);
        Optional<Promotion> promoWrapper = promotionRepository.findById(id);
        if (promoWrapper.isEmpty()) return Optional.empty();
        else {
            Promotion promotion = promoWrapper.get();
            if (promotion.getParticipants() != null) promotion.getParticipants().add(newParticipant);
            else promotion.setParticipants(Collections.singletonList(newParticipant));
            promotionRepository.save(promotion);
            return Optional.of(newParticipant.getId());
        }
    }

    @SneakyThrows
    @Override
    public Optional<String> deleteParticipant(Long promoId, Long participantId) {
        Promotion promo = promotionRepository.findById(promoId).orElseThrow(NoSuchPromotionException::new);
        Participant participant = participantRepository.findById(participantId).orElseThrow(NoSuchParticipantException::new);
        promo.getParticipants().remove(participant);
        promotionRepository.save(promo);
        return Optional.of("success");
    }

    @Override
    public Optional<Long> addNewPrize(Long id, RequestPrizeDTO dto) {
        Prize newPrize = new Prize(dto.getName());
        newPrize = prizeRepository.save(newPrize);
        Optional<Promotion> promoWrapper = promotionRepository.findById(id);
        if (promoWrapper.isEmpty()) return Optional.empty();
        else {
            Promotion promotion = promoWrapper.get();
            newPrize.setPromotion(promotion);
            if (promotion.getPrizes() != null) promotion.getPrizes().add(newPrize);
            else promotion.setPrizes(Collections.singletonList(newPrize));
            promotionRepository.save(promotion);
            return Optional.of(newPrize.getId());
        }
    }

    @SneakyThrows
    @Override
    public Optional<String> deletePrize(Long promoId, Long prizeId) {
        Promotion promo = promotionRepository.findById(promoId).orElseThrow(NoSuchPromotionException::new);
        Prize prize = prizeRepository.findById(prizeId).orElseThrow(NoSuchParticipantException::new);
        prize.setPromotion(null);
        prizeRepository.save(prize);
        promo.getPrizes().remove(prize);
        promotionRepository.save(promo);
        return Optional.of("success");
    }

    @SneakyThrows
    @Override
    public Optional<List<Result>> makeRaffle(Long id) {
        Optional<Promotion> promoWrapper = promotionRepository.findById(id);
        if (promoWrapper.isEmpty()) throw new NoSuchPromotionException();
        Promotion promotion = promoWrapper.get();

        if (promotion.getPrizes().size() != promotion.getParticipants().size()) throw new ImpossibleRaffleException();

        List<Result> results = new ArrayList<>();

        for (int i = 0; i < promotion.getParticipants().size(); i++){
            Result newResult = new Result(promotion.getParticipants().get(i), promotion.getPrizes().get(i));
            resultRepository.save(newResult);
            results.add(newResult);
        }
        return Optional.of(results);
    }


}