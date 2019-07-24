package paoo.Items;

import paoo.Game.Map;

public class Rock extends Item {
    private final int ROCK_SPEED = 3;
    private int direction;
    private boolean upgrade = false;
    private boolean isEnemy;

    Rock(int x, int y, int direction, boolean Enemy, String root) {
        super(x, y,root);
        this.direction = direction;
        if (direction == 0) {
            loadImage("images/"+filename+"Up.png");
        }
        if (direction == 1) {
            loadImage("images/"+filename+"Right.png");
        }
        if (direction == 2) {
            loadImage("images/"+filename+"Down.png");
        }
        if (direction == 3) {
            loadImage("images/"+filename+"Left.png");
        }
        isEnemy = Enemy;
        getImageDimensions();
    }

    public void move() {
        if (direction == 0) {
            y -= ROCK_SPEED;
        } else if (direction == 1) {
            x += ROCK_SPEED;
        } else if (direction == 2) {
            y += ROCK_SPEED;
        } else if (direction == 3) {
            x -= ROCK_SPEED;
        }
        if (x > Map.BOARD_WIDTH + 100 + width) {
            vis = false;
        }
        if (x < -width - 100) {
            vis = false;
        }
        if (y > Map.BOARD_HEIGHT + height + 100) {
            vis = false;
        }
        if (y < -height - 100) {
            vis = false;
        }
    }

    void upgrade() {
        this.upgrade = true;
    }

    public boolean getUpgrade() {
        return this.upgrade;
    }
}
