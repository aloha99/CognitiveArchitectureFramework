package framework;

import java.util.ArrayList;
import java.util.HashMap;

import datastructures.Concept;

public interface SimaAgent {
	public void init();
	public void startCycle();
	public void setDriveInput(HashMap<String, Double> drives);
	public void setBodyPerceptionInput(HashMap<String, Double> bodyPerception);
	public void setPerceptionInput(ArrayList<Concept> perception);
	public String getAction();
	public void killMind();
}
