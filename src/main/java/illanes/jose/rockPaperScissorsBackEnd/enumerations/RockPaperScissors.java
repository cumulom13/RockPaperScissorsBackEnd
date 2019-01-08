package illanes.jose.rockPaperScissorsBackEnd.enumerations;


public enum RockPaperScissors {

	ROCK("Rock"), PAPER("Paper"), SCISSORS("Scissors");

	private String value;

	RockPaperScissors(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

