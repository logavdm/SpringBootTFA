package com.repositry;
import org.springframework.data.repository.CrudRepository;
import com.model.Users;

public interface UserRepository extends CrudRepository<Users, Long>{
	
	Users findByEmail(String email);

			
}
