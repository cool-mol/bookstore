package coolmol.ebookstore.repository;

import coolmol.ebookstore.entity.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookImageRepository extends MongoRepository<BookImage, Integer> {

}
