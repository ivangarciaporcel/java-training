package sia.tacocloud.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.domain.UserPrincipal;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserPrincipal, Long>, JpaSpecificationExecutor<UserPrincipal> {

    Optional<UserPrincipal> findByUsername(String username);
}
