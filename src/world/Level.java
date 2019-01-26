package world;

import java.util.ArrayList;
import java.util.Stack;

public class Level {
    Room[][] rooms;
    int width;
    int height;

    Level previous;
    Level next;

    double difficulty;

    public Level(Level previous) {
        difficulty = scaleDifficulty(previous.difficulty);
        width = (int)(previous.width * difficulty);
        height = (int)(previous.height * difficulty);
        rooms = new Room[width][height];
    }

    private void generateRooms() {
        int startx = (int) (Rand.world_rand.nextDouble() * width);
        int starty = (int) (Rand.world_rand.nextDouble() * height);
        ArrayList<Integer> remainingDirections = new ArrayList<>();
        rooms[startx][starty] = new Room(startx, starty);
        int currentx = startx;
        int currenty = starty;
        Stack<IntCoordinate> roomStack = new Stack<>();
        do {
            for(int i = 0; i < 4; i++) {
                remainingDirections.add(i);
            }
            while(remainingDirections.size() != 0) {
                int r = Rand.world_next_int(0, remainingDirections.size());
                if(rooms[currentx][currenty].neighbors[remainingDirections.get(r)] == null) {
                    //TODO
                    switch(remainingDirections.get(r)) {
                        case 0: //EAST
                            if(currentx == width - 1) {
                                remainingDirections.remove(r);
                            } else {
                                rooms[currentx + 1][currenty] = new Room(currentx + 1, currenty);
                                rooms[currentx][currenty].setNeighbor(rooms[currentx + 1][currenty], 0);
                                roomStack.push(new IntCoordinate(currentx, currenty));
                                currentx = currentx + 1;
                            }
                            break;
                        case 1: //NORTH
                            if(currenty == height - 1) {
                                remainingDirections.remove(r);
                            } else {
                                rooms[currentx][currenty + 1] = new Room(currentx, currenty + 1);
                                rooms[currentx][currenty].setNeighbor(rooms[currentx][currenty + 1], 1);
                                roomStack.push(new IntCoordinate(currentx, currenty));
                                currenty = currenty + 1;
                            }
                            break;
                        case 2: //WEST
                            if(currentx == 0) {
                                remainingDirections.remove(r);
                            } else {
                                rooms[currentx - 1][currenty] = new Room(currentx - 1, currenty);
                                rooms[currentx][currenty].setNeighbor(rooms[currentx - 1][currenty], 2);
                                roomStack.push(new IntCoordinate(currentx, currenty));
                                currentx = currentx - 1;
                            }
                            break;
                        case 3: //SOUTH
                    }
                } else {
                    remainingDirections.remove(r);
                }
            }
        } while(currentx != startx && currenty != starty);
    }

    private static double scaleDifficulty(double previousDifficulty) {
        return previousDifficulty * 1.5;
    }
}
