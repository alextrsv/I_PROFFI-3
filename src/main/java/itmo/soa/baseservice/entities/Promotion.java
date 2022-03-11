package itmo.soa.baseservice.entities;

import itmo.soa.baseservice.entities.dto.responseDto.AbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "promotion")
public class Promotion extends AbstractEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.EAGER)
    private List<Prize> prizes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "promo_participants",
            joinColumns = @JoinColumn(name = "promo_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<Participant> participants;

    public Promotion(String name, String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public AbstractDTO mapToDto() {
        return new PromotionDTOBuilder()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .build();
    }
}