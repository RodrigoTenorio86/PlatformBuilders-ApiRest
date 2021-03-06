package br.com.PlatformBuilders.endpoints;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.PlatformBuilders.config.security.TokenService;
import br.com.PlatformBuilders.endpoints.dto.TokenDTO;
import br.com.PlatformBuilders.endpoints.form.LoginForm;

@RestController
@RequestMapping("/v1/auth")
public class AutenticacaoController {
	@Autowired
	private AuthenticationManager authManager;
	
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form){
		
		UsernamePasswordAuthenticationToken dadaosLogin=form.converter();
		
		try {
		Authentication authentication = authManager.authenticate(dadaosLogin);
		String token = tokenService.gerarToken(authentication);
		return ResponseEntity.ok(new TokenDTO(token,"Bearer"));
		}catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
