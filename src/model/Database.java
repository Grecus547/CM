package model;

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Database {

	private final static Logger LOGGER = Logger.getLogger(Database.class);

	private ArrayList<Device> generalDB = new ArrayList<Device>(10);

	public Database() {

	}

	public Database(ArrayList<Device> generalDB) {
		this.generalDB = generalDB;
	}

	public ArrayList<Device> getGeneralDB() {
		return generalDB;
	}

	public void setGeneralDB(ArrayList<Device> generalDB) {
		this.generalDB = generalDB;
	}

	public void addDevice(Device device) {
		LOGGER.debug("method \"addDevice\" called");
		generalDB.add(device);
	}

	public boolean deleteDevice(int index) {
		LOGGER.debug("method \"deleteDevice(int index)\" called");
		boolean isDeleted = false;
		generalDB.remove(index);
		isDeleted = true;
		return isDeleted;
	}

	public boolean deleteDevice(String name, int price) {
		LOGGER.debug("method \"deleteDevice\" called");
		boolean isDeleted = false;
		for (int i = 0; i < generalDB.size(); i++) {
			if (generalDB.get(i) != null && generalDB.get(i).getName() != null && generalDB.get(i).getPrice() != 0
					&& generalDB.get(i).getName().equals(name) && generalDB.get(i).getPrice() == price) {
				generalDB.remove(i);
				isDeleted = true;
			}
		}
		return isDeleted;
	}

	public Device showDevice(int index) {
		LOGGER.debug("method \"showDevice\" called");
		return generalDB.get(index);
	}

	public ArrayList<Device> editDevice(int dbIndex, String newName, int newPrice, String type) {
		LOGGER.debug("method \"editDevice\" called");
		Device newDevice = new Device(newName, newPrice, type);
		generalDB.set(dbIndex, newDevice);
		return generalDB;
	}

	public ArrayList<Device> searchDeviceByType(String type) {
		LOGGER.debug("method \"searchDeviceByType\" called");
		ArrayList<Device> answer = new ArrayList<Device>(10);
		for (Device device : generalDB) {
			if (device.getType().equals(type)) {
				answer.add(device);
			}
		}
		return answer;
	}

	public boolean searchDeviceByNameAndPrice(String name, int price) {
		LOGGER.debug("method \"searchDeviceByNameAndPrice\" called");
		boolean isFound = false;
		for (Device device : generalDB) {
			if (device.getName().equals(name) && device.getPrice() == price) {
				isFound = true;
			}
		}
		return isFound;
	}

	public int findDeviceByNameAndPrice(String name, int price) {
		LOGGER.debug("method \"findDeviceByNameAndPrice\" called");
		int index = -1;
		for (int i = 0; i < generalDB.size(); i++) {
			if (generalDB.get(i).getName().equals(name) && generalDB.get(i).getPrice() == price) {
				index = i;
			}
		}
		return index;
	}
}
