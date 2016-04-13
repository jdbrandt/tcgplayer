import java.util.*;
public class Player
{
    ArrayList<Card> deck;
    ArrayList<Card> prizes;
    ArrayList<Card> discard;
    ArrayList<Card> hand;
    ArrayList<Pokemon> bench;
    Pokemon active;
    
    public Player()
    {
        deck = new ArrayList<Card>();
        prizes = new ArrayList<Card>();
        discard = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        bench = new ArrayList<Pokemon>();
    }
    
    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    
    public ArrayList<Card> getPrizes()
    {
        return prizes;
    }
    
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public ArrayList<Pokemon> getBench()
    {
        return bench;
    }
    
    public Pokemon getActive()
    {
        return active;
    }
    
    public void setDeck(ArrayList<Card> cards)
    {
        deck = cards;
    }
    
    public void setPrizes(ArrayList<Card> cards)
    {
        prizes = cards;
    }
    


    
    /**
     * return value is number of mulligans
     */
    public int drawOpeningHand()
    {
        drawNCards(7);
        
        int mulliganCounter = 0;
        while (!isBasicPokemonInHand())
        {
            for (int i = 0; i < 7; i++)
            {
                deck.add(hand.get(0));
                hand.remove(0);
            }
            shuffle();
            drawNCards(7);
            mulliganCounter++;
        }
        return mulliganCounter;
    }
        


    
    public void draw()
    {
        hand.add(deck.get(0));
        deck.remove(0);
    }
    
    public void shuffle()
    {
        
    }
    
    public void drawNCards(int n)
    {
        for (int i = 0; i < n; i++)
        {
            draw();
        }
    }
    
    public void addPrizeFromDeck()
    {
        prizes.add(deck.get(0));
        deck.remove(0);
    }
    
    public boolean isBasicPokemonInHand()
    {
        for (Card c : hand)
        {
            if (c instanceof BasicPokemon)
            {
                return true;
            }
        }
        return false;
    }
    
    
    
    
    /**
     * PERSONALLY IMPLEMENT THIS METHOD!!!
     */
    public void chooseActivePokemonSetup()
    {
        
    }
    
    /**
     * PERSONALLY IMPLEMENT THIS METHOD!!!
     */
    public void chooseBenchedPokemonSetup()
    {
        
    }
    
    /**
     * PERSONALLY IMPLEMENT THIS METHOD!!!
     */
    public void doTurn()
    {
        
    }
        
}
    
    