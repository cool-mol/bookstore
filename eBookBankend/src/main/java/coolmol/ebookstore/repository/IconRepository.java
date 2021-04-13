package coolmol.ebookstore.repository;

import coolmol.ebookstore.entity.Icon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IconRepository extends MongoRepository<Icon, Integer> {
}
