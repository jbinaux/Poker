package jeu;

import java.util.ArrayList;

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
	
	public static ArrayList<String> mixCards(ArrayList<String> deck){
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> deck = mixCards(createCards());
		ArrayList<ArrayList<String>> players = createPlayers(6);
		players = dealCards(deck, players);
		System.out.println(players.get(0));
	}

}
