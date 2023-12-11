package renderEngine.View;

import assets.elements.Element;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import assets.elements.vehicles.cars.passengerCars.Volvo240;
import assets.elements.vehicles.cars.trucks.ScaniaL280;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import static java.util.Map.entry;

// This panel represent the animated part of the view with the car images.

public class GameView extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage backgroundImage;
    BufferedImage volvoImage;
    Map<Class<? extends Element>, BufferedImage> imagePaths = Map.ofEntries(
            entry(Volvo240.class, getImage("../pics/Volvo240.jpg")),
            entry(Saab95.class, getImage("../pics/Saab95.jpg")),
            entry(ScaniaL280.class, getImage("../pics/Scania.jpg")
            ));
    ArrayList<Element> elementsOnScreen;
        // TODO: Make this general for all cars

    private BufferedImage getImage(String path){
        try{
            return ImageIO.read(GameView.class.getResourceAsStream(path));
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    // Initializes the panel and reads the images
    public GameView(int x, int y, ArrayList<Element> elementsOnScreen) {
        this.elementsOnScreen = elementsOnScreen;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Element element : elementsOnScreen){
            g.drawImage(imagePaths.get(element.getClass()), (int)element.getPosition()[0], (int)element.getPosition()[1], null); // see javadoc for more info on the parameters
        }
    }
}
