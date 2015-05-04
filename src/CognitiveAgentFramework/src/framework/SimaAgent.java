package framework;

import java.util.HashMap;
import java.util.Map;

import datastructures.Concept;

public interface SimaAgent {
	public void init();
	public void startCycle();
	/**
	 * @param drives: id and intensity for [0,1]
	 */
	public void setDriveInput(Map<String, Concept> drives);
	public void setBodyPerceptionInput(Map<String, Concept> bodyPerception);
	public void setPerceptionInput(Map<String, Concept> perception);
	public String getAction();
	public void killMind();
	
	//public void setDriveModuleInput(Map<String, String> map);
	//public void setPerceptionInput(Map<String, String> map);
	public String getActionModuleOutput(String key);
}
