package framework;

import datastructures.Datapackage;

public interface AgentMain {
	public void init();
	public void startCycle();
	/**
	 * @param drives: id and intensity for [0,1]
	 */
	public void setDriveInput(Datapackage drives);
	public void setBodyPerceptionInput(Datapackage bodyPerception);
	public void setPerceptionInput(Datapackage perception);
	public String getAction();
	public String getActionModuleOutput(String key);
	public void killMind();
}
