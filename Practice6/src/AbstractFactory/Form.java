package AbstractFactory;

import AbstractFactory.GUI.Button;
import AbstractFactory.GUI.Select;
import AbstractFactory.GUI.TextField;

public class Form {
    private final TextField textField;
    private final Select select;
    private final Button button;

    public Form(GUIFactory factory) {
        textField = factory.createTextField();
        select = factory.createSelect();
        button = factory.createButton();
    }
}
