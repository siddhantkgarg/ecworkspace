package Interface;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public interface CRUD<T extends Serializable>{
	void Create(T t) throws RemoteException;
	List<T> Retrieve(Collection<T> collectionObject, Function<T, String> searchPropertyAccessor, String searchText) throws RemoteException;
	List<T> Retrieve(Collection<T> collectionObject, Function<T, Integer> searchPropertyAccessor, Integer searchText) throws RemoteException;
	List<T> Upsert(T t) throws RemoteException;
	void Delete(T t) throws RemoteException;
}
