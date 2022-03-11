package itmo.soa.baseservice.repositories;

import itmo.soa.baseservice.entities.Prize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepository extends CrudRepository<Prize, Long> {

//    public Optional<Prize> findByXAndY(Double xCoord, Double yCoord);
}
