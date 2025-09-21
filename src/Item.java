import java.awt.Graphics;

public class Item {
    Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
    }

    public void paint(Graphics g) {
        g.setColor(java.awt.Color.RED);
        g.fillOval(cell.x + 10, cell.y + 10, 15, 15);
    }
}
