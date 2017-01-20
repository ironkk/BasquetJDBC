package basquetjdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import model.Player;
import model.Team;
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
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //COnexion a la base de datos
            System.out.println("Estableciendo conexión con la bbdd...");
            gestor.connect();
            System.out.println("Conectado a la bbdd de players(JUGADORES)");
            LocalDate todayLocalDate = LocalDate.now(ZoneId.of("MADRID"));
            Team t = new Team("FCBLASA", "BCN", todayLocalDate);
            Player p = new Player("Ironk", todayLocalDate, 4,
                    10, 65, "BASE", t);

            //1
            gestor.insertTeam(t);
            System.out.println("Team dado de alta");

            //2
            gestor.insertPlayer(p);
            System.out.println("Player dado de alta.");

            //3
            System.out.println("MODIFY PLAYER STATS");
            gestor.UpdateStatisticsPlayers(p);

            //4
            System.out.println("MODIFY TEAM OF PLAYER");
            gestor.updateTeamOfPlayer(p, t);

            //5
            System.out.println("DELETE PLAYER");
            gestor.deletePlayer(p);

            //6
            System.out.println("SELECT PLAYER BY NAME");
            gestor.selectPlayerByName("Ironk");

            //7
            System.out.println("SELECT PLAYER BY % NAME %");
            gestor.PlayersByName("Ir");

            //8
            System.out.println("SELECT PLAYER BY BASKETS");
            gestor.PlayersByBaskets(2);

            //9
            System.out.println("SELECT PLAYER BY Nº ASSISTS");
            gestor.PlayersBynAssists(1, 20);

            //10
            System.out.println("SELECT PLAYER BY POS");
            gestor.PlayersBynPos("BASE");
            //11
            System.out.println("SELECT PLAYER BY VIRTH");
            gestor.PlayersByFecha(todayLocalDate);

            //12
            System.out.println("ORDER PLAYER BY POS");
            gestor.PlayersGroupByPos("BASE");

            //13
            //STATSBYPOSITION
            //14
            //RANKING
            //15
            System.out.println("TEAMS BY CITY");
            gestor.TeamByCity("IGUALADA");
            //16
            System.out.println("PLAYERS BY TEAM");
            gestor.PlayersByTeam(t);
            //17
            System.out.println("SELECT ALL PLAYERS BY POS");
            gestor.SelectAllPlayersByPos(t, "BASE");
            //18
            System.out.println("SELECT PLAYER BY MAX NBASKETS FROM A TEAM");
            gestor.PlayersBynBaskets(t);
            //19
            List<Player> allPlayers = gestor.selectAllPlayers();

            System.out.println("LIST OF PlAYERS");
            System.out.println(allPlayers.size());
            for (Player pr : allPlayers) {
                System.out.println(pr);
            }

            gestor.disconnect();
            System.out.println("Cerrada la conexión con la bbdd.");
        } catch (SQLException ex) {
            System.out.println("Error con la BBDD: " + ex.getCause());
        }
    }

}
