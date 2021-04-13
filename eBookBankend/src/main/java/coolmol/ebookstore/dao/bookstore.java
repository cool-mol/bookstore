package coolmol.ebookstore.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface bookstore extends Remote {
    List<String> getbook(String description) throws RemoteException;
}

