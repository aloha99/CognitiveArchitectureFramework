package agentminisima.modules;

import agentminisima.Names;
import datastructures.Concept;
import framework.ModuleImpl;

public class PerceptionTrackModule extends ModuleImpl {
	


	@Override
	protected void executeModuleFunction() {
		log.debug("Start perception track");
		//Get body perception
		Concept bodyperception = this.getInputData().get(Names.BODYPERCEPTIONADDRESS);
		
		//Get external perception
		Concept externalPerception = this.getInputData().get(Names.EXTERNALPERCEPTIONADDRESS);
		
		//Generate a self
		Concept self = Concept.newConcept(Names.SELFADDRESS).addSubconcept(bodyperception).build();
		
		//Create an image from the objects if necessary
		Concept perceivedImage = Concept.newConcept(externalPerception, Names.PERCEIVEDIMAGEADDRESS).addSubconcept(self).build();
		
		//Set output
		this.getOutputData().setContent(perceivedImage);
		log.debug("Perceived Image={}", perceivedImage);
		
	}

}
