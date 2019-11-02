package br.com.PlatformBuilders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.PlatformBuilders.model.Cliente;

@Repository
public interface PlatformBuildersRepository extends JpaRepository<Cliente, Long>{

}
