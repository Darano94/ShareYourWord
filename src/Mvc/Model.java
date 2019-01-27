package Mvc;

public class Model {
    private final static String APP_ID = "312285962731033";
    private String accessToken;
    private String authURL;

    public String getAppID() {
        return APP_ID;
    }

    public String getAuthURL() {
        return authURL;
    }

    public void setAuthURL(String authURL) {
        this.authURL = authURL;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
