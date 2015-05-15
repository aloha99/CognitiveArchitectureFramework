package datastructures;

public class Link {
	private final ConceptImpl rootElement;
	private final ConceptImpl leafElement;
	
	public Link(ConceptImpl rootElement, ConceptImpl leafElement) {
		super();
		this.rootElement = rootElement;
		this.leafElement = leafElement;
	}

	public ConceptImpl getRootElement() {
		return rootElement;
	}

	public ConceptImpl getLeafElement() {
		return leafElement;
	}
}
