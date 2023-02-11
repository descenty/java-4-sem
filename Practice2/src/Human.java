import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;
import java.util.function.Supplier;

public class Human {
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public Human(String firstName, String lastName, LocalDate birthDate, int weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Human [age=" + getAge() + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
                + birthDate
                + ", weight=" + weight + "]";
    }

}
