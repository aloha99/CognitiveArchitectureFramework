package framework;

import datastructures.Datapackage;

public interface ModuleListener {
	public String getModuleId();
	public void update(Datapackage data);
}
