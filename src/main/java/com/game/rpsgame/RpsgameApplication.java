package com.game.rpsgame;

import com.game.rpsgame.player.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class RpsgameApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpsgameApplication.class, args);
	}



}
