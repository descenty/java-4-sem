import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class TestStream {
  public static void main(String[] args) {
    List<Human> humansList = List.of(
        new Human("Кирилл", "Давыдов", LocalDate.of(1999, 3, 5), 65),
        new Human("Александр", "Выборнов", LocalDate.of(2001, 4, 7), 72),
        new Human("Дарья", "Казакова", LocalDate.of(1998, 5, 9), 60),
        new Human("Андрей", "Смирнов", LocalDate.of(2000, 6, 2), 90),
        new Human("Михаил", "Маров", LocalDate.of(2002, 6, 11), 85));

    Consumer<Stream<Human>> printHumans = (humans) -> {
      humans.forEach(human -> System.out.println(human.toString()));
      System.out.println();
    };

    System.out.println("Сортировка по второй букве имени:");
    printHumans.accept(
        humansList.stream()
            .sorted((human1, human2) -> human1.getFirstName().charAt(1) - human2.getFirstName().charAt(1)));

    System.out.println("Фильтрация по весу кратно 10:");
    printHumans.accept(humansList.stream().filter(human -> human.getWeight() % 10 == 0));

    System.out.println("Сортировка по произведению веса на возраст:");
    printHumans.accept(humansList.stream()
        .sorted((human1, human2) -> human1.getWeight() * human1.getAge() - human2.getWeight() * human2.getAge()));

    System.out.print("Произведение всех весов: ");
    System.out.println(humansList.stream().map((human) -> Long.valueOf(human.getWeight()))
        .reduce((weight1, weight2) -> weight1 * weight2).get());
  }
}
