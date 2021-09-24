package bg.softuni.MobiLeLeLe.repository;

import bg.softuni.MobiLeLeLe.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
