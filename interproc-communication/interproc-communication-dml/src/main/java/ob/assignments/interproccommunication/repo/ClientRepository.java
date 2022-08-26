package ob.assignments.interproccommunication.repo;

import ob.assignments.interproccommunication.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Client repository
 */
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
