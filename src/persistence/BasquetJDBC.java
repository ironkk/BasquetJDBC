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
import model.Equipo;
import model.Jugador;


/**
 *
 * @author ironkk
 */
public class BasquetJDBC {
    private Connection conexion;

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    public List<Jugador> selectAllJugador() throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from player e, team t where e.team=t.name";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            jugadores.add(j);
        }
        rs.close();
        st.close();
        return jugadores;
    }

    public void insertEquipo(Equipo e) throws SQLException {
        String insert = "insert into team values (?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getLocalidad());
        ps.setDate(3, java.sql.Date.valueOf(e.getCreacion()));
        ps.executeUpdate();
        ps.close();
    }

    public void insertJugador(Jugador c) throws SQLException {
        String insert = "insert into player values (?, ?, ?, ?,?,?,?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        ps.setString(1, c.getNombre());
        ps.setDate(2, java.sql.Date.valueOf(c.getFechaNacimiento()));
        ps.setInt(3, c.getNumCanastas());
        ps.setInt(4, c.getNumAsistencias());
        ps.setInt(5, c.getNumRebotes());
        ps.setString(6, c.getPosicion());
        ps.setString(7, c.getEquipo().getNombre());
        ps.executeUpdate();
        ps.close();
    }

    public void updateStatsJugador(Jugador j, int canastas, int asistencias, int rebotes) throws SQLException {
        String update = "update player set nbaskets = ?, nassists = ?, nrebounds =? where name = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setInt(1, canastas);
        ps.setInt(2, asistencias);
        ps.setInt(3, rebotes);
        ps.setString(4, j.getNombre());
        ps.executeUpdate();
        ps.close();
    }

    public void updateEquipoDeJugador(Jugador j, Equipo e) throws SQLException {
        String update = "update player set team = ? where name = ?";
        PreparedStatement ps = conexion.prepareStatement(update);
        ps.setString(1, e.getNombre());
        ps.setString(2, j.getNombre());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteJugador(Jugador j) throws SQLException {
        String delete = "delete from player where name = ?";
        PreparedStatement ps = conexion.prepareStatement(delete);
        ps.setString(1, j.getNombre());
        ps.executeUpdate();
        ps.close();
    }

    public Jugador getJugador(String nombre) throws SQLException {
        String query = "select * from player e, team t where e.name = ? and e.team=t.name";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, nombre);
        Jugador j = new Jugador();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
        }
        rs.close();
        return j;
    }

    public List<Jugador> getJugadorContaining(String nombre) throws SQLException {
        nombre += "%";
        String query = "select * from player e, team t where e.name like ? and e.team=t.name";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }

    public List<Jugador> getJugadoresMasCanastasQue(int canastas) throws SQLException {
        String query = "select * from player e, team t where nbaskets >= ? and e.team=t.name";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setInt(1, canastas);
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }
    public List<Jugador> getJugadoresCanastasBetween(int min, int max) throws SQLException {
        String query = "select * from player e, team t where nbaskets >= ? and nbaskets <= ? and e.team=t.name";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setInt(1, min);
        ps.setInt(2, max);
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }
    public List<Jugador> getJugadoresByPosicion(String posicion) throws SQLException {
        String query = "select * from player e, team t where position = ? and e.team=t.name";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, posicion);
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }
    public List<Jugador> getJugadoresNacidosAntesDe(LocalDate nacimiento) throws SQLException {
        String query = "select * from player e, team t where e.team=t.name and birth < ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setDate(1, java.sql.Date.valueOf(nacimiento));
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }

    public List<Jugador> getRankingCanastas() throws SQLException {
        String query = "select * from player e, team t where e.team = t.name order by nbaskets DESC";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }

    public int getPosicionRankingCanastas(Jugador j){
        List<Jugador> lista = new ArrayList<>();
        int i = 0;
        try {
            lista = getRankingCanastas();
            for (i = 0; i < lista.size(); i++) {
                if (lista.get(i).getNombre().equals(j.getNombre())){
                    return i;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Equipo> getEquiposByLocalidad(String localidad) throws SQLException{
        String query = "select * from team where city = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, localidad);
        ResultSet rs = ps.executeQuery();
        List<Equipo> lista = new ArrayList<>();
        while (rs.next()) {
            Equipo e = new Equipo();
            e.setNombre(rs.getString("name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            lista.add(e);
        }
        return lista;
    }

    public List<Jugador> getJugadoresByEquipo(String equipo) throws SQLException {
        String query = "select * from team t, player e where e.team=t.name and e.team = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, equipo);
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }
    public List<Jugador> getJugadoresEquipoByPosicion(String equipo, String posicion) throws SQLException {
        String query = "select * from team t, player e where e.team=t.name and e.team = ? and position = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, equipo);
        ps.setString(2, posicion);
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }

    public List<Jugador> getMaximoGoalerEquipo(String equipo) throws SQLException {
        String query = "select e.name, e.birth, max(e.nbaskets) as nbaskets, e.nassists, e.nrebounds, e.position, e.team, t.name, t.city, t.creation " +
                "from player e, team t where e.team=t.name and e.team = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setString(1, equipo);
        ResultSet rs = ps.executeQuery();
        List<Jugador> lista = new ArrayList<>();
        while (rs.next()) {
            Jugador j = new Jugador();
            j.setNombre(rs.getString("e.name"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumCanastas(rs.getInt("nbaskets"));
            Equipo e = new Equipo();
            e.setNombre(rs.getString("t.name"));
            e.setCreacion(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            j.setEquipo(e);
            j.setPosicion(rs.getString("position"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setFechaNacimiento(rs.getDate("birth").toLocalDate());
            lista.add(j);
        }
        rs.close();
        return lista;
    }
}


