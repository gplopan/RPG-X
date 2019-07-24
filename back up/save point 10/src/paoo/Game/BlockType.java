package paoo.Game;

public enum BlockType {
    GRASS(0), STONE1(1), STONE2(2), STONE3(3), LAVA(4), WOOD1(5), STONE6(6),
    WOOD3(7), PATH1(8),PATH2(9),STONE4(10), STONE190(11),PATH190(12), BRIDGE(13);

    private final int value;

    BlockType(int value) {
        this.value = value;
    }

    public static BlockType getTypeFromInt(int value) {
        return BlockType.values()[value];
    }
}
