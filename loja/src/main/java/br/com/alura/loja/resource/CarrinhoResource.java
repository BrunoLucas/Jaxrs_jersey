package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{id}")
	public Carrinho busca(@PathParam(value="id")Long id) {
	    Carrinho carrinho = new CarrinhoDAO().busca(id);
	    return carrinho;

	}
	
	@Path("adiciona")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho){
        new CarrinhoDAO().adiciona(carrinho);
        URI uri = URI.create("/carrinhos/" + carrinho.getId());
        return Response.created(uri).build();
	}
	
	@DELETE
	@Path("{idCarrinho}/produtos/{idProduto}")
	public Response removeProduto(@PathParam("idCarrinho") Long idCarrinho, @PathParam("idProduto") Long idProduto){
		Carrinho carrinho = new CarrinhoDAO().busca(idCarrinho);
		carrinho.remove(idProduto);
		return Response.ok().build();
	}
	
	@PUT
	@Path("{idCarrinho}/produtos/{idProduto}")
	public Response alteraProduto(@PathParam("idCarrinho") Long idCarrinho, @PathParam("idProduto") Long idProduto, String conteudo){
		Carrinho carrinho = new CarrinhoDAO().busca(idCarrinho);
		Produto produto  = (Produto) new XStream().fromXML(conteudo);
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
	}
}
