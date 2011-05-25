package edu.chalmers.platypus.view.splash;

import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.StateChanges;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Handler for updating the splash screen shown at startup, for example
 * showing new text.
 */
public class SplashScreenHandler implements PropertyChangeListener {
    SplashScreen sc;
    Rectangle2D splashTextArea;
    Graphics2D splashGraphics;
    Font font = new Font("SansSerif", Font.BOLD, 14);

    /**
     * Constructor. Specifies which area of the splash screen should be
     * affected when writing on it.
     */
    public SplashScreenHandler() {
        ComBus.subscribe(this);
        sc = SplashScreen.getSplashScreen();

        if (sc != null) {
            Dimension ssDim = sc.getSize();
            int height = ssDim.height;
            int width = ssDim.width;

            splashTextArea = new Rectangle2D.Double(25.0, height*0.82, width * 0.90, 32.0);

            splashGraphics = sc.createGraphics();
            splashGraphics.setFont(font);

            splashText("Starting...");
        }
    }

    /**
     * Writes the specified text on the splash screen image.
     *
     * @param str text to write
     */
    public void splashText(String str) {
        if (sc != null && sc.isVisible()) {

            splashGraphics.setPaint(Color.WHITE);
            splashGraphics.fill(splashTextArea);

            splashGraphics.setPaint(Color.BLACK);
            splashGraphics.drawString(str, (int)(splashTextArea.getX() + 10),(int)(splashTextArea.getY() + 15));

            sc.update();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String change = evt.getPropertyName();

	if (change.equals(StateChanges.FILTER_LOADED.toString())) {
            //A filter was loaded, writes to splash scren
            splashText("Loaded filter: "+ (String)evt.getNewValue());
	}
    }

}
