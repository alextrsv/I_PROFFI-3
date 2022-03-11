package itmo.soa.baseservice.entities.dto.responseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDTO extends AbstractDTO {

   private Long id;
   private String name;
   private String description;


}
