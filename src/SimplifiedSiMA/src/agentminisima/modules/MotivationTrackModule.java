package agentminisima.modules;

import datastructures.Concept;
import agentminisima.Names;
import framework.ModuleImpl;

public class MotivationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		log.debug("Start motivation track");
		//Get drives
		Concept drive1 = this.getInputData().get(Names.DRIVE1NAME);
		
		//Create goals
		Concept drivewish1 = Concept.newConcept(drive1, Names.DRIVEWISH1).build();
		
		//Create general drive wish structure
		Concept drivewishes = Concept.newConcept(Names.DRIVEWISHESADDRESS).addSubconcept(drivewish1).build();
		
		//Get emotions
		//TODO: Implement emotions
		
		//Perform decision making
		//TODO: Implement decision making
		
		//Set output
		//As there is only one drive, pass it through
		this.getOutputData().setContent(drivewishes);
		
	}

}
