package GameOfLife;

/**
 *
 * @author Aaron
 */
import java.awt.Graphics;

import javax.swing.JPanel;


public class GridPanel extends JPanel {

    private static final long serialVersionUID = 
        -73467988098237989L;
	
    private MyModel model;

    public GridPanel(MyModel model) {
	this.model = model;
	this.setPreferredSize(model.getPreferredSize());
    }
	
    @Override
    protected void paintComponent(Graphics gr) {
	super.paintComponent(gr);
	model.draw(gr);
    }
}
