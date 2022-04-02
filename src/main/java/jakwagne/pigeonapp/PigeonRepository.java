package jakwagne.pigeonapp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PigeonRepository extends CrudRepository<Pigeon, String> {

    @Query(value = "SELECT pigeon FROM Pigeon pigeon WHERE pigeon.motherId = :parentId OR pigeon.fatherId = :parentId")
    List<Pigeon> findPigeonsByParentId(@Param("parentId") String parentId);
}
