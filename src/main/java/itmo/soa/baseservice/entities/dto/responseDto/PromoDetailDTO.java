package itmo.soa.baseservice.entities.dto.responseDto;

import itmo.soa.baseservice.entities.Participant;
import itmo.soa.baseservice.entities.Prize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromoDetailDTO {
    private Long id;
    private String name;
    private String description;
    private List<Prize> prizes;
    private List<Participant> participants;

}
