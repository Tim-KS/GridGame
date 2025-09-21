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

  private boolean isNear(Cell cell, List<Cell> usedCells) {
    for (Cell used : usedCells) {
      int columnDifference = Math.abs(cell.row - used.col);
      int rowDifference = Math.abs(cell.row - used.row);
      if (columnDifference <= 5 && rowDifference <= 5) {
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
      Cell cell;
      do {
        cell = grid.cellAtColRow((int) (Math.random() * 20) + 1, (int) (Math.random() * 20) + 1).get();
      } while(usedCells.contains(cell) || cell.isRock || isNear(cell, usedCells));
      usedCells.add(cell);

      try {
      actors.add(actorType.getDeclaredConstructor(Cell.class).newInstance(cell));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
    for(Actor a: actors) {
      a.paint(g);
    }
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }
}