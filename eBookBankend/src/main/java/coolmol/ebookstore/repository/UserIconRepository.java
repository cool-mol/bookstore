package coolmol.ebookstore.repository;

import coolmol.ebookstore.entity.UserIcon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserIconRepository extends MongoRepository<UserIcon, Integer> {
}
