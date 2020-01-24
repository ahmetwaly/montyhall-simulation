package com.tele2.montyhall.simulation;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tele2.montyhall.simulation.pojo.SimulationResult;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class GameIntegrationTests {


    @BeforeAll
    static void enableLoggingOfRequestAndResponseForFailingTests() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


	public static final String HOST_URL = "http://localhost:8080";

	@Test
	public void test_service_liveness_prob() {
		when()
			.get(HOST_URL +"/ping")
			.then()
			.statusCode(HttpStatus.OK.value())
			.extract()
			.body()
			.asString()
			.equalsIgnoreCase("pong");
	}
	

	@Test
	public void test_when_mode_stick_then_valid_response() {
		 SimulationResult result = 
		given()
		.header("content-type", ContentType.JSON)
		.body("{\"simulationMode\":\"STICK\",\"simulationRounds\":500}")
        .accept(ContentType.JSON)
		.when()
		.post(HOST_URL +"/game/start")
		.then()
		.statusCode(HttpStatus.OK.value())
		.extract().body().as(SimulationResult.class);
		 assertNotNull(result);
		 assertNotNull(result.getWinningRoundsCount());
		 assertNotNull(result.getSimulationId());
		 assertNotNull(result.getLosingRoundsCount());
		 assertEquals(500, result.getWinningRoundsCount() + result.getLosingRoundsCount());


	}
	
	@Test
	public void test_when_mode_swap_then_valid_response() {
		 SimulationResult result = 
		given()
		.header("content-type", ContentType.JSON)
		.body("{\"simulationMode\":\"SWAP\",\"simulationRounds\":600}")
        .accept(ContentType.JSON)
		.when()
		.post(HOST_URL +"/game/start")
		.then()
		.statusCode(HttpStatus.OK.value())
		.extract().body().as(SimulationResult.class);
		 assertNotNull(result);
		 assertNotNull(result.getWinningRoundsCount());
		 assertNotNull(result.getSimulationId());
		 assertNotNull(result.getLosingRoundsCount());
		 assertEquals(600, result.getWinningRoundsCount() + result.getLosingRoundsCount());


	}
	
	@Test
	public void test_when_mode_alternate_then_valid_response() {
		 SimulationResult result = 
		given()
		.header("content-type", ContentType.JSON)
		.body("{\"simulationMode\":\"ALTERNATE\",\"simulationRounds\":101}")
        .accept(ContentType.JSON)
		.when()
		.post(HOST_URL +"/game/start")
		.then()
		.statusCode(HttpStatus.OK.value())
		.extract().body().as(SimulationResult.class);
		 assertNotNull(result);
		 assertNotNull(result.getWinningRoundsCount());
		 assertNotNull(result.getSimulationId());
		 assertNotNull(result.getLosingRoundsCount());
		 assertEquals(101, result.getWinningRoundsCount() + result.getLosingRoundsCount());

	}


	
	

}
