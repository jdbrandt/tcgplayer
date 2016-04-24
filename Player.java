import java.util.*;
public abstract class Player
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
    
    public ArrayList<Card> getDiscard()
    {
        return discard;
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
    
    public void setDiscard(ArrayList<Card> cards)
    {
        discard = cards;
    }
    
    public void setHand(ArrayList<Card> cards)
    {
        hand = cards;
    }
    
    public void setBench(ArrayList<Pokemon> cards)
    {
        bench = cards;
    }
    
    public void setActive(Pokemon mon)
    {
        active = mon;
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
    /**
     * 
     NEED TO IMPLEMENT
     */
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
    
    public void movePokemonAndAttachedToHand(Game g, Pokemon p)
    {
        for (int i = 0; i < p.getAttachedPokemon().size(); i++)
        {
            hand.add(p.getAttachedPokemon().remove(i));
        }
        
        for (int i = 0; i < p.getAttachedTools().size(); i++)
        {
            hand.add(p.getAttachedTools().remove(i));
        }
        
        for (int i = 0; i < p.getAttachedEnergy().size(); i++)
        {
            hand.add(p.getAttachedEnergy().remove(i));
        }
        
        
        hand.add(p);
        
        if (p.equals(active))
        {
            active = null;
            replaceActivePokemon(g);
        }
        
        else 
        {
            bench.remove(p);
        }
        
    }
    
    public void movePokemonToHandDiscardAttached(Game g, Pokemon p)
    {
        for (int i = 0; i < p.getAttachedPokemon().size(); i++)
        {
            discard.add(p.getAttachedPokemon().remove(i));
        }
        
        for (int i = 0; i < p.getAttachedTools().size(); i++)
        {
            discard.add(p.getAttachedTools().remove(i));
        }
        
        for (int i = 0; i < p.getAttachedEnergy().size(); i++)
        {
            discard.add(p.getAttachedEnergy().remove(i));
        }
        
        hand.add(p);
        
        if (p.equals(active))
        {
            active = null;
            replaceActivePokemon(g);
        }
        
        else
        {
            bench.remove(p);
        }
        
        
    }
    
    
    
    

    public abstract void chooseActivePokemonSetup();

    public abstract void chooseBenchedPokemonSetup();

    public abstract void doTurn();
    
    public abstract void dropDamageCounter(Game g);

    public abstract void dropNDamageCounters(Game g, int n);
    
    public abstract void replaceActivePokemon(Game g);
    
    public abstract Pokemon choosePokemonToAZ(Game g);

    public abstract Energy chooseEnergyToDiscardFromActive(Game g);

}
    
    