import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1-Adım:
        Class.forName("org.postgresql.Driver");//Bu adıma java 7 ile gerek kalmadı //Parantez içindeki kısmı yan taraftaki dosyalarda izlediğimiz yolu yazdık

        // 2-Adım: Database bağlantı oluşturma
     Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",  "techpro","password");

        //3-Adım:Statement oluşturma: SQL sorgularını olusturma ve DataBase (DB) ye göndermek ve çalıştırmak için kullanırız
        Statement st = connection.createStatement();

        System.out.println("Connection is succsess");

        //4-Adım: Query i yazıp çalıştırma

        //ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.

        boolean sql1 =  st.execute("CREATE TABLE IF NOT EXISTS workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");//pgAdminde yazılan sorgunun aynısını buraya yazdık

        System.out.println("sql1: " + sql1);//false verir. Geriye resultset döndürmez. Tekrar çalıştırırssk zaten workers tablosu oldugu için hata verir.

        //execute methodu DQL veya DDL komutları için kullanılır.

        //DQL (select)  için kullanılırsa: Geriye döndürülen raporlar ResultSet nesnesi alırsa TRUE döndürür. Aksi halde FALSE döndürür.

        //DDL (tablolarımızı oluşturmak , güncellemek , silmek ) için kullanılırsa : geriye FALSE döndürür.


        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.

     //   boolean sql2 =  st.execute("alter table workers add column city varchar(20)");

        // System.out.println("sql2: " + sql2);//false

        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.

        boolean sql3 = st.execute("drop table workers");
        System.out.println("sql3: " + sql3);


        //5-Adım: Bağlantıyı ve statement kapatma
        st.close();
        connection.close();





    }
}
