Exercise 1 - Step by Step
=========================

What's the problem?
-------------------

`TileCoordinate`'s `public int x, y` are exposed and used throughout the code.
* Primitive obsession
* Data clump

Goal
----

Finish the refactoring to use a new `TileCoordinate` class:
* Focus on `Maze` class
* Encapsulate `int x, y` inside the `TileCoordinate` class

Refactoring Strategy
--------------------

1. `Maze` currently accepts the tiles built by `MazeBuilder` as `Tile[][]`. In `MazeBuilder` we duplicated the building
of the tiles into a different structure: `Map<TileCoordinate, Tile>`. We must add the new structure of tiles to the
`Maze` constructor:

  ```java
  // In MazeBuilder
  Maze build() {
      // .. Code that builds up tiles1
      return new Maze(width, height, tiles1, tiles);
  }
  ```

  And let `Maze` store the new structure:

  ```java
  // In Maze
  private final Map<TileCoordinate, Tile> newTiles;

  Maze(int width, int height, Tile[][] tiles, Map<TileCoordinate, Tile> newTiles) {
    // ... Assign width, height and tiles
    this.newTiles = newTiles;
  }
  ```

1. Change method `tileAt(int x, int y)` to use `newTiles` and the `TileCoordinate` class:

  ```java
  // In Maze
  public Tile tileAt(int x, int y) {
    TileCoordinate tileCoordinate = new TileCoordinate(x, y);
    if (newTiles.containsKey(tileCoordinate)) {
      return newTiles.get(tileCoordinate);
    } else {
      return new EmptyTile(tileCoordinate);
    }
  }
  ```

1. **Extract a method** `tileAt(TileCoordinate tileCoordinate)` from the existing `tileAt(int x, int y)` and make it
`public`:

  ```java
  // In Maze
  public Tile tileAt(int x, int y) {
    TileCoordinate tileCoordinate = new TileCoordinate(x, y);
    return tileAt(tileCoordinate);
  }
  public Tile tileAt(TileCoordinate tileCoordinate) {
    // Extracted code is here
  }
  ```
  _Use your IDE: Extract Method is `Alt+Shift+M` in Eclipse, `Alt+Cmd+M` in IntelliJ._

1. **Safe Delete** `isValid(int x, int y)` method.

  _Use your IDE: `Ctrl+1` and "Remove method" in Eclipse, `Cmd+Del` in IntelliJ._

1. **Inline** `tileCoordinate` variable in `tileAt(int x, int y)` method.
  ```java
  // In Maze
  public Tile tileAt(int x, int y) {
    return tileAt(new TileCoordinate(x, y));
  }
  ```
  _Use your IDE: Inline Variable is `Alt+Shift+I` in Eclipse, `Alt+Cmd+N` in IntelliJ._

1. **Inline** `tileAt(int x, int y)` method. Tests for `tileAt(int x, int y)` are now testing our new
`tileAt(TileCoordinate tileCoordinate)`.

  _Use your IDE: Inline Method is `Alt+Shift+I` in Eclipse, `Alt+Cmd+N` in IntelliJ._

1. Repeat the same approach (steps 2-6) for `canMove(int x, int y)`:

  1. Extract `tileCoordinate` variable:
    ```java
    // In Maze
    public boolean canMove(int x, int y) {
      TileCoordinate tileCoordinate = new TileCoordinate(x, y);
      return tileAt(tileCoordinate).isMovable();
    }
    ```

  1. Extract `canMove(TileCoordinate tileCoordinate)` method and make it `public`:
    ```java
    public boolean canMove(int x, int y) {
      TileCoordinate tileCoordinate = new TileCoordinate(x, y);
      return canMove(tileCoordinate);
    }
    public boolean canMove(TileCoordinate tileCoordinate) {
      return tileAt(tileCoordinate).isMovable();
    }
    ```

  1. Inline `tileCoordinate` variable inside `canMove(int x, int y)`:
    ```java
    public boolean canMove(int x, int y) {
      return canMove(new TileCoordinate(x, y));
    }
    ```

  1. Inline `canMove(int x, int y)` method.

1. Refactor `getScore`, `hasDotsLeft` and `toString` in `Maze` class to use `newTiles`. References to `tiles[y][x]` will
become `newTiles.get(new TileCoordinate(x, y))`.

1. Clean `Maze` class:

  1. **Safe Delete** `tiles` field.

  1. Remove unused constructor argument `tiles`.

     _Use your IDE: `Alt+Shift+C` in Eclipse, `Cmd+F6` in IntelliJ to **Change Method Signature**. It will update all
     uses of the constructor for you._

  1. Rename `newTiles` field to `tiles`.

1. Clean `MazeBuilder` class:

  1. Remove building of `tiles1` in `build()` method:

  ```java
  // In MazeBuilder
  Maze build() {
    return new Maze(width, height, tiles);
  }
  ```

  1. Remove `allTiles[height][x] = tile;` line from `process` method.

  1. Remove unused `allTiles` variable.

1. Encapsulate `x` and `y` in `TileCoordinate`:

  1. Find occurrences of `x` or `y` outside of `TileCoordinate` class and replace them with the `TileCoordinate`
  instance that's already there:

     _Use your IDE: `Ctrl+Shift+G` in Eclipse, `Alt+F7` in IntelliJ to **Find Occurrences**._

    * In `Game.advance`:

      ```java
      public void advance(long timeDeltaInMillis) {
        // ... Lots of code
        Tile pacmanTile = maze.tileAt(tileCoordinate);
        Tile pacmanTile = maze.tileAt(tileCoordinate);
        pacmanTile.visit(pacmanTileVisitor);
      }
      ```

    * In `Ghost.getNextTile`:
      ```java
      protected TileCoordinate getNextTile(TileCoordinate currentTile) {
        // ... Lots of code
            TileCoordinate nextTile = currentTile.add(direction.tileDelta());
            if (maze.canMove(nextTile) && !nextTile.equals(previousTile)) {
              availableTiles.add(nextTile);
            }
        // ... And more code
      }
      ```

    * In `Pacman.allowMove`:
      ```java
      private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
      }
      ```

  1. Make `x` and `y` private:
    ```java
    public class TileCoordinate {
        private final int x;
        private final int y;
        // Code
    }
    ```