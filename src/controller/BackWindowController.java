package controller;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

public class BackWindowController extends BaseController {
	
	private final static Logger LOGGER = Logger.getLogger(BackWindowController.class);

	@Override
	public void actionPerformed(ActionEvent e) {
		listWindow.getFrame().setVisible(false);
		window.getFrame().setVisible(true);
		LOGGER.info("return to main window");
	}
}
