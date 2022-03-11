package itmo.soa.baseservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import itmo.soa.baseservice.entities.dto.responseDto.AbstractDTO;
import itmo.soa.baseservice.entities.dto.responseDto.ParticipantDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "participant")
public class Participant  extends AbstractEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "participants")
    private List<Promotion> promoList;

    @JsonIgnore
    @OneToMany(mappedBy = "winner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Result> results;

    public Participant(String name){this.name = name;}

    @Override
    public AbstractDTO mapToDto() {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(this.id);
        dto.setName(this.name);
//        dto.setResults();
//        dto.setPromoList();
        return dto;
    }
}
