
package kursinisjavafx;

import data.Keliautojas;
import data.Kelione;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FXMLPridetiLangasController implements Initializable {
    FXMLDocumentController controller = new FXMLDocumentController();
    ObservableList <Kelione> kelList;
     Configuration con = new Configuration();
    SessionFactory sf ;
    Session session ;
    Transaction tx;
    @FXML
    private javafx.scene.control.Button cancelBtn, okBtn;
    @FXML
    private javafx.scene.control.TextField kelionesId, salis;

    @FXML
    public void exitWindow() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();

    }

    public void passList(ObservableList<Kelione> kelioniuListas) {

        kelList = kelioniuListas;

    }

    @FXML
    public void okays() {
        Alert alert = new Alert(Alert.AlertType.ERROR,"Kelione su tokiu ID egzistuoja");
        Alert alertt = new Alert(Alert.AlertType.WARNING,"Tekstiniai laukeliai negali buti tusti");
        if (kelionesId.getText().equals("") || salis.getText().equals("")){
            alertt.show();
            return;
        }
        int checkForId = 0;
        for (Kelione kel : kelList){
             if (kel.getKelionesKodas().equals(kelionesId.getText())){
             checkForId ++ ;
             }
        }   
     
        if (checkForId == 0 ){    
        kelList.add(new Kelione(kelionesId.getText(), salis.getText(), 0));
        add2db(new Kelione(kelionesId.getText(), salis.getText(), 0));
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
        }
        else {
            alert.show();
        }

    }
 
    public void add2db(Kelione kelione){
        con.configure().addAnnotatedClass(data.Kelione.class);
        sf=con.buildSessionFactory();
        session=sf.openSession();
        tx = session.beginTransaction();
        session.save(kelione);
       
        tx.commit();
        session.close();
    }


    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
