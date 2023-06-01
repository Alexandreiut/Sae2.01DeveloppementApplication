package iut.sae.jeu;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class ControllerSAe {

    @FXML
    private ImageView smileID;

    @FXML
    private HBox bas;

    @FXML
    private GridPane JeuID;

    @FXML
    private Label MinuteurID;

    @FXML
    private Label DrapeauRestantsID;

    @FXML
    private HBox HautID;

    @FXML
    private VBox GaucheId;

    @FXML
    private VBox DroitId;

    @FXML
    private Label DifficultéID;

    @FXML
    void newGame(ActionEvent event) {
    	
    }

    @FXML
    void clickAction(ActionEvent event) {

    }

}