package com.tele2.montyhall.simulation.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tele2.montyhall.simulation.pojo.SimulationRequest;
import com.tele2.montyhall.simulation.pojo.SimulationResult;
import com.tele2.montyhall.simulation.service.GameService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class GameController {

	@Autowired
	GameService gameService ;
	
	@GetMapping("/ping")
	public String ping(){
		return new String("pong");
	}
	@ApiOperation(value = "start a new game simulation with the specified paramters", response = SimulationResult.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "success response "),
	})

	@PostMapping(value = "/game/start",produces = MediaType.APPLICATION_JSON_VALUE)
	public SimulationResult startGameSimulation(@RequestBody SimulationRequest request){
		SimulationResult result = gameService.startGame(request.getSimulationMode() , request.getSimulationRounds());
		return result ;
		
	}
	
}
