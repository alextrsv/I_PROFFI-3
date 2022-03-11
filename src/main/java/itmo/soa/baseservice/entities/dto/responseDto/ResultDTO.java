package itmo.soa.baseservice.entities.dto.responseDto;

import lombok.Data;

@Data
public class ResultDTO extends AbstractDTO {

    private Long id;
    private ParticipantDTO participantDTO;
    private PrizeDTO prizeDTO;
}
