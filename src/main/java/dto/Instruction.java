package dto;

public class Instruction {
    private final Type type;
    private final Position position;

    public Instruction(Type type, Position position) {
        this.type = type;
        this.position = position;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public enum Type{
        /**
         * Sets the initial Z position, can be skipped after multiple executions
         */
        SETUP,


        /**
         * Sets the speed
         */
        INIT,

        /**
         * Movement type of command
         */
        MOVE
    }
}
