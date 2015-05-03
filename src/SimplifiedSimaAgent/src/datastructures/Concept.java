package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Concept {
	private final String emptyString = "";
	private final HashMap<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();
	private final ArrayList<Link> links = new ArrayList<Link>();
	
	public Concept() {
		
	}
	
	public List<String> getValues(String type) {
		List<String> result = new ArrayList<String>();
		if (this.values.containsKey(type)==true && this.values.get(type).isEmpty()==false) {
			result = Collections.unmodifiableList(this.values.get(type));
		}
		
		return result;
	}
	
	public String getValue(String type) {
		String result = this.emptyString;
		
		List<String> list = this.getValues(type);
		if (list.isEmpty()==false) {
			result = list.get(0);
		}
		
		return result;
	}
	
	public void setValue(String type, String value) {
		this.values.put(type, new ArrayList<String>(Arrays.asList(value)));
	}
	
	public void addValue(String type, String value) {
		if (this.values.containsKey(type)==false) {
			this.setValue(type, value);
		} else {
			this.values.get(type).add(value);
		}
	}
	
	public void removeValue(String type) {
		this.values.remove(type);
	}
}
