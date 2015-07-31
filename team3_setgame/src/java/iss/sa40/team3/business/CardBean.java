package iss.sa40.team3.business;

import iss.sa40.team3.model.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class CardBean {

    
    public List<Card> getShuffledDeck(){
        List<Card> cards = new ArrayList<Card>(81);
        for(int number = 0; number<3; number++){
            for(int shape = 0; shape < 3; shape++){
                for(int shading = 0; shading < 3; shading++){
                    for(int color = 0; color < 3; color++){
                        cards.add(new Card(number, shape, shading, color));
                    }
                }
            }
        }
        Collections.sort(cards);
        return cards;
    }
    
    public List<List<Card>> issueCards(List<Card> deck, List<Card> table, int cardCount) {
        List<List<Card>> list = new ArrayList<>();
        for (int i=0; i < cardCount; i++) {
                table.add(deck.remove(deck.size() - 1));
        }
        
        list.add((List<Card>) deck);
        list.add((List<Card>)table);
        return list;
    }

    public void removeCards(List<Card> set, List<Card> table) {
            for (Card card : set) {
               table.remove(card);
            }
    }
    
    public ArrayList<ArrayList<Card>> getAllSets(List<Card> cards, boolean findOnlyFirstSet) {
       ArrayList<ArrayList<Card>> result = new ArrayList<ArrayList<Card>>();
       if (cards == null) return result;
       int size = cards.size();
       for (int ai = 0; ai < size; ai++) {
           Card a = cards.get(ai);
           for (int bi = ai + 1; bi < size; bi++) {
               Card b = cards.get(bi);
               for (int ci = bi + 1; ci < size; ci++) {
                   Card c = cards.get(ci);
                   if (validateSet(a, b, c)) {
                       ArrayList<Card> set = new ArrayList<Card>();
                       set.add(a);
                       set.add(b);
                       set.add(c);
                       result.add(set);
                       if (findOnlyFirstSet) return result;
                   }
               }
           }
       }
       return result;
   }
    
    public boolean setExists(List<Card> cards) {
        return getAllSets(cards, true).size() > 0;
    }
    
    public boolean validateSet (Card a, Card b, Card c){
        
        if (!((a.getNumber() == b.getNumber()) && (b.getNumber() == c.getNumber()) ||
                (a.getNumber() != b.getNumber()) && (a.getNumber() != c.getNumber()) && (b.getNumber() != c.getNumber()))) {
            return false;
        }
        if (!((a.getShape() == b.getShape()) && (b.getShape() == c.getShape()) ||
                (a.getShape() != b.getShape()) && (a.getShape() != c.getShape()) && (b.getShape() != c.getShape()))) {
            return false;
        }
        if (!((a.getShading() == b.getShading()) && (b.getShading() == c.getShading()) ||
                (a.getShading() != b.getShading()) && (a.getShading() != c.getShading()) && (b.getShading() != c.getShading()))) {
            return false;
        }
        if (!((a.getColor() == b.getColor()) && (b.getColor() == c.getColor()) ||
                (a.getColor() != b.getColor()) && (a.getColor() != c.getColor()) && (b.getColor() != c.getColor()))) {
            return false;
        }
        return true;
    }
    
}
