package kursinisjavafx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class KursinisJavaFX extends Application  {
    
  
    @Override
    public void start(Stage stage) throws Exception {
     
       Parent root = FXMLLoader.load(getClass().getResource("/kursinisjavafx/FXMLDocument.fxml")) ;
         
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
       // pradiniaiDuomenys();
        stage.show();
         
        
    }


    public static void main(String[] args) {
        
  
        launch(args);
 
       
        
    }
    
}










