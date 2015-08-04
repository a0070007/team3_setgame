package iss.sa40.team3.business;

import iss.sa40.team3.model.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class CardBean {

    
    public List<Card> getShuffledDeck(){
        List<Card> cards = new ArrayList<>(81);
        for(int number = 1; number< 4; number++){
            for(int shape = 1; shape < 4; shape++){
                for(int shading = 1; shading < 4; shading++){
                    for(int color = 1; color < 4; color++){
                        cards.add(new Card(number, shape, shading, color));
                    }
                }
            }
        }
        Collections.sort(cards);
        return cards;
    }
    
    public List<Object> issue12Cards(List<Card> deck, Card[] table) {
        List<Object> list = new ArrayList<>();
        for (int i=0; i < 12; i++) {
                Card card = deck.remove(deck.size() - 1); 
                table[i] = card;
        }
        
        list.add((List<Card>) deck);
        list.add(table);
        return list;
    }

    public List<Object> issue3Cards(int[] position, List<Card> deck, Card[] table) {
        List<Object> list = new ArrayList<>();
        for (int i=0; i < 3; i++) {
                Card card = deck.remove(deck.size() - 1); 
                table[position[i]] = card;
        }
        
        list.add((List<Card>) deck);
        list.add(table);
        return list;
    }
    
    public Card[] removeCards(int[] position, Card[] table) {
        for(int i =0; i<position.length; i++)    
            table[position[i]] = null;
            
        return table;
    }
    
    public ArrayList<ArrayList<Card>> getAllSets(Card[] cards, boolean findOnlyFirstSet) {
       ArrayList<ArrayList<Card>> result = new ArrayList<ArrayList<Card>>();
       if (cards == null) return result;
       int size = cards.length;
       for (int ai = 0; ai < size; ai++) {
           Card a = cards[ai];
           for (int bi = ai + 1; bi < size; bi++) {
               Card b = cards[bi];
               for (int ci = bi + 1; ci < size; ci++) {
                   Card c = cards[ci];
                   if (validateSet(a, b, c)) {
                       ArrayList<Card> set = new ArrayList<>();
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
    
    public boolean setExists(Card[] cards) {
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
