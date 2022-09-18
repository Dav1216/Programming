import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class GuessingGame {

    Scanner scanner = new Scanner(System.in);
    Random randomGenerator;

/*numberOfGuess will keep track of the number of the guess, when entering the while loop
the ArrayList allGuesses will keep in memory the value of each guess,
which is then used in the graf method to construct the lines with '.', 'X', '|'*/
 
    int code;
    int numberOfGuess = 1;
    ArrayList<Integer> allGuesses = new ArrayList<Integer>();
        
    void graf(int theGuess) {

        int number = theGuess;

        for (int i = 0; i < 100; i++){

/*if the counter i is different from the code and each value of the array 
allGuesses, which is itself a guess, which in the graf 
method is theGuess, a dot will be printed, if the counter 
is the value of the code, a '|' will be printed, and if the counter is 
the value of theGuess, an "X" will be printed.*/
 
            if (i != number && i != this.code){

                System.out.print('.');

            } else if (i == number){

                System.out.print('X');

            } else if (i == this.code){

                System.out.print('|');
                
            }
        }

        System.out.println("");

    }

    void run(){

    /*the run() contains the program beggining with the String sentence*/

        System.out.println("Do you want to enter the secret code yourself?");
        String answer = scanner.nextLine();

    /*the answer is used to react to user input to the String sentences,
     the input is either "yes" or "no",
    and the input is used in the if condition for either 
    the user to enter a secret number or for the computer 
    to generate a secret number, a code. The seed is used as 
    an arbitrary input from the user to enable the computer
    to generate the random number from 0 to 99*/
 
        if (answer.equals("yes")){

            System.out.println("Secretly type the code");
            code = scanner.nextInt();

        } else if (answer.equals("no")){

            System.out.println("Type an arbitrary number");
            long seed = scanner.nextLong();
            Random randomGenerator = new Random(seed);
            code = randomGenerator.nextInt(100);
                
        }

        System.out.println("Start guessing!");
    /*the game beggins, while the counter numberOfGuess is 
    smaller or equal to 7, the program enters the while loop. 
    The while loop gives the user feedback, for the user to 
    adjust the next guess based on that feedback "lower", "higher"
    and when the guess is equal to the code, the program returns 
    "Good guess! You won."*/

        while (numberOfGuess <= 7){

            int theGuess = scanner.nextInt();
            allGuesses.add(theGuess);

            if (theGuess > code){

                System.out.println("lower");

            } else if (theGuess < code){

                System.out.println("higher");

            } else if (theGuess == code){

                System.out.println("Good guess! You won.");
                break;

            }

            if (numberOfGuess == 7){

                System.out.println("No more guesses, you lost.");

            }

            numberOfGuess++;

        }
    /*the if condition is used to output the value of numberOfGuess, 
    if the numberOfGuess is 8 we must subtract
    1 from the numberOfGuess to show the real number numberOfGuess, 
    as if the user doesn't guess the code, the while loop ends in 
    numberOfGuess++ and makes the numberOfGuess larger than it actually is*/

        if (numberOfGuess < 8){

            System.out.println(numberOfGuess + " guesses:");

        } else {

            System.out.println(numberOfGuess - 1+ " guesses:");
        }
    /*for each elemnt of the arrayList allGuesses we use the 
    graf method to print the '.', 'X' and '|' */

        for (int i = 0; i < allGuesses.size(); i++){

            this.graf(allGuesses.get(i));

        }
            
            
    }
    public static void main(String[] args){

        (new GuessingGame()).run();

    }
}
   