package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.PlainDocument;

import controller.AddComputerDevice;
import controller.BackWindowController;
import controller.DeleteComputerDevice;
import controller.EditComputerDevice;
import model.Database;
import model.Device;

public class ListWindow extends JFrame {

	private Window window;
	private JFrame frame;
	private Database database;
	private JList myList;
	private JScrollPane myScrollpane;
	private DefaultListModel listModel;
	private PlainDocument priceDoc;

	private AddComputerDevice addDevice;
	private DeleteComputerDevice deleteDevice;
	private EditComputerDevice editDevice;
	private BackWindowController backWindowController;

	private JTextField deviceNameField = new JTextField(36);
	private JTextField devicePriceField = new JTextField(36);
	private JButton addButton = new JButton("Добавить");
	private JButton editButton = new JButton("Правка");
	private JButton deleteButton = new JButton("Удалить");
	private JButton backButton = new JButton("Выбрать раздел");

	public ListWindow() {

		frame = new JFrame();
		frame.setTitle("Device List");
		frame.setSize(420, 375);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());
		myList = new JList();
		myScrollpane = new JScrollPane(myList);
		myScrollpane.setPreferredSize(new Dimension(395, 205));
		myList.setLayoutOrientation(JList.VERTICAL);
		listModel = new DefaultListModel();

		priceDoc = (PlainDocument) devicePriceField.getDocument();
		priceDoc.setDocumentFilter(new DigitFilter());
	
		myList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = myList.getSelectedIndex();
				if (index >= 0) {
					String type = frame.getTitle();

					ArrayList<Device> newDatabase = database.searchDeviceByType(type);

					String name = newDatabase.get(index).getName();
					int price = newDatabase.get(index).getPrice();
					deviceNameField.setText(name);
					devicePriceField.setText(Integer.toString(price));

				} else {
					deviceNameField.setText(null);
					devicePriceField.setText(null);
				}
			}
		});

		container.add(myScrollpane);
		JLabel deviceNameLabel = new JLabel("Наименование");
		container.add(deviceNameLabel);
		container.add(deviceNameField);
		JLabel devicePriceLabel = new JLabel("Цена");
		container.add(devicePriceLabel);
		container.add(devicePriceField);
		container.add(addButton);
		container.add(deleteButton);
		container.add(editButton);
		container.add(backButton);

		frame.setResizable(false);
		frame.setVisible(false);
	}

	public PlainDocument getPriceDoc() {
		return priceDoc;
	}

	public void setPriceDoc(PlainDocument priceDoc) {
		this.priceDoc = priceDoc;
	}

	public AddComputerDevice getAddDevice() {
		return addDevice;
	}

	public void setAddDevice(AddComputerDevice addDevice) {
		this.addDevice = addDevice;
		addButton.addActionListener(addDevice);
	}

	public DeleteComputerDevice getDeleteDevice() {
		return deleteDevice;
	}

	public void setDeleteDevice(DeleteComputerDevice deleteDevice) {
		this.deleteDevice = deleteDevice;
		deleteButton.addActionListener(deleteDevice);
	}

	public EditComputerDevice getEditDevice() {
		return editDevice;
	}

	public void setEditDevice(EditComputerDevice editDevice) {
		this.editDevice = editDevice;
		editButton.addActionListener(editDevice);
	}

	public BackWindowController getBackWindowController() {
		return backWindowController;
	}

	public void setBackWindowController(BackWindowController backWindowController) {
		this.backWindowController = backWindowController;
		backButton.addActionListener(backWindowController);
	}

	public DefaultListModel getListModel() {
		return listModel;
	}

	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {

		this.window = window;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JList getMyList() {
		return myList;
	}

	public void setMyList(JList myList) {
		this.myList = myList;
	}

	public JScrollPane getMyScrollpane() {
		return myScrollpane;
	}

	public void setMyScrollpane(JScrollPane myScrollpane) {
		this.myScrollpane = myScrollpane;
	}

	public String getDeviceNameField() {
		return deviceNameField.getText();
	}

	public void setDeviceNameField(JTextField deviceNameField) {
		this.deviceNameField = deviceNameField;
	}

	public String getDevicePriceField() {
		return devicePriceField.getText();
	}

	public void setDevicePriceField(JTextField devicePriceField) {
		this.devicePriceField = devicePriceField;
	}

	public void showMessage(String text) {
		JOptionPane.showMessageDialog(null, text);
	}

	public void showComputerDevices(ArrayList<Device> devices) {
		if (devices.size() == 0) {
			showMessage("Устройство не найдено");
			return;
		}
		StringBuilder result = new StringBuilder();
		boolean isExists = false;
		for (Device device : devices) {
			if (device != null) {
				isExists = true;
				result.append(device).append("\n");
			}
		}
		if (isExists) {
			showMessage(result.toString());
		} else {
			showMessage("Нет устройств");
		}

	}
}
