package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import model.Device;
import view.DigitFilter;

public class DeleteComputerDevice extends BaseController {

	private final static Logger LOGGER = Logger.getLogger(DeleteComputerDevice.class);

	@Override
	public void actionPerformed(ActionEvent e) {
		LOGGER.info("pressed \"delete\"");
		int index = listWindow.getMyList().getSelectedIndex();
		String name = listWindow.getDeviceNameField();
		String type = listWindow.getFrame().getTitle();

		if (index < 0) {
			LOGGER.debug("nothing selected");
			listWindow.showMessage("Нужно выбрать устройство из списка");

		} else if (index >= 0 && name.isEmpty() || listWindow.getDevicePriceField().isEmpty()) {
			LOGGER.debug("not all fields are filled");
			listWindow.showMessage("Не все поля заполнены");
			return;

		} else if (index >= 0 && !name.isEmpty() && !listWindow.getDevicePriceField().isEmpty()) {
			int price = Integer.parseInt(listWindow.getDevicePriceField());
			boolean result = database.deleteDevice(name, price);
			LOGGER.debug("remote device name = " + name + ", price = " + price + ", type = " + type);
		}
		listWindow.getPriceDoc().setDocumentFilter(null);
		ArrayList<Device> newDatabase = database.searchDeviceByType(type);
		listWindow.getMyList().setListData(newDatabase.toArray());
		listWindow.getPriceDoc().setDocumentFilter(new DigitFilter());

		if (database.searchDeviceByType(type).size() == 0) {
			LOGGER.debug("all devices of type \"" + type + "\" are removed");
			listWindow.showMessage("Все " + type + " удалены");
		}
		if (database.getGeneralDB().size() == 0) {
			LOGGER.debug("all devices are removed");
			listWindow.showMessage("Все устройства удалены");
		}
	}
}
