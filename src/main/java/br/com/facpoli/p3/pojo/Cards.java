package br.com.facpoli.p3.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cards {

	@JsonProperty("foreignNames")
	private List<ForeignNames> foreignNames = new ArrayList<ForeignNames>();
	private String name;
	private String manacost;
	private String rarity;
	private String imageUrl;
	private String power;
	private String toughness;
	private String cmc;
	private String imgpoder;

	public String getImgpoder() {

		String aux = getManacost();
		imgpoder = "U.jpg";
		if( aux != null){
			for (String aux2 : aux.split("{")) {
				if(!isNumber(aux2)){
					imgpoder = aux2.replace("}",".jpg");
					
				}else{
					setCmc(aux2);
				}
			}
		}
		return "/img/" + imgpoder;
	}

	public void setImgpoder(String imgpoder) {
		this.imgpoder = imgpoder;
	}

	public List<ForeignNames> getForeignNames() {
		return foreignNames;
	}

	public void setForeignNames(List<ForeignNames> foreignNames) {
		this.foreignNames = foreignNames;
	}

	public String getHabilidade() {
		return getPower() + "/" + getToughness();
	}

	public String getManacost() {
		return manacost;
	}

	public void setManacost(String manacost) {
		this.manacost = manacost;
	}

	public String getCmc() {
		return cmc;
	}

	public void setCmc(String cmc) {
		this.cmc = cmc;
	}

	public String getPower() {

		return power != null ? power : "*";
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getToughness() {
		return toughness != null ? toughness : "*";
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private boolean isNumber(String numero) {
		try {
			int x = Integer.parseInt(numero);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
