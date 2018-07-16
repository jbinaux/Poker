package jeu;

import java.util.ArrayList;
import java.util.HashMap;

public class PokerClassique {

	public static ArrayList<String> createCards()
	{
		ArrayList<String> deck = new ArrayList<String>();
		ArrayList<String> colors = new ArrayList<String>();
		
		colors.add(" coeur");
		colors.add(" pique");
		colors.add(" carreau");
		colors.add(" trefle");
		for(int j = 0; j <= 3; j++)
		{
			for(int i = 2; i <= 10; i++)
			{
				deck.add(i + colors.get(j));
			}
			deck.add("Valet" + colors.get(j));
			deck.add("Dame" + colors.get(j));
			deck.add("Roi" + colors.get(j));
			deck.add("As" + colors.get(j));
		}
		return deck;
	}
	
	public static ArrayList<String> shuffleCards(ArrayList<String> deck){
		ArrayList<String> mixedDeck = new ArrayList<String>();
		for(int i = 0; i <= 51; i++)
		{
			mixedDeck.add("0");
		}
		for(int j = 0; j <= 51; j++)
		{
			int rand = (int) (Math.random()*52);
			while(mixedDeck.get(rand) != "0")
			{
				rand = (int) (Math.random() * 52);
			}
			mixedDeck.set(rand, deck.get(j));
		}
		return mixedDeck;
	}
	
	public static ArrayList<ArrayList<String>> createPlayers(int nbPlayers)
	{
		ArrayList<ArrayList<String>> players = new ArrayList<ArrayList<String>>();
		for(int i = 0; i < nbPlayers; i++)
		{
			ArrayList<String> cards = new ArrayList<String>();
			players.add(cards);
		}
		return players;
	}
	
	public static ArrayList<ArrayList<String>> dealCards(ArrayList<String> deck, ArrayList<ArrayList<String>>players)
	{
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < players.size(); j++)
			{
				players.get(j).add(deck.get( ((i+1) * (j+1) -1)));
			}
		}
		return players;
	}

	public static HashMap<Character, Integer> createHash(ArrayList<String> deck)
	{
		HashMap<Character, Integer> cardValue = new HashMap<Character, Integer>();
		
		for(int i = 1; i<=13; i++)
		{
			cardValue.put(deck.get(i - 1).charAt(0), i);
		}
		return cardValue;
	}

	public static boolean isCouleur(ArrayList<String> hand)
	{
		ArrayList<String> colors = new ArrayList<String>();
		int nbCouleur = 0;
		
		colors.add("coeur");
		colors.add("pique");
		colors.add("carreau");
		colors.add("trefle");
		for(int i = 0; i < 4;i++)
		{
			//cycle dans les couleurs
			for(int j = 0; j < 5; j++)
			{
				//cycle dans la main
				if(hand.get(j).contains(colors.get(i)))
				{
					//ajoute 1 au nombre de carte de la meme couleur
					nbCouleur++;
				}
				else
				{
					break;
				}
			}
			if(nbCouleur == 5)
			{
				return true;
			}
			nbCouleur = 0;
		}
		return false;
	}
	
	public static void main(String[] args) {
		ArrayList<String> deck = createCards();
		HashMap<Character, Integer> cardValue = createHash(deck);
		deck = shuffleCards(deck);
		//TODO scanner le nombre de joueurs
		ArrayList<ArrayList<String>> players = createPlayers(6);
		players = dealCards(deck, players);
		
		
		ArrayList<String> test = new ArrayList<String>();
		test.add("kajhf carreau");
		test.add("kajhf carreau");
		test.add("kajhf carreau");
		test.add("kajhf carreau");
		test.add("kajhf carreau");
		System.out.println(isCouleur(test));
	}

}
