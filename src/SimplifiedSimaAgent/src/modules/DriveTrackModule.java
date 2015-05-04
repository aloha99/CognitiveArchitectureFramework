package modules;

import framework.ModuleImpl;

public class DriveTrackModule extends ModuleImpl {
	

	@Override
	protected void executeModuleFunction() {
		//Just put the input to the output
		//this.getOutputData().setContent(this.getInputData().getViewOfAllData());
		
		log.debug("Start drive track");
		int inValue = Integer.valueOf(this.getInputData().getViewOfAllData().get("testvalue"));
		int outValue = inValue+1;
		this.getOutputData().setContent("testvalue1", String.valueOf(outValue));
		this.getOutputData().setContent("testvalue", String.valueOf(outValue));
		
		log.debug("Received invalue={}. Set outvalue={}", inValue, outValue);
	}



}
