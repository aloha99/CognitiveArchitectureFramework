package modules;

import framework.ModuleImpl;

public class TesterModule extends ModuleImpl {
	
	@Override
	protected void executeModuleFunction() {
		//Set input as output data
		this.getOutputData().setContent(this.getInputData().getViewOfAllData());
	}
	
	public void setValue(String address, String value) {
		this.getInputData().setContent(address, value);
	}

}
