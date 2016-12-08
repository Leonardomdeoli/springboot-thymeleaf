package br.com.facpoli.p3.controller;

import java.util.List;

import org.h2.util.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import br.com.facpoli.p3.entity.Personagem;
import br.com.facpoli.p3.pojo.Cards;
import br.com.facpoli.p3.pojo.CardsResponse;
import br.com.facpoli.p3.pojo.ForeignNames;
import br.com.facpoli.p3.repository.PersonagemRepository;

@Controller
public class CardsController {

	@Autowired
	PersonagemRepository personagemRepository;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("carta", new Personagem());
		model.addAttribute("cards", personagemRepository.findAll());
		return "home";
	}

	@GetMapping("/admin")
	public String logar() {
		return "login";
	}
	
	
	@GetMapping("/buscar")
	public String buscar(@RequestParam String nome, Model model) {
		
		model.addAttribute("carta", new Personagem());
		model.addAttribute("cards", personagemRepository.findByNomeContainingIgnoreCase(nome));
		
		return "home";
	}

	@GetMapping("/import")
	public String importCards(Model model) {

		CardsResponse cardsResponse = getCards();

		List<Cards> cards = cardsResponse.getCards();
		for (Cards card : cards) {
				
			List<ForeignNames> foreignNames =  card.getForeignNames();
			
			for (ForeignNames fore : foreignNames) {
				if("Portuguese (Brazil)".equals(fore.getLanguage())){
					personagemRepository.save(new Personagem(card));
					break;
				}
			}
		}
		return "redirect:/";
	}

	public CardsResponse getCards() {

		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
		HttpEntity<CardsResponse> entity = new HttpEntity<CardsResponse>(headers);
		ResponseEntity<CardsResponse> res = new RestTemplate().exchange(
				"https://api.magicthegathering.io/v1/cards?set=ROE", HttpMethod.GET, entity, CardsResponse.class);

		return res.getBody();
	}

}
