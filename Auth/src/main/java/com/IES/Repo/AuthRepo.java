package com.IES.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IES.DTO.ForgetPassDTO;
import com.IES.Entity.User;
@Repository
public interface AuthRepo extends JpaRepository<User, Integer>{

	User findByEmail(String email);

//	User findByEmail(Class<ForgetPassDTO> class1);

}
