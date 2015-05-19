package agentminisima.modules;

import datastructures.Concept;
import datastructures.ConceptImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class MotivationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		log.debug("Start motivation track");
		//Get drives
		Concept drive1 = this.getInputData().get(Names.DRIVE1NAME);
		
		//Create goals
		Concept drivewish1 = ConceptImpl.newConcept(drive1, Names.DRIVEWISH1).build();
		
		//Create general drive wish structure
		Concept drivewishes = ConceptImpl.newConcept(Names.DRIVEWISHESADDRESS).addSubconcept(drivewish1, this.getOutputData()).build();
		
		//Get emotions
		//TODO: Implement emotions
		
		//Perform decision making
		//TODO: Implement decision making
		
		//Set output
		//As there is only one drive, pass it through
		this.getOutputData().setContent(drivewishes);
		
	}

}
