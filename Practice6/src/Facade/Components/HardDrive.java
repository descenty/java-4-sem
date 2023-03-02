package Facade.Components;

public class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive: read " + size + " bytes from " + lba);
        return new byte[size];
    }
}
