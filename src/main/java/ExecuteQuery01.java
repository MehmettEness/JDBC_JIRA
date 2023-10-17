import javax.xml.transform.Result;
import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {

        //1-Adım java 7 ile birlikte gerek kalmadı.
        //2-Adım Database bağlama
      Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "techpro" ,"password");

      Statement st = con.createStatement();

      System.out.println("succsess");//başarılı bir  şekşide bağlanıtıyı sağlamışmıyız kontrol etmek içinn

        //ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.

        boolean sql1 = st.execute("select country_name from countries where id between 5 and 10");

        System.out.println("sql1: " + sql1);//true bize DQL verir. Result deger döndürür.

        //kayıtları görmek için çalışıp çalışmadığını görmek için

       ResultSet resultSet = st.executeQuery("select country_name from countries where id between 5 and 10");//executeQuery resultset methodu içine alıyor
    //    resultSet.next();//bir sonraki satıra geçmeyi sağlar
    //    System.out.println("Ülke ismi: " + resultSet.getString("country_name")); //getString country_name ismini string olarak getireceği için seçtik. ifade int olsaydı getInt diyecektik

    while(resultSet.next()){ //resultSet.next() 5ila 10 arasındaki tüm isimleri yazdıracak. true devam edecek en sonunda false verdiğinde işlem bitmiş olacak

        System.out.println("Ülke ismi: " + resultSet.getString("country_name"));

    }

    //ResultSet'te geriye dönüş yoktur.

        System.out.println("----------------------------------örnek 2 ------------------------------------------");


        //ÖRNEK 2: phone_code'u 600 den büyük olan ülkelerin "phone_code" ve "country_name" bilgisini listeleyiniz.

        ResultSet rs2= st.executeQuery("select phone_code,country_name from countries where phone_code>600");

          while(rs2.next()){

              System.out.println("Tel_kodu: " + rs2.getInt("phone_code") + "--Ülke ismi: "+rs2.getString("country_name"));

          }

        System.out.println("----------------------------------örnek 3 ------------------------------------------");
        //ÖRNEK 3:it_persons tablosunda "salary" değeri en düşük salary olan developerların tüm bilgilerini gösteriniz.

          ResultSet rs3 = st.executeQuery("select * from it_persons where salary= (select min(salary) from it_persons)");
          while(rs3.next()){
              System.out.println(rs3.getInt("id")+"--" + rs3.getString("name")+"--"
                                + rs3.getDouble("salary")+"--"+ rs3.getString("prog_lang") );



          }

        System.out.println("-----------------------Ornek 4 ------------------------------------------");

        //ÖRNEK 4:Puanı bölümlerin taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz.ÖDEVVV

   //     String query4 ="Select isim , puan from ogrenciler where puan > (select avg(taban_puani)from bolumler";
   //     ResultSet rs4=st.executeQuery(query4);
//
   //     while(rs4.next()){
   //         System.out.println(rs4.getString("isim") + "--"+rs4.getInt("puan"));
   //     }
//
        ResultSet rs4=st.executeQuery("select isim, puan from ogrenciler where puan > (select avg(taban_puani) from bolumler)");

        while (rs4.next()){
            System.out.println("isim : "+ rs4.getString("isim")+"--" + "taban_puani : "+rs4.getInt("puan") );
        }

        System.out.println("-----------------------Ornek 5 ------------------------------------------");

        //ÖRNEK5:bolumler tablosunda taban puanı en yüksek 2. bölümün ismini ve puanını yazdırınız.

        //SELECT isim,taban_puani FROM bolumler ORDER BY taban_puani DESC OFSET 1 LIMIT 1

        System.out.println("------------------ÖRNEK 5------------------------");


        //ÖRNEK5:bolumler tablosunda taban puanı en yüksek 2. bölümün ismini ve puanını yazdırınız.
        String query5="SELECT bolum,taban_puani FROM bolumler WHERE taban_puani=" +
                "(SELECT MAX(taban_puani) FROM bolumler " +
                "WHERE taban_puani<(SELECT MAX(taban_puani) FROM bolumler))";
        ResultSet rs5= st.executeQuery(query5);
        while (rs5.next()){
            System.out.println(rs5.getString("bolum")+"---"+rs5.getInt("taban_puani"));
        }




        st.close(); // kaynaklarda gereksiz obje oluşmaması ve güvenlik açığı oluşmaması için işimiz bitince kapatıyoruz
        con.close();




    }

}

