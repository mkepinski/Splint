package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.CardDao;
import project.dao.DeckDao;
import project.dao.StudyDao;
import project.data.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/deck")
public class DeckController {

    private CardDao cardDao;
    private StudyDao studyDao;
    private DeckDao deckDao;

    public DeckController(CardDao cardDao, StudyDao studyDao, DeckDao deckDao) {
        this.cardDao = cardDao;
        this.studyDao = studyDao;
        this.deckDao = deckDao;
    }

    @RequestMapping(value = "/newDeck", method = RequestMethod.GET)
    public String addNewDeckForm() {
        return "new_deck";
    }

    @RequestMapping(value = "/newDeck", method = RequestMethod.POST)
    public String processNewDeck(@RequestParam String deckName,
                                 HttpSession session) {
        if(deckDao.deckExists((User) session.getAttribute("user"), deckName)) {
            Deck deck = new Deck();
            deck.setDeckName(deckName);
            deck.setUser((User) session.getAttribute("user"));

            Study study = new Study();
            LocalDate today = LocalDate.now();
            study.setCreated(today);
            studyDao.saveStudy(study);

            deck.setStudy(study);
            deckDao.saveDeck(deck);
            session.setAttribute("currentDeck", deck);
            session.removeAttribute("cards");

            return "redirect:newCard";
        } else {
            return "deck_exists";
        }
    }

    @RequestMapping(value = "/newCard", method = RequestMethod.GET)
    public String addCardToDeckForm() {
        return "new_card";
    }

    @RequestMapping(value = "/newCard", method = RequestMethod.POST)
    public String addCardToDeck(@RequestParam String back,
                                @RequestParam String front,
                                HttpSession session) {

        Card card = new Card();
        card.setBack(back);
        card.setFront(front);
        card.setDeck((Deck) session.getAttribute("currentDeck"));
        cardDao.saveCard(card);

        List<Card> cards;
        if(session.getAttribute("cards") == null) {
            cards = new ArrayList<>();
        } else {
            cards = (List<Card>) session.getAttribute("cards");
        }

        cards.add(card);
        session.setAttribute("cards", cards);

        return "redirect:newCard";
    }

    @GetMapping("/finish")
    public String finishDeck(HttpSession session) {
        Deck deck = (Deck) session.getAttribute("currentDeck");
        List<Card> cards = (List<Card>) session.getAttribute("cards");

        deckDao.updateCurrentDeck(deck, cards);
        return String.format("redirect:show/deck/%s", deck.getDeckName());
    }

    @RequestMapping(value = "show/decks", method = RequestMethod.GET)
    public String showDecks(Model model,
                            HttpSession session) {

        session.removeAttribute("cardsToTrain");
        model.addAttribute("deckList", deckDao.getAllUserDecks((User) session.getAttribute("user")));
        return "show_decks";
    }

    @RequestMapping(value = "show/deck/{deck_name}", method = RequestMethod.GET)
    public String processDeck(Model model,
                              @PathVariable String deck_name,
                              HttpSession session) {

        session.removeAttribute("cardsToTrain");
        session.setAttribute("last_deck_name", deck_name);
        Deck deck = deckDao.getDeckByName(deck_name, (User) session.getAttribute("user"));
        session.setAttribute("currentDeck", deck);
        List<Card> cards = deck.getCards();
        session.setAttribute("cards", cards);

        model.addAttribute("deck", deck);
        model.addAttribute("cards", cards);

        return "show_cards";
    }

    @RequestMapping(value = "/studyDeck/{deck_name}", method = RequestMethod.GET)
    public String studyDeck(Model model,
                            @PathVariable String deck_name,
                            HttpSession session) {

        Deck deck = deckDao.getDeckByName(deck_name, (User) session.getAttribute("user"));
        session.setAttribute("currentDeck", deck);
        session.setAttribute("last_deck_name", deck_name);

        List<Card> cards = deck.getCards();

        List<Card> cardsToTrain;
        if(session.getAttribute("cardsToTrain") == null) {
            cardsToTrain = new ArrayList<>();
            for (Card card : cards) {
                cardsToTrain.add(card);
                cardsToTrain.add(card);
                cardsToTrain.add(card);
            }
        } else {
            cardsToTrain = (List<Card>) session.getAttribute("cardsToTrain");
        }

            Random r = new Random();
            Card nextCard = cardsToTrain.get(r.nextInt(cardsToTrain.size()));
            session.setAttribute("cardsToTrain", cardsToTrain);
            session.setAttribute("next_card", nextCard);

            model.addAttribute(nextCard);

        return "study_card";
    }

    @RequestMapping(value = "/studyCard", method = RequestMethod.GET)
    public String studyCard(Model model,
                            @RequestParam String front,
                            HttpSession session) {

        List<Card> cardsToTrain = (List<Card>) session.getAttribute("cardsToTrain");
        Card card = (Card) session.getAttribute("next_card");
        int indexToRemove = cardsToTrain.indexOf(card);
        cardsToTrain.remove(indexToRemove);
        Deck deck = (Deck) session.getAttribute("currentDeck");

        if(cardsToTrain.isEmpty()) {
            session.removeAttribute("cardsToTrain");
            model.addAttribute(deck);
            return "deck_study_finished";
        }

        session.setAttribute("cardsToTrain", cardsToTrain);

        StringBuilder message = new StringBuilder();
        if(card.getFront().equalsIgnoreCase(front)) {
            message.append("Answer correct!");
        } else {
            message.append("Answer incorrect, correct answer is: ").append(card.getFront());
        }

        model.addAttribute("message", message.toString());
        model.addAttribute("deck", deck);
        return "verify_answer";
    }

    @RequestMapping(value = "/delete/{deck_name}")
    public String deleteDeck(Model model,
                             HttpSession session,
                             @PathVariable String deck_name) {


        model.addAttribute("deckName", deck_name);
        return "validate_deck_delete";
    }

    @RequestMapping(value = "/delete/confirmed/{deck_name}")
    public String deleteConfirmed(Model model,
                                  HttpSession session,
                                  @PathVariable String deck_name) {

        Deck deck = deckDao.getDeckByName(deck_name, (User) session.getAttribute("user"));
        List<Card> cardsToDelete = deck.getCards();

        deckDao.deleteDeck(deck);

        model.addAttribute("deckList", deckDao.getAllUserDecks((User) session.getAttribute("user")));
        return "show_decks";
    }

    @RequestMapping(value = "showScheduledDecks", method = RequestMethod.GET)
    public String showScheduledDecks(Model model,
                                     HttpSession session) {

        model.addAttribute("scheduledDecks", deckDao.getScheduledDecks((User) session.getAttribute("user")));
        return "scheduled_decks";
    }
}
