package AbstractFactory;

import AbstractFactory.GUI.Button;
import AbstractFactory.GUI.Select;
import AbstractFactory.GUI.TextField;
import AbstractFactory.Windows.WindowsButton;
import AbstractFactory.Windows.WindowsSelect;
import AbstractFactory.Windows.WindowsTextField;

public class WindowsGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        System.out.println("Creating Button for Windows OS");
        return new WindowsButton();
    }

    @Override
    public TextField createTextField() {
        System.out.println("Creating TextField for Windows OS");
        return new WindowsTextField();
    }

    @Override
    public Select createSelect() {
        System.out.println("Creating Select for Windows OS");
        return new WindowsSelect();
    }

}
