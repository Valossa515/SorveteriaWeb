package br.com.felipe.sorveteria.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("sorveteria")
public class SorveteriaService {
	
	@GET
	public String exibir() {
		return "Sorveteria";
	}
}
