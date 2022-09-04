package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.Device;
import view.DigitFilter;

public class EditComputerDevice extends BaseController {

	private final static Logger LOGGER = Logger.getLogger(EditComputerDevice.class);

	@Override
	public void actionPerformed(ActionEvent e) {
		LOGGER.info("pressed \"edit\"");
		int index = listWindow.getMyList().getSelectedIndex();
		String type = listWindow.getFrame().getTitle();
		String newName = listWindow.getDeviceNameField();

		if (index < 0) {
			LOGGER.debug("device not selected");
			listWindow.showMessage("Нужно выбрать устройство из списка");

		} else if (index >= 0 && newName.isEmpty() || listWindow.getDevicePriceField().isEmpty()) {
			LOGGER.debug("not all fields are filled");
			listWindow.showMessage("Не все поля заполнены");
			return;

		} else if (index >= 0 && !newName.isEmpty() && !listWindow.getDevicePriceField().isEmpty()) {
			String name = database.searchDeviceByType(type).get(index).getName();
			int price = database.searchDeviceByType(type).get(index).getPrice();
			int newPrice = Integer.parseInt(listWindow.getDevicePriceField());
			if (database.searchDeviceByNameAndPrice(newName, newPrice)) {
				LOGGER.debug("entered device that already exists");
				listWindow.showMessage("Устройство с такими параметрами уже есть в списке");
				return;
			}
			int dbIndex = database.findDeviceByNameAndPrice(name, price);
			ArrayList<Device> editedDevices = database.editDevice(dbIndex, newName, newPrice, type);

			LOGGER.info("device edited");
			LOGGER.debug("edited device name = " + newName + ", price = " + newPrice + ", type = " + type);
		}
		
		listWindow.getPriceDoc().setDocumentFilter(null);
		ArrayList<Device> newDatabase = database.searchDeviceByType(type);
		listWindow.getMyList().setListData(newDatabase.toArray());
		listWindow.getPriceDoc().setDocumentFilter(new DigitFilter());
	}
}
