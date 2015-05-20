package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConceptImpl implements Concept {
	
	/**
	 * Nullobject pattern, in order not to get any nullpointer errors
	 */
	private final static Concept nullConcept = ConceptImpl.newConcept("null").build();
	/**
	 * Counter for generating concept ids
	 */
	private static long conceptCount;
	
	private final static String SUBCONCEPTPREDICATE  = "hasSubConcept";
	private final static String SUPERCONCEPTPREDICATE = "hasSuperConcept";
	
	private final String emptyString = "";
	private final String name;
	private final String id;
	private final Map<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();
	//private final Map<String, Double> weights = new HashMap<String, Double>();
	private final Map<String, ArrayList<String>> associatedConcepts = new HashMap<String, ArrayList<String>>();
	//private final ArrayList<ConceptImpl> subConcepts = new ArrayList<ConceptImpl>();
	//private ConceptImpl superConcept = null;
	
	private ConceptImpl(String name) {
		this.name = name;
		conceptCount++;
		this.id = this.name + String.valueOf(conceptCount);
		
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param concept
	 */
	private ConceptImpl(Concept concept) {
		this.id = concept.getId();
		this.name = concept.getName();
		this.values.putAll(concept.getValueMap());
		this.associatedConcepts.putAll(concept.getAssociatedConceptsMap());
		//Java 1.8 parallelized copying of concepts
		//concept.subConcepts.forEach((ConceptImpl subconcept)->this.subConcepts.add(new ConceptImpl(subconcept)));
		
		
//		if (concept.superConceptExist()==true) {
//			this.superConcept = new ConceptImpl(concept.superConcept);
//		}
	}
	
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public List<String> getValues(String type) {
		List<String> result = new ArrayList<String>();
		if (this.getValues().containsKey(type)==true && this.getValues().get(type).isEmpty()==false) {
			result = Collections.unmodifiableList(this.getValues().get(type));
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
	
	public String getDefaultValue() {
		return this.getValue(name);
	}
	
	public void setDefaultValue(String defaultValue) {
		this.addValue(name, defaultValue);
	}
	
	public void setValue(String type, String value) {
		this.getValues().put(type, new ArrayList<String>(Arrays.asList(value)));
	}
	
	public void addValue(String type, String value) {
		if (this.getValues().containsKey(type)==false) {
			this.setValue(type, value);
		} else {
			this.getValues().get(type).add(value);
		}
	}
	
	public void removeValue(String type) {
		this.getValues().remove(type);
	}

	private Map<String, ArrayList<String>> getValues() {
		return values;
	}

	@Override
	public Concept getAssociatedConcept(String predicate, Datapackage container) {
		String id = "";
		
		//Get all ids of sub concepts
		List<String> list = this.getValues(predicate);
		if (list.isEmpty()==false) {
			id = list.get(0);
		}
		
		//Get those ids from the container if they can be found
		//JAVA 1.8. For each id in the list, get their correpsonding concepts
		Concept result = container.getConceptByID(id);
		
		return result;
	}
	
	
	private void setAssociatedConceptID(String predicate, String id) {
		this.getAssociatedConcepts().put(predicate, new ArrayList<String>(Arrays.asList(id)));
	}
	
	private void addAssociatedConceptID(String predicate, String id) {
		if (this.getAssociatedConcepts().containsKey(predicate)==false) {
			this.setAssociatedConceptID(predicate, id);
		} else {
			this.getAssociatedConcepts().get(predicate).add(id);
		}
	}
	
	private void removeAssociatedConceptID(String id) {
		this.getAssociatedConcepts().remove(id);
	}

	@Override
	public List<Concept> getAssociatedConcepts(String predicate, Datapackage container) {
		List<Concept> result = new ArrayList<Concept>();
		
		//Get all ids of sub concepts
		List<String> subConceptIDs = this.getAssociatedConcepts().get(predicate);
		
		//Get those ids from the container if they can be found
		//JAVA 1.8. For each id in the list, get their correpsonding concepts
		subConceptIDs.forEach((String id)->result.add(container.getConceptByID(id)));
		
		return result;
	}

	@Override
	public void setAssociatedConcept(String predicate, Concept concept, Datapackage container) {
		this.getAssociatedConcepts().put(predicate, new ArrayList<String>(Arrays.asList(concept.getId())));
		container.setConceptByID(concept);
	}

	@Override
	public void setAssociatedConcept(String predicate, Concept concept, double weight, Datapackage container) {
		//TODO Implement
	}
	
	@Override
	public void addAssociatedConcept(String predicate, Concept concept, Datapackage container) throws Exception {
		addAssociatedConcept(predicate, concept, 1.0, container);
		
	}

	@Override
	public void addAssociatedConcept(String predicate, Concept concept, double weight, Datapackage container) throws Exception {
		if (this.getAssociatedConcepts().containsKey(predicate)==false) {
			this.setAssociatedConcept(predicate, concept, container);
		} else {
			this.getAssociatedConcepts().get(predicate).add(concept.getId());
			container.setConceptByID(concept);
		}
		
		//Add weight
		if (weight!=1.0) {
			//this.getWeights().put(concept.getId(), weight);
		} else if (weight==0.0) {
			throw new Exception("Cannot add a weight that is 0.0 because it is then no association");
		}
		
	}

	private Map<String, ArrayList<String>> getAssociatedConcepts() {
		return associatedConcepts;
	}

	public List<Concept> getSubConcepts(Datapackage container) {
		return this.getAssociatedConcepts(SUBCONCEPTPREDICATE, container);
	}

	public Concept getSubConcept(String name, Datapackage container) {
		Concept result = ConceptImpl.nullConcept();
		
		List<Concept> subconcepts = this.getSubConcepts(container);
		
		for (Concept subConcept : subconcepts) {
			if (subConcept.getName().equals(name)) {
				result = subConcept;
				break;
			}
		}
		
		return result;
	}

	public void addSubConcept(Concept concept, Datapackage container) {
		//Add id
		this.addAssociatedConceptID(SUBCONCEPTPREDICATE, concept.getId());
		
		//Add concept to container
		container.setConceptByID(concept);
	}

	public Concept getSuperConcept(Datapackage container) {
		//Get id and concept
		return this.getAssociatedConcept(SUPERCONCEPTPREDICATE, container);
	}

	public void setSuperConcept(Concept superConcept, Datapackage container) {
		this.setAssociatedConcept(SUPERCONCEPTPREDICATE, superConcept, container);
	}

	public boolean superConceptExist() {
		boolean result = false;
		
		if (this.getAssociatedConcepts().containsKey(SUPERCONCEPTPREDICATE)==true) {
			result=true;
		}
		
		return result;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if (obj.getClass() == this.getClass()) {
			String objectId = ((ConceptImpl)obj).id;
			if (this.id.equals(objectId)==true) {
				result = true;
			}
		}
		
		return result;
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		//builder.append(name);
		//builder.append(", id=");
		builder.append(name);
		if (this.getValues().isEmpty()==false) {
			builder.append(", values=");
			builder.append(values);
		}
		
		if (this.getAssociatedConcepts().containsKey(SUBCONCEPTPREDICATE)==true) {
			builder.append(", subConcepts=");
			this.getAssociatedConcepts().get(ConceptImpl.SUBCONCEPTPREDICATE).forEach((String id)->builder.append(id + ", "));
//			for (String id :  this.getAssociatedConcepts().get(ConceptImpl.SUBCONCEPTPREDICATE)) {
//				builder.append(id + ", ");
//			}
		}
		
		if (this.superConceptExist()==true) {
			builder.append(", super=");
			builder.append(this.getAssociatedConcepts().get(SUPERCONCEPTPREDICATE));
		}
		
		return builder.toString();
	}
	
	public static Concept nullConcept() {
		return ConceptImpl.nullConcept;
	}

	public static ConceptBuilder newConcept(String name) {
		return new ConceptBuilder(name);
	}

	public static ConceptBuilder newConcept(Concept concept) {
		return new ConceptBuilder(concept);
	}

	public static ConceptBuilder newConcept(Concept concept, String name) {
		return new ConceptBuilder(concept, name);
	}

	public interface Build {
		Build newValue(String name, String value);
		Build newDefaultValue(String value);
		Build addSubconcept(Concept concept, Datapackage datapackage);
		Build setSuperconcept(Concept concept, Datapackage datapackage);
		Build addAssociatedConcept(String predicate, Concept concept, Datapackage datapackage) throws Exception;
		Concept build();
	}
	
	public static class ConceptBuilder implements Build {
		private ConceptImpl instance;
		
		public ConceptBuilder(String name) {
			this.instance = new ConceptImpl(name);
		}
		
		public ConceptBuilder(Concept concept) {
			this.instance = new ConceptImpl(concept);
		}
		
		public ConceptBuilder(Concept concept, String name) {
			ConceptImpl copy = new ConceptImpl(concept);
			this.instance = new ConceptImpl(name);
			
			this.instance.values.putAll(copy.values);
			this.instance.associatedConcepts.putAll(copy.associatedConcepts);
			
			//Java 1.8 parallelized copying of concepts
			//copy.subConcepts.forEach((String subconcept)->this.instance.subConcepts.add(new ConceptImpl(subconcept)));
			
			//if (concept.superConceptExist()==true) {
			//	this.instance.superConcept = new ConceptImpl(copy.superConcept);
			//}
			
		}
		
		@Override
		public Build newValue(String name, String value) {
			this.instance.setValue(name, value);
			return this;
		}

		@Override
		public Build addSubconcept(Concept concept, Datapackage datapackage) {
			this.instance.addSubConcept(concept, datapackage);
			return this;
		}
		
		@Override
		public Build setSuperconcept(Concept concept, Datapackage datapackage) {
			this.instance.setSuperConcept(concept, datapackage);
			return this;
		}

		@Override
		public Concept build() {
			return this.instance;
		}

		@Override
		public Build newDefaultValue(String value) {
			this.instance.setDefaultValue(value);
			return this;
		}

		@Override
		public Build addAssociatedConcept(String predicate, Concept concept, Datapackage datapackage) throws Exception {
			this.instance.addAssociatedConcept(predicate, concept, datapackage);
			return this;
		}
		
	}

	@Override
	public Map<String, ArrayList<String>> getValueMap() {
		return Collections.unmodifiableMap(this.getValues());
	}

	@Override
	public Map<String, ArrayList<String>> getAssociatedConceptsMap() {
		return Collections.unmodifiableMap(this.getAssociatedConcepts());
	}
	
}
