import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;


public class gameMechanic {

    public static int rand;
    public static String stateInsert;
    public static String stateUpdate;
    private static  User user;

    gameMechanic()throws SQLException{

        getUser();

    }
static  boolean testQuery(String query,String param)throws SQLException {
    workStatement workStatement = new workStatement();

    ResultSet resultSet = workStatement.getResultSet("SELECT * FROM names where names='" + param + "'");
    if (!resultSet.next()) {
        System.out.println("Пользователь не найден. Продолжить поиск?");
        return false;
    } else {
        return true;
    }
}

  static User getUser() throws SQLException{
      Scanner scanner=new Scanner(System.in);
      System.out.println("input excising name user:");
      String name="";
      String query="";
      do{
          name=scanner.next();
          query="select * from names where names='"+name+"'";
      }while (! testQuery(query,name));

      try {
          workStatement workStatement = new workStatement();
          ResultSet  resultSet = workStatement.getResultSet("SELECT * FROM names where names='"+name+"'");

              resultSet.next();  //без этой херни не работает
              user=new User();
              user.setId(resultSet.getInt("idnames"));
              user.setNames(resultSet.getString("names"));
              Frontend.setName(user.getNames());
              System.out.println(name + " input namber:");
              user.setNumber(scanner.nextInt());              //(resultSet.getInt("numbers"));
              Frontend.setNumber(user.getNumber());
              user.setWin(resultSet.getInt("win"));
              user.setFall(resultSet.getInt("fall"));

         // System.out.println("before:"+user);
      } catch (SQLException e){
          e.printStackTrace();
      }
      System.out.println(user);
      startGame(user);
      return user;
  }
  static   String startGame(User user){
        Random random=new Random();
      rand=random.nextInt(10);

        int win=user.getWin();
        int fall=user.getFall();
        int number=0;
        String queryInsert="";
        String queryUpdate="";
        number=user.getNumber();

        if (user.getNumber()==rand){
            win++;
            System.out.println("winn");
        }
        else
        {
            fall++;
            System.out.println("fall");
        }
        queryInsert="INSERT INTO names (names, number, win, fall) VALUES ('"+user.getNames()
                +"', '"
                +number
                +"', '"
                +win
                +"', '"
                +fall+"')";
       queryUpdate="update names set number='"+number+"', win='"+win+"',fall='"+fall+"' where names='"+user.getNames()+"'";
      //System.out.println("all right:"+queryInsert);
        stateInsert=queryInsert;
        stateUpdate =queryUpdate;
        return queryInsert;//хз зачем, но пусть пока возвращает
    }


}