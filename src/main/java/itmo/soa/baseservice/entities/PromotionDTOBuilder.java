package itmo.soa.baseservice.entities;

import itmo.soa.baseservice.entities.dto.responseDto.PrizeDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PromotionDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PromotionDTOBuilder {
    private Long id;
    private String name;
    private String description;
    private List<PrizeDTO> prizeDTOList;


    public PromotionDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    PromotionDTOBuilder setName(String name){
        this.name = name;
        return this;
    }

    PromotionDTOBuilder setDescription(String description){
        this.description = description;
        return this;
    }


    PromotionDTOBuilder setPrizes(List<Prize> prizeListe){
        this.prizeDTOList = prizeListe.stream()
                .map(prizes -> (PrizeDTO) prizes.mapToDto())
                .collect(Collectors.toList());
        return this;
    }

    PromotionDTO build(){
        return new PromotionDTO(id, name, description);
    }

}
