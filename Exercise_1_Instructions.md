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

![Run tests!](run_tests.jpg) _When you see this, remember to run the tests!_

_Tip: for steps **in bold**, there is a recommended shortcut you can use to make it simpler. Refer to the table at the end of this page for IDE specific shortcuts._

1. In order for `Maze` to understand `TileCoordinate` instead of `x` and `y`, we must change how it stores the tiles from
`Tile[][]` to `Map<TileCoordinate, Tile>`. To achieve that, we must first duplicate the data structure in `Maze` and `MazeBuilder`:

  1. Add the new tiles format to `MazeBuilder`:

    ```java
    // In MazeBuilder
    private Tile[][] allTiles = new Tile[40][40];
    private Map<TileCoordinate, Tile> newTiles = new HashMap<TileCoordinate, Tile>();
    ```
   ![Run tests!](run_tests.jpg)

  1. In `MazeBuilder.process`, add processed tiles to `newTiles` as well:

    ```java
    // In MazeBuilder
    void process(String row) throws Exception {
      // .. Code that creates tile and coordinate
        allTiles[height][x] = tile;
        newTiles.put(coordinate, tile);
      // .. More code
    }
    ```
   ![Run tests!](run_tests.jpg)

  1. Add `newTiles` as an argument to the `Maze` constructor:

    ```java
    // In MazeBuilder
    Maze build() {
        // .. Code that builds up tiles1
        return new Maze(width, height, tiles1, newTiles);
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
   ![Run tests!](run_tests.jpg)

1. Now we will transform `tileAt(int x, int y)` to take a `TileCoordinate` instead:

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
   ![Run tests!](run_tests.jpg)

  1. **Extract method** `tileAt(TileCoordinate tileCoordinate)` from the existing `tileAt(int x, int y)` and make it
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

    ![Run tests!](run_tests.jpg)

  1. **Safe delete** `isValid(int x, int y)` method.

    ![Run tests!](run_tests.jpg)

  1. **Inline variable** `tileCoordinate` in `tileAt(int x, int y)` method.

    ```java
    // In Maze
    public Tile tileAt(int x, int y) {
      return tileAt(new TileCoordinate(x, y));
    }
    ```
    ![Run tests!](run_tests.jpg)

  1. **Inline method** `tileAt(int x, int y)`. Tests for `tileAt(int x, int y)` are now testing our new
  `tileAt(TileCoordinate tileCoordinate)`.

    ![Run tests!](run_tests.jpg)

1. Repeat the same approach for `canMove(int x, int y)`:

  1. **Extract variable** `tileCoordinate`:

    ```java
    // In Maze
    public boolean canMove(int x, int y) {
      TileCoordinate tileCoordinate = new TileCoordinate(x, y);
      return tileAt(tileCoordinate).isMovable();
    }
    ```

    ![Run tests!](run_tests.jpg)

  1. **Extract method** `canMove(TileCoordinate tileCoordinate)` and make it `public`:

    ```java
    public boolean canMove(int x, int y) {
      TileCoordinate tileCoordinate = new TileCoordinate(x, y);
      return canMove(tileCoordinate);
    }
    public boolean canMove(TileCoordinate tileCoordinate) {
      return tileAt(tileCoordinate).isMovable();
    }
    ```

    ![Run tests!](run_tests.jpg)

  1. **Inline variable** `tileCoordinate` inside `canMove(int x, int y)`:

    ```java
    public boolean canMove(int x, int y) {
      return canMove(new TileCoordinate(x, y));
    }
    ```

    ![Run tests!](run_tests.jpg)

  1. **Inline method** `canMove(int x, int y)`.

    ![Run tests!](run_tests.jpg)

1. Refactor `getScore`, `hasDotsLeft` and `toString` in `Maze` class to use `newTiles`. References to `tiles[y][x]` will
become `newTiles.get(new TileCoordinate(x, y))`.

    ![Run tests!](run_tests.jpg)

1. Clean `Maze` class:

  1. **Safe delete** unused `tiles` field.

    ![Run tests!](run_tests.jpg)

  1. **Change method signature** on `Maze` constructor to remove unused argument `tiles`.

    ![Run tests!](run_tests.jpg)

  1. **Rename** `newTiles` field to `tiles`.

    ![Run tests!](run_tests.jpg)

1. Clean `MazeBuilder` class:

  1. Remove building of `tiles1` in `build()` method:

    ```java
    // In MazeBuilder
    Maze build() {
      return new Maze(width, height, tiles);
    }
    ```

    ![Run tests!](run_tests.jpg)

  1. Remove `allTiles[height][x] = tile;` line from `MazeBuilder.process` method.

    ![Run tests!](run_tests.jpg)

  1. **Safe delete** unused `allTiles` variable from `MazeBuilder`.

    ![Run tests!](run_tests.jpg)

1. Encapsulate `x` and `y` in `TileCoordinate`:

  1. **Find occurrences** of `x` or `y` outside of `TileCoordinate` class and replace them with the `TileCoordinate`
  instance that's already there:

    1. In `Game.advance`:

      ```java
      public void advance(long timeDeltaInMillis) {
        // ... Lots of code
        Tile pacmanTile = maze.tileAt(tileCoordinate);
        pacmanTile.visit(pacmanTileVisitor);
      }
      ```

      ![Run tests!](run_tests.jpg)

    1. In `Ghost.getNextTile`:

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

      ![Run tests!](run_tests.jpg)

    1. In `Pacman.allowMove`:

      ```java
      private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
      }
      ```

      ![Run tests!](run_tests.jpg)

  1. Make `x` and `y` private:

    ```java
    public class TileCoordinate {
        private final int x;
        private final int y;
        // Code
    }
    ```

    ![Run tests!](run_tests.jpg)

Shortcut Cheatsheet
-------------------

| Refactoring | Eclipse on Windows/Linux | Eclipse on Mac | IntelliJ on Windows/Linux | IntelliJ on Mac |
|-------------|--------------------------|----------------|---------------------------|-----------------|
|Change Method Signature|`Alt+Shift+M`|No shortcut. Use menu.|`Ctrl+F6`               |`Cmd+F6`         |
|Extract Method|`Alt+Shift+M`            |`Cmd+Alt+M`     |`Ctrl+Alt+M`               |`Cmd+Alt+M`      |
|Extract Variable|`Alt+Shift+L`          |`Cmd+Alt+L`     |`Ctrl+Alt+V`               |`Cmd+Alt+V`      |
|Find Occurrences|`Ctrl+Shift+G`         |`Cmd+Shift+G`   |`Alt+F7`                   |`Alt+F7`         |
|Inline Variable/Method|`Alt+Shift+I`    |`Cmd+Alt+I`     |`Ctrl+Alt+N`               |`Cmd+Alt+N`      |
|Rename       |`Alt+Shift+R`             |`Cmd+Alt+R`     |`Shift+F6`                 |`Shift+F6`       |
|Safe Delete  |`Ctrl+1` and "Remove"|`Cmd+1` and "Remove" |`Ctrl+Del`                 |`Cmd+Del`        |

