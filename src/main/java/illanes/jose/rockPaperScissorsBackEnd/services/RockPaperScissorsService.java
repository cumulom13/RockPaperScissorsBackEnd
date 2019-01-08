package illanes.jose.rockPaperScissorsBackEnd.services;

import illanes.jose.rockPaperScissorsBackEnd.model.GameInformation;
import illanes.jose.rockPaperScissorsBackEnd.model.GlobalGameInformation;

import java.util.List;

public interface RockPaperScissorsService {

	/**
	 * Simulate a round of rock paper scissors.
	 *
	 * @return GameInformation object holding all the information about the game that has been played.
	 */
	GameInformation playRound();

	/**
	 * Get the total number of rounds played and the winner of each round.
	 *
	 * @return GlobalGameInformation singleton object.
	 */
	GlobalGameInformation getGlobalGameInformation();
}
