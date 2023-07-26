package au.com.devnull;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.Stream;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ToyRobot {

    private static final int DEFAULT_LENGTH = 5;
    private static final int DEFAULT_WIDTH = 5;

    private final int maxLength;
    private final int maxWidth;
    private final boolean[][] table;
    private int posX;
    private int posY;
    private Orientation direction;
    private Status status;

    public ToyRobot(int length, int width) {
        this.maxLength = length;
        this.maxWidth = width;
        this.table = new boolean[length + 1][width + 1];
        this.posX = 0;
        this.posY = 0;
        this.direction = null;
        this.status = Status.MISSING;
    }

    public ToyRobot() {
        this(DEFAULT_LENGTH, DEFAULT_WIDTH);
    }

    public void place(int x, int y, Orientation orientation) {
        posX = x;
        posY = y;
        direction = orientation;
        status = Status.MOVING;
    }

    public String report() {
        if (status == Status.MISSING) {
            return "ROBOT MISSING";
        } else {
            return String.format("%d,%d,%s", posX, posY, direction.name());
        }
    }

    public void place(String input) {
        String[] parts = input.split(",");
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        Orientation orientation = Orientation.valueOf(parts[2].trim().toUpperCase());
        place(x, y, orientation);
    }

    public void processCommand(Iterator<String> iterator) {
        while (iterator.hasNext()) {
            Command command = Command.valueOf(iterator.next());
            System.out.println(command);
            OUTER:
            switch (command) {
                case LEFT:
                    if (status == Status.MISSING) {
                        break OUTER;
                    }
                    direction = direction.rotateCounterClockwise();
                    break;
                case RIGHT:
                    if (status == Status.MISSING) {
                        break OUTER;
                    }
                    direction = direction.rotateClockwise();
                    break;
                case PLACE:
                    place(iterator.next().toString());
                    break;
                case MOVE:
                    if (status == Status.MISSING) {
                        break OUTER;
                    }
                    int tmpX = posX;
                    int tmpY = posY;
                    try {
                        switch (direction) {
                            case NORTH:
                                posY++;
                                break;
                            case SOUTH:
                                posY--;
                                break;
                            case WEST:
                                posX--;
                                break;
                            case EAST:
                                posX++;
                                break;
                        }
                        table[posX][posY] = true;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(String.format("Boundary collision %d,%d, %s", posX, posY, direction));

                        posX = tmpX;
                        posY = tmpY;
                    }
                    break;
                case REPORT:
                    System.out.println("Output: " + report());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid command " + command);
            }
        }
    }

    public void processCommand(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter(" ");
        processCommand(scanner);
    }

    public void processCommand(String commands) {
        InputStream inputStream = new ByteArrayInputStream(commands.getBytes());
        processCommand(inputStream);
    }

    public enum Orientation {
        NORTH(0),
        EAST(1),
        SOUTH(2),
        WEST(3);

        private final int value;

        Orientation(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Orientation fromInt(int intValue) {
            return Stream.of(values())
                    .filter(o -> o.getValue() == intValue)
                    .findFirst()
                    .orElse(null);
        }

        public Orientation rotateClockwise() {
            int newOrdinal = (value + 1) % 4;
            return Orientation.values()[newOrdinal];
        }

        public Orientation rotateCounterClockwise() {
            int newOrdinal = (value - 1 + 4) % 4;
            return Orientation.values()[newOrdinal];
        }
    }

    public enum Command {
        PLACE,
        MOVE,
        LEFT,
        RIGHT,
        REPORT
    }

    enum Status {
        MOVING,
        MISSING
    }
}
