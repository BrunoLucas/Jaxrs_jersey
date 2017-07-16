package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	public static void main(String[] args) {
		
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI uri = URI.create("http://localhost:8080/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Servidor rodando");
		try {
			System.in.read();
			server.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static HttpServer iniciaServidor(){
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI uri = URI.create("http://localhost:8080/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Servidor rodando");
		return server;
	}
}
