package com.tele2.montyhall.simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.tele2.montyhall.simulation.pojo.GameRound;
import com.tele2.montyhall.simulation.pojo.GameSimulationMode;
import com.tele2.montyhall.simulation.pojo.SimulationResult;
import com.tele2.montyhall.simulation.service.GameService;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class GameServiceTests {

	@Autowired
	GameService service;

	@Test
	public void test_game_service_start_game() {

		SimulationResult stickResult = service.startGame(GameSimulationMode.STICK, 100);
		Long winningRoundCount = stickResult.getWinningRoundsCount();
		Long losingRoundsCount = stickResult.getLosingRoundsCount();
		assertNotNull(stickResult.getSimulationId());
		assertEquals(100, winningRoundCount + losingRoundsCount);

		SimulationResult swapResult = service.startGame(GameSimulationMode.SWAP, 300);
		winningRoundCount = swapResult.getWinningRoundsCount();
		losingRoundsCount = swapResult.getLosingRoundsCount();
		assertNotNull(swapResult.getSimulationId());
		assertEquals(300, winningRoundCount + losingRoundsCount);

		SimulationResult alternateResult = service.startGame(GameSimulationMode.ALTERNATE, 555);
		winningRoundCount = alternateResult.getWinningRoundsCount();
		losingRoundsCount = alternateResult.getLosingRoundsCount();
		assertNotNull(alternateResult.getSimulationId());
		assertEquals(555, winningRoundCount + losingRoundsCount);

	}

	@Test
	public void test_create_game_rounds_successfully() {

		GameService service = new GameService();
		List<GameRound> roundsStick = service.createGameRounds(GameSimulationMode.STICK, 100);
		assertEquals(100, roundsStick.size());
		assertEquals(100, roundsStick.stream()
				.filter((round) -> GameSimulationMode.STICK.equals(round.getSimulationMode())).count());

		List<GameRound> roundsSwap = service.createGameRounds(GameSimulationMode.SWAP, 100);
		assertEquals(100, roundsSwap.size());
		assertEquals(100, roundsSwap.stream()
				.filter((round) -> GameSimulationMode.SWAP.equals(round.getSimulationMode())).count());

		List<GameRound> roundsAlternate = service.createGameRounds(GameSimulationMode.ALTERNATE, 101);
		assertEquals(101, roundsAlternate.size());
		assertEquals(51, roundsAlternate.stream()
				.filter((round) -> GameSimulationMode.SWAP.equals(round.getSimulationMode())).count());
		assertEquals(50, roundsAlternate.stream()
				.filter((round) -> GameSimulationMode.STICK.equals(round.getSimulationMode())).count());

	}

}
