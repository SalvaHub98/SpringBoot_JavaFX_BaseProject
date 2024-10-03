package com.base.core.ui.scenes;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.Initializable;

public abstract class Controller implements Initializable {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	private static HashMap<String, Object> dataMap = new HashMap<String, Object>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.info("initialize()");
		
		initialize();
	}
	
	/**
	 * Method that executes when the Scene is initialized for the first time.
	 */
	protected abstract void initialize();

	/**
	 * Method that executes each time the Scene is used, running after
	 * "initialize()".
	 */
	public abstract void initializeComponents();

	/**
	 * Method that executes each time the Scene is used, running after
	 * "initializeComponents()".
	 */
	public abstract void initializeFocus();

	/**
	 * Method for adding objects to the shared map accessible by all controllers.
	 * 
	 * @param key  Key associated with the object to be added to the shared map
	 *             accessible by all controllers. Preferably use a key defined in
	 *             the "AppKeys.class".
	 * @param data Object to be included in the map.
	 * 
	 */
	protected void addData(String key, Object data) {
		log.info("addData() - " + key + " " + data.getClass().getSimpleName());
		
		dataMap.put(key, data);
	}

	public void refresh() {
		log.info(("refresh()"));
		log.info(("initializeComponents()"));
		initializeComponents();
		log.info(("initializeFocus()"));
		initializeFocus();
	}
	
	/**
	 * Returns the object from the shared map accessible by all controllers
	 * associated with a key and remove it, or null if no object is associated with that key
	 * 
	 * @param key Key associated with the object to be added to the shared map
	 *            accessible by all controllers. Preferably use a key defined in the
	 *            "AppKeys.class".
	 */
	protected Object getData(String key) {

		if (!dataMap.containsKey(key)) {
			log.info("getData() - " + key + " no encontrado");
			return null;
		}

		Object object = dataMap.get(key);
		dataMap.remove(key);

		log.info("getData() - " + key + " " + object.getClass().getSimpleName());
		
		return object;
	}
	
	

}
