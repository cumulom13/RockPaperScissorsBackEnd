package illanes.jose.rockPaperScissorsBackEnd.services.impl;

import illanes.jose.rockPaperScissorsBackEnd.exceptions.MalformedGameException;
import illanes.jose.rockPaperScissorsBackEnd.model.GameInformation;
import illanes.jose.rockPaperScissorsBackEnd.model.RockPaperScissorsGame;
import illanes.jose.rockPaperScissorsBackEnd.services.RockPaperScissorsService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RockPaperScissorsServiceImplTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private RockPaperScissorsGame mockRockPaperScissorsGame;

	RockPaperScissorsService underTest;

	@Before
	public void init() {
		underTest = new RockPaperScissorsServiceImpl();
		((RockPaperScissorsServiceImpl) underTest).setRockPaperScissorsGame(mockRockPaperScissorsGame);
	}

	@Test
	public void playRoundShouldAlwaysSetPlayer2ChoiceToRock() {
		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getPlayer2Choice(), is("Rock"));
	}

	@Test
	public void player2ShouldBeTheWinner() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn("Scissors");
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willReturn(2);

		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getWinner(), is("Player 2"));
	}

	@Test
	public void player1ShouldBeTheWinner() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn("Paper");
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willReturn(1);

		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getWinner(), is("Player 1"));
	}

	@Test
	public void winnerShouldBeADraw() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn("Rock");
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willReturn(0);

		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getWinner(), is("Draw"));
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowARuntimeException() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn("Paper");
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willThrow(MalformedGameException.class);

		// When
		GameInformation response = underTest.playRound();

		// Then exception is thrown.
	}
}