package net.kominfo.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.kominfo.demo.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
