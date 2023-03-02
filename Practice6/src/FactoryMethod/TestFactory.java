package FactoryMethod;

import java.util.stream.Stream;

import FactoryMethod.Animals.Animal;

public class TestFactory {
    public static void main(String[] args) {
        Stream.of(new CatFactory(), new DogFactory()).map(AnimalFactory::createAnimal)
                .forEach((animal) -> animal.makeSound());
    }
}
