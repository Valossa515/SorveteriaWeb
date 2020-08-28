package br.com.felipe.sorveteria.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.com.felipe.sorveteria.DAO.FabricanteDAO;
import br.com.felipe.sorveteria.domain.Fabricante;

@Path("fabricante")
public class FabricanteService {
	@GET
	public String listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> fabricantes = fabricanteDAO.Listar();
		Gson gson = new Gson();
		String json = gson.toJson(fabricantes);
		return json;
	}
	@GET
	@Path("{codigo}")
	public String buscar(@PathParam("codigo") Integer codigo){
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.Buscar(codigo);
		
		Gson gson = new Gson();
		String json = gson.toJson(fabricante);
		
		return json;
	}
	@POST
	public String salvar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.Merge(fabricante);

		String jsonSaida = gson.toJson(fabricante);
		return jsonSaida;
	}
	@PUT
	public String editar(String json) {
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);

		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.Editar(fabricante);

		String jsonSaida = gson.toJson(fabricante);
		return jsonSaida;
	}
	@DELETE
	public String excluir(String json){
		Gson gson = new Gson();
		Fabricante fabricante = gson.fromJson(json, Fabricante.class);
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricante = fabricanteDAO.Buscar(fabricante.getId());
		fabricanteDAO.Excluir(fabricante);
		
		String saida = gson.toJson(fabricante);
		return saida;
	}
	
}
