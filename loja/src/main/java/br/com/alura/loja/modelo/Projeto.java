package br.com.alura.loja.modelo;

import com.thoughtworks.xstream.XStream;

public class Projeto {

	private Long id;
	private String nome;
	private Integer anoInicio;
	
	public Projeto() {
	}

	public Projeto(Long id, String nome, Integer anoInicio) {
		super();
		this.id = id;
		this.nome = nome;
		this.anoInicio = anoInicio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAnoInicio() {
		return anoInicio;
	}

	public void setAnoInicio(Integer anoInicio) {
		this.anoInicio = anoInicio;
	}
	
	
	public String toXML(){
		return new XStream().toXML(this);
	}
	
}	
