package Mvc;

public class Model {
    private final String APP_ID = "31xxxxxxxx033";
    private final String APP_SECRET = "";
    private final String stateParam = "st=state123abc,ds=123456789";
    private final String REDIRECT_URL = "https://www.google.de/";
    private String accessToken;
    private String authURL;

    //Getter

    public String getAppId() {
        return this.APP_ID;
    }

    public String getAppSecret() {
        return this.APP_SECRET;
    }

    public String getStateParam() {
        return this.stateParam;
    }

    public String getREDIRECT_URL() {
        return REDIRECT_URL;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAuthURL() {
        return authURL;
    }

    //Setter

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setAuthURL(String authURL) {
        this.authURL = authURL;
    }


}
