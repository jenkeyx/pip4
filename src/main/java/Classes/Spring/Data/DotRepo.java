package Classes.Spring.Data;


import Classes.Beans.Dot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(exported = false)
public interface DotRepo extends JpaRepository<Dot,Integer> {
    List<Dot> findAllByOwner(String owner);
}

//finished