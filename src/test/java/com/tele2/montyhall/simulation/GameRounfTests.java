package com.tele2.montyhall.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.tele2.montyhall.simulation.pojo.GameRound;
import com.tele2.montyhall.simulation.pojo.GameSimulationMode;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class GameRounfTests {


	@Test
	public void test_game_round_intialized_properly() {

		GameRound round = new GameRound(GameSimulationMode.STICK);
		long winningBoxCount = round.getBoxes().stream().filter((box) -> box == Boolean.TRUE).count();
		long losingBoxCount = round.getBoxes().stream().filter((box) -> box == Boolean.FALSE).count();
		assertEquals(1, winningBoxCount);
		assertTrue(losingBoxCount > 1);
	}

	@Test
	public void test_play_game_scenarios() {
		GameRound stickAndWinRound = spy(new GameRound(GameSimulationMode.STICK));
		doReturn(Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE)).when(stickAndWinRound).getBoxes();
		doReturn(0).when(stickAndWinRound).selectPlayerBox();
		boolean result = stickAndWinRound.play();
		assertEquals(true, result);

		GameRound stickAndLoose = spy(new GameRound(GameSimulationMode.STICK));
		doReturn(Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE)).when(stickAndLoose).getBoxes();
		doReturn(2).when(stickAndLoose).selectPlayerBox();
		result = stickAndLoose.play();
		assertEquals(false, result);

		GameRound swapAndWin = spy(new GameRound(GameSimulationMode.SWAP));
		doReturn(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE)).when(swapAndWin).getBoxes();
		doReturn(1).when(swapAndWin).selectPlayerBox();
		result = swapAndWin.play();
		assertEquals(true, result);

		GameRound swapAndLoose = spy(new GameRound(GameSimulationMode.SWAP));
		doReturn(Arrays.asList(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE)).when(swapAndLoose).getBoxes();
		doReturn(0).when(swapAndLoose).selectPlayerBox();
		result = swapAndLoose.play();
		assertEquals(false, result);
	}
}
