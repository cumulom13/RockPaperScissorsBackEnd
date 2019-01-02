package illanes.jose.rockPaperScissorsBackEnd.model;

import illanes.jose.rockPaperScissorsBackEnd.exceptions.MalformedGameException;
import illanes.jose.rockPaperScissorsBackEnd.model.RockPaperScissorsGame;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class RockPaperScissorsGameTest {

	private RockPaperScissorsGame underTest;

	@Before
	public void init() {
		underTest = new RockPaperScissorsGame();
	}

	@Test
	public void shouldSelectRandomChoice() {
		//Given
		String player1Choice;

		// When
		player1Choice = underTest.selectRandomChoice();

		// Then
		assertThat(player1Choice.isEmpty(), is(false));
	}


	@Test
	public void shouldReturnADraw() throws MalformedGameException {
		//Given
		String player1Choice = "Rock";

		// When
		int result = underTest.playAGame(player1Choice);

		// Then
		assertThat(result, is(0));
	}


	@Test
	public void player1ShouldBeTheWinner() throws MalformedGameException {
		//Given
		String player1Choice = "Paper";

		// When
		int result = underTest.playAGame(player1Choice);

		// Then
		assertThat(result, is(1));
	}


	@Test
	public void player2ShouldBeTheWinner() throws MalformedGameException {
		//Given
		String player1Choice = "Scissors";

		// When
		int result = underTest.playAGame(player1Choice);

		// Then
		assertThat(result, is(2));
	}

	@Test(expected = MalformedGameException.class)
	public void shouldThrowAMalformedException() throws MalformedGameException {
		// Given
		String player1Choice = "A non valid option";

		// When
		int result = underTest.playAGame(player1Choice);

		// Then exception is thrown.
	}
}