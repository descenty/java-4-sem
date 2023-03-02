package FactoryMethod;

import FactoryMethod.Animals.Animal;
import FactoryMethod.Animals.Cat;
import FactoryMethod.Animals.Dog;

public interface AnimalFactory {
    Animal createAnimal();
}

class CatFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}

class DogFactory implements AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}