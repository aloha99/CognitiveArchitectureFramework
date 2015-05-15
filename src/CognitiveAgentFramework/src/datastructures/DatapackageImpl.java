package datastructures;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DatapackageImpl implements Datapackage {

	/**
	 * Link datapackage address with a concept
	 */
	private final Map<String, Concept> data = new HashMap<String, Concept>();
	/**
	 * Link concept id with concept. It is used to get connected concepts for a certain concept
	 */
	private final Map<String, Concept> availableConcepts = new HashMap<String, Concept>();
	
	private DatapackageImpl() {
		
	}
	
	private DatapackageImpl(Datapackage data) {
		this.setContent(data.getViewOfAllConcepts());
	}
	
	public static Datapackage newDatapackage() {
		return new DatapackageImpl();
	}

	public static Datapackage newDatapackage(String address, String content) {
		Datapackage result = new DatapackageImpl();
		result.setContent(address, content);
		return result;
	}
	
	public static Datapackage newDatapackage(Datapackage data) {
		return new DatapackageImpl(data);
	}
	
	public static Datapackage newDatapackage(Map<String, Concept> data) {
		Datapackage dp = new DatapackageImpl();
		dp.setContent(data);
		return dp;
	}

	@Override
	public Concept get(String address) {
		Concept result = ConceptImpl.nullConcept();
		
		if (this.data.containsKey(address)==true) {
			result = this.data.get(address);
		}
		
		return result;
	}

	@Override
	public Map<String, Concept> getViewOfAllConcepts() {
		return Collections.unmodifiableMap(this.data);
	}

	@Override
	public void setContent(String address, String content) {
		this.data.put(address, ConceptImpl.newConcept(address).newValue(address, content).build());
		this.availableConcepts.put(data.get(address).getId(), data.get(address));
	}

	@Override
	public void setContent(Map<String, Concept> content) {
		this.data.putAll(content);
		this.setIdsForContent(content);
	}
	
	@Override
	public void setContent(Concept concept) {
		this.data.put(concept.getName(), concept);
		this.availableConcepts.put(concept.getId(), concept);
		
	}
	
	@Override
	public Concept getConceptByID(String id) {
		Concept result = ConceptImpl.nullConcept();
		
		if (this.availableConcepts.containsKey(id)==true) {
			result = this.availableConcepts.get(id);
		}
		
		return result;
	}

	@Override
	public void setConceptByID(Concept concept) {
		this.availableConcepts.put(concept.getId(), concept);
	}

	@Override
	public Map<String, Concept> getViewOfAllConceptsByID() {
		return Collections.unmodifiableMap(this.availableConcepts);
	}

	@Override
	public void clear() {
		this.data.clear();
		this.availableConcepts.clear();
	}

	private void setIdsForContent(Map<String, Concept> content) {
		//Put all added concepts in the ID-Map but with Ids as keys and not addresses
		content.values().forEach((Concept concept)->this.availableConcepts.put(concept.getId(), concept));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("data=");
		builder.append(this.data);
		builder.append(", concepts=");
		this.availableConcepts.values().forEach((Concept concept)->builder.append(concept.getName()+ ", "));
		return builder.toString();
	}

}
