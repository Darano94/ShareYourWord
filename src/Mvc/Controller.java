package Mvc;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Controller {
    private Model model;
    private WebDriver driver;
    private FacebookClient client;

    public void link(Model model, View view) {
        this.model = model;
        view.addController(this);
    }

    protected void btnLoginClick() {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        driver = new ChromeDriver();
        client = new DefaultFacebookClient(Version.LATEST);

        //scope - permission
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);

        //get facebook login dialog url
        String loginDialogUrlString = client.getLoginDialogUrl(model.getAppId(), model.getREDIRECT_URL(), scopeBuilder);


        //start webdriver with login dialog url
        driver.get(loginDialogUrlString);

        //get user-code for getting Accesstoken
        String userCode = getUsercodeForAccessToken(driver);

        //get user-AccessToken with user-code and save in Model
        FacebookClient.AccessToken usrAccessToken = client.obtainUserAccessToken(model.getAppId(), model.getAppSecret(), model.getREDIRECT_URL(), userCode);
        System.out.println("Token: " + usrAccessToken.getAccessToken());
        model.setAccessToken(usrAccessToken.getAccessToken());


        //Test if everything is working with printing user
        client = new DefaultFacebookClient(model.getAccessToken(), Version.VERSION_3_2);
        User user = client.fetchObject("me", User.class);
        System.out.println(user);
    }


    //get Usercode for getting the access-tokn
    private String getUsercodeForAccessToken(WebDriver driver) {
        String result;

        while (true) {
            if (!driver.getCurrentUrl().contains("facebook.com")) {

                String url = driver.getCurrentUrl();

                result = url.substring(url.indexOf("=") + 1, url.indexOf("#"));

                System.out.println("Result: " + result);

                driver.close();
                break;

            }
        }
        return result;
    }
}


