package sadstudent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sadstudent.components.DialogBox;
import sadstudent.ui.UI;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private SadStudent sadStudent;

    private UI ui;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        this.ui = new UI();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // make dialog container width follow the scroll viewport so children can wrap
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(20.0));
        scrollPane.setFitToWidth(true);
        var dukeDialog = DialogBox.getDukeDialog(ui.greet(), dukeImage);
        dialogContainer.getChildren().add(dukeDialog);
    }

    /** Injects the Duke instance */
    public void setDuke(SadStudent d) {
        sadStudent = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sadStudent.getResponse(input);
        // create dialog boxes and bind their label widths to the container
        var userDialog = DialogBox.getUserDialog(input, userImage);
        var dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        userDialog.bindWidth(dialogContainer.widthProperty());
        dukeDialog.bindWidth(dialogContainer.widthProperty());
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);
        userInput.clear();
    }
}
