package com.base.ui.secondscreen;

import com.base.core.ui.scenes.Controller;
import com.base.core.ui.scenes.manager.UIManager;
import com.base.ui.firstscreen.FirstScreenView;

import javafx.fxml.FXML;

public class SecondScreenController extends Controller {

	@Override
	protected void initialize() {}

	@Override
	public void initializeComponents() {}

	@Override
	public void initializeFocus() {}

	@FXML
	private void back() {
		UIManager.showView(FirstScreenView.class);
	}

}
