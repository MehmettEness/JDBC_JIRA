/*
PreparedStatement Statementi extend eder (Daha gelişmiş hali)
PreparedStatement ile önceden derlenmiş tekrar tekrar kullanılabilen
parametreli sorgular oluşturup çalistirabiliriz.


 */


import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "techpro" ,"password");

        Statement st = con.createStatement();

        System.out.println("-------------------------------------ORNEK 1 ---------------------------------------");

        //ÖRNEK1:(Prepared Statement kullanarak) bolumler tablosunda Matematik bölümünün taban_puani'nı 475 olarak güncelleyiniz.

       // st.executeUpdate("update bolumler set taban_puani=475 where bolum='Matematik'"); //bu şekilde de yapabiliriz ama değişken değişirse sürekli değiştirmemiz lazım

        //sorguyu parametreli olaral oluştur.

        String query = "update bolumler set taban_puani=? where bolum=?";

        PreparedStatement prst = con.prepareStatement(query);

        //parametreleri verelim
        prst.setInt(1,475);
        prst.setString(2,"Matematik");

        //sorguyu calistiralım
        int updated = prst.executeUpdate();
        System.out.println("updated =" + updated);


        System.out.println("-------------------------------------ORNEK 2 ---------------------------------------");
        //ÖRNEK2:(Prepared Statement kullanarak) bolumler tablosunda Edebiyat bölümünün taban_puanı'nı 455 olarak güncelleyiniz.
        //sadece daha önce oluşturulan prst in parametrelerini girelim

        prst.setInt(1,455);
        prst.setString(2,"Edebiyat");
        //sorguyu calistilaım
        int updated2=prst.executeUpdate();
        System.out.println("updated2 =" + updated2);

        System.out.println("-------------------------------------ORNEK 3 ---------------------------------------");

        //ÖRNEK3:Prepared Statement kullanarak ogrenciler tablosundan Matematik bölümünde okuyanları siliniz.

        PreparedStatement prst2 = con.prepareStatement("delete from ogrenciler where bolum ilike ?");
        //parametreyi set edelim
        prst2.setString(1,"Matematik");
        //caliştilaım
        int deleted =prst2.executeUpdate();
        System.out.println("deleted =" + deleted);

        System.out.println("-------------------------------------ORNEK 4 ---------------------------------------");

        //ÖRNEK4:Prepared Statement kullanarak bolumler tablosuna
        // Yazılım Mühendisliği(id=5006,taban_puanı=475, kampus='Merkez') bölümünü ekleyiniz.

        String sql="insert into bolumler values(?,?,?,?)";
      PreparedStatement prst3 =  con.prepareStatement(sql);

      prst3.setInt(1,5006);
      prst3.setString(2,"Yazılım Mühendisliği");
      prst3.setInt(3,475);
      prst3.setString(4,"Merkez");


        System.out.println("-------------ÖDEVV---------------");
        //1-Bölüm isimlerini, kampüslerini ve
        //her bir bölümde okuyan öğrencilerin en yüksek puanlarını listeleyiniz.
        //2-it_persons tablosundan prog_lang css olanları siliniz.
        //3-it_persons tablosundan prog_lang java olanları siliniz.

        st.close();
        prst.close();
        con.close();





    }
}
