package GameOfLife;

/**
 *
 * @author Aaron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClearSimActionListener implements ActionListener {

    private MyFrame frame;
	
    private MyModel model;

    public ClearSimActionListener(MyFrame frame, MyModel model) {
	this.frame = frame;
	this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	model.clearGrid();
	frame.setGenTextField();
	frame.repaintGridPanel();
    }
	
}
