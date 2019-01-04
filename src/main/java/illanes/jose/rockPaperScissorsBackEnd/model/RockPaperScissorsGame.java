package illanes.jose.rockPaperScissorsBackEnd.model;

import illanes.jose.rockPaperScissorsBackEnd.exceptions.MalformedGameException;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Class to hold all the methods related with a round of Rock paper scissors.
 */
@Component
public class RockPaperScissorsGame {

	/**
	 * Play a round of Rock Paper Scissors.
	 *
	 * @param player1Choice The choice should be Rock, Paper, Scissors.
	 * @return an integer that represents who is the winner. (1 for player 1, 2 for player 2, 0 for a draw)
	 * @throws MalformedGameException if the player 1 choice is not a valid value.
	 */
	public int playAGame(String player1Choice) throws MalformedGameException {
		if (player1Choice.equals("Rock")) {
			return 0;
		} else if (player1Choice.equals("Scissors")) {
			return 2;
		} else if (player1Choice.equals("Paper")) {
			return 1;
		}
		throw new MalformedGameException(player1Choice);
	}

	/**
	 * Select a random value bewtween Rock paper scissors.
	 *
	 * @return A random valid string to play rock paper scissors.
	 */
	public String selectRandomChoice() {
		int randomNumber = generateRandomNumber();
		switch (randomNumber) {
			case 0:
				return "Rock";
			case 1:
				return "Paper";
			case 2:
				return "Scissors";
			default:
				return "Rock ";
		}
	}

	private int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(3);
	}
}
