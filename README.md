# COMP2000 Assignment 1
Tim Kandan-Smith  
45972583

## New Features
The new features added were:
- Actors spawn in random locations each time the game is opened
- Rocks will spawn in random locations
- Two items will spawn in random locations

## Random actor spawning
The actors will randomly spawn each time the game is opened. Every time an actor is spawned into a cell, the cell is added to a 'usedCells' list and will prevent any actor, item or rock from spawning in the same cell. Additionally, the actors will not spawn near eachother where favourable. An 'isNear()' method will check the area around each actor and will return true if another actor is witin the area.

## Random Rock spawning
Cells will have ~10% chance of becoming rocks when the grid is being generated. Once the cell is marked as a rock. The rocks will become cells that can't be traversed through (not implemented).

## Item spawning
Two items will spawn in random locations on the screen (currently marked by red blobs). These items currently have no functionality.