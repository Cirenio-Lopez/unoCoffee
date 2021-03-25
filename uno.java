import java.util.*;
//Main Uno File
public class uno
{
    public static void main( String args[] ) throws Exception
	{
    //scanner
    Scanner keyboard = new Scanner(System.in);
    //initialize user variables
    int players;
    ArrayList<String> users = new ArrayList<String>();
    ArrayList<ArrayList<String>> playerDecks = new ArrayList<ArrayList<String>>();
    //initialize game state
    boolean gameOver = false;
    String currentColor;
    String currentNumber;
    ArrayList<String> playedCardsPile = new ArrayList<String>();
    ArrayList<String> drawCardsDeck = new ArrayList<String>();
    //initialize player deck
    ArrayList<String> shuffledCards = cardDeck();

    users = userSetup(keyboard);
    players = users.size();
    playerDecks = cardDistribution(shuffledCards, players);

    initialization(players, playedCardsPile, drawCardsDeck, shuffledCards);

    currentColor = "" + playedCardsPile.get(0).charAt(1);
    currentNumber = "" + playedCardsPile.get(0).charAt(0);

    game(players, playedCardsPile, drawCardsDeck, shuffledCards, currentColor, currentNumber, gameOver, playerDecks, users, keyboard);
    }
    
    static ArrayList<String> cardDeck()
    {
        List<String> cardsList = Arrays.asList("0R", "1R", "1R", "2R", "2R", "3R", "3R", "4R", "4R", "5R", "5R", "6R", "6R", "7R", "7R", "8R", "8R", "9R", "9R", "skipR", "skipR", "_R", "_R", "D2R", "D2R",
        "0G", "1G", "1G", "2G", "2G", "3G", "3G", "4G", "4G", "5G", "5G", "6G", "6G", "7G", "7G", "8G", "8G", "9G", "9G", "skipG", "skipG", "_G", "_G", "D2G", "D2G",
        "0B", "1B", "1B", "2B", "2B", "3B", "3B", "4B", "4B", "5B", "5B", "6B", "6B", "7B", "7B", "8B", "8B", "9B", "9B", "skipB", "skipB", "_B", "_B", "D2B", "D2B",
        "0Y", "1Y", "1Y", "2Y", "2Y", "3Y", "3Y", "4Y", "4Y", "5Y", "5Y", "6Y", "6Y", "7Y", "7Y", "8Y", "8Y", "9Y", "9Y", "skipY", "skipY", "_Y", "_Y", "D2Y", "D2Y",
        "W", "W", "W", "W", "D4W", "D4W", "D4W", "D4W");
        ArrayList<String> cards = new ArrayList<>();
        cards.addAll(cardsList);
        Collections.shuffle(cards);
        return cards;
    }

    static ArrayList<String> userSetup(Scanner keyboard){
        System.out.println("How many players? (Issue: No less than 2 but no more than 5.)");
        ArrayList<String> users = new ArrayList<String>();
        int players = keyboard.nextInt();
        while (players > 5 && players < 1)
        {
            System.out.println("Wrong input.");
            System.out.println("How many players? (Issue: No less than 2 but no more than 5.)\n");
            players = keyboard.nextInt();
        }
        keyboard.nextLine();
        for(int i = 1; i <= players; i++)
        {
            System.out.println("What is the username of player " + i + "?");
            users.add(keyboard.nextLine());
        }

        return users;
    }

