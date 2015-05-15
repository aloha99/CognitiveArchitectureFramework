package agentminisima.modules;

import datastructures.ConceptImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class SituationLearningTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Get state
		ConceptImpl currentState = this.getInputData().get(Names.CURRENTSTATEADDRESS);
		
		//Save the current state
		//TODO: @Kamil
		
		//Update associations
		//TODO: @Kamil
	}

}
