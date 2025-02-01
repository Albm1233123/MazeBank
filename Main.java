import Backend.Controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Frontend/pages/loginscene.fxml"));

            AnchorPane root = loader.load();

            LoginController controller = loader.getController();
            controller.setMainWindow(primaryStage);

            Scene scene = new Scene(root, 1080, 720);

            primaryStage.setResizable(false);
            primaryStage.setTitle("MazeBank");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
