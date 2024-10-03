package com.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.base.core.ui.scenes.manager.UIManager;

import javafx.application.Application;
import javafx.stage.Stage;

@SpringBootApplication
public class Main extends Application {

	private static final Logger log = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void init() throws Exception {
		log.info("init() - Inicializando App");
		
		// Cargamos el contexto de SpringBoot
//		ConfigurableApplicationContext springContext = SpringApplication.run(Main.class);

		/* Inicializamos la data de la aplicación */
//		ApplicationData.setSpringContext(springContext);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		log.info("start() - Inicializando JavaFX");

		UIManager.init(primaryStage);
	}
}
