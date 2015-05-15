package modules;

import framework.ModuleImpl;

public class DummyPerceptionModule extends ModuleImpl {

	private String targetAddress = "";
	
	@Override
	protected void executeModuleFunction() {
		log.debug("Input={}", this.getInputData().get(this.targetAddress));
		this.getOutputData().setContent(this.getInputData().getViewOfAllConcepts());

	}
	
	public void setTargetAddress(String address) {
		this.targetAddress = address;
	}

}
