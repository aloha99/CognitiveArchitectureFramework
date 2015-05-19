package agentminisima.modules;

import datastructures.Concept;
import datastructures.ConceptImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class ActionModule extends ModuleImpl {
	
	@Override
	protected void executeModuleFunction() {
		log.debug("Start action track");
		//Get action from input
		Concept selectedOption = this.getInputData().get(Names.SELECTEDOPTIONADDRESS);
		Concept action = selectedOption.getSubConcept(Names.ACTIONADDRESS, this.getInputData());
		
		
		//Set output
		this.getOutputData().setContent(action);
		
	}

}
