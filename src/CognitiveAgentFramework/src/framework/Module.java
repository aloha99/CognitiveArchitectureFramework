package framework;

import datastructures.Datapackage;

public interface Module extends ModuleListener {
	public void init(String id);
	public String getModuleId();
	public void runModule() throws Exception;
	public void registerModule(ModuleListener moduleListener);
	public void deregisterModule(ModuleListener moduleListener);
	public void setInputData(Datapackage data);
	public Datapackage getOutputData();
	
}
