/*
 * jason-I-T ... Basic Poker Game... Dealer.java
 *
 * Desc: This program describes the dealer. Data structure of 15 elements for
 * the deck. The deck is used in operations to deal cards.
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;

import java.lang.Integer;

public class Dealer {
	private HashMap<Integer, int[]> deck;
	private LinkedList<int[]> cardsForPlayers;
	private LinkedList<int[]> cardsForRiver;
	private ArrayList<LinkedList<int[]>> playerHands;

	public Dealer() {
		/*TODO:
		 * 	Implement dealing to the river
		 * 		> find a way to reveal the river after each stage
		 * 		> ... the dealer or the game will reveal the river
		 * 		  to the players.. either way; need a function to
		 * 		  
		 */	
		this.deck = deckInit();
		dealInit(this.deck);
		this.playerHands = handsInit(this.cardsForPlayers);
	}

	// Create new deck
	private HashMap<Integer, int[]> deckInit() {
		HashMap<Integer, int[]> cards = new HashMap<>();
		
		// Uniquely picking a random cards until we have 15 (hand+river)
		int i = 0;
		do { 
			int suit = (int)((Math.random() * 1000) % 4);
			int rank = (int)((Math.random() * 1000) % 13);	
			int[] temp = {suit, rank};
			if(!isCopy(cards, temp)) {
				cards.put(i, temp);	
				i++;
			} 
		} while(i < 15);	

		return cards;
	}

	

	// get cards ready to be dealt
	private void dealInit(HashMap<Integer, int[]> deck) {
		LinkedList<int[]> handCards = new LinkedList<>();
		LinkedList<int[]> riverCards = new LinkedList<>();

		for(int i = 0; i < 10; i++)
			handCards.add(deck.get(i));
		for(int j = 10; j < deck.size(); j++)
			riverCards.add(deck.get(j));

		this.cardsForPlayers = handCards;
		this.cardsForRiver = riverCards;
	}

	// dealing to players. returns arraylist w the hands of players 1 - 5.
	private ArrayList<LinkedList<int[]>> handsInit(LinkedList<int[]> cards){
		ArrayList<LinkedList<int[]>> hands = new ArrayList<>();

		while(cards.size() > 0) {
			LinkedList<int[]> newHand = new LinkedList<int[]>();
			newHand.add(cards.poll());
			newHand.add(cards.poll());
			hands.add(newHand);
		}

		return hands;
	}

	// used to ensure the deck will not have copies in deckInit()
	private boolean isCopy(HashMap<Integer, int[]> map, int[] newEntry){
		for(int[] pair: map.values())
			if(pair[0]==newEntry[0] && pair[1]==newEntry[1])
				return true; 	

		return false;	
	}

	// GETTERS //
	public ArrayList<LinkedList<int[]>> getPlayerHands() { return this.playerHands; }

	/**********************************************************************
	 **********************************************************************
	 ***********************DEBUGGING**FUNCTIONS***************************
	 **********************************************************************
	 **********************************************************************/

	public void printDeck() {
		this.deck.forEach((n, c) -> {
			System.out.printf("Card %d: %d %d\n", n, c[0], c[1]);
		});
	}

	public void printHandCards() {
		this.cardsForPlayers.forEach((h) -> {
			System.out.printf("Hand %d %d\n", h[0], h[1]);
		});

	}
	public void printRiverCards() {
		this.cardsForRiver.forEach((r) -> {
			System.out.printf("River %d %d\n", r[0], r[1]);
		});
	}
}
