package com.tele2.montyhall.simulation.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameRound {

	private GameSimulationMode simulationMode;
	private static final Integer BOXES_COUNT = 3;
	private List<Boolean> boxes;

	public GameRound(GameSimulationMode simulationMode) {
		this.simulationMode = simulationMode;
		intializeBoxes();
	}

	public GameSimulationMode getSimulationMode() {
		return simulationMode;
	}

	private void intializeBoxes() {
		boxes = Arrays.asList(false, false, false);
		setWinningBoxRandomly();
	}

	private void setWinningBoxRandomly() {
		Integer randomIndex = generateRandomIntNumber(BOXES_COUNT);

		// set the winning box
		boxes.set(randomIndex, Boolean.TRUE);
	}

	public List<Boolean> getBoxes() {
		return boxes;
	}

	public Boolean play() {
		Boolean roundResult = Boolean.FALSE;

		Integer playerBoxSelectionIndex = selectPlayerBox();
		Integer winnerBoxIndex = getBoxes().indexOf(Boolean.TRUE);

		switch (simulationMode) {
		case STICK:
			if (playerBoxSelectionIndex == winnerBoxIndex)
				roundResult = Boolean.TRUE;
			break;
		case SWAP:
			if (playerBoxSelectionIndex != winnerBoxIndex)
				roundResult = Boolean.TRUE;
			break;
		default:
			break;
		}

		return roundResult;
	}

	public Integer selectPlayerBox() {
		return generateRandomIntNumber(BOXES_COUNT);
	}

	private Integer generateRandomIntNumber(Integer upperLimit) {
		Random random = new Random();
		return random.nextInt(upperLimit);
	}

}
