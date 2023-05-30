package com.fafycom.trading.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fafycom.trading.entities.Token;
import com.fafycom.trading.entities.User;
import com.fafycom.trading.enums.Role;
import com.fafycom.trading.enums.TokenType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final com.fafycom.trading.repositories.UserRepository repository;
	private final com.fafycom.trading.repositories.TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final com.fafycom.trading.config.JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		
	
		
		var user = User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		var savedUser = repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}


	private void saveUserToken(User user, String jwtToken) {
		var token = Token.builder()
				.user(user)
				.token(jwtToken)
				.tokenType(TokenType.BEARER)
				.revoked(false)
				.expired(false)
				.build();
		tokenRepository.save(token);
	}
	
	
public AuthenticationResponse authenticate(AuthenticationRequest request) {
	authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()		
		)
	);
	
	var user = repository.findByEmail(request.getEmail())
			.orElseThrow();
	var jwtToken = jwtService.generateToken(user);
	revokeAllUserToken(user);
	saveUserToken(user, jwtToken);
	return AuthenticationResponse.builder()
			.token(jwtToken)
			.build();
}

private void revokeAllUserToken(User user) {
	var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
	if(validUserTokens.isEmpty())
		return;
	validUserTokens.forEach(t -> {
		t.setExpired(true);
		t.setRevoked(true);
	});
	tokenRepository.saveAll(validUserTokens);
}

}
