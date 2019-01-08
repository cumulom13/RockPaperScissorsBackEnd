package illanes.jose.rockPaperScissorsBackEnd.model;

import lombok.Data;

/**
 * Singleton pattern used to create the global game information.
 * Using lombok library to avoid boilerplate code.
 */
@Data
public class GlobalGameInformation {

	private static GlobalGameInformation instance;

	private int totalNumberOfGames;

	private int player1WinnerTimes;

	private int player2WinnerTimes;

	private int draws;

	private GlobalGameInformation() {
	}

	public static GlobalGameInformation getInstance() {
		if (instance == null) {
			instance = new GlobalGameInformation();
		}
		return instance;
	}
}


