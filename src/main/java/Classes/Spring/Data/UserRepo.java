package Classes.Spring.Data;

import Classes.Beans.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepo extends CrudRepository<User,Integer> {
    User findUserByUsername(String username);
}
//finished