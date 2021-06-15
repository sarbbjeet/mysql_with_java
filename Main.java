
public class Main{
  
    public static void main(String[] args){
    mydb.createTable();  
    mydb.insertData("rohan", 20);  //insert name and age 
    //mydb.readTable(); 
    System.out.println(mydb.readTableArry().get(2).name); 
   // mydb.deleteRowOfTable(1);  //table row data is deleted by id
    //mydb.deleteRowOfTable("singh"); //table row data is deleted by name
    }
}