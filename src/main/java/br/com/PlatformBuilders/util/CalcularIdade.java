package br.com.PlatformBuilders.util;

import java.time.LocalDate;
import java.time.Period;

import br.com.PlatformBuilders.model.Cliente;

public class CalcularIdade {
	public static int idade(Cliente cliente) {
		
		return(int) Period.between(cliente.getDataNascimento(), LocalDate.now()).getYears();
	}
}
