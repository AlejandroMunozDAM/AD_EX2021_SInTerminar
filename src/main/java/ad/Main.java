package ad;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Aplicación principal.
 *
 * NO DEBES CAMBIAR NADA AQUÍ.
 */
public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        try (AccesoOracle accesoOracle = new AccesoOracle(); AccesoMongo accesoMongo = new AccesoMongo()) {
            List<Persona> personas = accesoMongo.getPersonas();
            personas.forEach(accesoOracle::insertarPersona);
            System.out.println("Afición más común: " + accesoOracle.getAficionMasComun());
            //accesoMongo.demoConsultas();
        }
    }

}
