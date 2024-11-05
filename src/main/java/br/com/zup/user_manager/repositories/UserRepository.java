package br.com.zup.user_manager.repositories;

import br.com.zup.user_manager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public Optional<User> findByUserName(String username);
}
