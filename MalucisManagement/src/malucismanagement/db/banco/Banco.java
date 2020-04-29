package malucismanagement.db.banco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Banco {
    
    public static Conexao con = null;

    private Banco() {} // construtor privado, ou seja, nao pode dar um new em banco, da uma segurança maior no projeto
    
    static public Conexao getCon() {
        return con;
    }
    
    static public boolean conectar() {
        
        con = new Conexao(); 
        
        return con.conectar("jdbc:postgresql://localhost/", "malucidb", "postgres" ,"postgres123");    
    }
    
    public static void realizaBackupRestauracao(String arqlote) {
        
        String linha = "";  
        String aux = "";
        Runtime r = Runtime.getRuntime(); 
        
        try {
            
            Process p = r.exec(arqlote);//("bdutil\\backup.bat");
            
            if (p != null) {
                
                InputStreamReader str = new InputStreamReader(p.getErrorStream());  
                BufferedReader reader = new BufferedReader(str);  
              
                while ((aux = reader.readLine()) != null) 
                    linha += aux + "\n";  
           }  
           JOptionPane.showMessageDialog(null,"Backup/Restore realizado com sucesso!\n" + linha);  
       } 
       catch (IOException ex) {  
           JOptionPane.showMessageDialog(null,"Erro no backup!\n"+ex.getMessage());  
       }  
    }

    public static boolean criarTabelas(String script, String BD){
        
        try {
            
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/"+BD;
            Connection con = DriverManager.getConnection(url, "postgres","postgres123");
            Statement statement = con.createStatement();
            RandomAccessFile arq=new RandomAccessFile(script, "r");
            
            while(arq.getFilePointer() < arq.length()) 
                 statement.addBatch(arq.readLine());
            statement.executeBatch();
            statement.close();
            con.close();
        }
        catch(IOException | ClassNotFoundException | SQLException e) {  

            System.out.println(e.getMessage()); 
            return false;
        }

        return true;
    }

    public static boolean criarBD(String BD){
        
        try {
            
            String url = "jdbc:postgresql://localhost/";
            Connection con = DriverManager.getConnection(url,"postgres","postgres123");
            Statement statement = con.createStatement();
            
            statement.execute("CREATE DATABASE " + BD + " WITH OWNER = postgres ENCODING = 'UTF8' "
                                    + "TABLESPACE = pg_default LC_COLLATE = 'Portuguese_Brazil.1252' "
                                    + "LC_CTYPE = 'Portuguese_Brazil.1252'  CONNECTION LIMIT = -1;");
            statement.close();
            con.close();
        }
        catch(SQLException e) {
            
            System.out.println(e.getMessage()); 
            return false;
        }
        
        return true;                
    }
}