package Model;

import java.time.LocalDate;
import java.util.List;

public class Prenotazione {
    private String codice;
    private LocalDate dataArrivo;
    private LocalDate dataPartenza;
    List<Cliente> clienti;
    List<Camera> camere;

    public Prenotazione() {}

    public Prenotazione(String codice, List<Camera> camere , LocalDate dataArrivo, LocalDate dataPartenza) {
        this.codice = codice;
        this.camere = camere;
        this.dataArrivo = dataArrivo;
        this.dataPartenza = dataPartenza;
    }

    public String getCodice() { return codice; }
    public void setCodice(String codice) { this.codice = codice; }

    public void setClienti(List<Cliente> clienti) {
        this.clienti = clienti;
    }
    public List<Cliente> getClienti(){ return clienti;}

    public List<Camera> getCameraNumeroStanza() { return camere; }
//    public void setCameraNumeroStanza(int cameraNumeroStanza) { this.cameraNumeroStanza = cameraNumeroStanza; }

    public LocalDate getDataArrivo() { return dataArrivo; }
    public void setDataArrivo(LocalDate dataArrivo) { this.dataArrivo = dataArrivo; }

    public LocalDate getDataPartenza() { return dataPartenza; }
    public void setDataPartenza(LocalDate dataPartenza) { this.dataPartenza = dataPartenza; }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "codice='" + codice + '\'' +
                ", dataArrivo=" + dataArrivo +
                ", dataPartenza=" + dataPartenza +
                '}';
    }
}