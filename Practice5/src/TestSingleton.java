public class TestSingleton {
    public static void main(String[] args) {
        String username = "user1";
        String password = "qwerty1234";
        EagerInitSingleton.getInstance().login(username, password);
        EnumSingleton.INSTANCE.login(username + 't', password);
        LazyInitSingleton.getInstance().login(username, password);
    }
}
