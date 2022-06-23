package platform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.business.entities.Code;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, String> {
    // latest 10 not secret codes by createtime descending
    List<Code> findTop10BySecretFalseOrderByCreateTimeDesc();
}
