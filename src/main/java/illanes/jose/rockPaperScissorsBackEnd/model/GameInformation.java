package illanes.jose.rockPaperScissorsBackEnd.model;

import lombok.Data;

/**
 * Data object class to store the information of a round.
 * This class is using lombok library to avoid the boilerplate code.
 */
@Data
public class GameInformation {

	private String player1Choice;

	private String player2Choice;

	private String winner;

}
