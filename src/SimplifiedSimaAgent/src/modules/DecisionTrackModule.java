package modules;

import framework.ModuleImpl;

public class DecisionTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		log.debug("Start decision track track");
		int inValue1 = Integer.valueOf(this.getInputData().get("testvalue1"));
		int inValue2 = Integer.valueOf(this.getInputData().get("testvalue2"));
		int inValue3 = Integer.valueOf(this.getInputData().get("testvalue3"));
		int outValue = inValue1 + inValue2 + inValue3;
		this.getOutputData().setContent("testvalue", String.valueOf(outValue));
		
		log.debug("Received drive invalue1={}, percept invalue2={}, eval invalue3={}. Set outvalue={}", inValue1, inValue2, inValue3, outValue);
		
	}

}
