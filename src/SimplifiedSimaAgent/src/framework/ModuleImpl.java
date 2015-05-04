package framework;

import java.util.ArrayList;

import logger.MyLogger;

import org.slf4j.Logger;

public abstract class ModuleImpl implements Module, ModuleListener {
	
	protected static final Logger log = MyLogger.getLog("Sima");
	
	private final ArrayList<ModuleListener> listeners = new ArrayList<ModuleListener>();
	private final ArrayList<Module> publisherModules = new ArrayList<Module>();
	private final Datapackage inputData = DatapackageImpl.newDatapackage();
	private final Datapackage outputData = DatapackageImpl.newDatapackage();
	
	
	private String moduleId;
	
	@Override
	public void runModule() {
		//Execute the function of the module
		this.executeModuleFunction();
		
		//Update listeners
		this.updateListeners();
		
	}
	
	public void init(String id) {
		this.moduleId = id;
		log.debug("Init module={}", this.moduleId);
	}
	
	public String getModuleId() {
		return this.moduleId;
	}
	
	protected void updateListeners() {
		//Send data to each module
		for (ModuleListener listener : this.getListeners()) {
			listener.update(DatapackageImpl.newDatapackage(this.outputData));
		}
	}
	
	protected abstract void executeModuleFunction();

	@Override
	public void registerModule(ModuleListener moduleListener) {
		this.listeners.add(moduleListener);
		log.debug("Module={} registered as listener at module={}", moduleListener.getModuleId(), this.getModuleId());
	}

	@Override
	public void deregisterModule(ModuleListener moduleListener) {
		this.listeners.remove(moduleListener);
	}

	protected ArrayList<ModuleListener> getListeners() {
		return listeners;
	}
	
	@Override
	public void setInputData(Datapackage data) {
		this.inputData.clear();
		this.inputData.setContent(data.getViewOfAllData());
		
	}

	protected Datapackage getInputData() {
		return inputData;
	}

	public Datapackage getOutputData() {
		return outputData;
	}
	
	@Override
	public void update(Datapackage data) {
		//Update data
		this.getInputData().setContent(data.getViewOfAllData());
		log.debug("Update module={} with incoming data={}", this.getModuleId(), data);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ModuleImpl [listeners=");
		builder.append(listeners);
		builder.append(", publisherModules=");
		builder.append(publisherModules);
		builder.append(", inputData=");
		builder.append(inputData);
		builder.append(", outputData=");
		builder.append(outputData);
		builder.append(", moduleId=");
		builder.append(moduleId);
		builder.append("]");
		return builder.toString();
	}

}
