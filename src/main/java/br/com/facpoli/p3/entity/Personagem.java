package br.com.facpoli.p3.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.com.facpoli.p3.pojo.Cards;

@Entity
public class Personagem {

	@Id
	@GeneratedValue
	private Integer id;
	private String nome;
	private String raridade;
	private String caminhoImagem;
	private String poder;
	private String dureza;
	private String cmc;
	private String habilidade;
	private String imgPoder;

	public Personagem() {

	}
	public Personagem(Cards cards) {
		this.nome = cards.getName();
		this.raridade = cards.getRarity();
		this.caminhoImagem = cards.getImageUrl();
		this.poder = cards.getPower();
		this.dureza = cards.getToughness();
		this.cmc = cards.getCmc();
		this.habilidade = cards.getHabilidade();
		this.imgPoder = cards.getImgpoder();
	}
	
	public String getImgPoder() {
		return imgPoder;
	}
	public void setImgPoder(String imgPoder) {
		this.imgPoder = imgPoder;
	}
	public String getHabilidade() {
		return habilidade;
	}
	public void setHabilidade(String habilidade) {
		this.habilidade = habilidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPoder() {
		return poder;
	}

	public void setPoder(String poder) {
		this.poder = poder;
	}

	public String getDureza() {
		return dureza;
	}

	public void setDureza(String dureza) {
		this.dureza = dureza;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaridade() {
		return raridade;
	}

	public void setRaridade(String raridade) {
		this.raridade = raridade;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public String getCmc() {
		return cmc;
	}

	public void setCmc(String cmc) {
		this.cmc = cmc;
	}

}
