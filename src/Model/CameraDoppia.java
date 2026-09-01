package Model;

public class CameraDoppia extends Camera{

    public CameraDoppia(int numeroStanza, double prezzo) {
        // La doppia ha di default 2 posti letto
        super(numeroStanza, 2, prezzo);
    }

    public double calcolaCostoSoggiorno(int giorni) {
        // Maggiorazione fissa di 15€ al giorno sul prezzo base
        return (getPrezzo() + 15.0) * giorni;
    }

    @Override
    public String toString() {
        return "[Doppia] " + super.toString();
    }
}