    static ArrayList<ArrayList<String>> cardDistribution(ArrayList<String> cards, int players){
        ArrayList<ArrayList<String>> playerDecks = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < players; i++)
        {
            ArrayList<String> temp = new ArrayList<String>();
            for(int j = 0; j < 7; j++)
            {
                temp.add(cards.get(0));
                cards.remove(0);
            }
            playerDecks.add(temp);
        }
        return playerDecks;
    }

    static void initialization(int players, ArrayList<String> playedCardsPile, ArrayList<String> drawCardsDeck, ArrayList<String> shuffledCards){
        int startingCardIndex;
        while(true){
        startingCardIndex = (int)Math.floor(Math.random() * (108 - (players * 7)) );
            if(shuffledCards.get(startingCardIndex) == "skipR" || shuffledCards.get(startingCardIndex) == "_R" || shuffledCards.get(startingCardIndex) == "D2R" || shuffledCards.get(startingCardIndex) == "skipG" || shuffledCards.get(startingCardIndex) == "_G" || shuffledCards.get(startingCardIndex) == "D2G" || shuffledCards.get(startingCardIndex) == "skipB" || shuffledCards.get(startingCardIndex) == "_B" || shuffledCards.get(startingCardIndex) == "D2B" || shuffledCards.get(startingCardIndex) == "skipY" || shuffledCards.get(startingCardIndex) == "_Y" || shuffledCards.get(startingCardIndex) == "D2Y" || shuffledCards.get(startingCardIndex) == "W" || shuffledCards.get(startingCardIndex) == "D4W"){
            continue;
            } else break;
        }
        playedCardsPile.add(shuffledCards.get(startingCardIndex));
        shuffledCards.remove(startingCardIndex);
        drawCardsDeck.addAll(shuffledCards);
    }
    static void game(int players, ArrayList<String> playedCardsPile, ArrayList<String> drawCardsDeck, ArrayList<String> shuffledCards, String currentColor, String currentNumber, boolean gameOver, ArrayList<ArrayList<String>> playerDecks, ArrayList<String> users, Scanner keyboard){
        boolean direction_clockwise = true; //since the game is in the terminal, it starts going forward (1,2,3,4)
        int counter = 0;
        String currentUser = "";
        String played_card = "";
        String numberOfPlayedCard = "";
        String colorOfPlayedCard = "";
        while(!gameOver)
        {
            while(counter != players)
            {
                System.out.println("Here is the current card at play: " + playedCardsPile.get(playedCardsPile.size() - 1));
                currentUser = users.get(counter);
                System.out.println(currentUser + ": Here is your current deck.");
                System.out.println("Deck: "+ playerDecks.get(counter));
                System.out.println("What card would you like to play, would you like to draw, or would you like to yell uno?");
                played_card = keyboard.next();
                while(!playerDecks.get(counter).contains(played_card) && !played_card.equals("draw"))
                {
                    System.out.println("Card was not found.");
                    System.out.println("What card would you like to play?");
                    played_card = keyboard.nextLine();
                }
                switch(played_card)
                {
                    //if card played was a number card
                    case "0R":
                    case "1R":
                    case "2R":
                    case "3R":
                    case "4R":
                    case "5R":
                    case "6R":
                    case "7R":
                    case "8R":
                    case "9R":
                    case "_R":
                    case "0G":
                    case "1G":
                    case "2G":
                    case "3G":
                    case "4G":
                    case "5G":
                    case "6G":
                    case "7G":
                    case "8G":
                    case "9G":
                    case "_G":
                    case "0B":
                    case "1B":
                    case "2B":
                    case "3B":
                    case "4B":
                    case "5B":
                    case "6B":
                    case "7B":
                    case "8B":
                    case "9B":
                    case "_B":
                    case "0Y":
                    case "1Y":
                    case "2Y":
                    case "3Y":
                    case "4Y":
                    case "5Y":
                    case "6Y":
                    case "7Y":
                    case "8Y":
                    case "9Y":
                    case "_Y": {
                        numberOfPlayedCard = "" + played_card.charAt(0);
                        colorOfPlayedCard = "" + played_card.charAt(1);
                        if(currentColor.equals(colorOfPlayedCard)){
                            //Testing purposes.
                            System.out.println("Colors Match!");
                            //Removes played card from player's deck.
                            playerDecks.get(counter).remove(played_card);
                            playedCardsPile.add(played_card);
                            currentNumber = "" + played_card.charAt(0);
                            currentColor = "" + played_card.charAt(1);
                            if(direction_clockwise){
                                counter++;
                                if(counter == players){
                                    counter = 0;
                                }
                            }
                            else if (!direction_clockwise){
                                counter--;
                                if(counter == -1){
                                    counter = players - 1;
                                }
                            }
                        }
                        else if(currentNumber.equals(numberOfPlayedCard)){
                            //Testing purposes.
                            System.out.println("Numbers Match!");
                            //Removes played card from player's deck.
                            playerDecks.get(counter).remove(played_card);
                            playedCardsPile.add(played_card);
                            currentNumber = "" + played_card.charAt(0);
                            currentColor = "" + played_card.charAt(1);
                            if(direction_clockwise){
                                counter++;
                                if(counter == players){
                                    counter = 0;
                                }
                            }
                            else if (!direction_clockwise){
                                counter--;
                                if(counter == -1){
                                    counter = players - 1;
                                }
                            }
                        }
                        else{
                            System.out.println("Invalid Move!");
                        }
                        break;
                    }
                    //if card played was a skip card
                    case "skipR":
                    case "skipG":
                    case "skipB":
                    case "skipY": {
                        colorOfPlayedCard = "" + played_card.charAt(4);
                        numberOfPlayedCard = "" + played_card.substring(0, 3);
                        System.out.println(numberOfPlayedCard);
                        if(currentColor.equals(colorOfPlayedCard)){
                            System.out.println("Colors Match!");
                            playerDecks.get(counter).remove(played_card);
                            playedCardsPile.add(played_card);
                            currentNumber = "skip";
                            currentColor = "" + played_card.charAt(4);
                            if(direction_clockwise){
                                counter++;
                                counter++;
                                if(counter == players || counter == players + 1){
                                    counter = 0;
                                }
                                break;
                            }
                            else if (!direction_clockwise){
                                counter--;
                                counter--;
                                if(counter == -1 || counter == -2){
                                    counter = players - 1;
                                }
                                break;
                            }
                        }
                        else if(currentNumber.equals(numberOfPlayedCard)){
                            System.out.println("Numbers Match! (Skip)");
                            playerDecks.get(counter).remove(played_card);
                            playedCardsPile.add(played_card);
                            currentNumber = "skip";
                            currentColor = "" + played_card.charAt(4);
                            if(direction_clockwise){
                                counter++;
                                counter++;
                                if(counter == players || counter == players + 1){
                                    counter = 0;
                                }
                                break;
                            }
                            else if (!direction_clockwise){
                                counter--;
                                counter--;
                                if(counter == -1 || counter == -2){
                                    counter = players - 1;
                                }
                                break;
                            }
                        }
                        else{System.out.println("Invalid move.");}
                        break;
                    }
                    //if card played was a draw 2 card.
                    case "D2R":
                    case "D2G":
                    case "D2B":
                    case "D2Y": {
                        colorOfPlayedCard = "" + played_card.charAt(2);
                        numberOfPlayedCard = "" + played_card.substring(0, 1);
                        if(currentColor.equals(colorOfPlayedCard)){
                            System.out.println("Colors Match!");
                            playerDecks.get(counter).remove(played_card);
                            playedCardsPile.add(played_card);
                            currentNumber = "D2";
                            currentColor = "" + played_card.charAt(2);
                            if(direction_clockwise){
                                //normal switching procedure
                                counter++;
                                if(counter == players){
                                    counter = 0;
                                }
                                //code where 2 cards are drawn
                                playerDecks.get(counter).add(drawCardsDeck.get(0));
                                playerDecks.get(counter).add(drawCardsDeck.get(1));
                                drawCardsDeck.remove(0);
                                drawCardsDeck.remove(0);
                                break;
                            }
                            else if (!direction_clockwise){
                                counter--;
                                if(counter == -1){
                                    counter = players - 1;
                                }
                                playerDecks.get(counter).add(drawCardsDeck.get(0));
                                playerDecks.get(counter).add(drawCardsDeck.get(1));
                                drawCardsDeck.remove(0);
                                drawCardsDeck.remove(0);
                                break;
                            }
                        }
                        else if(currentNumber.equals(numberOfPlayedCard)){
                            System.out.println("Numbers Match! (Draw 2)");
                            playerDecks.get(counter).remove(played_card);
                            playedCardsPile.add(played_card);
                            currentNumber = "D2";
                            currentColor = "" + played_card.charAt(2);
                            if(direction_clockwise){
                                //normal switching procedure
                                counter++;
                                if(counter == players){
                                    counter = 0;
                                }
                                //code where 2 cards are drawn
                                playerDecks.get(counter).add(drawCardsDeck.get(0));
                                playerDecks.get(counter).add(drawCardsDeck.get(1));
                                drawCardsDeck.remove(0);
                                drawCardsDeck.remove(0);
                                break;
                            }
                            else if (!direction_clockwise){
                                counter--;
                                if(counter == -1){
                                    counter = players - 1;
                                }
                                playerDecks.get(counter).add(drawCardsDeck.get(0));
                                playerDecks.get(counter).add(drawCardsDeck.get(1));
                                drawCardsDeck.remove(0);
                                drawCardsDeck.remove(0);
                                break;
                            }
                        }
                        else{System.out.println("Invalid move.");}
                        break;
                    }
                    //if card played was a wild card
                    case "W":{
                        System.out.println("What should be the new color? (Ex: Red = R, Green = G, Blue = B, Yellow = Y)");
                        currentColor = keyboard.next();
                        while(!currentColor.equals("R") && !currentColor.equals("G") && !currentColor.equals("B") && !currentColor.equals("Y")){
                            System.out.println("Invalid color.");
                            System.out.println("What should be the new color? (Ex: Red = R, Green = G, Blue = B, Yellow = Y)");
                            currentColor = keyboard.next();
                        }
                        playerDecks.get(counter).remove(played_card);
                        playedCardsPile.add(played_card);
                        currentNumber = "";
                        if(direction_clockwise){
                            counter++;
                            if(counter == players){
                                counter = 0;
                            }
                            break;
                        }
                        else if (!direction_clockwise){
                            counter--;
                            if(counter == -1){
                                counter = players - 1;
                            }
                            break;
                        }
                    }
                    //if card played was a draw 4 wild card
                    case "D4W":
                    {
                        System.out.println("What should be the new color? (Ex: Red = R, Green = G, Blue = B, Yellow = Y)");
                        currentColor = keyboard.next();
                        while(!currentColor.equals("R") && !currentColor.equals("G") && !currentColor.equals("B") && !currentColor.equals("Y")){
                            System.out.println("Invalid color.");
                            System.out.println("What should be the new color? (Ex: Red = R, Green = G, Blue = B, Yellow = Y)");
                            currentColor = keyboard.nextLine();
                        }
                        playerDecks.get(counter).remove(played_card);
                        playedCardsPile.add(played_card);
                        currentNumber = "";
                        if(direction_clockwise){
                            counter++;
                            if(counter == players){
                                counter = 0;
                            }
                            playerDecks.get(counter).add(drawCardsDeck.get(0));
                            playerDecks.get(counter).add(drawCardsDeck.get(1));
                            playerDecks.get(counter).add(drawCardsDeck.get(2));
                            playerDecks.get(counter).add(drawCardsDeck.get(3));
                            drawCardsDeck.remove(0);
                            drawCardsDeck.remove(0);
                            drawCardsDeck.remove(0);
                            drawCardsDeck.remove(0);
                        }
                        else if (!direction_clockwise){
                            counter--;
                            if(counter == -1){
                                counter = players - 1;
                            }
                            playerDecks.get(counter).add(drawCardsDeck.get(0));
                            playerDecks.get(counter).add(drawCardsDeck.get(1));
                            playerDecks.get(counter).add(drawCardsDeck.get(2));
                            playerDecks.get(counter).add(drawCardsDeck.get(3));
                            drawCardsDeck.remove(0);
                            drawCardsDeck.remove(0);
                            drawCardsDeck.remove(0);
                            drawCardsDeck.remove(0);
                        }
                    }
                    //if player needs to draw
                    case "draw":{
                        playerDecks.get(counter).add(drawCardsDeck.get(0));
                        drawCardsDeck.remove(0);
                        break;
                    }
                }
                for(int i = 0; i < players; i++){
                    if(playerDecks.get(i).size() == 0){
                        System.out.println(users.get(i) + " has won the game.");
                        gameOver = true;
                        System.exit(0);
                    }
                }
            }
        }
    }
}
