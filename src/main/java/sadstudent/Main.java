package sadstudent;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sadstudent.exceptions.SadStudentException;
import sadstudent.ui.UI;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private SadStudent sadStudent;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            sadStudent = new SadStudent();
            fxmlLoader.<MainWindow>getController().setDuke(sadStudent); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(SadStudentException e) {
            new UI().showMessage(e.getMessage());
            System.exit(0);
        }
    }
}
