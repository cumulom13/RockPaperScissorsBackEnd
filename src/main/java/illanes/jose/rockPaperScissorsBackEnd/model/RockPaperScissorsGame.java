package illanes.jose.rockPaperScissorsBackEnd.model;

import illanes.jose.rockPaperScissorsBackEnd.enumerations.RockPaperScissors;
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
		if (RockPaperScissors.ROCK.getValue().equals(player1Choice)) {
			return 0;
		} else if (RockPaperScissors.SCISSORS.getValue().equals(player1Choice)) {
			return 2;
		} else if (RockPaperScissors.PAPER.getValue().equals(player1Choice)) {
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
				return RockPaperScissors.ROCK.getValue();
			case 1:
				return RockPaperScissors.PAPER.getValue();
			case 2:
				return RockPaperScissors.SCISSORS.getValue();
			default:
				return RockPaperScissors.ROCK.getValue();
		}
	}

	private int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(3);
	}
}
