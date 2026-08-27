import java.sql.*;

public class Main{
public static void main(String[] args) {
    try{
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/hotel_progetto",
                "roots",
                "roots"
        );

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select nome, cf from cliente");

        while(resultSet.next()){
            System.out.println(resultSet.getString("nome"));
            System.out.println(resultSet.getString("cf"));
        }
    }catch(SQLException e){
        e.printStackTrace();
    }
}
}