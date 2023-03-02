public abstract class LoginSingleton {
    public void login(String username, String password) {
        if (username == "user1" && password == "qwerty1234")
            System.out.println(String.format("%s logged in", this.getClass()));
        else
            System.out.println(String.format("%s wrong username or email", this.getClass()));
    }
}
