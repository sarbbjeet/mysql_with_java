import java.sql.*;
import java.util.ArrayList;

public class mydb{
    
    // database connection  
    private static Connection con(){
        try{
            String _url= StaticVariable.url; //database url 
            String dbName= StaticVariable.databaseName;  //mysql database name
            String userName = StaticVariable.userName;  //username used in database
            String pass= StaticVariable.dbPassword;  //password of mysql database 
            String finalUrl= _url + dbName;  //concat url and database name
            Connection _conn= DriverManager.getConnection(finalUrl, userName, pass); //database connection fun.
            System.out.println("connected with database");
            return _conn;  //return connection ref.  if successfully connected with database 
        }catch(Exception e){e.printStackTrace();};
       
       return null;     //return null, if error to connect with database 
     }


    //  create database table if not exist
    public static void createTable() {
       try{
            PreparedStatement create= con().prepareStatement("CREATE TABLE IF NOT EXISTS todo(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(33) NOT NULL , age INT NOT NULL)");
            create.executeUpdate();  
       }
       catch(Exception e){e.printStackTrace();}
       finally{
           System.out.println("table created ..");
       }
    }


// insert data to table
public static void insertData(String name, int age){
    try{
         String query="INSERT INTO todo(name,age) VALUES('"+name+"', '"+age+"')";
         PreparedStatement insert = con().prepareStatement(query);
         insert.executeUpdate();   
    }
    catch(Exception e) { e.printStackTrace();}
    finally{
        System.out.println("inserted data.");
    }    
}

// read table data simple example . ..   
public static void readTable(){
    try{
        String query="SELECT name,age FROM todo";  //read only name and age
        PreparedStatement read= con().prepareStatement(query);
        ResultSet result=read.executeQuery();
        int i=0;
        while(result.next()){
            i++; 
            System.out.printf("row%d name=%s age=%d\n", i,result.getString("name"), result.getInt("age"));
        }

    }catch(Exception e) {e.printStackTrace();}
}

// read table data the form of arry  
public static ArrayList<studentModel> readTableArry(){
 ArrayList<studentModel> rt = new ArrayList<>();
    try{
        String query="SELECT name,age FROM todo";  //read only name and age
        PreparedStatement read= con().prepareStatement(query);
        ResultSet result=read.executeQuery();
        int i=0;
        while(result.next()){
            rt.add(i, new studentModel(result.getString("name"), result.getInt("age"))); 
            //System.out.printf("row%d name=%s age=%d\n", i,result.getString("name"), result.getInt("age"));
            i++; 
        }
        return rt; 

    }catch(Exception e) {e.printStackTrace();}

  return null;   
}

//delete by id // 
public static void deleteRowOfTable(int id){
try{
String query="DELETE from todo WHERE id='"+id+"'";
PreparedStatement delete = con().prepareStatement(query);
delete.execute();
System.out.printf("id=%d row is deleted...\n",id);
}catch(Exception e) { e.printStackTrace();}
}


//delete by name // 
public static void deleteRowOfTable(String name){
    try{
    String query="DELETE from todo WHERE name='"+name+"'";
    PreparedStatement delete = con().prepareStatement(query);
    delete.executeUpdate();
    System.out.printf("name=%s row is deleted...\n",name);
    }catch(Exception e) { e.printStackTrace();}
    }
}