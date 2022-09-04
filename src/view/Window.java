package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import controller.OpenNewWindowController;

public class Window extends JFrame {
	private OpenNewWindowController openController;

	private JButton processorsButton = new JButton(
			new ImageIcon(getClass().getClassLoader().getResource(("processor1.jpg"))));
	private JButton videocardsButton = new JButton(
			new ImageIcon(getClass().getClassLoader().getResource(("videocard1.jpg"))));
	private JButton motherboardsButton = new JButton(
			new ImageIcon(getClass().getClassLoader().getResource(("motherboard1.jpg"))));
	private JFrame frame;

	public Window() throws IOException {

		frame = new JFrame("Computer store");
		frame.setSize(1500, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());

		JLabel processorsLabel = new JLabel("Процессоры");
		container.add(processorsLabel);

		processorsButton.setBorderPainted(false);
		processorsButton.setFocusPainted(true);
		processorsButton.setContentAreaFilled(false);
		processorsButton.setName("Процессоры");
		container.add(processorsButton);

		JLabel videocardsLabel = new JLabel("Видеокарты");
		container.add(videocardsLabel);

		videocardsButton.setBorderPainted(false);
		videocardsButton.setFocusPainted(true);
		videocardsButton.setContentAreaFilled(false);
		videocardsButton.setName("Видеокарты");
		container.add(videocardsButton);

		JLabel motherboardsLabel = new JLabel("Материнские платы");
		container.add(motherboardsLabel);

		motherboardsButton.setBorderPainted(false);
		motherboardsButton.setFocusPainted(true);
		motherboardsButton.setContentAreaFilled(false);
		motherboardsButton.setName("Материнские платы");
		container.add(motherboardsButton);

		frame.setResizable(false);
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;

	}

	public JButton getProcessorsButton() {
		return processorsButton;
	}

	public void setProcessorsButton(JButton processorsButton) {
		this.processorsButton = processorsButton;
	}

	public JButton getVideocardsButton() {
		return videocardsButton;
	}

	public void setVideocardsButton(JButton videocardsButton) {
		this.videocardsButton = videocardsButton;
	}

	public JButton getMotherboardsButton() {
		return motherboardsButton;
	}

	public void setMotherboardsButton(JButton motherboardsButton) {
		this.motherboardsButton = motherboardsButton;
	}

	public OpenNewWindowController getOpenController() {
		return openController;
	}

	public void setOpenController(OpenNewWindowController openController) {
		this.openController = openController;
		processorsButton.addActionListener(openController);
		videocardsButton.addActionListener(openController);
		motherboardsButton.addActionListener(openController);
	}
}
