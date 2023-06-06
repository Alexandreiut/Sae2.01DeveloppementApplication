package iut.sae.jeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.Parent;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/** TODO comment class responsibility (SRP)
 * @author tany.catalabailly
 *
 */
public class Test extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            MenuBar barreDeMenu = new MenuBar();
            /* Création des 3 options de la barre de menu */
            Menu menuFichier = new Menu("Fichier");
            Menu menuEdition = new Menu("Edition");
            Menu menuRecherche = new Menu("Recherche");
            // ajout des 3 options à la barre de menu
            barreDeMenu.getMenus().setAll(menuFichier, menuEdition, menuRecherche);
            // création des 3 options du menu "Fichier"
            MenuItem menuItemNouveau = new MenuItem("Nouveau");
            MenuItem menuItemOuvrir = new MenuItem("Ouvrir");
            MenuItem menuItemEnregistrer = new MenuItem("Enregistrer");
            FXMLLoader chargeurFXML = new FXMLLoader();
            menuFichier.getItems().setAll(menuItemNouveau, menuItemOuvrir, menuItemEnregistrer);
            chargeurFXML.setLocation(getClass().getResource("test.fxml")); 

            Parent racine = chargeurFXML.load();



            Scene scene = new Scene(racine); 

            primaryStage.setTitle("Chaine de caractere");
            primaryStage.setHeight(400);
            primaryStage.setWidth(600);
            primaryStage.setScene(scene);

            primaryStage.show(); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** TODO comment method role
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}        

