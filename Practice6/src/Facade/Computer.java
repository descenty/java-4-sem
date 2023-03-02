package Facade;

import Facade.Components.CPU;
import Facade.Components.HardDrive;
import Facade.Components.Memory;

class Computer {
    private final static long BOOT_ADDRESS = 1L;
    private final static long BOOT_SECTOR = 2L;
    private final static int SECTOR_SIZE = 3;

    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        hardDrive = new HardDrive();
    }

    public void start() {
        cpu.freeze();
        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }

}