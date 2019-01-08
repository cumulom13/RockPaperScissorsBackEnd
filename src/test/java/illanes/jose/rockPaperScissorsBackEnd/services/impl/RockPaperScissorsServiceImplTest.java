package illanes.jose.rockPaperScissorsBackEnd.services.impl;

import illanes.jose.rockPaperScissorsBackEnd.enumerations.RockPaperScissors;
import illanes.jose.rockPaperScissorsBackEnd.enumerations.Winner;
import illanes.jose.rockPaperScissorsBackEnd.exceptions.MalformedGameException;
import illanes.jose.rockPaperScissorsBackEnd.model.GameInformation;
import illanes.jose.rockPaperScissorsBackEnd.model.GlobalGameInformation;
import illanes.jose.rockPaperScissorsBackEnd.model.RockPaperScissorsGame;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RockPaperScissorsServiceImplTest {
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private RockPaperScissorsGame mockRockPaperScissorsGame;

	RockPaperScissorsServiceImpl underTest;

	@Before
	public void init() {
		underTest = new RockPaperScissorsServiceImpl();
		underTest.setRockPaperScissorsGame(mockRockPaperScissorsGame);
	}

	@Test
	public void playRoundShouldAlwaysSetPlayer2ChoiceToRock() {
		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getPlayer2Choice(), is(RockPaperScissors.ROCK.getValue()));
	}

	@Test
	public void player2ShouldBeTheWinner() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn(RockPaperScissors.SCISSORS.getValue());
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willReturn(2);

		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getWinner(), is(Winner.PLAYER2.getValue()));
	}

	@Test
	public void player1ShouldBeTheWinner() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn(RockPaperScissors.PAPER.getValue());
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willReturn(1);

		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getWinner(), is(Winner.PLAYER1.getValue()));
	}

	@Test
	public void winnerShouldBeADraw() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn(RockPaperScissors.ROCK.getValue());
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willReturn(0);

		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(response.getWinner(), is(Winner.DRAW.getValue()));
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowARuntimeException() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn(RockPaperScissors.PAPER.getValue());
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willThrow(MalformedGameException.class);

		// When
		GameInformation response = underTest.playRound();

		// Then exception is thrown.
	}

	@Test
	public void shouldUpdateGlobalGameInformation() throws MalformedGameException {
		// Given
		given(mockRockPaperScissorsGame.selectRandomChoice()).willReturn(RockPaperScissors.ROCK.getValue());
		given(mockRockPaperScissorsGame.playAGame(any(String.class))).willReturn(0);
		int currentNumberOfDraws = GlobalGameInformation.getInstance().getDraws();
		int currentNumberOfRounds = GlobalGameInformation.getInstance().getTotalNumberOfGames();

		// When
		GameInformation response = underTest.playRound();

		// Then
		assertThat(GlobalGameInformation.getInstance().getDraws(), is(currentNumberOfDraws + 1));
		assertThat(GlobalGameInformation.getInstance().getTotalNumberOfGames(), is(currentNumberOfRounds + 1));
	}

	@Test
	public void shouldReturnGlobalGameInformation() {
		// When
		GlobalGameInformation globalGameInformation = underTest.getGlobalGameInformation();

		// Then
		assertThat(globalGameInformation, instanceOf(GlobalGameInformation.class));
	}
}