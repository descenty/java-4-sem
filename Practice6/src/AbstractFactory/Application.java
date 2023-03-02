package AbstractFactory;

import java.util.Scanner;

public class Application {
    public static GUIFactory getFactory(String os) {
        return switch (os) {
            case "Windows" -> new WindowsGUIFactory();
            case "Linux" -> new LinuxGUIFactory();
            default -> throw new IllegalArgumentException("Unknown OS");
        };
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter OS: ");
            final String os = scanner.nextLine();
            new Form(getFactory(os));
        }
    }
}
