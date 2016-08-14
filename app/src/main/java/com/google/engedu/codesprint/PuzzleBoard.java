package com.google.engedu.codesprint;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;


public class PuzzleBoard {

    private static final int NUM_TILES = 2;
//     a constant array storing the relative coords of all the neighbors of one tile. This will make
// possible to check neighbors with a for-loop rather than 4 if-statements.
    private static final int[][] NEIGHBOUR_COORDS = {
            { -1, 0 },
            { 1, 0 },
            { 0, -1 },
            { 0, 1 }
    };
    private ArrayList<PuzzleTile> tiles;
    Bitmap scaledBitmap;

//  Constructor. This should handle breaking up the given image into tile-sized chunks.
    PuzzleBoard(Bitmap bitmap, int parentWidth) {
        int shownTileIndex = (int)(Math.random() * (NUM_TILES*NUM_TILES - 1));
        tiles = new ArrayList<>();
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, parentWidth, parentWidth, true);
        int tileWidth = parentWidth / NUM_TILES;
        int tileHeight = scaledBitmap.getHeight() / NUM_TILES;
//        Log.d("puzzleboad", "" + tileWidth + "," + tileHeight);
        int count = 0;
        for(int x = 0; x<NUM_TILES; x++) {
            for(int y = 0; y<NUM_TILES; y++) {
//                if(i == 3 && j == 3) {
//                    tiles.add()
//                }
//                if(count != NUM_TILES*NUM_TILES - 1) {
                if(count == shownTileIndex) {
                    Bitmap tilePic = Bitmap.createBitmap(scaledBitmap, y * tileWidth, x * tileHeight, tileWidth, tileHeight);
                    PuzzleTile tile = new PuzzleTile(tilePic, count);
                    tiles.add(tile);
                }
                else {
//                    Log.d("puzzleboard constructor", "blank tile is index " + XYtoIndex(x,y));
                    tiles.add(null);
                }
                count++;
                Log.d("puzzleboard constructor", "made tile " + count);
            }
        }
    }

    public void showAll() {
        int shownTileIndex = (int)(Math.random() * (NUM_TILES*NUM_TILES - 1));
        tiles = new ArrayList<>();
        int tileWidth = scaledBitmap.getWidth() / NUM_TILES;
        int tileHeight = scaledBitmap.getHeight() / NUM_TILES;
//        Log.d("puzzleboad", "" + tileWidth + "," + tileHeight);
        int count = 0;
        for(int x = 0; x<NUM_TILES; x++) {
            for(int y = 0; y<NUM_TILES; y++) {
                Bitmap tilePic = Bitmap.createBitmap(scaledBitmap, y * tileWidth, x * tileHeight, tileWidth, tileHeight);
                PuzzleTile tile = new PuzzleTile(tilePic, count);
                tiles.add(tile);
                count++;
                Log.d("puzzleboard constructor", "made tile " + count);
            }
        }
    }

//    Copy constructor. This constructor creates a puzzle board that copies the state of another
//      puzzle board. This will be handy when implementing neighbors below.
    PuzzleBoard(PuzzleBoard otherBoard) {
        tiles = (ArrayList<PuzzleTile>) otherBoard.tiles.clone();
    }

//    Part of the solver functionality
    public void reset() {
        // Nothing for now but you may have things to reset once you implement the solver.
    }

//    Tests whether two boards are equal by checking whether their tile configuration is the same.
    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        return tiles.equals(((PuzzleBoard) o).tiles);
    }

//    Called by PuzzleBoardView's onDraw. Makes each tile draw itself.
    public void draw(Canvas canvas) {
        Log.d("draw", "draw called");
        if (tiles == null) {
            return;
        }
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                tile.draw(canvas, i % NUM_TILES, i / NUM_TILES);
                Log.d("draw", "drew tile " + i);
            }
        }
    }

//    Called by PuzzleBoardView's onTouchEvent. Determines which tile was clicked and attempts to move it.
    public boolean click(float x, float y) {
        for (int i = 0; i < NUM_TILES * NUM_TILES; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile != null) {
                if (tile.isClicked(x, y, i % NUM_TILES, i / NUM_TILES)) {
                    return tryMoving(i % NUM_TILES, i / NUM_TILES);
                }
            }
        }
        return false;
    }

//    helper for click.
    private boolean tryMoving(int tileX, int tileY) {
        for (int[] delta : NEIGHBOUR_COORDS) {
            int nullX = tileX + delta[0];
            int nullY = tileY + delta[1];
            if (nullX >= 0 && nullX < NUM_TILES && nullY >= 0 && nullY < NUM_TILES &&
                    tiles.get(XYtoIndex(nullX, nullY)) == null) {
                swapTiles(XYtoIndex(nullX, nullY), XYtoIndex(tileX, tileY));
                return true;
            }

        }
        return false;
    }

//    Checks whether the current board is solved. This is the reason why we store indexes in each tile.
    public boolean resolved() {
        for (int i = 0; i < NUM_TILES * NUM_TILES - 1; i++) {
            PuzzleTile tile = tiles.get(i);
            if (tile == null || tile.getNumber() != i)
                return false;
        }
        return true;
    }

//    helper method to convert between two-dimensional coordinates and positions in the ArrayList.
    private int XYtoIndex(int x, int y) {
        return x + y * NUM_TILES;
    }

//    private helper. Does what it says.
    protected void swapTiles(int i, int j) {
        PuzzleTile temp = tiles.get(i);
        tiles.set(i, tiles.get(j));
        tiles.set(j, temp);
    }
// will create a list of all board configurations that can be reached by moving a single tile in the current board.
    public ArrayList<PuzzleBoard> neighbours() {
//        Log.d("neighbours", "blank found at " + locateBlank());
        int blankIndex = locateBlank();
        ArrayList<PuzzleBoard> altBoards = new ArrayList<>();
        for(int i = 0; i < 4; i++) {

        }
        return null;
    }

    private int locateBlank() {
        for(int i = 0; i < tiles.size(); i ++) {
            if(tiles.get(i) == null)
                return i;
        }
        return -1;
    }

//computes the priority of the current board. This will be explained as part of the instructions for the solver.
    public int priority() {
        return 0;
    }

}
