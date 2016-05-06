import java.util.*;
public abstract class Player
{
    ArrayList<Card> deck;
    ArrayList<Card> prizes;
    ArrayList<Card> discard;
    ArrayList<Card> hand;
    ArrayList<Pokemon> bench;
    Pokemon active;
    
    boolean canKnowPrizes;
    boolean canKnowDeck;
    
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
    
    public void setCanKnowPrizes(boolean bool)
    {
        canKnowPrizes = bool;
    }
    
    public void setCanKnowDeck(boolean bool)
    {
        canKnowDeck = bool;
    }
    
    public boolean getCanKnowPrizes()
    {
        return canKnowPrizes;
    }
    
    public boolean getCanKnowDeck()
    {
        return canKnowDeck;
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
    
    public void scoopUp(Game g, Pokemon p)
    {
        for (int i = 0; i < p.getPokemon().size(); i++)
        {
            hand.add(p.getPokemon().remove(i));
        }
        
        for (int i = 0; i < p.getTools().size(); i++)
        {
            hand.add(p.getTools().remove(i));
        }
        
        for (int i = 0; i < p.getEnergy().size(); i++)
        {
            hand.add(p.getEnergy().remove(i));
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
    
    public void AZ(Game g, Pokemon p)
    {
        for (int i = 0; i < p.getPokemon().size(); i++)
        {
            discard.add(p.getPokemon().remove(i));
        }
        
        for (int i = 0; i < p.getTools().size(); i++)
        {
            discard.add(p.getTools().remove(i));
        }
        
        for (int i = 0; i < p.getEnergy().size(); i++)
        {
            discard.add(p.getEnergy().remove(i));
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
    
    public void shuffleHandIntoDeck()
    {
        for (int i = 0; i < hand.size(); i++)
        {
            deck.add(hand.remove(i));
        }
        
        shuffle();
        
    }
    
    

    public abstract void chooseActivePokemonSetup();

    public abstract void chooseBenchedPokemonSetup();

    public abstract void doTurn();
    
    public abstract void dropDamageCounter(Game g);

    public abstract void dropNDamageCounters(Game g, int n);
    
    public abstract void replaceActivePokemon(Game g);
    
    /**
     * @return: Pokemon was on your Bench or in Active
     */
    public abstract Pokemon chooseToAZ(Game g);
    
    /**
     * @return: Pokemon was on Opponent's bench
     */
    public abstract Pokemon chooseToGust(Game g);
    
    /**
     * @return: All cards were in deck, only two cards in array
     */
    public abstract Card[] chooseTeammates(Game g);
    
    /**
     * @return: First card is a Pokemon, second is a Tool or Special Energy attached to that Pokemon
     */
    public abstract Card[] chooseXerosic(Game g);
    
    /**
     * @return: Card was attached to Active
     */
    public abstract Energy chooseTFG(Game g);
    
    /**
     * @return: Card[].length == num, all cards were in deck in that number
     */
    public abstract Card[] chooseBattleCompressor(Game g, int num);
    
    /**
     * 
     */
    public abstract boolean shouldPlayTwoPuzzle(Game g);
    
    /**
     * @return: Both cards were in discard, so long as there is no infinite comboing
     */
    public abstract Card[] choosePuzzle(Game g, int num);
    
    public abstract Card[] sortPokedex(Game g, ArrayList<Card> cards);
    
    public abstract void viewCardsFromDeck(ArrayList<Card> cards);
    
    public abstract Trainer chooseTrainersMail(Game g, ArrayList<Card> cards);
    
    /**
     * @return: All items in Card[] were in player.hand, Card[].length => 2.
     */
    public abstract Card[] chooseUltraBallAway(Game g);
    
    /**
     * @return: Pokemon was in deck. 
     */
    public abstract Pokemon chooseUltraBall(Game g);
    
    /**
     * @return: Supporter was in discard
     */
    public abstract Supporter cardToVSSeeker(Game g);
    /**
     * @return: Card was in cards
     */
    public abstract Card chooseToHandAcroBike(Game g, ArrayList<Card> cards);

}
    
    