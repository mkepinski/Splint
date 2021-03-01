package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.CardDao;
import project.dao.DeckDao;
import project.data.Card;
import project.data.Deck;
import project.data.User;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/card")
public class CardController {

    private CardDao cardDao;
    private DeckDao deckDao;

    public CardController(CardDao cardDao, DeckDao deckDao) {
        this.cardDao = cardDao;
        this.deckDao = deckDao;
    }

    @RequestMapping(value = "/edit/{card_id}", method = RequestMethod.GET)
    public String editCardForm(Model model,
                               @PathVariable int card_id,
                               HttpSession session) {

        session.setAttribute("card_id_to_edit", card_id);
        model.addAttribute("card", cardDao.findById(card_id));
        return "edit_card";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String processCardForm(@RequestParam String back,
                                  @RequestParam String front,
                                  HttpSession session) {

        Card card = cardDao.findById((int) session.getAttribute("card_id_to_edit"));
        session.removeAttribute("card_id_to_edit");
        card.setBack(back);
        card.setFront(front);
        cardDao.updateCard(card);
        Deck deck = (Deck) session.getAttribute("currentDeck");
        return String.format("redirect:http://localhost:8080/deck/show/deck/%s", deck.getDeckName());
    }

    @RequestMapping(value = "/edit/success", method = RequestMethod.GET)
    public String showEditMessage(Model model,
                                  HttpSession session) {

        Deck deck = deckDao.getDeckByName((String) session.getAttribute("last_deck_name"), (User) session.getAttribute("user"));
        model.addAttribute(deck);
        return "edit_success";
    }

    @RequestMapping(value = "/delete/{card_id}", method = RequestMethod.GET)
    public String deleteCard(Model model,
                             HttpSession session,
                             @PathVariable int card_id) {

        session.setAttribute("card_id_to_delete", card_id);
        model.addAttribute((Deck) session.getAttribute("currentDeck"));
        return "validate_card_delete";
    }

    @RequestMapping(value = "/delete/confirmed", method = RequestMethod.GET)
    public String deleteConfirmed(Model model,
                                  HttpSession session) {

        Card card = cardDao.findById((int) session.getAttribute("card_id_to_delete"));
        session.removeAttribute("card_id_to_delete");
        Deck deck = (Deck) session.getAttribute("currentDeck");
        List<Card> cards = (List<Card>) session.getAttribute("cards");
        cards.remove(card);
        session.setAttribute("cards", cards);
        cardDao.deleteCard(card);
        return String.format("redirect:http://localhost:8080/deck/show/deck/%s", deck.getDeckName());
    }
}
