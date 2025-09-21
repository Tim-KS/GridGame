import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<Actor> actors;
  List<Item> items = new ArrayList<>();

  private boolean isNear(Cell cell, List<Cell> usedCells) {
    for (Cell used : usedCells) {
      int columnDifference = Math.abs(cell.col - used.col);
      int rowDifference = Math.abs(cell.row - used.row);
      if (columnDifference <= 2 && rowDifference <= 2) {
        return true;
      }
    }
    return false;
  }

  public Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();

    List<Cell> usedCells = new ArrayList<>();

    for(Class<? extends Actor> actorType:  Arrays.asList(Cat.class, Dog.class, Bird.class)) {
      Cell cell = null;
      int tries = 0;
      do {
        cell = grid.cellAtColRow((int) (Math.random() * 20), (int) (Math.random() * 20)).get();
        tries++;
        if (tries > 1000) break;
      } while(usedCells.contains(cell) || cell.isRock || isNear(cell, usedCells));
      if (tries <= 1000) {
        usedCells.add(cell);
        try {
          actors.add(actorType.getDeclaredConstructor(Cell.class).newInstance(cell));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    for(int i = 0; i < 2; i++) {
      Cell cell = null;
      int tries = 0;
      do {
        cell = grid.cellAtColRow((int) (Math.random() * 20), (int) (Math.random() * 20)).get();
        tries++;
        if (tries > 1000) break;
      } while  (usedCells.contains(cell) || cell.isRock || isNear(cell, usedCells));
      if (tries <= 1000) {
        usedCells.add(cell);
        items.add(new Item(cell));
      }
    }
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
    for(Actor a: actors) {
      a.paint(g);
    }
    for(Item i : items) {
      i.paint(g);
    }
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }
}