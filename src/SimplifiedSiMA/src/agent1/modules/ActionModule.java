package agent1.modules;

import framework.ModuleImpl;

public class ActionModule extends ModuleImpl {
	
	@Override
	protected void executeModuleFunction() {
		log.debug("Start action track");
		int inValue = Integer.valueOf(this.getInputData().get("testvalue").getDefaultValue());
		int outValue = inValue+1;
		this.getOutputData().setContent("testvalue", String.valueOf(outValue));
		
		log.debug("Received invalue={}. Set outvalue={}", inValue, outValue);
		
	}

}
