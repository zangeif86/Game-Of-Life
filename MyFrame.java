package GameOfLife;

/**
 *
 * @author Aaron
 */
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame {

    private ControlPanel controls;

    private MyModel model;

    private GridPanel gridPanel;

    private JFrame frame;

    public MyFrame(MyModel model) {
	this.model = model;
	createControls();
    }

    private void createControls() {
	controls = new ControlPanel(this, model);
	gridPanel = new GridPanel(model);

	frame = new JFrame();
	frame.setTitle("The Game of Life");
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
	});

	JPanel main = new JPanel();
	main.setLayout(new FlowLayout());
	main.add(gridPanel);
	main.add(controls.getPanel());

	frame.setLayout(new FlowLayout());
	frame.add(main);
	frame.setSize(700, 500);
	frame.pack();
	frame.setLocationByPlatform(true);
	frame.setVisible(true);
    }

    public void exit() {
	frame.dispose();
	System.exit(0);
    }

    public void setGenTextField() {
	controls.setGenTextField(model.getGenCount());
    }

    public void repaintGridPanel() {
	gridPanel.repaint();
    }
}
