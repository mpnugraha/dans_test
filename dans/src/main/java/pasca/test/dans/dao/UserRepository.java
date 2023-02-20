package pasca.test.dans.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pasca.test.dans.entity.User;

/**
 * @author pascanugraha
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findFirstByUsername(String username);
}
