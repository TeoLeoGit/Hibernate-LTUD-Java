import dao.AccountDAO;

import java.util.List;
import pojo.Account;
public class Main {

    public static void main(String[] args) {
        List<Account> rs = AccountDAO.getAllAccount();
        for (Account item : rs) {
            System.out.println("OK trc");
            System.out.println(item.getId() + ", " +  item.getUsername());
            System.out.println("OK");
        }
    }
}
