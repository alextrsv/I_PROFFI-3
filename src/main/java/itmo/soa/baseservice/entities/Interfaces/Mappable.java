package itmo.soa.baseservice.entities.Interfaces;


import itmo.soa.baseservice.entities.dto.responseDto.AbstractDTO;

public interface Mappable {
    AbstractDTO mapToDto();
}
