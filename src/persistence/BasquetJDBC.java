package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Player;
import model.StatsByPosition;
import model.Team;

/**
 *
 * @author ironkk
 */
public class BasquetJDBC {

    private Connection conexion;

    //connect bbdd
      public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jugadores";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

     //disconnect bbdd
    public void disconnect() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
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
    public void UpdateStatisticsPlayers(Player p) throws SQLException {
              String update = "update player set nbaskets=?,nassists=?,nrebounds=? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setInt(1, p.getNbaskets());
        ps.setInt(2, p.getNassists());
        ps.setInt(3, p.getNrebots());
        ps.setString(4, p.getName());
        ps.executeUpdate();
        ps.close();
    }

    // Buscar Players
     public List<Player> searchingPlayers(ResultSet rs) throws SQLException{
        List<Player> players = new ArrayList<>();
        while (rs.next()) {
            Team t = new Team(rs.getString("TEAM"));
            Player p = new Player(
                    rs.getString("NAME"), 
                    rs.getDate("BIRTHDAY").toLocalDate(),
                    rs.getInt("Nº BASKETS"),
                    rs.getInt("Nº ASSISTs"), 
                    rs.getInt("Nº REBOUNDS"),
                    rs.getString("POSITION"), 
                    t
            );
            players.add(p);
        }
        return players;
    }
    //4. Modificar el equipo de un jugador determinado.
    public void updateTeamOfPlayer(Player p, Team t) throws SQLException {
        
        String update = "update player set team=? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, t.getName());
        ps.setString(2, p.getName());
        ps.executeUpdate();
        ps.close();
  
    }

    //5. Borrar un jugador de la bbdd.
    public void deletePlayer(Player p) throws SQLException {
        String query = "delete from player where name = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, p.getName());
        ps.executeUpdate();
        ps.close();
    }

    //6. Obtener un objeto Jugador a partir de su nombre.
    public Player selectPlayerByName(String name) throws SQLException {
            String select = "select * from player where name = ?;";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setString(1, name);
        ResultSet rs = st.executeQuery();

        Player p = new Player();
        while (rs.next()) {

            p.setName(rs.getString("NAME"));
            p.setBirth(rs.getDate("BIRTHDAY").toLocalDate());
            p.setNbaskets(rs.getInt("Nº BASKETS"));
            p.setNassists(rs.getInt("Nº ASSISTS"));
            p.setNrebots(rs.getInt("Nº REBOUNDS"));
            p.setPos(rs.getString("POSITION"));
            Team t = new Team(rs.getString("TEAM"));
             p.setTeam(t);
        }
        rs.close();
        st.close();
        return p;
    }

    //7. Obtener el listado de jugadores a partir de un nombre de manera que no haga falta indicar el nombre completo.
    public List<Player> PlayersByName(String name) throws SQLException {
       List<Player> players = new ArrayList<>();
           String select= "select * from player where name like ?";
          PreparedStatement st = conexion.prepareStatement(select);
          st.setString(1, "%"+name+"%");
        ResultSet rs = st.executeQuery(select);
        rs.close();
        players = searchingPlayers(rs);
        return players;

    }

    //8. Listado de jugadores que hayan conseguido un número mayor o igual a un número de canastas especificado como parámetro.
    public List<Player> PlayersByBaskets(int nBaskets) throws SQLException {
         List<Player> players = new ArrayList<>();
        String select= "select * from player where nbaskets >= ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setInt(1, nBaskets);
        ResultSet rs = st.executeQuery();
        players = searchingPlayers(rs);
        return players;
    }

    //9. Listado de jugadores que hayan efectuado un número de asistencias dentro de un rango especificado como parámetro.
    public List<Player> PlayersBynAssists(int nAssistsMin, int nAssistsMax) throws SQLException {
     List<Player> players = new ArrayList<>();
        String select = "select * from player where nassists between ? and ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setInt(1, nAssistsMin);
        st.setInt(2, nAssistsMax);
        ResultSet rs = st.executeQuery();
        players = searchingPlayers(rs);
        return players;
    }

