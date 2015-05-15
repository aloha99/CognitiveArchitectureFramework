package datastructures;

import java.util.Map;

public interface Datapackage {

	/**
	 * Get a concept that has explicitely an address in the datapackage
	 * 
	 * @param address
	 * @return
	 */
	public Concept get(String address);
	
	
	/**
	 * Get an unmodifiable view of the concepts, which are accessible by addresses.
	 * 
	 * @return
	 */
	public Map<String, Concept> getViewOfAllConcepts();

	/**
	 * Create a new concept by address, where a concept is generated from the string.
	 * 
	 * @param address
	 * @param content
	 */
	public void setContent(String address, String content);
	
	
	/**
	 * Set a map of concepts with addresses.
	 * 
	 * @param content
	 */
	public void setContent(Map<String, Concept> content);
	
	
	/**
	 * Set a concept, where the address is the concept name
	 * 
	 * @param content
	 */
	public void setContent(Concept content);
	
	/**
	 * Get a concept by its ID
	 * 
	 * @param id
	 * @return
	 */
	public Concept getConceptByID(String id);
	
	/**
	 * Set a concept in the data package with the concept ID as key. Hereby, no address is set. 
	 * This is used for associated concepts like subconcepts
	 * 
	 * @param concept
	 */
	public void setConceptByID(Concept concept);
	
	
	/**
	 * Get an unmodifiable view of all concepts that are registered by their IDs.
	 * 
	 * 
	 * @return
	 */
	public Map<String, Concept> getViewOfAllConceptsByID();

	/**
	 * Clear the whole datapackage from all concepts
	 */
	public void clear();
}
