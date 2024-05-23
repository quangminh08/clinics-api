package vn.dev.clinics.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.clinics.model.UserModel;
import vn.dev.clinics.service.UserService;

@CrossOrigin(value = "http://localhost:5555")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<UserModel> patients(){
		return userService.findAllActiveModels();
	}
	
	@GetMapping("/{id}")
	public UserModel patient(@PathVariable("id") int id){
		return userService.getModelById(id);
	}
	
	@PostMapping("/add-user")
	public UserModel saveAddUser(@RequestBody UserModel model) {
		if (model.getId() == null || model.getId() <=0 ) {
			return userService.saveAddUser(model);
		}
		return null;
	}
	
	@PostMapping("/add-staff")
	public UserModel saveAddStaff(@RequestBody UserModel model) {
		if (model.getId() == null || model.getId() <=0 ) {
			return userService.saveAddUser(model);
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		userService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public UserModel updatePatient(@RequestBody UserModel model, @PathVariable("id") int id) {
		return userService.updateUser(model, id);
	}
	
}
