package itmo.soa.baseservice.repositories;

import itmo.soa.baseservice.entities.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long>{

//    @Query("SELECT w FROM Worker w")
//    List<Worker> findAllWithPagination(PageRequest page);
}
