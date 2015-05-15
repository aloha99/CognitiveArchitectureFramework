package agentminisima.modules;

import datastructures.ConceptImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class ActionModule extends ModuleImpl {
	
	@Override
	protected void executeModuleFunction() {
		log.debug("Start action track");
		//Get action from input
		ConceptImpl selectedOption = this.getInputData().get(Names.SELECTEDOPTIONADDRESS);
		ConceptImpl action = selectedOption.getSubConcept(Names.ACTIONADDRESS);
		
		
		//Set output
		this.getOutputData().setContent(action);
		
	}

}
