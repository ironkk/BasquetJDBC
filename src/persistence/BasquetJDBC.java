package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Player;
import model.Team;

/**
 *
 * @author ironkk
 */
public class BasquetJDBC {

    private Connection conexion;

    // 1. Insertar un equipo nuevo en la bbdd 
    public void insertTeam(Team t) throws SQLException {
        String insert = "insert into team values (?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, t.getName());
        ps.setString(2, t.getCity());
        ps.setDate(3, java.sql.Date.valueOf(t.getCreation()));
        ps.executeUpdate();
        ps.close();
    }

    //2. Insertar un nuevo jugador a la bbdd
    public void insertPlayer(Player p) throws SQLException {
        String insert = "insert into player values (?, ?, ?, ?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, p.getName());
        ps.setDate(2, java.sql.Date.valueOf(p.getBirth()));
        ps.setInt(3, p.getNbaskets());
        ps.setInt(4, p.getNassists());
        ps.setInt(5, p.getNrebots());
        ps.setString(6, p.getPos());
        ps.setString(7, p.getTeam().getName());
        ps.executeUpdate();
        ps.close();
    }

    //3. Modificar canastas, asistencias y rebotes de un jugador determinado.
    public void UpdateStatisticsPlayers(Player p, int nBaskets, int nAssists, int nRebots) throws SQLException {
        String query = "update player set nbaskets = ?, nassists = ?, nrebounds =? where name = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setInt(1, nBaskets);
        ps.setInt(2, nAssists);
        ps.setInt(3, nRebots);
        ps.setString(4, p.getName());
        ps.executeUpdate();
        ps.close();
    }

    //4. Modificar el equipo de un jugador determinado.
    
    public void updateTeamOfPlayer(Player p, Team t) throws SQLException{
        String query = "update player set team = ? where name = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, t.getName());
        ps.setString(2, p.getName());
        ps.executeUpdate();
        ps.close();
    }
    //5. Borrar un jugador de la bbdd.
       public void deletePlayer(Player p) throws SQLException{
        String query = "delete from player where name = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, p.getName());
        ps.executeUpdate();
        ps.close();
    }

    //6. Obtener un objeto Jugador a partir de su nombre.
    public void selectPlayerByName(String name)  throws SQLException{
        String query = "select * from player where name = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1,name);
        ps.executeUpdate();
        ps.close();
    }

    //7. Obtener el listado de jugadores a partir de un nombre de manera que no haga falta indicar el nombre completo.
    //8. Listado de jugadores que hayan conseguido un número mayor o igual a un número de canastas especificado como parámetro.
    //9. Listado de jugadores que hayan efectuado un número de asistencias dentro de un rango especificado como parámetro.
    //10. Listado de jugadores que pertenezcan a una posición específica, por ejemplo: base.
    //11. Listado de jugadores que hayan nacido en una fecha anterior a una fecha especificada como parámetro.
    //12. Agrupar los jugadores por la posición del campo y devolver para cada grupo la siguiente información: la media, el máximo y el mínimo de canastas, asistencias y rebotes.
    //13. Ranking de jugadores por número de canastas. Debe de devolver un resultado como el siguiente: 1 Jugador1 24 2 Jugador2 18 3 Jugador3 26
    // Es decir, deberá devolver un listando indicando posición en el ranking, nombre del jugador y no de canastas ordenador de mayor a menor número de canastas.
    //14.Obtener la posición dentro del ranking para un jugador determinado.
    //15. Listado de equipos existentes en una localidad determinada.
    //16. Listado de todos los jugadores de un equipo, a partir del nombre completo del equipo.
    //17. Listado de todos los jugadores de un equipo, que además jueguen en la misma posición (parámetro adicional de la consulta), por ejemplo, alero.
    //18. Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.
    
    
    public List<Player> selectAllPlayers() throws SQLException{
        List<Player> players = new ArrayList<>();
        String query = "select * from player e, team t where e.team=t.name";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            Player p = new Player();
            p.setName(rs.getString("PLAYER NAME"));
            p.setNassists(rs.getInt("Nº ASSISTS"));
            p.setNbaskets(rs.getInt("Nº BASKETS"));
            p.setPos(rs.getString("POSITION"));
            p.setNrebots(rs.getInt("Nº REBOUNDS"));
            p.setBirth(rs.getDate("BIRTH").toLocalDate());
            Team t = new Team();
            t.setName(rs.getString("TEAM NAME"));
            t.setCreation(rs.getDate("CREATION").toLocalDate());
            t.setCity(rs.getString("CITY"));
            p.setTeam(t);
            players.add(p);
        }
        rs.close();
        st.close();
        return players;
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jugadores";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void disconnect() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
