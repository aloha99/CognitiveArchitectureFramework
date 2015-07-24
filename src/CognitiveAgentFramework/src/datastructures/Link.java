package datastructures;

public class Link {
	private final ChunkImpl rootElement;
	private final ChunkImpl leafElement;
	
	public Link(ChunkImpl rootElement, ChunkImpl leafElement) {
		super();
		this.rootElement = rootElement;
		this.leafElement = leafElement;
	}

	public ChunkImpl getRootElement() {
		return rootElement;
	}

	public ChunkImpl getLeafElement() {
		return leafElement;
	}
}
