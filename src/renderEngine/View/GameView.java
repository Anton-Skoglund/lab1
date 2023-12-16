package renderEngine.View;

import assets.elements.Element;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import assets.elements.vehicles.cars.passengerCars.Volvo240;
import assets.elements.vehicles.cars.trucks.ScaniaL280;
import org.junit.experimental.max.MaxHistory;
import renderEngine.Application;
import renderEngine.ElementsToRender;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import static java.util.Map.entry;

// This panel represent the animated part of the view with the car images.

public class GameView extends JPanel{
    private static ElementsToRender elementsToRender = ElementsToRender.getInstance();

    boolean background = true;
    BufferedImage backgroundImage = getImage("../pics/background.jpg");

    Map<Class<? extends Element>, BufferedImage> imagePaths = Map.ofEntries(
            entry(Volvo240.class, getImage("../pics/volvo.png")),
            entry(Saab95.class, getImage("../pics/saab.png")),
            entry(ScaniaL280.class, getImage("../pics/TRUCK.png")
            ));


    private BufferedImage getImage(String path){
        try{
            return ImageIO.read(GameView.class.getResourceAsStream(path));
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
    // Initializes the panel and reads the images
    public GameView(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background){
            g.drawImage(backgroundImage, 0, 0, null);
        }

        for (Element element : elementsToRender){
            double elementRot = element.getRotation();
            double locationX = (double) imagePaths.get(element.getClass()).getWidth() / 2;
            double locationY = (double) imagePaths.get(element.getClass()).getHeight() / 2;
            AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90 - elementRot), locationX, locationY);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            // Drawing the rotated image at the required drawing locations
            g.drawImage(op.filter(imagePaths.get(element.getClass()), null), (int)element.getPosition()[0], (int)element.getPosition()[1], null);


            //g.drawImage(imagePaths.get(element.getClass()), (int)element.getPosition()[0], (int)element.getPosition()[1], null); // see javadoc for more info on the parameters
        }
    }
}
