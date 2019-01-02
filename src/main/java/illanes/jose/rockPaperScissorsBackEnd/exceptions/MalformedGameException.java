package illanes.jose.rockPaperScissorsBackEnd.exceptions;

public class MalformedGameException extends Throwable {

	String errorMessage;

	public MalformedGameException(String player1) {
		this.errorMessage = "Option" + player1 + "is not valid";
	}

	public String getErrorMessage(){
		return errorMessage;
	}
}
