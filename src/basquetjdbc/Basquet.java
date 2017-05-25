package basquetjdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import model.Equipo;
import model.Jugador;
import persistence.BasquetJDBC;

/**
 *
 * @author ironkk
 */
public class Basquet {

    /**
     * En el método main, se debe implementar el código necesario que compruebe
     * todas las funcionalidades programadas.
     *
     * Consideraciones: - Los nombres de las funciones deben ser claros y
     * correctamente formateados. Por ejemplo: public Jugador
     * obtenerJugadorPorNombre(String nombre); - Los argumentos y los tipos
     * devueltos deben ser lo más sencillos posibles, pero que cumplan la
     * expectativa de la funcionalidad solicitada. Por ejemplo: public void
     * insertarJugador(Jugador j); public List<Jugador>
     * listadoJugadoresPorNombre(String nombre); - Se valorará la limpieza,
     * tabulación y optimización del código.
     *
     */
    public static void main(String[] args) throws IOException {
        BasquetJDBC gestor = new BasquetJDBC();
        try {
            //Conexion a la base de datos
            System.out.println("Estableciendo conexión con la bbdd...");
            gestor.conectar();
            System.out.println("Conectado a la bbdd restaurant");
            LocalDate todayLocalDate = LocalDate.now( ZoneId.of( "España" ) );
            Equipo e = new Equipo("yeaaah", "Barcelona", todayLocalDate);
            Jugador j = new Jugador("yiyiaaah", todayLocalDate, 223,
                    553,335,"alero", e);

            /*//Insert Equipo
            gestor.insertEquipo(e);
            System.out.println("Equipo dado de alta.");
            //Insert Jugador
            gestor.insertJugador(j);*/

            List<Jugador> todosJugadores = gestor.selectAllJugador();

            System.out.println("Listado de jugadores");
            System.out.println(todosJugadores.size());
            for (Jugador c : todosJugadores) {
                System.out.println(c);
            }

            System.out.println("Modificar stats jugaodr");
            gestor.updateStatsJugador(j, 2, 777, 888);

            System.out.println("Modificar equipo de jugador:");
            gestor.updateEquipoDeJugador(j, e);

            Jugador a = new Jugador("yoyoww", todayLocalDate, 1,
                    2,3,"Base", e);
            gestor.insertJugador(a);
            gestor.deleteJugador(a);
            System.out.println("Jugador: "+gestor.getJugador("Jugador"));

            Jugador jugador = gestor.getJugador("Jugador");

            System.out.println("jugador 2: "+gestor.getJugadorContaining("Jug"));

            System.out.println("Jugadores mas canastas de 10: "+gestor.getJugadoresMasCanastasQue(10));

            System.out.println("Jugadores canastas entre 1 y 6: "+gestor.getJugadoresCanastasBetween(1, 6));

            System.out.println("Jugadores posicion alero"+gestor.getJugadoresByPosicion("alero"));

            System.out.println("Jugadores nacidos antes de: "+gestor.getJugadoresNacidosAntesDe(todayLocalDate));


            System.out.println("ranking: "+gestor.getRankingCanastas());

            System.out.println("Posicion en el ranking: "+gestor.getPosicionRankingCanastas(jugador));

            System.out.println("Equipos por localidad: "+gestor.getEquiposByLocalidad("Barcelona"));

            System.out.println("Jugadores de un equipo: "+gestor.getJugadoresByEquipo("yeaaah"));

            System.out.println("Jugadores de un equipo de unamisma posicion: "+gestor.getJugadoresEquipoByPosicion("yeaaah", "alero"));

            System.out.println("Maximo goaler del equipo yeaaah: "+gestor.getMaximoGoalerEquipo("yeaaah"));
            
            gestor.desconectar();
            System.out.println("Cerrada la conexión con la bbdd.");
        } catch (SQLException ex) {
            System.out.println("Error con la BBDD: "+ex.getMessage());
        }
    }

}
