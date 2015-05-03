package framework;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DatapackageImpl implements Datapackage {

	private final Map<String, String> data = new HashMap<String, String>();
	
	private DatapackageImpl() {
		
	}
	
	private DatapackageImpl(Datapackage data) {
		this.setContent(data.getViewOfAllData());
	}
	
	@Override
	public String getContent(String address) {
		return this.data.get(address);
	}
	
	public static Datapackage newDatapackage(String address, String content) {
		Datapackage result = new DatapackageImpl();
		result.setContent(address, content);
		return result;
	}
	
	public static Datapackage newDatapackage(Datapackage data) {
		return new DatapackageImpl(data);
	}
	
	public static Datapackage newDatapackage() {
		return new DatapackageImpl();
	}

	@Override
	public void setContent(String address, String content) {
		this.data.put(address, content);
	}

	@Override
	public Map<String, String> getViewOfAllData() {
		return Collections.unmodifiableMap(this.data);
	}

	@Override
	public void setContent(Map<String, String> content) {
		this.data.putAll(content);
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("data=");
		builder.append(data);
		return builder.toString();
	}

	@Override
	public void clear() {
		this.data.clear();
	}

}
