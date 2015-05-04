package framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import datastructures.Concept;

public interface SimaAgent {
	public void init();
	public void startCycle();
	public void setDriveInput(HashMap<String, Double> drives);
	public void setBodyPerceptionInput(HashMap<String, Double> bodyPerception);
	public void setPerceptionInput(ArrayList<Concept> perception);
	public String getAction();
	public void killMind();
	
	public void setDriveModuleInput(Map<String, String> map);
	public void setPerceptionInput(Map<String, String> map);
	public String getActionModuleOutput(String key);
}
