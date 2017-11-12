package Servants;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import Interface.CRUD;


public abstract class AbstractCRUD<T extends Serializable>  implements CRUD<T>{
	protected List<T> t;

	public AbstractCRUD() throws RemoteException{
		//gets from file
	}

	@Override
	public void Create(T t) {
		this.t.add(t);
	}

	@Override
	public List<T> Retrieve(Collection<T> collectionObject, Function<T, String> searchPropertyAccessor, String searchText) {
		List<T> result = collectionObject.stream()
		        .filter(item -> Objects.equals(searchPropertyAccessor.apply(item), searchText))
		        .collect(Collectors.toList());
		return result;
	}
	
	@Override
	public List<T> Retrieve(Collection<T> collectionObject, Function<T, Integer> searchPropertyAccessor, Integer searchInt) {
		List<T> result = collectionObject.stream()
		        .filter(item -> Objects.equals(searchPropertyAccessor.apply(item), searchInt))
		        .collect(Collectors.toList());
		return result;
	}

	@Override
	public List<T> Upsert(T t) {
		this.t.add(t);
		return this.t;
	}
	/*
	 * (non-Javadoc)
	 * @see CRUD#Delete(java.lang.Object)
	 */
	@Override
	public void Delete(T t) {
		this.t.size();
	}

}