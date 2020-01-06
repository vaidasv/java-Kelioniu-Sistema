package kursinisjavafx;

import data.Keliautojas;
import data.Kelione;
import data.Vartotojas;
import java.io.IOException;
import java.net.URL;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.hibernate.SQLQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FXMLDocumentController implements Initializable {


    private String radioBtnText = "Keliautojas";
    
   
    @FXML
    private TableView<Kelione> kelioniuTablee;
    @FXML
    private TableView<Keliautojas> keliautojuTablee;
    
    @FXML
    TableColumn<Keliautojas, String> idd;
    @FXML
    TableColumn<Keliautojas, String> tipass;
    @FXML
    TableColumn<Keliautojas, String> vardass;
    @FXML
    TableColumn<Keliautojas, String> pavardee;
    @FXML
    TableColumn<Keliautojas, String> priskirtaKelionee;
   
    @FXML
    private TableColumn<Kelione, String> kelionesKodass;
    @FXML
    private TableColumn<Kelione, String> saliss;
    @FXML
    private TableColumn<Kelione, Integer> keliautojuKiekiss;
    
    
    
    
    @FXML
    private TableView<Kelione> kelioniuTable;
    @FXML
    private TableView<Keliautojas> keliautojuTable;
    @FXML
    private TableView<Vartotojas> vartotojuTable;
    @FXML
    private TableColumn <Vartotojas, Integer> vartotojoId;
    @FXML
    private TableColumn <Vartotojas, String> vartotojoTipas;
    @FXML
    private TableColumn<Kelione, String> kelionesKodas;
    @FXML
    private TableColumn<Kelione, String> salis = new TableColumn<>("salis");
    @FXML
    private TableColumn<Kelione, Integer> keliautojuKiekis;
    @FXML
    TableColumn<Keliautojas, String> id;
    @FXML
    TableColumn<Keliautojas, String> tipas;
    @FXML
    TableColumn<Keliautojas, String> vardas;
    @FXML
    TableColumn<Keliautojas, String> pavarde;
    @FXML
    TableColumn<Keliautojas, String> priskirtaKelione;
    @FXML
    private TextField keliones_id_priskirti_txt;
    

    @FXML
    private TextField vardas_txt;
    @FXML
    private TextField pavarde_txt;
    @FXML
    private RadioButton keliautojasBtn;
    @FXML
    private RadioButton imoneBtn;
    @FXML
    ToggleGroup toggleGroup = new ToggleGroup();

    Keliautojas keliautojas = new Keliautojas();
    Kelione kelione = new Kelione();

    Configuration con = new Configuration();
    Configuration con2 = new Configuration();

    SessionFactory sf;
    Session session;
    Transaction tx;

    ObservableList<Kelione> kelioniuList = FXCollections.observableArrayList();
    ObservableList<Vartotojas> vartotojuList = FXCollections.observableArrayList();
    ObservableList<Keliautojas> keliautojuList = FXCollections.observableArrayList();

    public void atnaujintiSaliDb(String naujaSalis, String id) throws SQLException {
       
        session = sf.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Kelione kel = (Kelione) session.get(Kelione.class, id);
        kel.setSalis(naujaSalis);
        session.update(kel);
        tx.commit();
        session.close();

    }
    
    
    
      public ObservableList<Vartotojas> pridetiVartotojusIsDbIList() {
        session = sf.openSession();

        List<Vartotojas> vList = session.createCriteria(Vartotojas.class).list();
        for (Vartotojas vart : vList) {
            vartotojuList.add(vart);
        }
        session.close();
        return vartotojuList;
    }

    public ObservableList<Kelione> pridetiKelionesIsDbIList() {
        session = sf.openSession();

        List<Kelione> kList = session.createCriteria(Kelione.class).list();
        for (Kelione kel : kList) {
            kelioniuList.add(kel);
        }
        session.close();
        return kelioniuList;
    }

    public ObservableList<Keliautojas> pridetiKeliautojusIsDbIList() {
        session = sf.openSession();

        List<Keliautojas> kList = session.createCriteria(Keliautojas.class).list();
        for (Keliautojas kel : kList) {
            keliautojuList.add(kel);
        }
        session.close();
        return keliautojuList;
    }

    public void atnaujintiPavardeDb(String naujaSalis, int id) throws SQLException {
        // sf = con.buildSessionFactory();
        session = sf.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Keliautojas kel = (Keliautojas) session.get(Keliautojas.class, id);
        kel.setPavarde(naujaSalis);
        session.update(kel);
        tx.commit();
        session.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con.configure().addAnnotatedClass(data.Kelione.class);
        con.configure().addAnnotatedClass(data.Vartotojas.class);
        con.configure().addAnnotatedClass(data.Keliautojas.class);
        
        sf = con.buildSessionFactory();
        pridetiKelionesIsDbIList();
        pridetiVartotojusIsDbIList();
        pridetiKeliautojusIsDbIList();
        
        
         vartotojoId.setCellValueFactory(new PropertyValueFactory<Vartotojas, Integer>("Id"));
         vartotojoTipas.setCellValueFactory(new PropertyValueFactory<Vartotojas, String>("Tipas"));  
         vartotojuTable.setItems(vartotojuList);
       
        kelionesKodas.setCellValueFactory(new PropertyValueFactory<Kelione, String>("kelionesKodas"));
        salis.setCellValueFactory(new PropertyValueFactory<Kelione, String>("salis"));
        keliautojuKiekis.setCellValueFactory(new PropertyValueFactory<Kelione, Integer>("keliautojuKiekis"));
        salis.setCellFactory(TextFieldTableCell.<Kelione>forTableColumn());

        salis.setOnEditCommit( //redaguoti sali
                (CellEditEvent<Kelione, String> t) -> {

                    ((Kelione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setSalis(t.getNewValue());

                    String idKeliones = ((Kelione) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).getKelionesKodas();

                    String naujaSalis = t.getNewValue(); //tam, kad perkelti i duombaze
                    System.out.println(idKeliones);

                    try {
                        atnaujintiSaliDb(naujaSalis, idKeliones);

                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

        kelioniuTable.setEditable(true);
     
        kelioniuTable.setItems(kelioniuList);
        pavarde.setOnEditCommit( //pavarde
                (CellEditEvent<Keliautojas, String> t) -> {

                    ((Keliautojas) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).setPavarde(t.getNewValue());

                    int idKeliautojo = ((Keliautojas) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())).getId();

                    String naujaPavarde = t.getNewValue(); //tam, kad perkelti i duombaze
                    System.out.println(idKeliautojo);

                    try {
                        atnaujintiPavardeDb(naujaPavarde, idKeliautojo);

                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tipas.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("tipas"));
        vardas.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("vardas"));
        pavarde.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("pavarde"));
        priskirtaKelione.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("priskirtaKelione"));
        pavarde.setCellFactory(TextFieldTableCell.<Keliautojas>forTableColumn());
        keliautojuTable.setEditable(true);
        keliautojuTable.setItems(keliautojuList);

        keliautojasBtn.setToggleGroup(toggleGroup);
        keliautojasBtn.setSelected(true);
        imoneBtn.setToggleGroup(toggleGroup);

        kelioniuList.addListener(new InvalidationListener() {
            public void invalidated(Observable observable) {
                System.out.println("Kazkas pasikeite Kelioniu Sarase");
                
                

            }
        });
        
        
        
        
        kelionesKodass.setCellValueFactory(new PropertyValueFactory<Kelione, String>("kelionesKodas"));
        saliss.setCellValueFactory(new PropertyValueFactory<Kelione, String>("salis"));
        keliautojuKiekiss.setCellValueFactory(new PropertyValueFactory<Kelione, Integer>("keliautojuKiekis"));
       
        
        idd.setCellValueFactory(new PropertyValueFactory<>("id"));
        tipass.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("tipas"));
        vardass.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("vardas"));
        pavardee.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("pavarde"));
        priskirtaKelionee.setCellValueFactory(new PropertyValueFactory<Keliautojas, String>("priskirtaKelione"));
        pavardee.setCellFactory(TextFieldTableCell.<Keliautojas>forTableColumn());


        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                    Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) toggleGroup.getSelectedToggle();

                radioBtnText = rb.getText();
               
            }

        });

    }
