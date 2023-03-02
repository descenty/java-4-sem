package AbstractFactory;

import AbstractFactory.GUI.Button;
import AbstractFactory.GUI.Select;
import AbstractFactory.GUI.TextField;

public interface GUIFactory {
    Button createButton();
    TextField createTextField();
    Select createSelect();
}
