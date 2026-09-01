package Model;

import java.time.LocalDate;

public class Cliente {
    private String cf;
    private String nome;
    private String cognome;
    private String email;
    private String via;
    private String civico;
    private String citta;
    private LocalDate dataNascita;
    private int eta;

    public Cliente() {}

    public Cliente(String cf, String nome, String cognome, String email, String via, String civico, String citta, LocalDate dataNascita) {
        this.cf = cf;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.via = via;
        this.civico = civico;
        this.citta = citta;
        this.dataNascita = dataNascita;
    }

    public String getCf() { return cf; }
    public void setCf(String cf) { this.cf = cf; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getVia() { return via; }
    public void setVia(String via) { this.via = via; }

    public String getCivico() { return civico; }
    public void setCivico(String civico) { this.civico = civico; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public LocalDate getDataNascita() { return dataNascita; }
    public void setDataNascita(LocalDate dataNascita) { this.dataNascita = dataNascita; }

    public int getEta() { return eta; }
    public void setEta(int eta) { this.eta = eta; }

    @Override
    public String toString() {
        return "Cliente{" +
                "cf='" + cf + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", via='" + via + '\'' +
                ", civico='" + civico + '\'' +
                ", citta='" + citta + '\'' +
                ", dataNascita=" + dataNascita +
                ", eta=" + eta +
                '}';
    }
}