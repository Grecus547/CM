package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import model.Device;
import view.DigitFilter;

public class AddComputerDevice extends BaseController {

	private final static Logger LOGGER = Logger.getLogger(AddComputerDevice.class);

	@Override
	public void actionPerformed(ActionEvent e) {
		LOGGER.info("pressed \"add\"");
		String name = listWindow.getDeviceNameField();
		String type = listWindow.getFrame().getTitle();

		if (name.isEmpty() || listWindow.getDevicePriceField().isEmpty()) {
			LOGGER.debug("nothing entered");
			listWindow.showMessage("Не все поля заполнены");
			return;
		}
		if (database.searchDeviceByNameAndPrice(name, Integer.parseInt(listWindow.getDevicePriceField()))) {
			LOGGER.debug("the entered device already exists");
			listWindow.showMessage("Устройство с такими параметрами уже есть в списке");
			return;
		}
		int price = Integer.parseInt(listWindow.getDevicePriceField());
		Device newDevice = new Device(name, price, type);
		database.addDevice(newDevice);
		LOGGER.info("new device added");
		LOGGER.debug("name entered = " + name + ", price entered = " + price + ", type entered = " + type);
		
		listWindow.getPriceDoc().setDocumentFilter(null);
		ArrayList<Device> newDatabase = database.searchDeviceByType(type);
		listWindow.getMyList().setListData(newDatabase.toArray());
		listWindow.getPriceDoc().setDocumentFilter(new DigitFilter());
	}
}
