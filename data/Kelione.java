
package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity 
@Table (name = "Keliones")

public class Kelione {
    
    
    private SimpleStringProperty kelionesKodas;
    private SimpleStringProperty salis;
    private SimpleIntegerProperty keliautojuKiekis;

    
    
    
    public Kelione(String kelionesKodas,String salis,int keliautojuKiekis) {
        this.kelionesKodas = new SimpleStringProperty(kelionesKodas);
        this.salis = new SimpleStringProperty(salis);
        this.keliautojuKiekis = new SimpleIntegerProperty(keliautojuKiekis);
    }
    
    
    public Kelione() {
        this.kelionesKodas = new SimpleStringProperty("kelionesKodas");
        this.salis = new SimpleStringProperty("salis");    
        this.keliautojuKiekis = new SimpleIntegerProperty(0);
    }

    
    @Column (name = "ID")
    @Id
    public String getKelionesKodas() {
        return kelionesKodas.get();
    }

    public void setKelionesKodas(String kelionesKodas) {
        this.kelionesKodas = new SimpleStringProperty(kelionesKodas);
    }
    @Column (name="KELIAUTOJU_KIEKIS")
    public int getKeliautojuKiekis() {
        return keliautojuKiekis.get();
    }

    public void setKeliautojuKiekis(int keliautojuKiekis) {
        this.keliautojuKiekis = new SimpleIntegerProperty(keliautojuKiekis);
    }



    
   @Column(name = "SALIS")
    public String getSalis() {
        return salis.get();
    }

   
    public void setSalis(String salis) {
        this.salis =new SimpleStringProperty(salis);
    }

   
    @Override
    public String toString() {
        return  kelionesKodas + " " + salis + " " +  keliautojuKiekis  ;
    }


   
    
}
