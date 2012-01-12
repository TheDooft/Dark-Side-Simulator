package dss.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MappedList<T,U> implements Iterable<U>{
	
	List<U> list = new ArrayList<U>();
	Map<T,U> map = new HashMap<T,U>();
	
	
	public void add(T key, U value) {
		list.add(value);
		map.put(key, value);
	}


	public List<U> getList() {
		return list;
	}


	public U get(String key) {
		return map.get(key);
	}


	@Override
	public Iterator<U> iterator() {
		return list.iterator();
	}
}
