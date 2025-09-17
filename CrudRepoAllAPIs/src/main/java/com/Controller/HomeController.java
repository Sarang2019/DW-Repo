package com.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.UserResponseDTO;
import com.Entity.User;
import com.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping("/exponent/user/")
@Tag(name = "User Related Operation" , description = "All user Operations like ADD, DELETE, GET, PUT")
public class HomeController {

	@Autowired
	private UserService us;

	@Operation(summary = "Single User Registration" , description = "Adding Single User in database")
	@PostMapping("/addUser")
	public ResponseEntity<?> addSingleUser(@RequestBody @Valid User u) {

		User singleuser = us.addSingleUserInService(u);

		log.info("User details :" + singleuser);

		return new ResponseEntity(singleuser, HttpStatus.OK);
	}

	@Operation(summary = "Multiple User Registration" , description = "Adding Multiple User in database")
	@PostMapping("/addMultipleUser")
	public ResponseEntity<?> addMultipleUser(@RequestBody @Valid List<User> u) {

		List<User> multipleuser = us.addMultipleUserInService(u);
		log.info("Multiple User Details :" + multipleuser);

		return new ResponseEntity(multipleuser, HttpStatus.OK);
	}

	
	@Operation(summary = "Show All User" , description = "Displaying all User in database")
	@GetMapping("/findAllUser")
	public ResponseEntity<?> findAllUser() {

		List<User> findAllUsers = us.findAllUserInService();

		return new ResponseEntity(findAllUsers, HttpStatus.OK);
	}

	
	@Operation(summary = "Show (Path Variable) All User By Id" , description = "Displaying all User in database from id")
	@GetMapping("/findById/{uid}")
	public ResponseEntity<?> findUserById(@PathVariable("uid") int id) {

		Optional<User> findUserById = us.findUserByIdInService(id);

		log.info("findUserById :" + findUserById);

		return new ResponseEntity(findUserById, HttpStatus.OK);

	}

	@Operation(summary = "Show (Request Param) All Multiple User By Id" , description = "Displaying all Multiple User in database from id")
	@GetMapping("/findAllUserById")
	public ResponseEntity<?> findAllUserById(@RequestParam("uid") List<Integer> id) {

		Iterable<User> findAllUserById = us.findAllUserByIdInService(id);

		log.info("findAllUserById (we are) in Controller :" + findAllUserById);

		return new ResponseEntity(findAllUserById, HttpStatus.OK);
	}

	
	@Operation(summary = "Delete User By User Object" , description = "Delete User in database from object")
	@DeleteMapping("/deletebyuserobject")
	public ResponseEntity<?> deleteByPassingObjectofUser(@RequestBody User u) {

		List<User> listofuserafterdeletion = us.deleteSingleUserByPasingObjectofUser(u);

		log.info("User list after deletion :" + listofuserafterdeletion);

		return new ResponseEntity(listofuserafterdeletion, HttpStatus.OK);
	}

	
	@Operation(summary = "Delete User By User Id" , description = "Delete User in database from id")
	@DeleteMapping("/deletebyuserid/{uid}")
	public ResponseEntity<?> deleteByIdofUser(@PathVariable("uid") int id) {

		List<User> listofuserafterdeletion = us.deleteSingleUserByIdofUser(id);

		log.info("User list after deletion :" + listofuserafterdeletion);

		return  new ResponseEntity(listofuserafterdeletion, HttpStatus.OK);
	}

	
	@Operation(summary = "Delete Multiple User By Id" , description = "Delete Multiple User in database from id")
	@DeleteMapping("/deleteAllUserById")
	public ResponseEntity<?> deleteAllUserById(@RequestParam("uid") List<Integer> id) {

		Iterable<User> deleteAllUserById = us.deleteAllUserByIdInService(id);

		log.info("More than one Users deleted by id ::" + deleteAllUserById);

		return new ResponseEntity(deleteAllUserById, HttpStatus.OK);
	}

	
	@Operation(summary = "Delete Multiple User By Object" , description = "Delete Multiple User in database from Object")
	@DeleteMapping("/deleteMultipleUser")
	public ResponseEntity<?> deleteMultipleUserfromUserObject(@RequestBody List<User> u) {

		List<User> multipleuser = us.deleteMultipleUserInService(u);
		log.info("Multiple Users Deleted by passing user object :" + multipleuser);

		return new ResponseEntity(multipleuser, HttpStatus.OK);
	}

	
	@Operation(summary = "Delete All Users" , description = "Delete all the User present in database")
	@DeleteMapping("/deleteAllNoParameters")
	public ResponseEntity<?> deleteAllStudentsNoanyParameters() {

		Iterable<User> allUsersDeleted = us.deleteAllUsersInServiceNoParameters();

		log.info("All Users Deleted by without passing any parameter :" + allUsersDeleted);

		return new ResponseEntity(allUsersDeleted, HttpStatus.OK);

	}

	//long count = sr.count();
//	System.out.println(count);
	@Operation(summary = "Count All Users" , description = "Count all the User present in database")
	@GetMapping("/countUser")
	public ResponseEntity<?> countOFUser() {
		
		long UserCount = us.countUserInService();
		
		log.info("User Count :" + UserCount);
		
		return new ResponseEntity(UserCount, HttpStatus.OK);
	}
	
	
//	boolean existsById = sr.existsById(1);
//	System.out.println(existsById);
	
	@Operation(summary = "Search user exist by id" , description = "Return true or false based on id whether it is exist or not")
	@GetMapping("/existbyid/{uid}")
	public ResponseEntity<?> existById(@PathVariable("uid") int id) {
		
		String existById = us.existByIdInService(id);
		
		return new ResponseEntity(existById, HttpStatus.OK);
		
	}
	
	
	@Operation(summary = "Fetch user id for updation" , description = "In this api we fetching user from id for updation")
	@GetMapping("/getId/{uid}")
	public ResponseEntity<?> getUserIdforUpdation(@PathVariable("uid") int id) {
		
		User userUpdation = us.getUserIdforUpdationInService(id);
		
		log.info("User which want to update :" + userUpdation);
		
		return new ResponseEntity(userUpdation, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Update the User" , description = "In this api we are updating the user")
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User u) {
		
		User updatedUser  = us.updateUserInService(u);
		log.info("Updated User : " + updatedUser);
		return new ResponseEntity(updatedUser , HttpStatus.OK);
	}
	
	
	//DTO below api
	
	@Operation(summary = "Fetching only essential data" , description = "In this api we are fetching username , user address , usersalary only")
	@GetMapping("/fetchUser/{uid}")
	@Cacheable("id1")
	@CacheEvict("id1")
	public ResponseEntity<?> fetchUserById(@PathVariable("uid") int id) {
                    log.info("User id :" + id);
		
		UserResponseDTO userResponse = us.fetchUserInService(id);

		log.info("Essential User Data :" + userResponse );

		return new ResponseEntity(userResponse, HttpStatus.OK);

	}
	
	
	
	@Operation(summary = "Sending Mail" , description = "In this api we are sending mail")
	@PostMapping("/sendmail")
	public ResponseEntity<?> sendingMail() {
               
		us.sendMailInService();

		return new ResponseEntity("Mail Sended", HttpStatus.OK);

	}
	
	
	@PutMapping("/updateuserinonemethod/{uid}")
	public ResponseEntity<?> updateUserInOneMethod(@PathVariable("uid") int id, @RequestBody User u){
		
		User updatedUser = us.updateUserInOneMethodInService(id , u);
		
		return new ResponseEntity(updatedUser, HttpStatus.OK);

	}
	
	
}
