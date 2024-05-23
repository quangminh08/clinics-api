package vn.dev.clinics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.dev.clinics.auth.AuthenticationRequest;
import vn.dev.clinics.auth.AuthenticationResponse;
import vn.dev.clinics.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(
							@RequestBody AuthenticationRequest authenticationRequest){
		
		System.out.println("UUUUUNAMANAM" + authenticationRequest.getUsername());
		System.out.println("UUUUUNAMANAM" + authenticationRequest.getPassword());
		return (ResponseEntity<AuthenticationResponse>) ResponseEntity.ok(
						authenticationService.authenticate(authenticationRequest));
	}
	
}
