package framework;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import datastructures.Concept;

public class DatapackageImpl implements Datapackage {

	private final Map<String, Concept> data = new HashMap<String, Concept>();
	
	private DatapackageImpl() {
		
	}
	
	private DatapackageImpl(Datapackage data) {
		this.setContent(data.getViewOfAllData());
	}
	
	public static Datapackage newDatapackage(String address, String content) {
		Datapackage result = new DatapackageImpl();
		result.setContent(address, content);
		return result;
	}
	
	public static Datapackage newDatapackage(Datapackage data) {
		return new DatapackageImpl(data);
	}
	
	public static Datapackage newDatapackage() {
		return new DatapackageImpl();
	}
	
	public static Datapackage newDatapackage(Map<String, Concept> data) {
		Datapackage dp = new DatapackageImpl();
		dp.setContent(data);
		return dp;
	}

	@Override
	public void setContent(String address, String content) {
		this.data.put(address, Concept.newConcept(address).newValue(address, content).build());
	}

	@Override
	public Map<String, Concept> getViewOfAllData() {
		return Collections.unmodifiableMap(this.data);
	}

	@Override
	public void setContent(Map<String, Concept> content) {
		this.data.putAll(content);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("data=");
		builder.append(data);
		return builder.toString();
	}

	@Override
	public void clear() {
		this.data.clear();
	}

	@Override
	public Concept get(String address) {
		return this.data.get(address);
	}

	@Override
	public void setContent(Concept content) {
		this.data.put(content.getName(), content);
		
	}

}
