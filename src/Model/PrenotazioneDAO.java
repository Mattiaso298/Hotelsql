package Model;


import java.sql.*;

public class PrenotazioneDAO {
    private Connection conn;

    public PrenotazioneDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Prenotazione prenotazione) throws SQLException {
        // 1. Inserisci la prenotazione principale
        String queryPrenotazione = "INSERT INTO Prenotazione (codice, dataArrivo, dataPartenza) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(queryPrenotazione)) {
            ps.setString(1, prenotazione.getCodice());
            ps.setDate(2, Date.valueOf(prenotazione.getDataArrivo()));
            ps.setDate(3, Date.valueOf(prenotazione.getDataPartenza()));
            ps.executeUpdate();
        }

        // 2. Popola la tabella ponte Cliente_Prenotazione (Many-to-Many)
        if (prenotazione.getClienti() != null && !prenotazione.getClienti().isEmpty()) {
            String queryPonte = "INSERT INTO Cliente_Prenotazione (cliente_cf, prenotazione_codice) VALUES (?, ?)";
            try (PreparedStatement psPonte = conn.prepareStatement(queryPonte)) {
                for (Cliente c : prenotazione.getClienti()) {
                    psPonte.setString(1, c.getCf());
                    psPonte.setString(2, prenotazione.getCodice());
                    psPonte.addBatch();
                }
                psPonte.executeBatch();
            }
        }
    }

    public Prenotazione getPrenotazioneByCodice(String codice) throws SQLException {
        String query = "SELECT * FROM Prenotazione WHERE codice = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, codice);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prenotazione p = new Prenotazione();
                    p.setCodice(rs.getString("codice"));
                    p.setDataArrivo(rs.getDate("dataArrivo").toLocalDate());
                    p.setDataPartenza(rs.getDate("dataPartenza").toLocalDate());
                    return p;
                }
            }
        }
        return null;
    }
}