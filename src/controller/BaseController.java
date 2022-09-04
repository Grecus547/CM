package controller;

import java.awt.event.ActionListener;

import model.Database;
import view.ListWindow;
import view.Window;

public abstract class BaseController implements ActionListener {

	protected Window window;
	protected ListWindow listWindow;
	protected Database database;

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public ListWindow getListWindoww() {
		return listWindow;
	}

	public void setListWindow(ListWindow listWindow) {
		this.listWindow = listWindow;
	}

}
