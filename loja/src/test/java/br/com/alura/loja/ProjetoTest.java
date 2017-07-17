package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Projeto;

public class ProjetoTest {
	
	private HttpServer server;
	private Client client;
	private WebTarget target;
	
	@Before
	public void setUp(){
		server = Servidor.iniciaServidor();
		client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080");
        
	}
	
	@After
	public void after(){
		server.stop();
	}

    @Test
    public void testaQueOServicoProjetosFunciona() {

    	Projeto projeto = target.path("/projetos/1").request().get(Projeto.class);
		Assert.assertEquals("Minha loja",projeto.getNome());
		
    }
    
    @Test
    public void testaAdicaoNovoProjeto(){
    	
        Projeto projeto = new Projeto();
        projeto.setId(13L);
        projeto.setNome("Nome projeto teste");
        projeto.setAnoInicio(2017);
        
        Entity<Projeto> entity = Entity.entity(projeto, MediaType.APPLICATION_XML);
        Response response = target.path("/projetos/adiciona").request().post(entity);
        Assert.assertEquals(201, response.getStatus());
        
        String location = response.getHeaderString("Location");
        Projeto projetoCarregado = client.target(location).request().get(Projeto.class);
        Assert.assertEquals("Nome projeto teste", projetoCarregado.getNome());
        
   }
    

}
