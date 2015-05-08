package agentminisima.modules;

import datastructures.Concept;
import agentminisima.Names;
import framework.ModuleImpl;

public class ActionModule extends ModuleImpl {
	
	@Override
	protected void executeModuleFunction() {
		log.debug("Start action track");
		//Get action from input
		Concept selectedOption = this.getInputData().get(Names.SELECTEDOPTIONADDRESS);
		Concept action = selectedOption.getSubConcept(Names.ACTIONADDRESS);
		
		
		//Set output
		this.getOutputData().setContent(action);
		
	}

}
