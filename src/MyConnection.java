import java.sql.*;

public class MyConnection {

    Connection con = null;
    Statement st = null;

     MyConnection(){
        //etape1: chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("chargement driver");
        } catch (ClassNotFoundException e) {
            System.out.println("erreur driver "+e.getMessage());
        }
    }

    void connect(){
    //2eme etape: connextion
        try {
             con= DriverManager.getConnection("jdbc:mysql://127.0.0.1/tpjava","root","");
             st = con.createStatement();
             System.out.println("connection etablit");
        } catch (SQLException e) {
            System.out.println("erreur connnection "+e.getMessage());
        }

    }

    int ajout(String n, String p, String ps){

        int a =0;

        try {
            a= st.executeUpdate("insert into profile values('"+n+"','"+p+"','"+ps+"')");
        } catch (SQLException e) {
            System.out.println("erreur ajout ");
            e.printStackTrace();
        }
        return a;
    }


    ResultSet selection(String req){
        ResultSet rs = null;
        try {
            rs = st.executeQuery(req);
            System.out.println("selection realiser");
        } catch (SQLException e) {
            System.out.println("selection error");
            e.printStackTrace();
        }
        return rs;
    }

    ResultSet remove(Profile p){
        ResultSet rs = null;
        String pnom,pprenom,ppseudo;
        pnom = p.getNom();
        pprenom = p.getPrenom();
        ppseudo = p.getPseudo();
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM profile WHERE nom = ? AND prenom = ? AND pseudo = ?");
            st.setString(1,pnom);
            st.setString(2,pprenom);
            st.setString(3,ppseudo);
            st.executeUpdate();
            System.out.println("remove realiser");
        } catch (SQLException e) {
            System.out.println("remove error");
            e.printStackTrace();
        }
        return rs;
    }

    ResultSet removeall(){
        ResultSet rs = null;
        try {
            rs = st.executeQuery("TRUNCATE TABLE `profile`;");
            System.out.println("selection realiser");
        } catch (SQLException e) {
            System.out.println("remove all error");
            e.printStackTrace();
        }
        return rs;
    }



    void affiche(ResultSet rs) throws SQLException{
         int nblig=0;
         ResultSetMetaData rsmd = rs.getMetaData();
         int nbcol = rsmd.getColumnCount();
         String[] title = new String[nbcol];
         for (int i=0 ; i<title.length;i++)
             title[i]=rsmd.getCatalogName(i+1);
         while (rs.next()){
             nblig++;
             for (int i=0;i<nbcol;i++)
                 System.out.println(rs.getObject(i+1));
            // System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
         }
    }

   /* public static void main(String[] dff) throws SQLException {
        MyConnection c = new MyConnection();
        c.connect();
        c.ajout("wiem","sghaier","wiiii");
        c.affiche(c.selection("select * from profile;"));
    }*/

}
