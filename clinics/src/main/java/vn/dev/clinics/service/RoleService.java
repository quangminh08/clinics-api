package vn.dev.clinics.service;

import vn.dev.clinics.entity.Role;
import vn.dev.clinics.entity.User;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role> {
	
	@Override
	public Class<Role> clazz() {
		return Role.class;
	}
	
	public Role getRoleByName(String roleName) {
		String sql = "select * from clinics04.tbl_role where name='" + roleName + "'";
		return this.getEntityByNativeSql(sql);
	}
	
	public String checkRoleOfUserById(int userId) {
		Role adminRole = getRoleByName("Admin");
		List<User> admins = adminRole.getUsers();
		
		for(User admin : admins) {
			if(admin.getId() == userId) {
				return "Admin";
			}
		}	
		
		Role staffRole = getRoleByName("Staff");
		List<User> staffs = staffRole.getUsers();
		
		for(User staff : staffs) {
			if(staff.getId() == userId) {
				return "Staff";
			}
		}	
		
		return "Customer";
	}
}
