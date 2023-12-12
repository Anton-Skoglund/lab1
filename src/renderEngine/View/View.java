package renderEngine.View;

import renderEngine.Controller.Controller;
import renderEngine.Model.ModelObserver;

import javax.swing.*;
import java.awt.*;


public class View extends JFrame implements ModelObserver {
    public static final int width = 800;
    public static final int height = 800;
    public static final int controllerHeight = 200;

    private GameView gameView;
    private Controller controller = new Controller();

    public View(String frameName){
        this.gameView = new GameView(width, height - controllerHeight);
        setupView(frameName);
        this.add(gameView);

        controller.addControlPanelToFrame(this);
    }

    /**
     * update function for ModelObserver
     */
    @Override
    public void update() {
        this.gameView.repaint();
    }

    private void setupView(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setUpFrame();
    }

    private void setUpFrame() {
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}