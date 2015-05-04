package datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Concept {
	private static long conceptCount;
	
	private final String emptyString = "";
	private final String name;
	private final String id;
	private final HashMap<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();
	private final ArrayList<Link> links = new ArrayList<Link>();
	private final ArrayList<Concept> subConcepts = new ArrayList<Concept>();
	private Concept superConcept = null;
	
	public Concept(String name) {
		this.name = name;
		conceptCount++;
		this.id = this.name + String.valueOf(conceptCount);
		
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param concept
	 */
	public Concept(Concept concept) {
		this.id = concept.id;
		this.name = concept.name;
		this.values.putAll(concept.values);
		
		//Java 1.8 parallelized copying of concepts
		this.subConcepts.forEach((Concept subconcept)->this.subConcepts.add(new Concept(subconcept)));
		if (concept.superConceptExist()==true) {
			this.superConcept = new Concept(concept.superConcept);
		}
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
	
	public String getDefaultValue() {
		return this.getValue(name);
	}
	
	public void setDefaultValue(String defaultValue) {
		this.addValue(name, defaultValue);
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

	public String getId() {
		return id;
	}

	public List<Concept> getSubConcepts() {
		return Collections.unmodifiableList(subConcepts);
	}
	
	public Concept getSubConcept(String id) {
		Concept result = null;
		
		for (Concept subConcept : this.subConcepts) {
			if (subConcept.getId().equals(id)) {
				result = subConcept;
			}
		}
		
		return result;
	}
	
	public void addSubConcept(Concept concept) {
		this.subConcepts.add(concept);
	}

	public Concept getSuperConcept() {
		return superConcept;
	}

	public void setSuperConcept(Concept superConcept) {
		this.superConcept = superConcept;
	}
	
	public boolean superConceptExist() {
		boolean result = false;
		
		if (this.superConcept!=null) {
			result=true;
		}
		
		return result;
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj.getClass() == this.getClass()) {
			String objectId = ((Concept)obj).id;
			if (this.id.equals(objectId)==true) {
				result = true;
			}
		}
		
		return result;
		
	}
	
	public static ConceptBuilder newConcept(String name) {
		return new ConceptBuilder(name);
	}
	
	public interface Build {
		Build newValue(String name, String value);
		Build newDefaultValue(String value);
		Build addSubconcept(Concept concept);
		Build setSuperconcept(Concept concept);
		Concept build();
	}
	
	public String getName() {
		return name;
	}
	
	public static class ConceptBuilder implements Build {
		private Concept instance;
		
		public ConceptBuilder(String name) {
			this.instance = new Concept(name);
		}
		
		@Override
		public Build newValue(String name, String value) {
			this.instance.setValue(name, value);
			return this;
		}

		@Override
		public Build addSubconcept(Concept concept) {
			this.instance.addSubConcept(concept);
			return this;
		}
		
		@Override
		public Build setSuperconcept(Concept concept) {
			this.instance.setSuperConcept(concept);
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
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		//builder.append(name);
		//builder.append(", id=");
		builder.append(id);
		if (values.isEmpty()==false) {
			builder.append(", values=");
			builder.append(values);
		}
		
		if (subConcepts.isEmpty()==false) {
			builder.append(", subConcepts=");
			for (Concept concept :  this.subConcepts) {
				builder.append(concept.id + ", ");
			}
		}
		
		if (this.superConceptExist()==true) {
			builder.append(", super=");
			builder.append(superConcept);
		}
		
		return builder.toString();
	}
	
}
