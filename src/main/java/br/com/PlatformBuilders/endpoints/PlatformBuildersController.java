package br.com.PlatformBuilders.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.PlatformBuilders.model.Cliente;
import br.com.PlatformBuilders.repository.PlatformBuildersRepository;

@RestController
@RequestMapping(path = "v1/cliente", produces = { "application/json" }, consumes = { "application/json" })

public class PlatformBuildersController {

	private final PlatformBuildersRepository repository;

	@Autowired
	public PlatformBuildersController(PlatformBuildersRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public ResponseEntity<?> listAll() {
		List<Cliente> clientes = repository.findAll();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findClientById(@PathVariable("id") Long id) {
		Cliente cliente = repository.getOne(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> saveClient(@RequestBody Cliente cliente) {
		Cliente clienteCreate = repository.save(cliente);
		return new ResponseEntity<>(clienteCreate, HttpStatus.CREATED);
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") Long id ){
		repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
