package framework;

import java.util.Map;

public interface Datapackage {
	public String get(String address);
	public void setContent(String address, String content);
	public void setContent(Map<String, String> content);
	public Map<String, String> getViewOfAllData();
	public void clear();
}
