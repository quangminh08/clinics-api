package vn.dev.clinics.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.dev.clinics.model.UserModel;
import vn.dev.clinics.service.CloudinaryService;
import vn.dev.clinics.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
    private CloudinaryService cloudinaryService;
	
	@GetMapping("")
	public List<UserModel> patients(){
		return userService.findAllActiveModels();
	}
	
	@GetMapping("/search")
	public List<UserModel> searchPatient(@RequestParam(value="name", required = false) String name, 
			@RequestParam(value="phone", required = false) String phone){
		
		return userService.getModelByModelSearch(name,phone);
	}
	
	@GetMapping("/{id}")
	public UserModel patient(@PathVariable("id") int id){
		return userService.getModelById(id);
	}
	
	@PostMapping("/add-customer")
	public UserModel saveAddCustomer(@RequestBody UserModel model, 
			@RequestParam(value = "file", required = false) MultipartFile file) {
		if(file != null) {
			Map data = this.cloudinaryService.upload(file);
			model.setAvatar(data.get("url").toString());
		}
		
		if (model.getId() == null || model.getId() <=0 ) {
			return userService.saveAddCustomer(model);
		}
		return null;
	}
	
	
	
	@PostMapping("/add-doctor")
	public UserModel saveAddDoctor(@RequestBody UserModel model,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		if(file != null) {
			Map data = this.cloudinaryService.upload(file);
			model.setAvatar(data.get("url").toString());
		}
		if (model.getId() == null || model.getId() <=0 ) {
			return userService.saveAddDoctor(model);
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") int id) {
		userService.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public UserModel updateUser(@RequestBody UserModel model, @PathVariable("id") int id,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		if(file != null) {
			Map data = this.cloudinaryService.upload(file);
			model.setAvatar(data.get("url").toString());
		}
		return userService.updateUser(model, id);
	}
	
	@PostMapping("/upload-avatar/{userId}")
    public UserModel uploadImage(@RequestParam("file")MultipartFile file, @PathVariable("userId") Integer userId){
        Map data = this.cloudinaryService.upload(file);
        
        return userService.uploadAvatar(data.get("url").toString(), userId);
//        return new ResponseEntity<>(data, HttpStatus.OK);
    }
	
}
