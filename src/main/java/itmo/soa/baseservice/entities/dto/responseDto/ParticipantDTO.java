package itmo.soa.baseservice.entities.dto.responseDto;

import itmo.soa.baseservice.entities.Promotion;
import itmo.soa.baseservice.entities.Result;
import lombok.Data;

import java.util.List;

@Data
public class ParticipantDTO  extends AbstractDTO {
    private Long id;
    private String name;

    private List<Promotion> promoList;
    private List<Result> results;

}
