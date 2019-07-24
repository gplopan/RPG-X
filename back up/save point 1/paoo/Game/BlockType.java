package paoo.Game;

public enum BlockType {
    GRASS(0), STONE1(1), STONE2(2), STONE3(3), LAVA(4), WOOD(5), WOOD2(6);

    private final int value;
    private  Boolean passable=true;

    private BlockType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static BlockType getTypeFromInt(int value) {
        return BlockType.values()[value];
    }

    public void setPassable(Boolean passable) { this.passable = passable; }

    public Boolean getPassable() { return passable; }
}
