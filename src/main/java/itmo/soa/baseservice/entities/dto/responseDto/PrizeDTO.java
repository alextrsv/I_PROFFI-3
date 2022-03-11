package itmo.soa.baseservice.entities.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrizeDTO extends AbstractDTO {
    private Long id;
   String description;
}
