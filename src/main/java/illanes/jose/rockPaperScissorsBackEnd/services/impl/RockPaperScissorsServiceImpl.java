package illanes.jose.rockPaperScissorsBackEnd.services.impl;

import illanes.jose.rockPaperScissorsBackEnd.exceptions.MalformedGameException;
import illanes.jose.rockPaperScissorsBackEnd.model.GameInformation;
import illanes.jose.rockPaperScissorsBackEnd.model.RockPaperScissorsGame;
import illanes.jose.rockPaperScissorsBackEnd.services.RockPaperScissorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RockPaperScissorsServiceImpl implements RockPaperScissorsService {

	@Autowired
	RockPaperScissorsGame rockPaperScissorsGame;

	/**
	 * Play a game assuming that player 2 will always choose Rock.
	 *
	 * @return GameInformation object with all the information related to the game that has been played.
	 * @throws MalformedGameException if the selection for the first player is not correct.
	 */
	@Override
	public GameInformation playRound() {
		try {
			String player1Choice = rockPaperScissorsGame.selectRandomChoice();
			int winner = rockPaperScissorsGame.playAGame(player1Choice);
			return createGameInformation(player1Choice, winner);

		} catch (MalformedGameException ex) {
			throw new RuntimeException(ex);
		}
	}

	private GameInformation createGameInformation(String player1Choice, int winner) {
		GameInformation gameInformation = new GameInformation();
		gameInformation.setPlayer1Choice(player1Choice);
		gameInformation.setPlayer2Choice("Rock");
		String winnerName = setWinnerName(winner);
		gameInformation.setWinner(winnerName);
		return gameInformation;
	}

	private String setWinnerName(int winner) {
		String winnerName = "";
		switch (winner) {
			case 0:
				winnerName = "Draw";
				break;
			case 1:
				winnerName = "Player 1";
				break;
			case 2:
				winnerName = "Player 2";
				break;
		}
		return winnerName;

	}

	protected void setRockPaperScissorsGame(RockPaperScissorsGame rockPaperScissorsGame){
		this.rockPaperScissorsGame = rockPaperScissorsGame;
	}
}
