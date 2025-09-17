package com.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.DTO.UserResponseDTO;

import com.Entity.User;

public interface UserService {

	User addSingleUserInService(@Valid User u);

	List<User> addMultipleUserInService(List<User> u);

	List<User> findAllUserInService();

	Optional<User> findUserByIdInService(int id);

	Iterable<User> findAllUserByIdInService(List<Integer> id);

	List<User> deleteSingleUserByPasingObjectofUser(User u);

	List<User> deleteSingleUserByIdofUser(int id);

	Iterable<User> deleteAllUserByIdInService(List<Integer> id);

	List<User> deleteMultipleUserInService(List<User> u);

	Iterable<User> deleteAllUsersInServiceNoParameters();

	long countUserInService();

	String existByIdInService(int id);

	User getUserIdforUpdationInService(int id);

	User updateUserInService(User u);

	UserResponseDTO fetchUserInService(int id);

	void sendMailInService();

	User updateUserInOneMethodInService(int id, User u);
	
	

}
