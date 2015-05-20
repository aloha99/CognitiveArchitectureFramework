package datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Concept {
	//Metadata
	
	/**
	 * Get concept ID that is unique for a concept
	 * 
	 * @return
	 */
	public String getId();
	
	/**
	 * Get the name of a concept that can be present multiple times
	 * 
	 * @return
	 */
	public String getName();
	
	//String Values
	/**
	 * Get all values
	 * 
	 * @return
	 */
	public Map<String, ArrayList<String>> getValueMap();
	/**
	 * Get all values of the concept
	 * 
	 * @param type
	 * @return
	 */
	public List<String> getValues(String type);
	
	/**
	 * Get a value of a concept
	 * 
	 * @param type
	 * @return
	 */
	public String getValue(String type);
	
	
	/**
	 * Set a value and replace an existing one if there is one
	 * 
	 * @param type
	 * @param value
	 */
	public void setValue(String type, String value);
	
	/**
	 * Set or add a value to an existing value
	 * 
	 * @param type
	 * @param value
	 */
	public void addValue(String type, String value);
	
	/**
	 * Remove a value
	 * 
	 * @param type
	 */
	public void removeValue(String type);
	
	/**
	 * Get the default value. The key for this value is the name of the concept. In that way, simple 
	 * string can be transformed into concepts
	 * 
	 * @return
	 */
	public String getDefaultValue();
	
	/**
	 * Set the default value. The key for this value is the name of the concept. In that way, simple 
	 * string can be transformed into concepts
	 * 
	 * @return
	 */
	public void setDefaultValue(String defaultValue);
	
	//Sub concepts
	public List<Concept> getSubConcepts(Datapackage container);
	public Concept getSubConcept(String name, Datapackage container);
	public void addSubConcept(Concept concept, Datapackage container);
	
	//Super concepts
	public void setSuperConcept(Concept superConcept, Datapackage container);
	public Concept getSuperConcept(Datapackage container);
	public boolean superConceptExist();
	
	//Associated concepts
	public Map<String, ArrayList<String>> getAssociatedConceptsMap();
	public Concept getAssociatedConcept(String predicate, Datapackage container);
	public List<Concept> getAssociatedConcepts(String predicate, Datapackage container);
	public void setAssociatedConcept(String predicate, Concept concept, Datapackage container);
	public void setAssociatedConcept(String predicate, Concept concept, double weight, Datapackage container);
	public void addAssociatedConcept(String predicate, Concept concept, Datapackage container) throws Exception;
	public void addAssociatedConcept(String predicate, Concept concept, double weight, Datapackage container) throws Exception;
	
}
