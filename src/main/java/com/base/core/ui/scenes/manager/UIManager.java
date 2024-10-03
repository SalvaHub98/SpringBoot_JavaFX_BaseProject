package com.base.core.ui.scenes.manager;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.core.ui.alerts.AlertManager;
import com.base.core.ui.scenes.Controller;
import com.base.core.ui.scenes.View;
import com.base.core.ui.scenes.modal.ModalController;
import com.base.core.ui.scenes.modal.ModalView;
import com.base.ui.firstscreen.FirstScreenView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UIManager {

	private static Stage primaryStage;
	private static Map<Class<?>, Scene> sceneCacheMap;
	private static Map<Class<?>, Controller> controllerCacheMap;

	private static final Logger log = LoggerFactory.getLogger(UIManager.class);

	/**
	 * Initialize the UIManager: set up the main Stage and prepare the cache.
	 * 
	 * @param stage Main Stage of the application.
	 */
	public static void init(Stage stage) {
		log.info("init()");

		sceneCacheMap = new HashMap<Class<?>, Scene>();
		controllerCacheMap = new HashMap<Class<?>, Controller>();

		primaryStage = stage;
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
		primaryStage.sizeToScene();
		showView(FirstScreenView.class);
		
		primaryStage.show();
	}

	/**
	 * Changes the Scene of the main Stage to the one associated with the given
	 * class, or shows a new modal Stage if the given class is modal.
	 * 
	 * @param clazz Subclass of "View.class" associated with the FXML file to be
	 *              loaded.
	 */
	public static <T extends View> void showView(Class<T> clazz) {
		log.info("show() - Mostrando View asociada a " + clazz.getName());

		try {
			Object[] sceneControllerPair = loadSceneControllerPair(clazz);
			Scene scene = (Scene) sceneControllerPair[0];
			Controller controller = (Controller) sceneControllerPair[1];

			if (!ModalView.class.isAssignableFrom(clazz)) { // No modal
				log.info("show() - Cambiando Scene principal por la asociada a la View " + clazz.getName());

				/* The current Scene is changed. */
				primaryStage.setScene(scene);
				primaryStage.centerOnScreen();

				controller.refresh();
				
			} else { // Modal
				log.info("show() - Mostrando pantalla modal asociada a la View " + clazz.getName());

				/* A new Stage is created and initialized. */
				Stage modalStage = new Stage();
				modalStage.setAlwaysOnTop(true);
				modalStage.setResizable(false);
				modalStage.centerOnScreen();
				modalStage.initModality(Modality.APPLICATION_MODAL);
				
				modalStage.setScene(scene);

				controller.refresh();
				((ModalController) controller).initModalStage(modalStage);

				modalStage.showAndWait();
			}
		} catch (IOException | IllegalStateException e) {
			String headerMessage = "Error cargando la pantalla " + clazz.getSimpleName().replace("View", "");
			AlertManager.showAlert(headerMessage, e.getMessage(), AlertType.ERROR);
			
			log.error(headerMessage);
			e.printStackTrace();
		}
	}

	/**
	 * Returns an Object[] containing the Scene/Controller pair for the class
	 * passed as a parameter. If the pair was previously loaded, it retrieves it
	 * from the cache; otherwise, it adds it.
	 * 
	 * @param clazz Subclass of "View.class" associated with the FXML file to be
	 *              loaded.
	 */
	private static <T extends View> Object[] loadSceneControllerPair(Class<T> clazz) throws IOException, IllegalStateException {
		log.info("loadSceneControllerPair() - Cargando Scene y Controller asociados a la View " + clazz.getName());
		
		Scene scene = sceneCacheMap.get(clazz);
		Controller controller = controllerCacheMap.get(clazz);

		/*
		 * On the first execution, we instantiate the Scene and the Controller, and add
		 * them to the cache to avoid creating additional instances.
		 */
		try {
			if (scene == null) {
				URL location = buildFxmlURL(clazz);

				FXMLLoader fxmlLoader = new FXMLLoader(location);
				scene = new Scene(fxmlLoader.load());

				controller = fxmlLoader.getController();
				sceneCacheMap.put(clazz, scene);
				controllerCacheMap.put(clazz, controller);

				log.info("loadSceneControllerPair() - Scene y Controller asociados a la View " + clazz.getName() + " agregados a la caché");
				
				// Contexto de spring
//					fxmlLoader.setControllerFactory(ApplicationData.getSpringContext()::getBean);
			}
		} catch (IOException e) {
			if (e.getCause() instanceof ClassNotFoundException) {
				throw new IOException("La clase del controlador no se encuentra para la View " + clazz.getName());
			} else {
				throw e;
			}
		}

		return new Object[] { scene, controller };
	}

	/**
	 * Returns the URL of the FXML file associated with the given class. The search
	 * is performed in the application's resources, in the same path as the class
	 * provided as a parameter.
	 * 
	 * @param clazz Subclass of "View.class" used as a resource for locating the
	 *              FXML file.
	 * 
	 * @return URL of the FXML file associated with the subclass of "View.class"
	 *         provided.
	 * 
	 * @throws IllegalStateException If the file does not exist
	 */
	public static <T extends View> URL buildFxmlURL(Class<T> clazz) throws IllegalStateException {
		
		String fxmlPath = "fxml/" + clazz.getName().replace(".", "/").replace("View", ".fxml").toLowerCase();
		
		log.info("buildFxmlURL() - Path del FXML asociado a la View " + clazz.getName() + ": " + fxmlPath);
		
		URL fxmlURL = clazz.getClassLoader().getResource(fxmlPath);
		
		if (fxmlURL == null)
			throw new IllegalStateException("Fichero FXML no encontrado: " + fxmlPath);

		return fxmlURL;
	}

}
