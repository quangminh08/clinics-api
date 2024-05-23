package vn.dev.clinics.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.dev.clinics.entity.User;

@Service
public class UserDetailsServiceImpl extends BaseService<User> implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String sql = "SELECT * FROM tbl_user WHERE username = '" + username + "' AND status = 1";
		
		User user = this.getEntityByNativeSql(sql);
		
		try {
			return user;
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
		}
		return new User();
	}

	@Override
	public Class<User> clazz() {
		return User.class;
	}
	
	
}
