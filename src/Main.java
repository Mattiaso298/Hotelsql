import Model.CameraDAO;
import Model.ClienteDAO;
import Model.PrenotazioneDAO;
import Model.Camera;
import Model.Cliente;
import Model.Prenotazione;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Parametri di connessione al database MySQL
        String url = "jdbc:mysql://localhost:3306/hotel_progetto";
        String user = "roots"; // Sostituire con il proprio username
        String password = "roots"; // Sostituire con la propria password

        // Utilizzo del costrutto try-with-resources per garantire la chiusura della connessione
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // 1. Inizializzazione dei DAO
            CameraDAO cameraDAO = new CameraDAO(conn);
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            PrenotazioneDAO prenotazioneDAO = new PrenotazioneDAO(conn);

            // 2. Creazione e inserimento di una nuova Camera con 3 posti letto
            // Costruttore: Camera(numeroStanza, postiLetto, prezzo)
            Camera cameraTripla = new Camera(201, 3, 180.00);
            cameraDAO.insert(cameraTripla);
            System.out.println("Camera 201 inserita nel database (come libera, senza prenotazione).");

            // 3. Creazione di 3 nuovi Clienti
            // Costruttore: Cliente(cf, nome, cognome, email, via, civico, citta, dataNascita)
            Cliente cliente1 = new Cliente("CF143459", "Mario", "Greco", "mario.greco@email.com", "Via Greca", "10", "Roma", LocalDate.of(1990, 1, 1));
            Cliente cliente2 = new Cliente("CF823952", "Giulia", "Bianchi", "giulia.bianchi@email.com", "Via Milano", "20", "Milano", LocalDate.of(1992, 2, 2));
            Cliente cliente3 = new Cliente("CF173751", "Luca", "Darto", "luca.darto@email.com", "Via Agostini", "30", "Napoli", LocalDate.of(1988, 3, 3));

            // Inserimento dei clienti nel database
            clienteDAO.insert(cliente1);
            clienteDAO.insert(cliente2);
            clienteDAO.insert(cliente3);
            System.out.println("I 3 clienti sono stati inseriti nel database.");

            // 4. Creazione della Prenotazione (Aggiornata per la relazione 1:N)
            // Costruttore aggiornato: Prenotazione(codice, listaCamere, dataArrivo, dataPartenza)
            List<Camera> listaCamere = Arrays.asList(cameraTripla);

            Prenotazione prenotazione = new Prenotazione(
                    "PREN999",
                    listaCamere,
                    LocalDate.of(2026, 9, 1),
                    LocalDate.of(2026, 9, 10)
            );

            // Associazione dei clienti all'oggetto prenotazione
            List<Cliente> clientiPrenotazione = Arrays.asList(cliente1, cliente2, cliente3);
            prenotazione.setClienti(clientiPrenotazione);

            // 5. Salvataggio della prenotazione, associazione alle camere e salvataggio clienti
            prenotazioneDAO.insert(prenotazione);
            System.out.println("Prenotazione PREN999 creata e associata ai 3 clienti e alla camera 201 con successo.");

        } catch (SQLException e) {
            System.err.println("Errore di interazione con il database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}