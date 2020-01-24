package com.tele2.montyhall.simulation.pojo;

public class SimulationRequest {
	
	private GameSimulationMode simulationMode ;
	private Integer simulationRounds ;
	
	public SimulationRequest(GameSimulationMode simulationMode, Integer simulationRounds) {
		super();
		this.simulationMode = simulationMode;
		this.simulationRounds = simulationRounds;
	}

	public GameSimulationMode getSimulationMode() {
		return simulationMode;
	}

	public Integer getSimulationRounds() {
		return simulationRounds;
	}
	
	

}
