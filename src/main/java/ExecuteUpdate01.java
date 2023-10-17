import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "techpro" ,"password");

        Statement st = con.createStatement();

        //executeUpdate():DML için kullanılır; INSERT,UPDATE,DELETE
        //return:sorgudan etkilenene kayıt sayısını döndürür.

        //ÖRNEK1:it_persons tablosunda maaşı ortalama maaştan az olanların maaşını 5111 olarak güncelleyiniz.

 //   String query="update it_persons set salary=5111 where salary < (select avg(salary) from it_persons )";
 //      int update= st.executeUpdate(query);
 //       System.out.println("güncellenen kayıt sayısı= " + update);
//
        //maası 5111 tüm kayıtları alalımmm

        ResultSet rs1 = st.executeQuery("select * from it_persons where salary=5111");
        while (rs1.next()){
            System.out.println(rs1.getString("name")+"----"+ rs1.getDouble("salary"));
        }
        System.out.println("----------------------------------------------------------------------------------------");
        //Tüm kayıtları alalımm
        ResultSet rs2 = st.executeQuery("select * from it_persons ");
        while (rs2.next()){
            System.out.println(rs2.getString("name")+"----"+ rs2.getDouble("salary"));
        }

        System.out.println("-----------------------ORNEK 2 -------------------------------");

        //ÖRNEK2:it_persons tablosuna yeni bir kayıt ekleyiniz.
//       String query2="insert into it_persons(name,salary,prog_lang) values ('Safa', 5000.0 , 'Java')";
//       int inserted=st.executeUpdate(query2);
//       System.out.println("eklenen kayıt sayısı =" + inserted);

    //tüm kayıtları listeleyelimmm
        ResultSet rs3 = st.executeQuery("select * from it_persons ");
        while (rs3.next()){
            System.out.println(rs3.getString("name")+"----"+ rs3.getDouble("salary"));
        }


        //ÖRNEK3:it_persons tablosundan id'si 11 olanı siliniz.
      int deleted =  st.executeUpdate("delete from it_persons where id=11");
        System.out.println("silinen kayıt sayısı =" + deleted);

//tüm kayıtları görünütleyelim
        ResultSet rs4 = st.executeQuery("select * from it_persons ");
        while (rs4.next()){
            System.out.println(rs4.getString("name")+"----"+ rs4.getInt("id"));
        }




        st.close();
        con.close();




    }
}
