package illanes.jose.rockPaperScissorsBackEnd.enumerations;

public enum Winner {
	PLAYER1("Player 1"), PLAYER2("Player 2"), DRAW("Draw"), EMPTY_WINNER("");

	private String value;

	Winner(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Winner valueOfIgnoreCase(String value) {
		value = value.replaceAll(" ", "").toUpperCase();
		return valueOf(value);
	}


}
