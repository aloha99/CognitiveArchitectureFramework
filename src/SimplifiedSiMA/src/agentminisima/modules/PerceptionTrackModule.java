package agentminisima.modules;

import agentminisima.Names;
import datastructures.Concept;
import datastructures.ConceptImpl;
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
		Concept self = ConceptImpl.newConcept(Names.SELFADDRESS).addSubconcept(bodyperception, this.getOutputData()).build();
		
		//Create an image from the objects if necessary
		Concept perceivedImage = ConceptImpl.newConcept(externalPerception, Names.PERCEIVEDIMAGEADDRESS).addSubconcept(self, this.getOutputData()).build();
		
		//Set output
		this.getOutputData().setContent(perceivedImage);
		log.debug("Perceived Image={}", perceivedImage);
		
	}

}
