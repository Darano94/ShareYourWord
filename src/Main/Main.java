package Main;

import Mvc.Controller;
import Mvc.Model;
import Mvc.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View();

        controller.link(model, view);

        Scene sceneStart = new Scene(view, 728, 728 / 16 * 9);

        stage.setScene(sceneStart);
        stage.setTitle("Spread your music");
        stage.show();
    }
}