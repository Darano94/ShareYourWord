package Mvc;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class View extends BorderPane {
    private Controller controller;
    private Button btnLogin;

    public void addController(Controller controller) {
        this.controller = controller;
    }
    public View(){
        btnLogin = new Button("Login");
        btnLogin.setOnAction(e -> controller.btnLoginClick());

        this.setCenter(btnLogin);
    }
}
