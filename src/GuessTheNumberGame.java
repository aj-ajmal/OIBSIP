import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private static final int MAX_ROUNDS = 5;
    private static final int MAX_ATTEMPTS = 10;
    private static final int RANGE = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        System.out.println("Welcome to the Guess the Number Game!");

        // Loop through rounds
        for (int round = 1; round <= MAX_ROUNDS; round++) {
            System.out.println("\nRound " + round + " begins!");

            // Generate a random number between 1 and RANGE
            Random random = new Random();
            int targetNumber = random.nextInt(RANGE) + 1;

            int attemptsLeft = MAX_ATTEMPTS;
            boolean hasGuessedCorrectly = false;

            // Prompt user for guesses
            while (attemptsLeft > 0 && !hasGuessedCorrectly) {
                System.out.println("Enter your guess (1 to " + RANGE + "): ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == targetNumber) {
                    int score = attemptsLeft * 10;
                    totalScore += score;
                    System.out.println("Correct! You've guessed the number.");
                    System.out.println("Score for this round: " + score);
                    hasGuessedCorrectly = true;
                } else if (userGuess < targetNumber) {
                    System.out.println("Guess number is higher than yours ! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Guess number is lower than yours ! Attempts left: " + attemptsLeft);
                }
            }

            // Check if user failed to guess the number
            if (!hasGuessedCorrectly) {
                System.out.println("Sorry! You've used all attempts. The number was: " + targetNumber);
            }
        }
        System.out.println("\nGame over! Your total score: " + totalScore);
        scanner.close();
    }
}