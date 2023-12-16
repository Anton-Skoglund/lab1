package renderEngine;

import assets.elements.Element;

import java.util.ArrayList;

public class ElementsToRender extends ArrayList<Element> {
    private static ElementsToRender INSTANCE;

    /**
     * Singleton
     */
    private ElementsToRender() {}
    public static ElementsToRender getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ElementsToRender();
        }
        return INSTANCE;
    }
}
