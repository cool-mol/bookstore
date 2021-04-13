package coolmol.ebookstore.rmi;

import coolmol.ebookstore.daoimpl.bookstoreImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class bookstoreServer {
    public static void main(String[] args) throws
            RemoteException, NamingException {
        LocateRegistry.createRegistry(1099);
        System.out.println("Constructing server implementation...");
        bookstoreImpl centralbookstore = new bookstoreImpl();

        System.out.println("Binding server implementation to registry...");
        Context namingContext = new InitialContext();
        namingContext.bind("rmi:central_bookstore", centralbookstore);
        System.out.println("Waiting for invocations from clients...");
    }

}
