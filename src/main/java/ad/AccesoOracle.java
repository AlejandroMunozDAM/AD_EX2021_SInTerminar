package ad;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;


/**
 * Clase de acceso a Oracle.
 * <p>
 * DEBES IMPLEMENTAR LAS PARTES QUE SE PIDEN.
 */
public class AccesoOracle implements Closeable {

    // TODO: puedes añadir campos y métodos, pero solamente si son PRIVADOS.
    private final Connection connection;
    private CallableStatement insertarPersonaCall;
    private CallableStatement ponerMultaCall;
    private CallableStatement ponerAficionCall;
    private CallableStatement aficionMasComunCall;

    public AccesoOracle() throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "skynet", "bla");
        insertarPersonaCall = connection.prepareCall("CALL crearPersona(?,?)");
        ponerMultaCall = connection.prepareCall("CALL ponerAficion(?,?)");
        ponerAficionCall = connection.prepareCall("CALL ponerMulta(?,?)");
        aficionMasComunCall = connection.prepareCall("CALL aficionMasComun()");
    }

    public void insertarPersona(Persona persona) {
        try {
            // - "crearPersona", que da de alta una persona con su nombre y país.
            insertarPersonaCall.setString(1, persona.nombre());
            insertarPersonaCall.setString(2, persona.pais());
            insertarPersonaCall.execute();

            // - "ponerMulta", que añade una multa a la persona indicada.
            for (int multa : persona.multas()) {
                ponerMultaCall.setString(1, persona.nombre());
                ponerMultaCall.setInt(2, multa);
                ponerMultaCall.execute();
            }

            // - "ponerAficion", que añade una afición a la persona indicada.
            for (String aficion : persona.aficiones()) {
                ponerAficionCall.setString(1, persona.nombre());
                ponerAficionCall.setString(2, aficion);
                ponerAficionCall.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getAficionMasComun() {
        // TODO: invoca a la función "aficionMasComun" y devuelve su resultado.
        try {
            aficionMasComunCall.execute();
            String aficion = aficionMasComunCall.getResultSet().getString(1);
            return aficion;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
