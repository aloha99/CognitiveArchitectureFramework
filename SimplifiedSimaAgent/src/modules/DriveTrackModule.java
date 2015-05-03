package modules;

import framework.ModuleImpl;

public class DriveTrackModule extends ModuleImpl {
	

	@Override
	protected void executeModuleFunction() {
		//Just put the input to the output
		this.getOutputData().setContent(this.getInputData().getViewOfAllData());
		
	}



}