    //10. Listado de jugadores que pertenezcan a una posición específica, por ejemplo: base.
    public List<Player> PlayersBynPos(String position) throws SQLException {
       List<Player> players = new ArrayList<>();
        String select = "select * from player where position = ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setString(1, position);
        ResultSet rs = st.executeQuery();
        players = searchingPlayers(rs);
        return players;
    }

    //11. Listado de jugadores que hayan nacido en una fecha anterior a una fecha especificada como parámetro.
    public List<Player> PlayersByFecha(LocalDate birth) throws SQLException {
        List<Player> players = new ArrayList<>();
        String select = "select * from player where birth <= ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setDate(1, java.sql.Date.valueOf(birth));
        ResultSet rs = st.executeQuery();
        players = searchingPlayers(rs);
        return players;

    }
    //12. Agrupar los jugadores por la posición del campo y devolver para cada grupo la siguiente información: la media, el máximo y el mínimo de canastas, asistencias y rebotes.
        public List<Player> PlayersGroupByPos(String position) throws SQLException {
         List<Player> players = new ArrayList<>();
        String query = "select sinsn from player where fksdmfkd ='position' group by ='position'";
         Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
         while (rs.next()) {
            Player p = new Player();
            p.setName(rs.getString("PLAYER NAME"));
            players.add(p);
         }
        rs.close();
        return players;

    }
    //13. Ranking de jugadores por número de canastas. Debe de devolver un resultado como el siguiente: 1 Jugador1 24 2 Jugador2 18 3 Jugador3 26
//StatsByPosition.class
        
    //14.Obtener la posición dentro del ranking para un jugador determinado.
     // Ranking.class

    //15. Listado de equipos existentes en una localidad determinada.
                public List<Team> TeamByCity(String city) throws SQLException {
              List<Team> teams = new ArrayList<>();
        String select = "select * from team where city = ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setString(1, city);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Team t = new Team(rs.getString("TEAM NAME"), rs.getString("CITY NAME"), rs.getDate("TEAM CREATION").toLocalDate());
            teams.add(t);
        }
        return teams;

    }
    //16. Listado de todos los jugadores de un equipo, a partir del nombre completo del equipo.
        public List<Player> PlayersByTeam(Team t) throws SQLException {
        List<Player> players = new ArrayList<>();
        String select = "select * from player where team = ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setString(1, t.getName());
        ResultSet rs = st.executeQuery();
        players = searchingPlayers(rs);
        return players;

    }
    //17. Listado de todos los jugadores de un equipo, que además jueguen en la misma posición (parámetro adicional de la consulta), por ejemplo, alero.
                        public List<Player> SelectAllPlayersByPos(Team t, String position) throws SQLException {
        List<Player> players = new ArrayList<>();
        String select = "select * from player where team = ? and position = ?";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setString(1, t.getName());
        st.setString(2, position);
        ResultSet rs = st.executeQuery();
        players = searchingPlayers(rs);
        return players;

    }
    //18. Devuelve el jugador que más canastas ha realizado de un equipo determinado como parámetro.
    public Player PlayersBynBaskets(Team t) throws SQLException {
         String select = "select * from player where team = ? and nbaskets = (select max(nbaskets) from player where team = ?)";
        PreparedStatement st = conexion.prepareStatement(select);
        st.setString(1, t.getName());
        st.setString(2, t.getName());
        ResultSet rs = st.executeQuery();

        Player p = new Player();
        while (rs.next()) {

            p.setName(rs.getString("PLAYER NAME"));
            p.setBirth(rs.getDate("BIRTHDAY").toLocalDate());
            p.setNbaskets(rs.getInt("Nº BASKETS"));
            p.setNassists(rs.getInt("Nº ASSISTS"));
            p.setNrebots(rs.getInt("Nº REBOUNDS"));
            p.setPos(rs.getString("POSITION"));
   
            Team tm = new Team(rs.getString("TEAM"));
            p.setTeam(tm);
        }
        rs.close();
        st.close();
        return p;

    }
    
    //19. Devuelve una Lista con todos los jugadores.
    public List<Player> selectAllPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String query = "select * from player e, team t where e.team=t.name";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
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
}
