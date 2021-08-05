package mx.edu.utez.model.games;

import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.beancontext.BeanContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoGames {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final Logger CONSOLE= LoggerFactory.getLogger(DaoGames.class);

    public List<BeanGames> findAll(){
        List<BeanGames> listGames = new ArrayList<>();
        try{
           con= ConnectionMySQL.getConnection() ;
           cstm= con.prepareCall("call sp_findAll");
           rs= cstm.executeQuery();

           while (rs.next()){
               BeanCategory category= new BeanCategory();
               BeanGames games= new BeanGames();

               category.setIdCategory(rs.getInt("idCategory"));
               category.setName(rs.getString("name"));
               category.setStatus(rs.getInt("status"));

               games.setIdGames(rs.getInt("idGames"));
               games.setName(rs.getString("name"));
               games.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGames")));
               games.setDatePremiere(rs.getDate("datePremiere"));
               games.setStatus(rs.getInt("status"));

               games.setIdCategory(category);

               listGames.add(games);
           }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());

        }finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listGames;
    }

    public BeanGames findById (int idGames){
           BeanGames games = null;
        try {

            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM games AS G INNER JOIN category AS C ON G.idGames = C.idCategory WHERE G.idGames = ?");
            cstm.setInt(1, idGames);
            rs = cstm.executeQuery();

            if(rs.next()){
                BeanCategory category = new BeanCategory();
                games = new BeanGames();

                category.setIdCategory(rs.getInt("idCategory"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getInt("status"));

                games.setIdGames(rs.getInt("idGames"));
                games.setName(rs.getString("name"));
                games.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("imgGames")));
                games.setDatePremiere(rs.getDate("datePremiere"));
                games.setStatus(rs.getInt("status"));

                games.setIdCategory(category);

            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return games;
    }

    public boolean create(BeanGames games){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_create(?,?,?,?,?)}");
            cstm.setString(1, games.getName());
            cstm.setString(2, games.getImgGame());
            cstm.setDate(3, (Date) games.getDatePremiere());
            cstm.setInt(4, games.getIdCategory().getIdCategory());
            cstm.setInt(5, games.getStatus());

            cstm.execute();
            flag = true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean update(BeanGames games){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_update(?,?,?,?,?,?}");
            cstm.setString(1, games.getName());
            cstm.setString(2, games.getImgGame());
            cstm.setString(3, String.valueOf(games.getDatePremiere()));
            cstm.setString(4, games.getIdCategory().getName());
            cstm.setInt(5, games.getIdCategory().getStatus());
            cstm.setInt(6,games.getIdGames());

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }

    public boolean delete(int idGames){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_delete(?)}");
            cstm.setInt(1, idGames);

            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
    public static void main(String[] args) {
        BeanGames beanGames = new BeanGames();
        BeanCategory beanCategory = new BeanCategory();
        DaoGames daoGames = new DaoGames();

        List<BeanGames> listGames = new ArrayList<>();
        listGames = daoGames.findAll();
        for (int i = 0; i < listGames.size(); i++) {
            System.out.println(listGames.get(i).getName());
        }

    }

    // Eliminar de manera "baja lógica"
/*
        boolean flag = false;
        flag = daoUser.delete(4);
        System.out.println("Se realizó correctamente");

 */
}

