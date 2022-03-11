package itmo.soa.baseservice.entities;

import itmo.soa.baseservice.entities.dto.responseDto.AbstractDTO;
import itmo.soa.baseservice.entities.dto.responseDto.PrizeDTO;
import itmo.soa.baseservice.entities.dto.responseDto.ResultDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "results")
public class Result extends AbstractEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade =  CascadeType.ALL)
    @JoinColumn(name = "winner_id")
    private Participant winner;

    @ManyToOne(optional = false, cascade =  CascadeType.ALL)
    @JoinColumn(name = "prize_id")
    private Prize prize;

    public Result(Participant winner, Prize prize){
        this.winner = winner;
        this.prize = prize;
    }

    @Override
    public AbstractDTO mapToDto() {
        ResultDTO dto = new ResultDTO();
//        dto.setParticipantDTO(this.winner.map);
        dto.setId(this.id);
        return dto;
    }
}