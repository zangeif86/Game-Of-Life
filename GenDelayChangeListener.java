package GameOfLife;

/**
 *
 * @author Aaron
 */
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class GenDelayChangeListener implements ChangeListener {

    private MyModel model;
	
    public GenDelayChangeListener(MyModel model) {
	this.model = model;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
	JSlider source = (JSlider) e.getSource();
	if (!source.getValueIsAdjusting()) {
            model.setGenDelay(1000L * source.getValue());
	}
    }

}
