package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void main(String[] args) {
        int s = 4;
        int WIDTH = 11 * s - 2;
        int HEIGHT = 10 * s + 4;
        TERenderer te = new TERenderer();
        te.initialize(WIDTH,HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(world, s);
        te.renderFrame(world);
    }
    private static void addHexagon(TETile[][] world, int s) {
        drawHugeHexagon(world, s, 5 * s - 1, 10 * s + 1);
    }

    private static void drawHexagon(TETile[][] world, TETile item, int s, int px, int py) {
        int flag = 1;
        int cha = 0;
        for (int i = 0; i < 2 * s; i += 1) {
            int y = py - i;
            for (int j = px - cha; j < px + s + cha; j += 1) {
                world[j][y] = item;
            }
            if (flag == 1 && cha == s - 1) {
                flag = -1;
               continue;
            }
            cha += flag;
        }
    }

    private static void drawHugeHexagon(TETile[][] world, int s, int px, int py) {
        int lx = px;
        int rx = px;
        int y = py;
        for (int i = 0; i < 5; i += 1) {
           TETile item = randomTETile();
           drawHexagon(world, item, s, px, y - i * 2 * s);
        }
        lx -= (2 * s - 1);
        rx += (2 * s - 1);
        y -= s;
        for (int i = 0; i < 4; i += 1) {
            TETile item = randomTETile();
            drawHexagon(world, item, s, lx, y - i * 2 * s);
            item = randomTETile();
            drawHexagon(world, item, s, rx, y - i * 2 * s);
        }
        lx -= (2 * s - 1);
        rx += (2 * s - 1);
        y -= s;
        for (int i = 0; i < 3; i += 1) {
            TETile item = randomTETile();
            drawHexagon(world, item, s, lx, y - i * 2 * s);
            item = randomTETile();
            drawHexagon(world, item, s, rx, y - i * 2 * s);
        }
    }

    private static TETile randomTETile() {
       int tetileNum = RANDOM.nextInt(5);
       switch (tetileNum) {
           case 0: return Tileset.WALL;
           case 1: return Tileset.FLOWER;
           case 2: return Tileset.GRASS;
           case 3: return Tileset.TREE;
           case 4: return Tileset.FLOOR;
           default: return Tileset.SAND;
       }
    }
}
