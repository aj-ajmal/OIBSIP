import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {
    private static final int MAX_ROUNDS = 5; // Maximum number of rounds
    private static final int MAX_ATTEMPTS = 10; // Maximum attempts per round
    private static final int RANGE = 100; // Range of random numbers (1 to 100)

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
                    int score = attemptsLeft * 10; // Score based on remaining attempts
                    totalScore += score;
                    System.out.println("Correct! You've guessed the number.");
                    System.out.println("Score for this round: " + score);
                    hasGuessedCorrectly = true;
                } else if (userGuess < targetNumber) {
                    System.out.println("The number is higher! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("The number is lower! Attempts left: " + attemptsLeft);
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