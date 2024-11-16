import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private static final int Max_Rounds = 3;
    private static final int Max_Attempts = 5;
    private static final int Range = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        System.out.println("Welcome to the Guess the Number Game!");

        for (int round = 1; round <= Max_Rounds; round++) {
            System.out.println("\nRound " + round + " begins!");

            Random random = new Random();
            int targetNumber = random.nextInt(Range) + 1;

            int attemptsLeft = Max_Attempts;
            boolean hasGuessedCorrectly = false;

            while (attemptsLeft > 0 && !hasGuessedCorrectly) {
                System.out.println("Enter your guess (1 to " + Range + "): ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == targetNumber) {
                    int score = attemptsLeft * 10;
                    totalScore += score;
                    System.out.println("Correct! You guessed the number.");
                    System.out.println("Score for this round: " + score);
                    hasGuessedCorrectly = true;
                } else if (userGuess < targetNumber) {
                    System.out.println("Guess number is higher than yours ! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Guess number is lower than yours ! Attempts left: " + attemptsLeft);
                }
            }
            
            if (!hasGuessedCorrectly) {
                System.out.println("Sorry! You used all attempts. The number was: " + targetNumber);
            }
        }
        System.out.println("\nGame over! Your total score is: " + totalScore);
        scanner.close();
    }
}