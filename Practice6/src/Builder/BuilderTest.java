package Builder;

public class BuilderTest {
    public static void main(String[] args) {
        Customer customer = new Customer.Builder()
                .setFirstName("John")
                .setLastName("Doe")
                .setAge(30)
                .setEmail("john.doe@example.com")
                .build();
        System.out.println(customer);
    }
}
