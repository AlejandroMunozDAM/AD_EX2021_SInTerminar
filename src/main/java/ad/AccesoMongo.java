package ad;

import com.mongodb.client.*;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

/**
 * Clase de acceso a MongoDB.
 * <p>
 * DEBES IMPLEMENTAR LAS PARTES QUE SE PIDEN.
 */
public class AccesoMongo implements Closeable {
    private final MongoDatabase db;
    private final MongoCollection<Document> colSkynet;

    // TODO: puedes añadir campos y métodos, pero solamente si son PRIVADOS.

    public AccesoMongo() {
        // TODO: haz un constructor apropiado.
        Logger.getLogger("org.mongodb").setLevel(Level.WARNING);

            MongoClient client = MongoClients.create();
            db = client.getDatabase("examen");
            colSkynet = db.getCollection("skynet");

    }

    public List<Persona> getPersonas() {
        // TODO: consulta todas las personas de la colección de MongoDB, y devuélvelas de la forma indicada.
        List<Persona> personas = new ArrayList<>();
        FindIterable<Document> datos = colSkynet.find();
        for (Document doc :
                datos) {
            System.out.println(doc.getString("nombre"));
        }
//        datos.forEach((Consumer<? super Document>) doc -> {
//            Persona p = new Persona(
//                    doc.getString("nombre"),
//                    doc.getString("pais"),
//                    doc.getList("aficiones", String.class),
//                    doc.getList("multas", Integer.class)
//            );
//            personas.add(p);
//        });
        return personas;
    }

    // TODO: implementa las consulas descritas en los comentarios, mostrando por consola el resultado de cada una.
    public void demoConsultas() {
        // 1. Nombre y multas de las personas que tengan alguna multa de 100 y alguna de 200.

        // 2. Cantidad de personas que tienen alguna multa mayor o igual a 200, o son aficionadas a la programación, o son del país "Spain".

        // 3. Cantidad de personas aficionadas al deporte por cada país, pero solamente para los países donde haya más de una.

        // 4. Añade la afición "Programación" a todas las personas que no la tengan ya, y muestra cuántas se han modificado.

        // 5. Borra las personas de "United States" que no tengan ninguna multa de 200, y muestra cuántas se han borrado.
    }

    @Override
    public void close() throws IOException {
        // TODO: cierra la conexión a MongoDB.
    }
}
