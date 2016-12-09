package br.com.facpoli.p3.controller;

import java.util.List;

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
		model.addAttribute("cards", personagemRepository.findAll());
		return "home";
	}

	@GetMapping("/login")
	public String logar() {
		return "login";
	}

	@GetMapping("/admin")
	public String importCard(Model model) {
		return "buscar";
	}

	@GetMapping("/import")
	public String importCards(@RequestParam String url, Model model) {

		CardsResponse cardsResponse = getCards(url);

		List<Cards> cards = cardsResponse.getCards();
		for (Cards card : cards) {

			List<ForeignNames> foreignNames = card.getForeignNames();

			for (ForeignNames fore : foreignNames) {
				if ("Portuguese (Brazil)".equals(fore.getLanguage())) {
					personagemRepository.save(new Personagem(card));
					break;
				}
			}
		}
		return "redirect:/";
	}

	@GetMapping("/buscar")
	public String buscar(@RequestParam String nome, Model model) {
		model.addAttribute("cards", personagemRepository.findByNomeContainingIgnoreCase(nome));
		return "home";
	}

	public CardsResponse getCards(String url) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
			HttpEntity<CardsResponse> entity = new HttpEntity<CardsResponse>(headers);
			ResponseEntity<CardsResponse> res = new RestTemplate().exchange(url, HttpMethod.GET, entity,
					CardsResponse.class);

			return res.getBody();

		} catch (Exception e) {
			return new CardsResponse();
		}
	}
}
