package itmo.soa.baseservice.repositories;

import itmo.soa.baseservice.entities.Promotion;
import itmo.soa.baseservice.entities.Result;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepository  extends CrudRepository<Result, Long> {
}
