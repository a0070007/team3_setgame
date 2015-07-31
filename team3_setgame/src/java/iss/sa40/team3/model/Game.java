package iss.sa40.team3.model;

import java.util.List;

public class Game {

    List<Card> Deck;
    List<Card> Table;

    public List<Card> getDeck() {
        return Deck;
    }

    public void setDeck(List<Card> Deck) {
        this.Deck = Deck;
    }

    public List<Card> getTable() {
        return Table;
    }

    public void setTable(List<Card> Table) {
        this.Table = Table;
    }

    @Override
    public String toString() {
        return "Game{" + "Deck=" + Deck + ", Table=" + Table + '}';
    }
    
    
    
}
