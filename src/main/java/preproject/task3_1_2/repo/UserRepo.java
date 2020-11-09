package preproject.task3_1_2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import preproject.task3_1_2.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

    void deleteById(Long id);

    User findByUsername(String username);
}
