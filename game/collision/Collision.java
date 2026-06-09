package game.collision;

import javafx.scene.image.ImageView;
import javafx.geometry.Bounds;

public class Collision {

    public static boolean isColliding(ImageView a, ImageView b) {
        Bounds boundsA = a.getBoundsInParent();
        Bounds boundsB = b.getBoundsInParent();

        // shrink hitbox by 40% on each side to ignore black borders
        double shrink = 0.1;

        double ax = boundsA.getMinX() + boundsA.getWidth() * shrink;
        double ay = boundsA.getMinY() + boundsA.getHeight() * shrink;
        double aw = boundsA.getWidth() * (1 - shrink * 2);
        double ah = boundsA.getHeight() * (1 - shrink * 2);

        double bx = boundsB.getMinX() + boundsB.getWidth() * shrink;
        double by = boundsB.getMinY() + boundsB.getHeight() * shrink;
        double bw = boundsB.getWidth() * (1 - shrink * 2);
        double bh = boundsB.getHeight() * (1 - shrink * 2);

        return ax < bx + bw &&
               ax + aw > bx &&
               ay < by + bh &&
               ay + ah > by;
    }
}
