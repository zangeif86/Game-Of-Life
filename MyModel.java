package GameOfLife;

/**
 *
 * @author Aaron
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;


public class MyModel {
	
    private static final int GRID_WIDTH = 100;
    private static final int CELL_WIDTH = 8;

    private boolean[][] grid;

    private volatile long genCount;
    private volatile long genDelay;

    public MyModel() {
        this.grid = new boolean[GRID_WIDTH][GRID_WIDTH];
        this.genDelay = 2000L;
        clearGrid();
    }
        
    public void clearGrid() {
        this.genCount = 0;
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                resetCells(i, j);
            }
        }
    }

    public void setCells(int i, int j) {
        grid[i][j] = true;
    }

    public void resetCells(int i, int j) {
        grid[i][j] = false;
    }

    public synchronized void loopGrid() {
        this.genCount++;

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                int count = countCells(i, j);
                if (count == 3) grid[i][j] = true;
                if (grid[i][j] && count < 2) grid[i][j] = false;
                if (grid[i][j] && count > 3) grid[i][j] = false;
            }
        }
    }

    private int countCells(int i, int j) {
		
        int count = 0;
        int isubtract = i - 1;
        int iadd = i + 1;
        int jsubtract = j - 1;
        int jadd = j + 1;

        if (isubtract >= 0) {
            if (jsubtract >= 0) {
                if (grid[isubtract][jsubtract])
                count++;
            }
		
            if (grid[isubtract][j])
            count++;

            if (jadd < GRID_WIDTH) {
                if (grid[isubtract][jadd])
                count++;
            }
        }

        if (jsubtract >= 0) {
            if (grid[i][jsubtract])
            count++;
        }

        if (jadd < GRID_WIDTH) {
            if (grid[i][jadd])
            count++;
        }

        if (iadd < GRID_WIDTH) {
            if (jsubtract >= 0) {
                if (grid[iadd][jsubtract])
                count++;
            }

            if (grid[iadd][j])
            count++;

            if (jadd < GRID_WIDTH) {
                if (grid[iadd][jadd])
                count++;
            }
        }

        return count;

    }

    public Dimension getPreferredSize() {
        int x = (GRID_WIDTH * (CELL_WIDTH + 1)) + 1;
        return new Dimension (x, x);
    }

    public int getGridWidth() {
        return GRID_WIDTH;
    }

    public long getGenCount() {
        return genCount;
    }

    public long getGenDelay() {
        return genDelay;
    }

    public synchronized void setGenDelay(long genDelay) {
        this.genDelay = genDelay;
    }
            
    public void draw(Graphics gr) {
        int x = 1;
        for (int i = 0; i < GRID_WIDTH; i++) {
            int y = 1;
            for (int j = 0; j < GRID_WIDTH; j++) {
		drawLines(gr, x, i, y, j);
		drawCells(gr, x, i, y, j);
		y += CELL_WIDTH + 1;
            }
            x += CELL_WIDTH + 1;
        }
    }

    private void drawLines(Graphics gr, int x, int i, int y, int j) {
        gr.setColor(Color.CYAN);
		
        if (i == 0) {
            gr.drawLine(0, y - 1, 0, y + CELL_WIDTH - 1);
        }

        if (j == 0) {
            gr.drawLine(x - 1, 0, x + CELL_WIDTH - 1, 0);
        }

        gr.drawLine(x, y +CELL_WIDTH, x + CELL_WIDTH, y + CELL_WIDTH);
        gr.drawLine(x + CELL_WIDTH, y, x + CELL_WIDTH, y + CELL_WIDTH);
    }

    private void drawCells(Graphics gr, int x, int i, int y, int j) {
        if (grid[i][j]) {
            gr.setColor(Color.DARK_GRAY);
            gr.fillRect(x, y, CELL_WIDTH, CELL_WIDTH);
        }
    }
}
