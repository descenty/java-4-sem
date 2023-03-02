package AbstractFactory;

import AbstractFactory.GUI.Button;
import AbstractFactory.GUI.Select;
import AbstractFactory.GUI.TextField;
import AbstractFactory.Linux.LinuxButton;
import AbstractFactory.Linux.LinuxSelect;
import AbstractFactory.Linux.LinuxTextField;

public class LinuxGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        System.out.println("Creating Button for Linux OS");
        return new LinuxButton();
    }

    @Override
    public TextField createTextField() {
        System.out.println("Creating TextField for Linux OS");
        return new LinuxTextField();
    }

    @Override
    public Select createSelect() {
        System.out.println("Creating Select for Linux OS");
        return new LinuxSelect();
    }
}
