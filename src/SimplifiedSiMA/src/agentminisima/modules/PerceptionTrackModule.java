package agentminisima.modules;

import agentminisima.Names;
import datastructures.ConceptImpl;
import framework.ModuleImpl;

public class PerceptionTrackModule extends ModuleImpl {
	


	@Override
	protected void executeModuleFunction() {
		log.debug("Start perception track");
		//Get body perception
		ConceptImpl bodyperception = this.getInputData().get(Names.BODYPERCEPTIONADDRESS);
		
		//Get external perception
		ConceptImpl externalPerception = this.getInputData().get(Names.EXTERNALPERCEPTIONADDRESS);
		
		//Generate a self
		ConceptImpl self = ConceptImpl.newConcept(Names.SELFADDRESS).addSubconcept(bodyperception).build();
		
		//Create an image from the objects if necessary
		ConceptImpl perceivedImage = ConceptImpl.newConcept(externalPerception, Names.PERCEIVEDIMAGEADDRESS).addSubconcept(self).build();
		
		//Set output
		this.getOutputData().setContent(perceivedImage);
		log.debug("Perceived Image={}", perceivedImage);
		
	}

}
