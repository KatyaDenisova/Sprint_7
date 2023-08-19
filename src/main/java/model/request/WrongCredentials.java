package model.request;

public class WrongCredentials {
    private String login;

    public WrongCredentials(String login) {
        this.login = login;
    }

    public WrongCredentials() {

    }

    public static WrongCredentials from(Courier courier) {
        WrongCredentials c = new WrongCredentials();
        c.setLogin(courier.getLogin());
        return c;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
