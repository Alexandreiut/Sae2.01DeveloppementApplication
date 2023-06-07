package iut.sae.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.Parent;

/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class LauncheurDemineur extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {

            
            FXMLLoader chargeurFXML = new FXMLLoader();

            chargeurFXML.setLocation(getClass().getResource("VueDemineur.fxml")); 

            Parent racine = chargeurFXML.load();

            Scene scene = new Scene(racine); 

            scene.getStylesheets().add( getClass().getResource( "cssDemineur.css" ).toExternalForm() );
            
            primaryStage.setTitle("Demineur");
            primaryStage.setHeight(1000);
            primaryStage.setWidth(1000);
            primaryStage.setScene(scene);

            primaryStage.show(); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * lance le jeu
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}