package GameOfLife;

/**
 *
 * @author Aaron
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;



public class StartSimActionListener implements ActionListener {

    private MyFrame frame;
	
    private MyModel model;
	
    private RunSim runSim;
	
    public StartSimActionListener (MyFrame frame, MyModel model) {
	this.frame = frame;
	this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	runSim = new RunSim();
	new Thread(runSim).start();
    }
	
    public void stopSimulation() {
        runSim.stopRunning();
	runSim = null;
    }
	
    class RunSim implements Runnable {
		
	private volatile boolean running;

	@Override
	public void run() {
            this.running = true;
            while (running) {
		sleep();
		model.loopGrid();
		repaint();
            }
	}
		
	private void repaint() {
            SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
                    frame.setGenTextField();
                    frame.repaintGridPanel();
		}
            });
	}
		
	private void sleep() {
            try {
		Thread.sleep(model.getGenDelay());
            } catch (InterruptedException e) {
            }
	}
		
	public synchronized void stopRunning() {
            this.running = false;
	}
		
    }

}
