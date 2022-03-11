package itmo.soa.baseservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import itmo.soa.baseservice.entities.dto.responseDto.AbstractDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PrizeDTO;
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
@Table(name = "prize")
public class Prize extends AbstractEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String description;

    @JsonIgnore
    @ManyToOne(optional = true, cascade =  CascadeType.ALL)
    @JoinColumn(name = "promo_id")
    private Promotion promotion;

    @JsonIgnore
    @OneToMany(mappedBy = "prize", fetch = FetchType.EAGER)
    private List<Result> resultList;


    public Prize(String description){
        this.description = description;
    }

    @Override
    public AbstractDTO mapToDto() {
        PrizeDTO dto = new PrizeDTO();
        dto.setDescription(this.description);
        dto.setId(this.id);
        return dto;
    }
}