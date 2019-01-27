package Mvc;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import org.apache.commons.exec.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Controller {
    private Model model;
    private String accessToken;

    public void link(Model model, View view) {
        this.model = model;
        view.addController(this);
    }

    public void btnLoginClick() {

        String domain = "https://www.google.de/";
        String appID = "312285962731033";
        String appSecret = "6fa94a7c147d9ef930b0ae5b0381696f";
        String stateParam = "st=state123abc,ds=123456789";

        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

        FacebookClient client = new DefaultFacebookClient(Version.LATEST);

        //scope - permission
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);

        //get facebook login dialog url
        String loginDialogUrlString = client.getLoginDialogUrl(appID, domain, scopeBuilder);


        //start webdriver with login dialog url
        driver.get(loginDialogUrlString);

//        FacebookClient.AccessToken accessToken = client.obtainAppAccessToken(appID, appSecret);
//        String token = accessToken.getAccessToken();
//        String[] segmentsToken = StringUtils.split(token, "|");
//
//
//        for (String s : segmentsToken) {
//            System.out.println(s);
//        }

//        System.out.println(segmentsToken[1]);
//        client = new DefaultFacebookClient(segmentsToken[1], Version.VERSION_3_2);
//        User me = client.fetchObject("me", User.class);
//        System.out.println(me.getEmail());
        String result = null;
        while (true) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {

                String sdsd = driver.getCurrentUrl();

                result = sdsd.substring(sdsd.indexOf("=") + 1, sdsd.indexOf("#"));

                System.out.println("Result: "+ result);

                driver.close();
                break;


            }

        }

        FacebookClient.AccessToken at = client.obtainUserAccessToken(appID, appSecret, domain, result);
        System.out.println("Token: " + at.getAccessToken());

        model.setAccessToken(at.getAccessToken());
        client = new DefaultFacebookClient(model.getAccessToken(), Version.VERSION_3_2);
        User user = client.fetchObject("me", User.class);

        System.out.println(user);
    }
}


