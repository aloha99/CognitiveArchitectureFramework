package agentminisima.modules;

import agentminisima.Names;
import datastructures.Concept;
import datastructures.ConceptImpl;
import framework.ModuleImpl;

public class ActivationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Similar images like in perception shall be activated
		//TODO: Create a search function
		
		
		
		Concept activatedImages = ConceptImpl.newConcept(Names.ACTIVATEDIMAGESADDRESS).build();
		this.getOutputData().setContent(activatedImages);
	}

}
