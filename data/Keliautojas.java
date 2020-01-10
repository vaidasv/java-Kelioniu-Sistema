package data;
import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.*;
@Entity
@Table (name = "Keliautojai")
public class Keliautojas extends Vartotojas implements Serializable {
    Kelione kelione;
    private SimpleStringProperty vardas;
    private SimpleStringProperty pavarde;
    private SimpleStringProperty priskirtaKelione;
   
    ObservableList<Kelione> kelioniuList = FXCollections.observableArrayList(
    );
    
    ObservableList<Keliautojas> keliautojuList = FXCollections.observableArrayList();
           
  
    
    public Keliautojas(Kelione kelione,int id, String tipas, String vardas, String pavarde, String priskirtaKelione) {
        super(id, tipas);
       
        //this.kelioniuList= kelioniuList;
        this.vardas = new SimpleStringProperty(vardas);
        this.pavarde = new SimpleStringProperty(pavarde);   
        this.priskirtaKelione = new SimpleStringProperty(priskirtaKelione);

        
    }
    
    public Keliautojas() {
        this.id =  new SimpleIntegerProperty(0);
        this.tipas = new SimpleStringProperty("Tipas");
        this.vardas = new SimpleStringProperty("vardas");
        this.pavarde = new SimpleStringProperty("pavarde");
        this.priskirtaKelione =new SimpleStringProperty("0");
        //this.kelioniuList.add(new Kelione("0","nera",0));
    }
@Transient
    public ObservableList <Kelione> getKelioniuList() {
        
        return kelioniuList;
    }

    @Transient
    public  ObservableList<Keliautojas> getKeliautojuList() {
        return keliautojuList;
    }

   
    
    @Column (name="PRISKIRTA_KELIONE")
    public String getPriskirtaKelione() {
        return priskirtaKelione.get();
    }

    public void setPriskirtaKelione(String priskirtaKelione) {
        this.priskirtaKelione = new SimpleStringProperty(priskirtaKelione);
    }
    
    @Column (name = "VARDAS")
    public String getVardas() {
        return vardas.get();
    }

    public void setVardas(String vardas) {
        this.vardas = new SimpleStringProperty(vardas);
    }
    @Column (name = "PAVARDE")
    public String getPavarde() {
        return pavarde.get();
    }
    
    

    
    
    public void setPavarde(String pavarde) {
        this.pavarde = new SimpleStringProperty(pavarde);
    }

    public String toString() {
        return id + " "  + tipas + " " + vardas + " "  + pavarde + " "  + priskirtaKelione;
    }
}
