package com.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.DTO.UserResponseDTO;

import com.Entity.User;
import com.ExceptionHandler.UserNotFoundException;
import com.Repository.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceImpl implements UserService {

	@Autowired
	private UserRepo ur;

	@Autowired
	private JavaMailSender jms;

	@Override
	public User addSingleUserInService(User user) {

		User u = ur.save(user);
		log.info("User Added :" + u);

		return u;
	}

	@Override
	public List<User> addMultipleUserInService(List<User> u) {

		ur.saveAll(u);

		log.info("Multiple User added :" + u);

		return u;
	}

	@Override
	public List<User> findAllUserInService() {
		List<User> findAllUser = (List<User>) ur.findAll();

		log.info("All User List :" + findAllUser);
		return findAllUser;
	}

	@Override
	public Optional<User> findUserByIdInService(int id) {

		Optional<User> findByUserId = ur.findById(id);
		log.info("findByUserId : " + findByUserId);
		return findByUserId;
	}

	@Override
	public Iterable<User> findAllUserByIdInService(List<Integer> id) {

		Iterable<User> findAllById = ur.findAllById(id);

		log.info("All users from Id (we are) in service :" + findAllById);

		return findAllById;
	}

	@Override
	public List<User> deleteSingleUserByPasingObjectofUser(User u) {

		ur.delete(u);

		List<User> findAllUser = (List<User>) ur.findAll();
		log.info("User Deleted by passing user Object :" + findAllUser);

		return findAllUser;

	}

	@Override
	public List<User> deleteSingleUserByIdofUser(int id) {

		ur.deleteById(id);

		List<User> findAllUser = (List<User>) ur.findAll();
		log.info("Single User Deleted by passing user id :" + findAllUser);

		return findAllUser;
	}

	@Override
	public Iterable<User> deleteAllUserByIdInService(List<Integer> id) {

		ur.deleteAllById(id);

		Iterable<User> findAllUser = ur.findAll();

		log.info("More than one users deleted by id :" + findAllUser);

		return findAllUser;

	}

	@Override
	public List<User> deleteMultipleUserInService(List<User> u) {
		ur.deleteAll(u);

		List<User> findAllUser = (List<User>) ur.findAll();
		log.info("Multiple Users Deleted by passing user object :" + findAllUser);

		return findAllUser;

	}

	@Override
	public Iterable<User> deleteAllUsersInServiceNoParameters() {

		ur.deleteAll();

		Iterable<User> findAllUser = ur.findAll();

		log.info("All Users deleted without any parameters passing :" + findAllUser);

		return findAllUser;

	}

	@Override
	public long countUserInService() {
		long count = ur.count();

		log.info("Count of User :" + count);

		return count;
	}

	@Override
	public String existByIdInService(int id) {

		String existsById = ur.existsById(id) ? "User Present " : "User Not Present";
		log.info("User exist or not :" + existsById);
		return existsById;
	}

	@Override
	public User getUserIdforUpdationInService(int id) {

		User user = ur.findById(id).get();
		log.info("User For Updation :" + user);

		return user;
	}

	@Override
	public User updateUserInService(User u) {
		User existingUser = getUserIdforUpdationInService(u.getUid());

		if (u.getUname() != null)
			existingUser.setUname(u.getUname());
		if (u.getUaddress() != null)
			existingUser.setUaddress(u.getUaddress());
		if (u.getUsalary() != null)
			existingUser.setUsalary(u.getUsalary());
		if (u.getUcontact() != null)
			existingUser.setUcontact(u.getUcontact());
		if (u.getUpassword() != null)
			existingUser.setUpassword(u.getUpassword());

		return ur.save(existingUser);

//		User updatedUser = ur.save(u);
//		log.info("Update User :" + updatedUser);

	}

	@Override
	public UserResponseDTO fetchUserInService(int id) {
//		User user = ur.findById(id).get();
		User user = ur.findById(id).orElseThrow(() -> new UserNotFoundException("User id invalid"));

		log.info("User :" + user);

		ModelMapper convert = new ModelMapper();
		UserResponseDTO response = convert.map(user, UserResponseDTO.class);

		return response;

//	User user = ur.findById(id).orElseThrow(n -> new UserNotFoundException("User id invalid"));

//		user = null;	
//		if(user==null) {
//			throw new UserNotFoundException("User id inavlid");
//		}

//		String str = null;  
//		System.out.println(str.toLowerCase());

		// ModelMapper Library --> it will map ur pojo class to dto class --> 2.4.5 add
		// dependency
		// it automatically scan source--user & destination--dto

		// Manually set --> not recommended so use model mapper
//		UserResponseDTO response = new UserResponseDTO();
//		response.setUname(user.getUname());
//		response.setUaddress(user.getUaddress());
//		response.setUsalary(user.getUsalary());

//		User user = ur.findById(id).get();
//		if(user!=null) {
//			
//			log.info("User :" + user);
//			
//			ModelMapper convert = new ModelMapper();
//			UserResponseDTO response = convert.map(user, UserResponseDTO.class);
//			return response;
//		}else {
//			 throw new UserNotFoundException("id invalid ");
//		}
//		

	}

	@Override
	public void sendMailInService() {

//		SimpleMailMessage sms = new SimpleMailMessage();
//		sms.setTo("ssarang469@gmail.com");
//		sms.setCc("ssarang469@gmail.com");
//		sms.setText("hello from spring boot");
//		sms.setSubject("---mail testing---");
//		
//		jms.send(sms);

		MimeMessage msg = jms.createMimeMessage();
		try {

			MimeMessageHelper m = new MimeMessageHelper(msg, true);

			String[] recipients = { "ssarang469@gmail.com" };
			m.setTo(recipients);
			m.setSubject("About Joining");

			String html = "<!DOCTYPE html>" + "<html>"
					+ "<body style='font-family:Comic Sans MS, sans-serif; background:#fffbe6; padding:20px;'>"
					+ "  <div style='max-width:600px; margin:auto; background:#fff; border:2px dashed #ff9800; padding:20px; border-radius:10px;'>"
					+ "    <h2 style='color:#4caf50;'>🎉 Congrats, Champ! 🎉</h2>"
					+ "    <p>You've been <strong>selected!</strong> 🥳</p>"
					+ "    <p>Your offer letter is attached below. 📄</p>"
					+ "    <p><strong>Joining starts tomorrow.</strong> Don’t be late, we already ordered snacks! 😄</p>"
					+ "    <p style='margin-top:20px; font-size:12px; color:#888;'>(Yes, this is real. No, we're not joking... maybe 😜)</p>"
					+ "  </div>" + "</body>" + "</html>";

			m.addAttachment("Offer Letter", new File("G://Exponent B-55//j.jpg"));

			m.setText("Congratulations you are selected your letter has attached below and joining is from tommorrow",
					html);

		} catch (Exception e) {

		}
		jms.send(msg);
	}

	@Override
	public User updateUserInOneMethodInService(int id, User u) {
//		  User existingUser = ur.findById(id).get();
//		User existingUser = ur.findById(id).orElseThrow(() -> new UserNotFoundException("User id invalid"));
//
//		if (u.getUname() != null)
//			existingUser.setUname(u.getUname());
//		if (u.getUaddress() != null)
//			existingUser.setUaddress(u.getUaddress());
//		if (u.getUsalary() != null)
//			existingUser.setUsalary(u.getUsalary());
//		if (u.getUcontact() != null)
//			existingUser.setUcontact(u.getUcontact());
//		if (u.getUpassword() != null)
//			existingUser.setUpassword(u.getUpassword());
//
//		User updatedUser = ur.save(existingUser);
//		return updatedUser;

		User userdb = ur.findById(id).get();
		ModelMapper m = new ModelMapper();
		m.map(u, userdb);
		ur.save(userdb);
		return userdb;

	}

}
