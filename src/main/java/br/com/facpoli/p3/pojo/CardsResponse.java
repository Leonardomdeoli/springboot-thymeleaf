package br.com.facpoli.p3.pojo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardsResponse {

	@JsonProperty("cards")
	private List<Cards> cards = new ArrayList<Cards>();

	public List<Cards> getCards() {
		return cards;
	}

	public void setCards(List<Cards> cards) {
		this.cards = cards;
	}

}