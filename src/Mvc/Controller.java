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

        String domain = "https://www.facebook.com/connect/login_success.html";
        String appID = "312285962731033";
        String appSecret = "6fa94a7c147d9ef930b0ae5b0381696f";
        String stateParam = "st=state123abc,ds=123456789";

        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        WebDriver driver = new ChromeDriver();

//        https://www.facebook.com/connect/login_success.html?code=AQDuxAxzpamqjD6qNGCKME3EJE1y7SnxUwMmiPHTDuf5E4uonOwQi1lsZLwXRoNN1UOlSvLDoOOOxSJR6WaTQbK_5R7wc1EaiuZvEvvcG3fJ0AsOq2Wj1OuiDUjb8m5l30Vkn41ouhmXb3Rh7WXsaPfpR_osvl2-0rBh7Q5OcZdNNeYOzFVPljdzPzLZvN_ULUK-9hCed36FfVCc-NM4u15FH-T2qQZZT5rn_JRemu8maBvhTPShy-H1YrcmD9fi0osJYj0DUOm_SRNr2UvgtwDAh-8PeTpLG_l75WSFEy2Z6AdGRCD7GhIt_a518bbDXnV7nKo_gVtUdy79Y8l4n8d34639HC2ylZ9Oq6f_c_Dw0Q&state=st%3Dstate123abc%2Cds%3D123456789#_=_
//        while(true){
//            if(!driver.getCurrentUrl().contains("facebook.com")){
//                model.setAccessToken(driver.getCurrentUrl().replaceAll(".*?code=(.+)&.*", "$1"));
//                driver.quit();
//                FacebookClient client = new DefaultFacebookClient(model.getAccessToken(), Version.VERSION_3_2);
//                User user = client.fetchObject("me", User.class);
//
//                System.out.println(user.getFirstName());
//            }
        FacebookClient client = new DefaultFacebookClient(Version.LATEST);
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        String loginDialogUrlString = client.getLoginDialogUrl(appID, domain, scopeBuilder);

        driver.get(loginDialogUrlString);

        FacebookClient.AccessToken accessToken = client.obtainAppAccessToken(appID, appSecret);
        String token = accessToken.getAccessToken();
        String[] segmentsToken = StringUtils.split(token, "|");


        for (String s : segmentsToken) {
            System.out.println(s);
        }


    }
}


