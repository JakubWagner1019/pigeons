package jakwagne.pigeonapp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PigeonRepository extends CrudRepository<PigeonDao, String> {

    @Query(value = "SELECT pigeon FROM PigeonDao pigeon WHERE pigeon.motherId = :parentId OR pigeon.fatherId = :parentId")
    List<PigeonDao> findPigeonDaosByParentId(@Param("parentId") String parentId);
}
