package com.tele2.montyhall.simulation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.tele2.montyhall.simulation.pojo.GameRound;
import com.tele2.montyhall.simulation.pojo.GameSimulationMode;
import com.tele2.montyhall.simulation.pojo.SimulationResult;

@Service
public class GameService {

	public SimulationResult startGame(GameSimulationMode simulationMode, Integer simulationRounds) {
		String gameId = UUID.randomUUID().toString();
		ArrayList<GameRound> gameRounds = createGameRounds(simulationMode,simulationRounds) ;
		List<Boolean> gameRoundsResult = gameRounds.parallelStream()
				  								   .map( (gameRound) -> gameRound.play())
                                                   .collect(Collectors.toList());
		Long losingRoundsCount = gameRoundsResult.stream().filter((roundResult) -> roundResult==Boolean.FALSE).count();
		Long winningRoundsCount = gameRoundsResult.stream().filter((roundResult) -> roundResult==Boolean.TRUE).count();
		return new SimulationResult(gameId ,winningRoundsCount , losingRoundsCount );
	}

	public ArrayList<GameRound> createGameRounds(GameSimulationMode simulationMode, Integer numberOfRounds) {
		ArrayList<GameRound> rounds = null;
		if (simulationMode.equals(GameSimulationMode.ALTERNATE)) {
			
			Integer stickNumberOfRounds = numberOfRounds / 2;
			Integer swapNumberOfRounds = numberOfRounds - stickNumberOfRounds;

			rounds = Stream.generate(() -> new GameRound(GameSimulationMode.STICK)).limit(stickNumberOfRounds)
					.collect(Collectors.toCollection(ArrayList::new));
			
			rounds.addAll(Stream.generate(() -> new GameRound(GameSimulationMode.SWAP)).limit(swapNumberOfRounds)
					.collect(Collectors.toCollection(ArrayList::new)));
			return rounds;

		} else {
			rounds = Stream.generate(() -> new GameRound(simulationMode)).limit(numberOfRounds)
					.collect(Collectors.toCollection(ArrayList::new));

		}

		return rounds;
	}

}
