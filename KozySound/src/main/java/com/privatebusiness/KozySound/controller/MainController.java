package com.privatebusiness.KozySound.controller;

import com.privatebusiness.KozySound.model.MainModel;
import com.privatebusiness.KozySound.view.MainView;

public class MainController {

	private MainModel m_model;
	private MainView m_view;
	
	public MainController(MainModel model, MainView view) {
		m_model = model;
		m_view = view;
		
	}
}

