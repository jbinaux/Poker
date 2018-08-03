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
			//pour chaque couleur, ajoute une valeur de 2 a As
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
			//randomise la position dans la nouvelle List
			int rand = (int) (Math.random()*52);
			while(mixedDeck.get(rand) != "0")
			{
				//si la valeur a la position donnee est differente de 0, on choisi une nouvelle position aleatoire
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
			//ajoute une ArrayList<String> vide pour chaque joueur
			ArrayList<String> cards = new ArrayList<String>();
			players.add(cards);
		}
		return players;
	}
	
	public static void dealCards(ArrayList<String> deck, ArrayList<ArrayList<String>>players)
	{
		for(int i = 0; i < 5; i++)
		{
			//cycle 5 fois
			for(int j = 0; j < players.size(); j++)
			{
				//donne une carte a chaque joueur;
				players.get(j).add(deck.get( ((i * players.size()) + j)));
			}
		}
	}

	public static HashMap<Character, Integer> createHash(ArrayList<String> deck)
	{
		/* utilise les treize premiere cartes du jeu cree pour faire un hashmap qui associe la premiere lettre de la carte 
		 * a une valeur de 1 a 13
		 */
		HashMap<Character, Integer> cardValue = new HashMap<Character, Integer>();
		
		for(int i = 1; i<=13; i++)
		{
			cardValue.put(deck.get(i - 1).charAt(0), i);
		}
		return cardValue;
	}

	public static void sortHand(ArrayList<String> hand, HashMap<Character, Integer> cardValue)
	{
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add("0");
		int i = 0;
		while(i < hand.size()-1)
		{
			//si la carte suivante et superieure selon le hashmap, elles sont interverties
			if(cardValue.get(hand.get(i).charAt(0)) < cardValue.get(hand.get(i+1).charAt(0)))
			{
				tmp.set(0, hand.get(i));
				hand.set(i, hand.get(i + 1));
				hand.set(i + 1, tmp.get(0));
				//revient en arriere 
				if(i>0)
				{
					i--;
				}
			}
			else
			{
				i++;
			}
		}
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
			//cycle dans les couleurs (coeur, puis pique etc ...)
			for(int j = 0; j < 5; j++)
			{
				//cycle dans les cartes du joueur
				if(hand.get(j).contains(colors.get(i)))
				{
					//Si la carte est de la bonne couleur, on incremente
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
			//reset
			nbCouleur = 0;
		}
		return false;
	}
	
	public static boolean isSuite(ArrayList<String> hand, HashMap<Character, Integer> cardValue)
	{
		int nbSuite = 0; 
		for(int i = 0; i < hand.size() - 1; i++)
		{
			//si la valeur de la carte suivante +1 est egal a la valeur actuelle, alors elle la suit
			if(cardValue.get(hand.get(i).charAt(0)) == (cardValue.get(hand.get(i + 1).charAt(0)) + 1) )
			{
				nbSuite++;
			}
			else
			{
				break;
			}
		}
		if(nbSuite == 4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isQuinteFlush(ArrayList<String> hand, HashMap<Character, Integer> cardValue)
	{
		if(isCouleur(hand) && isSuite(hand, cardValue))
		{
			return true;
		}
		return false;
	}
	
	public static int nbSameCards(ArrayList<String> hand, HashMap<Character, Integer> cardValue)
	{
		int nbSame = 0;
		int max = 0;
		int card = 0;
		
		for(int i = 0; i < hand.size(); i++)
		{
			//compare chaque carte a toutes les cartes, y compris elle meme
			card = cardValue.get(hand.get(i).charAt(0));
			for(int j = 0; j < hand.size(); j++)
			{
				if(card == cardValue.get(hand.get(j).charAt(0)))
				{
					//si la valeur des cartes sont egales, incremente le compteur
					nbSame++;
				}
				if(max < nbSame)
				{
					//prend le maximum de cartes identiques
					max = nbSame;
				}
			}
			//remet a zero le compteur apres chaque carte testee
			nbSame = 0;
		}
		
		return max;
	}
	
	public static boolean isFull(ArrayList<String> hand, HashMap<Character, Integer> cardValue)
	{
		ArrayList<String> tmp = new ArrayList<String>();
		tmp = (ArrayList<String>)hand.clone();
		int card;
		int nbSame = 0;
		for(int i = 0; i < tmp.size(); i++)
		{
			//compare chaque carte a toutes les cartes, y compris elle meme
			card = cardValue.get(tmp.get(i).charAt(0));
			for(int j = 0; j < tmp.size(); j++)
			{
				//si la valeur des cartes sont egales, incremente le compteur
				if(card == cardValue.get(tmp.get(j).charAt(0)))
				{
					nbSame++;
				}
				if(nbSame == 3)
				{
					//si il y a trois cartes de meme valeur, on retire toutes les instances de cette valeur
					for(int k = 0; k < tmp.size(); k++)
					{
						if(card == cardValue.get(tmp.get(k).charAt(0)))
						{
							tmp.remove(k);
							//pour remettre k a zero apres l'incrementation du for
							k = -1;
						}
					}
				}
			}
			nbSame = 0;
		}
		if(tmp.size() == 2 && cardValue.get(tmp.get(0).charAt(0)) == cardValue.get(tmp.get(1).charAt(0)))
		{
			//si les deux cartes restante on la meme valeur
			return true;
		}
		return false;
	}
	
	public static boolean isDoublePair(ArrayList<String> hand, HashMap<Character, Integer> cardValue)
	{
		int nbSame = 0;
		int card = 0;
		int nbPair = 0;
		
		for(int i = 0; i < hand.size(); i++)
		{
			//compare chaque carte a toutes les cartes, y compris elle meme
			card = cardValue.get(hand.get(i).charAt(0));
			for(int j = 0; j < hand.size(); j++)
			{
				if(card == cardValue.get(hand.get(j).charAt(0)))
				{
					//si la valeur des cartes sont egales, incremente le compteur
					nbSame++;
				}
				if(nbSame == 2 && card == cardValue.get(hand.get(j).charAt(0)))
				{
					//les paires sont compte quand il y a deux carte similaire
					nbPair++;
				}
				if(nbSame > 2)
				{
					nbPair--;
				}
			}
			//remet a zero le compteur apres chaque carte testee
			nbSame = 0;
		}
		//comme chaque paire est compte deux fois, retourne true quand nbPair = 4
		if(nbPair == 4)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String cardPair(ArrayList<String> hand, HashMap<Character, Integer> cardValue)
	{
		int nbSame = 0;
		int testedCardValue = 0;
		
		for(int i = 0; i < hand.size(); i++)
		{
			//compare chaque carte a toutes les cartes, y compris elle meme
			testedCardValue = cardValue.get(hand.get(i).charAt(0));
			for(int j = 0; j < hand.size(); j++)
			{
				if(testedCardValue == cardValue.get(hand.get(j).charAt(0)))
				{
					//si la valeur des cartes sont egales, incremente le compteur
					nbSame++;
				}
				if(nbSame == 2 && testedCardValue == cardValue.get(hand.get(j).charAt(0)))
				{
					//si on trouve deux cartes similaires, et comme les cartes sont triee, les premiere sont les plus fortes 
					//(dans le cas d'une double paire)
					return hand.get(j);
				}
			}
			//remet a zero le compteur apres chaque carte testee
			nbSame = 0;
		}
		return "0";
	}
	
	public static String handCombination(ArrayList<String> hand, HashMap<Character, Integer> cardValue, ArrayList<String> combinationCard, ArrayList<Integer> combinationsValue)
	{
		//de la meilleur combination a la plus faible, donne la carte de la combination, et sa valeur
		if(isQuinteFlush(hand, cardValue))
		{
			combinationCard.add(hand.get(0));
			combinationsValue.add(8);
			return ("Quinte Flush");
		}
		else if(4 == nbSameCards(hand, cardValue))
		{
			combinationCard.add(hand.get(2));
			combinationsValue.add(7);
			return ("Carre");
		}
		else if(isFull(hand, cardValue))
		{
			combinationCard.add(hand.get(2));
			combinationsValue.add(6);
			return ("Full");
		}
		else if(isCouleur(hand))
		{
			combinationCard.add(hand.get(0));
			combinationsValue.add(5);
			return ("Couleur");
		}
		else if(isSuite(hand, cardValue))
		{
			combinationCard.add(hand.get(0));
			combinationsValue.add(4);
			return ("Suite");
		}
		else if(3 == nbSameCards(hand, cardValue))
		{
			combinationCard.add(hand.get(2));
			combinationsValue.add(3);
			return ("Brelan");
		}
		else if(isDoublePair(hand, cardValue))
		{
			combinationCard.add(cardPair(hand, cardValue));
			combinationsValue.add(2);
			return ("Double Paire");
		}
		else if(2 == nbSameCards(hand, cardValue))
		{
			combinationCard.add(cardPair(hand, cardValue));
			combinationsValue.add(1);
			return ("Paire");
		}
		else
		{
			combinationCard.add(hand.get(0));
			combinationsValue.add(0);
			return hand.get(0);
		}
	}
	
	public static int bestCombination(HashMap<Character, Integer> cardValue, ArrayList<String> combinations, ArrayList<Integer> combinationsValue, ArrayList<String> combinationCard)
	{
		//stocke l'index de la meilleure combination
		int indexBestCombination = 0;
		
		for(int i = 0; i < combinations.size(); i++)
		{
			if(combinationsValue.get(indexBestCombination) < combinationsValue.get(i))
			{
				//si la combination est meilleure, on garde l' index
				indexBestCombination = i;
			}
			else if(combinationsValue.get(indexBestCombination) == combinationsValue.get(i))
			{
				//si la combination est egale, on compare leurs cartes
				if(cardValue.get(combinationCard.get(indexBestCombination).charAt(0)) < cardValue.get(combinationCard.get(i).charAt(0)))
				{
					indexBestCombination = i;
				}
			}
		}
		//TODO resolve equalities 
		return indexBestCombination;
	}
	
	public static void main(String[] args) {
		ArrayList<String> deck = createCards();
		ArrayList<String> combinationCard = new ArrayList<String>();
		HashMap<Character, Integer> cardValue = createHash(deck);
		ArrayList<Integer> combinationsValue = new ArrayList<Integer>();
		ArrayList<String> combinations = new ArrayList<String>();
		int winner;
		deck = shuffleCards(deck);
		//TODO scanner le nombre de joueurs
		ArrayList<ArrayList<String>> players = createPlayers(6);
		dealCards(deck, players);
		
		
		for (int i = 0; i < players.size(); i++)
		{
			sortHand(players.get(i), cardValue);
			combinations.add(handCombination(players.get(i), cardValue, combinationCard, combinationsValue));
		}
		System.out.println(players);
		System.out.println(combinations);
		System.out.println(combinationCard);
		System.out.println(combinationsValue);
		winner = bestCombination(cardValue, combinations, combinationsValue, combinationCard);
		System.out.println("le gagnant est le joueur numero " + (winner + 1) + " avec une " + combinations.get(winner));
	}

}
