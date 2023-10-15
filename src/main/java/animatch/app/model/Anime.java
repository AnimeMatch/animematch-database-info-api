package animatch.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private int idApi;
    private double notaMedia;

//    public Anime() {
//    }
//
//    public Anime(int id, int idApi, double notaMedia) {
//        this.id = id;
//        this.idApi = idApi;
//        this.notaMedia = notaMedia;
//    }

    public int getId() {
        return id;
    }

    public int getIdApi() {
        return idApi;
    }

    public double getNotaMedia() {
        return notaMedia;
    }
}
