package GameOfLife;

/**
 *
 * @author Aaron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class RandomInitializationActionListener implements ActionListener {

    private MyFrame frame;
	
    private MyModel model;
	
    private Random random;

    public RandomInitializationActionListener(MyFrame frame, MyModel model) {
	this.frame = frame;
	this.model = model;
	this.random = new Random();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	int size = model.getGridWidth();
	int count = size * size / 5;
	for (int z = 0; z < count; z++) {
            int i = random.nextInt(size);
            int j = random.nextInt(size);
            model.setCells(i, j);
	}
	frame.repaintGridPanel();
    }
		
}
