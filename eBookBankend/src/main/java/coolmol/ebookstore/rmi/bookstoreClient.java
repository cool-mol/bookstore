package coolmol.ebookstore.rmi;

import coolmol.ebookstore.dao.bookstore;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

public class bookstoreClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context namingContext = new InitialContext();

        String url = "rmi://localhost/central_bookstore";
        bookstore central_bookstore = (bookstore) namingContext.lookup(url);

        String descr = "lalala";
        List<String> des = central_bookstore.getbook(descr);
        System.out.println(des);
    }

}
