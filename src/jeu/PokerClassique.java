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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> deck = mixCards(createCards());
		System.out.println(deck);
	}

}
