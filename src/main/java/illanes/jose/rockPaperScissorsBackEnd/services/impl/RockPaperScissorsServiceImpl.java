package illanes.jose.rockPaperScissorsBackEnd.services.impl;

import illanes.jose.rockPaperScissorsBackEnd.enumerations.RockPaperScissors;
import illanes.jose.rockPaperScissorsBackEnd.enumerations.Winner;
import illanes.jose.rockPaperScissorsBackEnd.exceptions.MalformedGameException;
import illanes.jose.rockPaperScissorsBackEnd.model.GameInformation;
import illanes.jose.rockPaperScissorsBackEnd.model.GlobalGameInformation;
import illanes.jose.rockPaperScissorsBackEnd.model.RockPaperScissorsGame;
import illanes.jose.rockPaperScissorsBackEnd.services.RockPaperScissorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RockPaperScissorsServiceImpl implements RockPaperScissorsService {

	@Autowired
	private RockPaperScissorsGame rockPaperScissorsGame;

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
			GameInformation gameInformation = createGameInformation(player1Choice, winner);
			updateGlobalGameInformation(gameInformation);
			return gameInformation;

		} catch (MalformedGameException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public GlobalGameInformation getGlobalGameInformation() {
		return GlobalGameInformation.getInstance();

	}

	private GameInformation createGameInformation(String player1Choice, int winner) {
		GameInformation gameInformation = new GameInformation();
		gameInformation.setPlayer1Choice(player1Choice);
		gameInformation.setPlayer2Choice(RockPaperScissors.ROCK.getValue());
		String winnerName = setWinnerName(winner);
		gameInformation.setWinner(winnerName);
		return gameInformation;
	}

	private String setWinnerName(int winner) {
		String winnerName = "";
		switch (winner) {
			case 0:
				winnerName = Winner.DRAW.getValue();
				break;
			case 1:
				winnerName = Winner.PLAYER1.getValue();
				break;
			case 2:
				winnerName = Winner.PLAYER2.getValue();
				break;
		}
		return winnerName;

	}

	private void updateGlobalGameInformation(GameInformation gameInformation) {
		GlobalGameInformation global = GlobalGameInformation.getInstance();
		global.setTotalNumberOfGames(global.getTotalNumberOfGames() + 1);
		addWinnerInformation(global, Winner.valueOfIgnoreCase(gameInformation.getWinner()));
	}

	private void addWinnerInformation(GlobalGameInformation global, Winner winner) {
		int currentNumber = 0;
		switch (winner) {
			case PLAYER1:
				currentNumber = global.getPlayer1WinnerTimes();
				global.setPlayer1WinnerTimes(currentNumber + 1);
				break;
			case PLAYER2:
				currentNumber = global.getPlayer2WinnerTimes();
				global.setPlayer2WinnerTimes(currentNumber + 1);
				break;
			case DRAW:
				currentNumber = global.getDraws();
				global.setDraws(currentNumber + 1);
				break;
		}

	}

	// Visible for testing
	protected void setRockPaperScissorsGame(RockPaperScissorsGame rockPaperScissorsGame) {
		this.rockPaperScissorsGame = rockPaperScissorsGame;
	}
}
