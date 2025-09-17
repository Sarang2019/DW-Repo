package com.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{

}
