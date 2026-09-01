package Model;


import java.sql.*;

public class CameraDAO {
    private Connection conn;

    public CameraDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Camera camera) throws SQLException {
        String query = "INSERT INTO Camera (numeroStanza, postiLetto, prezzo, tipoCamera) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, camera.getNumeroStanza());
            ps.setInt(2, camera.getPostiLetto());
            ps.setDouble(3, camera.getPrezzo());

            // Determina il tipo di camera basandosi sulla classe dell'oggetto
            if (camera instanceof CameraSingola) {
                ps.setString(4, "singola");
            } else if (camera instanceof CameraDoppia) {
                ps.setString(4, "doppia");
            } else {
                ps.setString(4, "standard");
            }

            ps.executeUpdate();
        }
    }

    public Camera getCameraByNumero(int numeroStanza) throws SQLException {
        String query = "SELECT * FROM Camera WHERE numeroStanza = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, numeroStanza);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int num = rs.getInt("numeroStanza");
                    double prezzo = rs.getDouble("prezzo");
                    String tipo = rs.getString("tipoCamera");

                    if ("singola".equalsIgnoreCase(tipo)) {
                        return new CameraSingola(num, prezzo);
                    } else if ("doppia".equalsIgnoreCase(tipo)) {
                        return new CameraDoppia(num, prezzo);
                    }
                }
            }
        }
        return null;
    }
}