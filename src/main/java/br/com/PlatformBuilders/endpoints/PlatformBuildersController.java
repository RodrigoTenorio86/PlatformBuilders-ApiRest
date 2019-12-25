package br.com.PlatformBuilders.endpoints;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.PlatformBuilders.endpoints.dto.ClienteDTO;
import br.com.PlatformBuilders.errors.ResourceNotFoundException;
import br.com.PlatformBuilders.model.Cliente;
import br.com.PlatformBuilders.repository.PlatformBuildersRepository;

@RestController
@RequestMapping(path = "/v1/clients")

public class PlatformBuildersController {

	private final PlatformBuildersRepository _repository;

	@Autowired
	public PlatformBuildersController(PlatformBuildersRepository repository) {
		this._repository = repository;
	}

	@GetMapping
	public ResponseEntity<?> listAll(@PageableDefault(page=0, size =5, sort="id", direction = Direction.DESC ) Pageable paginacao) {
		
		Page<Cliente> clientes = _repository.findAll(paginacao);
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findClientById(@PathVariable("id") Long id) {
		verifyIfClientExists(id);
		Optional<Cliente>  cliente = _repository.findById(id);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> saveClient(@Valid @RequestBody Cliente cliente) {

		Cliente clienteCreate = _repository.save(cliente);
		return new ResponseEntity<>(clienteCreate, HttpStatus.CREATED);
	}

	@PutMapping(path="/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> updateCliente(@PathVariable Long id , @RequestBody Cliente clienteDto) {
		verifyIfClientExists(id);
        Cliente newClient=  _repository.save(clienteDto);
		
		return ResponseEntity.ok(newClient);
	}

	@PatchMapping(path = "/{id}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> changeClient(@Valid @RequestBody Cliente cliente, @PathVariable("id") Long id) {
		verifyIfClientExists(id);
		Cliente change = _repository.saveAndFlush(cliente);
		return new ResponseEntity<>(change, HttpStatus.OK);
	}

	@GetMapping(path = "/findClientByName/{nome}")
	public ResponseEntity<?> findClientByNameIgnoreCaseContaining(@PathVariable("nome") String nome) {
	
		List<Cliente> clientes = _repository.findByNomeIgnoreCaseContaining(nome);
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping(path = "/findByCpf/{cpf}")
	public ResponseEntity<?> findByCpf(@PathVariable String cpf) {
		Cliente cliente = _repository.findByCpf(cpf);
		return new ResponseEntity<>(cliente, HttpStatus.OK);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") Long id) {
		verifyIfClientExists(id);
		_repository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private void verifyIfClientExists(Long id) {
		Optional<Cliente> clientExists = _repository.findById(id);
		if (!clientExists.isPresent())
			throw new ResourceNotFoundException("Client not found for ID " + id);
	}

}
