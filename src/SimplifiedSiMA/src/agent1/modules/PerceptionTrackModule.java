package agent1.modules;

import framework.ModuleImpl;

public class PerceptionTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		log.debug("Start perception track");
		int inValue = Integer.valueOf(this.getInputData().getViewOfAllData().get("testvalue").getDefaultValue());
		int outValue = inValue+1;
		this.getOutputData().setContent("testvalue2", String.valueOf(outValue));
		
		log.debug("Received invalue={}. Set outvalue={}", inValue, outValue);
		
	}

}
