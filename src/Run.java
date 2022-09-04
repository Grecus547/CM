import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import controller.AddComputerDevice;
import controller.BackWindowController;
import controller.DeleteComputerDevice;
import controller.EditComputerDevice;
import controller.OpenNewWindowController;
import model.Database;
import model.Device;
import view.ListWindow;
import view.Window;

public class Run {

	private final static Logger LOGGER = Logger.getLogger(Run.class);

	public static void main(String[] args) throws IOException {

		Window window = new Window();
		LOGGER.info("new window created");

		ListWindow listWindow = new ListWindow();
		LOGGER.info("new listWindow created");

		OpenNewWindowController openController = new OpenNewWindowController();
		BackWindowController backController = new BackWindowController();
		AddComputerDevice addDevice = new AddComputerDevice();
		DeleteComputerDevice deleteDevice = new DeleteComputerDevice();
		EditComputerDevice editDevice = new EditComputerDevice();

		Device device1 = new Device("AMD Ryzen 3 1200", 100, "Процессоры");
		Device device2 = new Device("Intel Core i3-8100", 130, "Процессоры");
		Device device3 = new Device("Intel Core i5-8400", 200, "Процессоры");
		Device device4 = new Device("Gigabyte GeForce GT 1030", 125, "Видеокарты");
		Device device5 = new Device("Sapphire Pulse Radeon RX 550", 150, "Видеокарты");
		Device device6 = new Device("ASUS Phoenix GeForce GT 1030", 190, "Видеокарты");
		Device device7 = new Device("MSI H510M-A PRO", 80, "Материнские платы");
		Device device8 = new Device("Gigabyte B560M H", 90, "Материнские платы");
		Device device9 = new Device("ASUS Prime H510M-A", 100, "Материнские платы");

		ArrayList<Device> devices = new ArrayList<Device>(10);

		List deviceList = Arrays.asList(device1, device2, device3, device4, device5, device6, device7, device8,
				device9);

		devices.addAll(deviceList);

		Database generalDB = new Database(devices);

		LOGGER.info("new database created");

		window.setOpenController(openController);

		listWindow.setDatabase(generalDB);
		listWindow.setBackWindowController(backController);
		listWindow.setAddDevice(addDevice);
		listWindow.setDeleteDevice(deleteDevice);
		listWindow.setEditDevice(editDevice);

		openController.setWindow(window);
		openController.setListWindow(listWindow);
		openController.setDatabase(generalDB);

		addDevice.setListWindow(listWindow);
		addDevice.setDatabase(generalDB);

		deleteDevice.setListWindow(listWindow);
		deleteDevice.setDatabase(generalDB);

		editDevice.setListWindow(listWindow);
		editDevice.setDatabase(generalDB);

		backController.setListWindow(listWindow);
		backController.setWindow(window);

	}
}