@FXML 
public void gautiInfo(){
    Vartotojas pasirinktasVartotojas = vartotojuTable.getSelectionModel().getSelectedItem();

    int vartotojoId = pasirinktasVartotojas.getId() - 1;
    System.out.println(vartotojoId);
    Keliautojas kel = keliautojuList.get(vartotojoId);
     int kelionKodas = Integer.parseInt(kel.getPriskirtaKelione());
     
    Kelione kelion = kelioniuList.get(kelionKodas);
    keliautojuTablee.getItems().removeAll();
    kelioniuTablee.getItems().removeAll();
    
    
    
    ObservableList<Keliautojas> klist = FXCollections.observableArrayList();
    ObservableList<Kelione> kelionlist = FXCollections.observableArrayList();
    klist.add(kel);
    kelionlist.add(kelion);
    kelioniuTablee.setItems(kelionlist);
    keliautojuTablee.setItems(klist);
    
}
 

    public int generuotiId() {

        int rand_int = 1;
        for (Keliautojas kel : keliautojuList) {

            if (rand_int == kel.getId()) {
                rand_int++;

            } else {
                break;
            }

        }

        return rand_int;
    }

    @FXML
    public void pridetiNaujaKeliautoja() {
        Alert alert = new Alert(AlertType.WARNING);
        if (vardas_txt.getText().equals("") || pavarde_txt.getText().equals("")){
            alert.setTitle("Klaida");
            alert.setContentText("Tekstiniai laukeliai negali buti tusti.");
            alert.show();
            return;
        }
        else{
        int naujasId = generuotiId();
        System.out.println(naujasId);
        vartotojuList.add(new Vartotojas (naujasId,radioBtnText));
    
        
        
        keliautojuList.add(new Keliautojas(kelioniuList.get(0),naujasId, radioBtnText, vardas_txt.getText(), pavarde_txt.getText(), "0"));
        pridetiIDBkeliautoja(new Keliautojas(kelioniuList.get(0),naujasId, radioBtnText, vardas_txt.getText(), pavarde_txt.getText(), "0"));
       
       
        
        padidintiKeliautojuKiekiKelioneje("0");
        isvalytiKeliautojoTxtLaukus();
        vartotojuTable.refresh();
        kelioniuTable.refresh();
     
        }
    }

    public void pridetiIDBkeliautoja(Keliautojas keliautojas) {

        session = sf.openSession();
        tx = session.beginTransaction();
        session.save(keliautojas);
        tx.commit();
    }

    public void isvalytiKeliautojoTxtLaukus() {
        vardas_txt.clear();
        pavarde_txt.clear();
        keliautojasBtn.setSelected(true);
        radioBtnText = "Keliautojas";

    }

    @FXML
    public void trintiKeliautoja() {
       
        String keliautojoKodas;
        Keliautojas pasirinktasKeliautojas = keliautojuTable.getSelectionModel().getSelectedItem();
        keliautojoKodas = pasirinktasKeliautojas.getPriskirtaKelione();
        int keliautojoId = pasirinktasKeliautojas.getId();
        System.out.println(kelionesKodas);
        removeFromDbKeliautoja(pasirinktasKeliautojas);
        keliautojuTable.getItems().remove(pasirinktasKeliautojas);
        sumazintiKeliautojuKiekiKelioneje(keliautojoKodas);
       
          
          vartotojuList.removeIf(e -> e.getId() == (keliautojoId));
        kelioniuTable.refresh();
        vartotojuTable.refresh();

    }

    public void removeFromDbKeliautoja(Keliautojas keliautojas) {

        session = sf.openSession();
        tx = session.beginTransaction();
        session.delete(keliautojas);

        tx.commit();
        session.close();
    }

    public void removeFromDbKelione(Kelione kelione) {

        session = sf.openSession();
        tx = session.beginTransaction();
        session.delete(kelione);

        tx.commit();
        session.close();
    }

    @FXML
    public void pasalintiKelione() {
        String kelionesKodas;
        Kelione pasirinktaKelione = kelioniuTable.getSelectionModel().getSelectedItem();
        kelionesKodas = pasirinktaKelione.getKelionesKodas();
        kelioniuTable.getItems().remove(pasirinktaKelione);
        removeFromDbKelione(pasirinktaKelione);
        atskirtiKelionePoKelionesIstrynimo(kelionesKodas);
    }

    public boolean tikrintiArEgzistuojaKelione(String kelionesKodas) {
        //System.out.println(kelioniuList.get(0).getKelionesKodas());
        boolean bool = false;

        for (Kelione kel : keliautojas.getKelioniuList()) {

            if (kelionesKodas.equals(kel.getKelionesKodas())) {

                System.out.println("kelione rasta");
                bool = true;
                return bool;

            }

        }

        System.out.println("Kelione su tokiu kodu nerasta");
        return bool;
    }

    public void padidintiKeliautojuKiekiKelioneje(String kelionesKodas) {
        for (Kelione kel : kelioniuList) {

            if (kelionesKodas.equals(kel.getKelionesKodas())) {
                session = sf.openSession();
                Transaction tx = null;
                tx = session.beginTransaction();
                kel.setKeliautojuKiekis(kel.getKeliautojuKiekis() + 1);
                session.update(kel);
                session.getTransaction().commit();
                session.close();
            }

        }

    }

    public void priskirtiKelione() {
        String kelionesKodas = keliones_id_priskirti_txt.getText();
        System.out.println(kelionesKodas);
        int poop = 0;
        Alert warningAlert = new Alert(AlertType.WARNING);
        if ((keliautojuTable.getSelectionModel().getSelectedItem() == null) || ( kelionesKodas.equals(""))) {
            warningAlert.setTitle("Klaida");
            warningAlert.setContentText("");
            warningAlert.show();
        }
        for (Kelione kelion : kelioniuList) {
            System.out.println("sukas kelioniu ciklas: " + kelion.getKelionesKodas());
            if (kelionesKodas.equals(kelion.getKelionesKodas())) {

                System.out.println("kelione rasta");
                poop = 1;

            }
            if (poop == 1) {
                break;
            }
        }
        if (poop == 1) {
            padidintiKeliautojuKiekiKelioneje(kelionesKodas);
            Keliautojas pasirinktasKeliautojas = keliautojuTable.getSelectionModel().getSelectedItem();
            System.out.println("pasirinkto keliautojo priskirta kelione " + pasirinktasKeliautojas.getPriskirtaKelione());
            sumazintiKeliautojuKiekiKelioneje(pasirinktasKeliautojas.getPriskirtaKelione());
            session = sf.openSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            pasirinktasKeliautojas.setPriskirtaKelione(kelionesKodas);
            session.update(pasirinktasKeliautojas);
            session.getTransaction().commit();
            session.close();
            System.out.println("pasirinkto keliautojo priskirta kelione po pakeitimo " + pasirinktasKeliautojas.getPriskirtaKelione());
            System.out.println("sekmingai priskirta kelione");
            //atskirtiKelione(kelionesKodas);

            keliautojuTable.refresh();
            kelioniuTable.refresh();
            keliones_id_priskirti_txt.clear();
        } else {
            warningAlert.setTitle("Klaida");
            warningAlert.setContentText("Privaloma irasyti keliones ID arba nepasirinktas keliautojas arba kelione su tokiu ID neegzistuoja");
            warningAlert.show();
        }

    }

    public void atskirtiKelionePoKelionesIstrynimo(String kelionesKodas) {

        for (Keliautojas keliaut : keliautojuList) {
            if (kelionesKodas.equals(keliaut.getPriskirtaKelione())) {
                keliaut.setPriskirtaKelione("0");
                padidintiKeliautojuKiekiKelioneje("0");
                
                
                session = sf.openSession();
                Transaction tx = null;
                tx = session.beginTransaction();
              
                session.update(keliaut);
                session.getTransaction().commit();
                session.close();
                
                
                
                
                keliautojuTable.refresh();

            }

        }

    }


   
   

    public void sumazintiKeliautojuKiekiKelioneje(String kelionesKodas) {
        for (Kelione kel : kelioniuList) {

            if (kelionesKodas.equals(kel.getKelionesKodas())) {
                session = sf.openSession();
                Transaction tx = null;
                tx = session.beginTransaction();
                System.out.println(kel.getKelionesKodas());
                kel.setKeliautojuKiekis(kel.getKeliautojuKiekis() - 1);
                session.update(kel);
                session.getTransaction().commit();
                session.close();

            }

        }

    }

    @FXML
    public void pridetiKelione() throws IOException {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/kursinisjavafx/FXMLPridetiLangas.fxml"));
        Parent root = loader.load();

        FXMLPridetiLangasController controller = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.show();
        controller.passList(kelioniuList);

    }

}
