package illanes.jose.rockPaperScissorsBackEnd.services;

import illanes.jose.rockPaperScissorsBackEnd.model.GameInformation;

public interface RockPaperScissorsService {

	/**
	 * Simulate a round of rock paper scissors.
	 *
	 * @return GameInformation object holding all the information about the game that has been played.
	 */
	GameInformation playRound();
}
