package com.tele2.montyhall.simulation.pojo;

public class SimulationResult {
	
	private String simulationId ;
	private Long winningRoundsCount ;
	private Long losingRoundsCount ;
	
	
	public SimulationResult(String simulationId, Long winningRoundsCount, Long losingRoundsCount) {
		this.simulationId = simulationId;
		this.winningRoundsCount = winningRoundsCount;
		this.losingRoundsCount = losingRoundsCount;
	}
	
	public String getSimulationId() {
		return simulationId;
	}
	public Long getWinningRoundsCount() {
		return winningRoundsCount;
	}

	public Long getLosingRoundsCount() {
		return losingRoundsCount;
	}
	
	
	

}
