package com.privatebusiness.KozySound.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.privatebusiness.KozySound.model.MainModel;



public class MainView extends JFrame{
	
	private MainModel m_model;
	private JLabel statusbar;
	private JPanel MainPanel;
	private JMenuBar menubar;
	
	public MainView(MainModel model) {
		m_model = model;
		/*Main Frame*/
		setSize(500, 500);
		setLayout(new BorderLayout());
		
		/*MenuBar*/
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu files = new JMenu("Files");
		JMenu preferences = new JMenu("Preferences");
		menubar.add(files);
		menubar.add(preferences);
		
		
		/*Files Item*/
		JMenuItem closeAction = new JMenuItem("Close");
		files.add(closeAction);
		
		/*Preferences Item*/
		JMenuItem configurationAction = new JMenuItem("Settings");
		preferences.add(configurationAction);
		
			
		
		/*Status Label*/
		statusbar = new JLabel();
		statusbar.setHorizontalAlignment(SwingConstants.RIGHT);
		statusbar.setHorizontalTextPosition(SwingConstants.RIGHT);
		
		
		/*Main Panel*/
		MainPanel = new JPanel();
		MainPanel.setSize(500, 500);
		MainPanel.setLayout(new BorderLayout());
		MainPanel.add(statusbar, BorderLayout.SOUTH);
		add(MainPanel);
		
	}
}
