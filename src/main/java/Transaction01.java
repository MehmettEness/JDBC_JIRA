/*
Transaction: DB deki parçalanamaz(atomik) en küçük işlem
birden fazla işlem için tek bir Transaction oluşturabiliriz.
Bu işlemlerden en az bir tanesi başarısız olursa ROLLBACK ile diğer işlemleri de iptal edebiliriz.
BNu işlemlerin tamamı başarılı olursa değişiklikleri DB de kalıcı hale getirmek için Transaction COMMIT edilir.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction01 {
    public static void main(String[] args) throws SQLException {

        //olmaması gereken senaryo



//       Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "techpro" ,"password");

//       //hesap numarası : 1234 olan müşteri hesap numarası:5678 olan müşteri hesabına 1000 para transferi gerçekleştirsin.

//       String sql="update hesaplar set bakiye =bakiye+? where hesap_no=?";
//       PreparedStatement prst = con.prepareStatement(sql);

//       //para transferini yapan hesap
//       prst.setInt(1,-1000);
//       prst.setInt(2,1234);

//       prst.executeUpdate();

//       //sistemsel hata oluştu...
//       if (true){
//           throw new Exception(); // burada uygulama durdu
//           //para eksildi ama diğer hesaba geççmedii
//       }

//       //para transferini alan hesap
//       prst.setInt(1,1000);
//       prst.setInt(2,5678);

//       prst.executeUpdate();


        //olması gerekn senaryooo.....
        //tek bir transaction içine alınmalı(veri tutarlılığı içinn )

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "techpro" ,"password");

        con.setAutoCommit(false); //Transaction yönetimini false yaparak kapatıyoruz. Yönetimi bizde ele alalımm



       //hesap numarası : 1234 olan müşteri hesap numarası:5678 olan müşteri hesabına 1000 para transferi gerçekleştirsin.
        PreparedStatement prst=null;
  try{
      String sql="update hesaplar set bakiye =bakiye+? where hesap_no=?";
     prst = con.prepareStatement(sql);


      //para transferini yapan hesap
      prst.setInt(1,-1000);
      prst.setInt(2,1234);

      prst.executeUpdate();

      //sistemsel hata oluştu...
      if (true){
          throw new Exception(); // burada uygulama durdu
          //para eksildi ama diğer hesaba geççmedii
      }

      //para transferini alan hesap
      prst.setInt(1,1000);
      prst.setInt(2,5678);

      prst.executeUpdate();

      con.commit();// yapılan değişiklikleri kayıtlı hale getir.
      prst.close();
      con.close();


  }catch (Exception e){

      con.rollback();
      System.out.println(e.getMessage());

      prst.close();
      con.close();

  }




    }
}
