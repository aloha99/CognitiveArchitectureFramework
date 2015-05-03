package datastructures;

public class Link {
	private final Concept rootElement;
	private final Concept leafElement;
	
	public Link(Concept rootElement, Concept leafElement) {
		super();
		this.rootElement = rootElement;
		this.leafElement = leafElement;
	}

	public Concept getRootElement() {
		return rootElement;
	}

	public Concept getLeafElement() {
		return leafElement;
	}
}
