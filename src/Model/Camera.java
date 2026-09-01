package Model;

public class Camera {

    int numeroStanza;
    int postiLetto;
    double prezzo;

    public Camera(int numeroStanza, int postiLetto, double prezzo){
        this.numeroStanza = numeroStanza;
        this.postiLetto = postiLetto;
        this.prezzo = prezzo;
    }

    public int getNumeroStanza(){ return this.numeroStanza;}
    public  int getPostiLetto(){ return this.postiLetto;}
    public double getPrezzo(){return this.prezzo;}

@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Camera camera = (Camera) obj;
    return numeroStanza == camera.numeroStanza &&
            postiLetto == camera.postiLetto &&
            Double.compare(camera.prezzo, prezzo) == 0;
}

    @Override
    public String toString() {
        return "Camera ID: " + numeroStanza + " | Posti letto: " + postiLetto + " | Prezzo base: " + prezzo + "€";
    }

}
