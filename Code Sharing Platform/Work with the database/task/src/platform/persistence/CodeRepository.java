package platform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.business.entities.Code;

import java.util.List;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {
    // latest 10 codes by createtime descending
    List<Code> findTop10ByOrderByCreateTimeDesc();
}
