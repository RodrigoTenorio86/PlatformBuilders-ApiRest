package br.com.PlatformBuilders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.PlatformBuilders.model.Cliente;

@Repository
public interface PlatformBuildersRepository extends JpaRepository<Cliente, Long>{
    public List<Cliente> findByNomeIgnoreCaseContaining(String nome);
    public Cliente findByCpf(String cpf);
}
