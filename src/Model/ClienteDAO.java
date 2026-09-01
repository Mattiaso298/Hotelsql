package Model;

import java.sql.*;


public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Cliente cliente) throws SQLException {
        // L'età non viene inserita perché calcolata automaticamente dal database
        String query = "INSERT INTO Cliente (cf, nome, cognome, email, via, civico, citta, dataNascita) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getCf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getCognome());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getVia());
            ps.setString(6, cliente.getCivico());
            ps.setString(7, cliente.getCitta());
            ps.setDate(8, Date.valueOf(cliente.getDataNascita()));
            ps.executeUpdate();
        }
    }

    public Cliente getClienteByCf(String cf) throws SQLException {
        String query = "SELECT * FROM Cliente WHERE cf = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setCf(rs.getString("cf"));
                    c.setNome(rs.getString("nome"));
                    c.setCognome(rs.getString("cognome"));
                    c.setEmail(rs.getString("email"));
                    c.setVia(rs.getString("via"));
                    c.setCivico(rs.getString("civico"));
                    c.setCitta(rs.getString("citta"));
                    c.setDataNascita(rs.getDate("dataNascita").toLocalDate());
                    c.setEta(rs.getInt("eta"));
                    return c;
                }
            }
        }
        return null;
    }
}