package engine;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class fps {

    public static int getRefreshRate() {
        GraphicsDevice device =
                GraphicsEnvironment.getLocalGraphicsEnvironment()
                        .getDefaultScreenDevice();

        int rate = device.getDisplayMode().getRefreshRate();
        return rate > 0 ? rate : 60;
    }
}
