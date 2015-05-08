package agentminisima.modules;

import datastructures.Concept;
import agentminisima.Names;
import framework.ModuleImpl;

public class PerceptionPreparationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Get perception
		Concept perceivedImage = this.getInputData().get(Names.PERCEIVEDIMAGEADDRESS);
		
		//Test social rules on perception
		//TODO: implement social rules
		
		//Get emotion
		//TODO: Implement emotion
		
		//Get drive reward
		Concept driveReward = this.getInputData().get(Names.DRIVE1REWARD);
		
		//Create state
		Concept currentState = Concept.newConcept(Names.CURRENTSTATEADDRESS).
				addSubconcept(perceivedImage).
				addSubconcept(driveReward).
				build();
		
		//Set output
		this.getOutputData().setContent(currentState);
	}

}
