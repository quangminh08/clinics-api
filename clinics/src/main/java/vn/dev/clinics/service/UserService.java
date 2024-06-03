package vn.dev.clinics.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import vn.dev.clinics.entity.User;
import vn.dev.clinics.model.UserModel;

@Service
public class UserService extends BaseService<User>{

	@Override
	public Class<User> clazz() {
		return User.class;
	}
	
	@Autowired
	private RoleService roleService;
	
	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM clinics04.tbl_user where username='" + username + "';";
		return super.getEntityByNativeSql(sql);
	}
	
	public UserModel getUserModelByUsernameAndPassword(String username, String password) {
		password = (password == null) ? "" : password;
		String sql = "SELECT * FROM clinics04.tbl_user where username='"+ username + "';";
		User entity = super.getEntityByNativeSql(sql);
		BCryptPasswordEncoder b = new BCryptPasswordEncoder(4);
		if(!b.matches(password, entity.getPassword()) || password == null) {
			entity=null;
		}
		
		return userToModel(entity);
	}
	
	public User getUserLogined() {
		Object userLogined = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userLogined != null && userLogined instanceof UserDetails) {
			User user = (User) userLogined;	
			System.out.println("UUSERLOGIN UNAME: " + user.getUsername());
			User userForcus = getUserByUsername(user.getUsername());		 
			return userForcus;
		}
		return new User();
	}
	
	public UserModel getModelById(int id) {
		UserModel model = userToModel(super.getById(id));
		return model;
	}
	
	public List<UserModel> findAllModels(){
		List<UserModel> models = userEntitiesToModels(super.findAll());
		return models;
	}
	
	public List<UserModel> findAllActiveModels(){
		String sql = "SELECT * FROM clinics04.tbl_user where status=1 order by name;";
		List<User> entities = super.executeNativeSql(sql);
		List<UserModel> models = userEntitiesToModels(entities);
		return models;
	}
	
	@Transactional
	public UserModel saveAddCustomer(UserModel model) {
		List<User> users = findAll();
		for(User user : users) {
			if(user.getUsername().equals(model.getUsername())) {
				throw new ExceptionInInitializerError("Tai khoan da ton tai");
			}
		}
		if(model.getRole() != "customer") {
			model.setRole("customer");
		}
		if(model.getPasswrod() != null && model.getPasswrod().trim() != "") {
			model.setPasswrod(new BCryptPasswordEncoder(4).encode(model.getPasswrod()));
		}else {
			throw new NullPointerException("Mật khẩu không để trống");
		}
		User entity = userToEntity(model);
		entity.setCreateDate(new Date());
		entity.addRole(roleService.getRoleByName("customer"));
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public UserModel saveAddDoctor(UserModel model) {
		List<User> users = findAll();
		for(User user : users) {
			if(user.getUsername().equals(model.getUsername())) {
				return null;
			}
		}
		if(model.getRole() != "doctor") {
			model.setRole("doctor");
		}
		if(model.getPasswrod() != null && model.getPasswrod().trim() != "") {
			model.setPasswrod(new BCryptPasswordEncoder(4).encode(model.getPasswrod()));
		}else {
			throw new NullPointerException("Mật khẩu không để trống");
		}
		User entity = userToEntity(model);
		entity.setCreateDate(new Date());
		entity.addRole(roleService.getRoleByName("doctor"));
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public UserModel updateUser(UserModel model, Integer id) {
		User entity = super.getById(id);
		if(model.getPasswrod() != null && model.getPasswrod().trim() != "") {
			model.setPasswrod(new BCryptPasswordEncoder(4).encode(model.getPasswrod()));
		}else {
			throw new NullPointerException("Mật khẩu không để trống");
		}
		entity.setUpdateDate(new Date());
		entity.setName(model.getName());
		entity.setDateOfBirth(model.getDateOfBirth());
		entity.setDescription(model.getDescription());
		entity.setSalary(BigDecimal.valueOf(model.getSalary()));
		entity.setSpectialty(model.getSpectialty());
		entity.setAddress(model.getAddress());
		entity.setPhoneNumber(model.getPhoneNumber());
		entity.setPersonId(model.getPersonId());
		super.saveOrUpdate(entity);
		return model;
	}
	
	@Transactional
	public void deleteById(Integer id) {
		User user = super.getById(id);
		user.setStatus(!user.getStatus());
		super.saveOrUpdate(user);
	}
	
	
	List<UserModel> userEntitiesToModels(List<User> doctorEntities){
		List<UserModel> doctors = doctorEntities.stream()
				.map(entity -> new UserModel(
						entity.getId(),
						entity.getCreateDate(),
						entity.getUpdateDate(),
						entity.getUsername(),
						entity.getPassword(),
						entity.getName(),
						entity.getAvatar(),
						entity.getDateOfBirth(),
						entity.getSpectialty(),
						entity.getSalary().doubleValue(),
						entity.getDescription(),
						entity.getPhoneNumber(),
						entity.getAddress(),
						entity.getPersonId(),
						entity.getRole(),
						entity.getStatus()
						)).collect(Collectors.toList());
		return doctors;
	}
	
	UserModel userToModel(User entity) {
			if(entity == null) {
				return null;
			}
				UserModel model = new UserModel();
				model.setId(entity.getId());
				model.setCreateDate(entity.getCreateDate());
				model.setUpdateDate(entity.getUpdateDate());
				model.setUsername(entity.getUsername());
				model.setPasswrod(entity.getPassword());
				model.setName(entity.getName());
				model.setAvatar(entity.getAvatar());
				model.setDateOfBirth(entity.getDateOfBirth());
				model.setSpectialty(entity.getSpectialty());
				model.setSalary(entity.getSalary().doubleValue());
				model.setDescription(entity.getDescription());
				model.setPhoneNumber(entity.getPhoneNumber());
				model.setAddress(entity.getAddress());
				model.setPersonId(entity.getPersonId());
				model.setRole(entity.getRole());
				model.setStatus(entity.getStatus());
			return model;
	}
	
	User userToEntity(UserModel model) {
		User entity = new User();
		entity.setId(model.getId());
		entity.setCreateDate(model.getCreateDate());
		entity.setUpdateDate(model.getUpdateDate());
		entity.setUsername(model.getUsername());
		entity.setPassword(model.getPasswrod());
		entity.setName(model.getName());
		entity.setAvatar(model.getAvatar());
		entity.setDateOfBirth(model.getDateOfBirth());
		entity.setSpectialty(model.getSpectialty());
		entity.setSalary(BigDecimal.valueOf(model.getSalary()));
		entity.setDescription(model.getDescription());
		entity.setPhoneNumber(model.getPhoneNumber());
		entity.setAddress(model.getAddress());
		entity.setPersonId(model.getPersonId());
		entity.setRole(model.getRole());
		entity.setStatus(model.getStatus());
		return entity;
	}

	public List<UserModel> getModelByModelSearch(String name, String phone) {
		String sql = "SELECT * FROM clinics04.tbl_user";
		
		if (name != null && name.trim() != "") {
			sql = sql + " where name like '%" + name +"%' ";
			
			if(phone != null && phone.trim() != "") {
				sql = sql + " and phone_number='" + phone +"' order by id desc;";
			}else {
				sql = sql + " order by id desc";
			}
		} else if(phone != null && phone.trim() != "") {
			sql = sql + " where phone_number='" + phone + "' order by id desc;";
		}else {
			sql = sql + " order by id desc";
		}
		
		
		
		List<User> entities = super.executeNativeSql(sql);
		return userEntitiesToModels(entities);
	}

	@Transactional
	public UserModel uploadAvatar(String url, Integer userId) {	
		User entity = super.getById(userId);
		entity.setAvatar(url);
		super.saveOrUpdate(entity);
		return userToModel(entity);
	}

}
