package Model;

public class CameraSingola extends Camera {

    public CameraSingola(int numeroStanza, double prezzo){
        super(numeroStanza,1,prezzo);
    }

    public double calcolaCostoSoggiorno(int giorni){
        return getPrezzo() * giorni;
    }

    @Override
    public String toString(){
        return "[Singola]" + super.toString();
    }

    public int getNumeroStanza(){ return this.numeroStanza;}
    public  int getPostiLetto(){ return this.postiLetto;}
    public double getPrezzo(){return this.prezzo;}




}
