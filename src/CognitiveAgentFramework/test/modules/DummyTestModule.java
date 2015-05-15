package modules;

import datastructures.Datapackage;
import framework.ModuleImpl;

public class DummyTestModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		log.debug("Input data: {}", this.getInputData());
		this.getOutputData().setContent(this.getInputData().getViewOfAllConcepts());
		log.debug("New output data: {}", this.getOutputData());
		
	}
}
