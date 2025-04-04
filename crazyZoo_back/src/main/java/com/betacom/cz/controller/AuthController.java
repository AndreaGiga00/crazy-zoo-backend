package com.betacom.cz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.betacom.cz.dto.LoginDTO;
import com.betacom.cz.dto.RegisterDTO;
import com.betacom.cz.request.LoginRequest;
import com.betacom.cz.request.UtenteRequest;
import com.betacom.cz.response.ResponseObject;
import com.betacom.cz.services.interfaces.AuthServices;
import com.betacom.cz.services.interfaces.UtenteServices;

@RestController
@RequestMapping("/rest/auth")
@CrossOrigin(origins = "${url_api}")
public class AuthController {

	@Autowired
	AuthServices authS;

	@Autowired
	UtenteServices utenteS;

	@PostMapping("/register")
	public ResponseObject<RegisterDTO> register(@RequestBody UtenteRequest request) {
		
		ResponseObject<RegisterDTO> response = new ResponseObject<>();
		response.setRc(true);

		try {
			response.setDati(authS.registerUser(request));
		} catch (Exception e) {
			response.setRc(false);
			response.setMsg(e.getMessage());
		}

		return response;
	}

	@PostMapping("/login")
	public ResponseObject<LoginDTO> login(@RequestBody LoginRequest request) {
		
		ResponseObject<LoginDTO> response = new ResponseObject<>();
		response.setRc(true);

		try {
			response.setDati(authS.authenticate(request));
		} catch (Exception e) {
			response.setRc(false);
			response.setMsg(e.getMessage());
		}

		return response;
	}

}
