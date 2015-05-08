package datastructures;

import java.util.Map;

public interface Datapackage {
	//public String get(String address);
	public Concept get(String address);
	public void setContent(String address, String content);
	public void setContent(Map<String, Concept> content);
	public void setContent(Concept content);
	public Map<String, Concept> getViewOfAllData();
	public void clear();
}
