package ui.pojo;

import ui.utils.Config;

public class User {
    private String login = Config.getProperty("login");
    private String password = Config.getProperty("password");
    private String url = Config.getProperty("url");

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
