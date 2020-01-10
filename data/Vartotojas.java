package data;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.*;
import org.hibernate.type.SerializableType;
import static org.hibernate.type.TypeFactory.serializable;

@Table (name = "Vartotojai")
//@Inheritance
@MappedSuperclass
public class Vartotojas implements Serializable{
   
    SimpleIntegerProperty id;
    SimpleStringProperty tipas;
   
    ObservableList<Vartotojas> vartotojaiList = FXCollections.observableArrayList();
  
    public Vartotojas(int id, String tipas) {
        this.id = new SimpleIntegerProperty(id);
        this.tipas = new SimpleStringProperty(tipas);
    }
   @Transient
    public ObservableList <Vartotojas> getVartotojaiList() {
        return vartotojaiList;
    }

    public void setVartotojaiList(int id, String tipas) {
        vartotojaiList.add(new Vartotojas(id,tipas));
        //this.vartotojaiList = FXCollections.observableList(vartotojaiList);
        
          
    }

    public Vartotojas() {
        this.id = new SimpleIntegerProperty(0);
        this.tipas = new SimpleStringProperty("tipas");
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public void setTipas(String tipas) {
        this.tipas = new SimpleStringProperty(tipas);
    }
     @Id
    @Column (name = "ID")

    public int getId() {
        return id.get();
    }
@Column (name = "TIPAS")
    public String getTipas() {
        return tipas.get();
    }

    @Override
    public String toString() {
        return id + " " + tipas;
    }

}
