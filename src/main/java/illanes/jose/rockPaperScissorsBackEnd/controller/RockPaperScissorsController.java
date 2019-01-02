package illanes.jose.rockPaperScissorsBackEnd.controller;

import illanes.jose.rockPaperScissorsBackEnd.model.GameInformation;
import illanes.jose.rockPaperScissorsBackEnd.services.RockPaperScissorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RockPaperScissorsController {

	@Autowired
	private RockPaperScissorsService rockPaperScissorsService;

	@GetMapping(path = "/rockPaperScissors/playRound")
	public ResponseEntity<GameInformation> playRound() {
		return ResponseEntity.status(HttpStatus.OK).body(rockPaperScissorsService.playRound());
	}
}
