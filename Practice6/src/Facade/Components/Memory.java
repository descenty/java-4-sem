package Facade.Components;

public class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory: load " + data.length + " bytes from " + position);
    }
}
