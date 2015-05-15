package agentminisima.modules;

import datastructures.ConceptImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class MotivationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		log.debug("Start motivation track");
		//Get drives
		ConceptImpl drive1 = this.getInputData().get(Names.DRIVE1NAME);
		
		//Create goals
		ConceptImpl drivewish1 = ConceptImpl.newConcept(drive1, Names.DRIVEWISH1).build();
		
		//Create general drive wish structure
		ConceptImpl drivewishes = ConceptImpl.newConcept(Names.DRIVEWISHESADDRESS).addSubconcept(drivewish1).build();
		
		//Get emotions
		//TODO: Implement emotions
		
		//Perform decision making
		//TODO: Implement decision making
		
		//Set output
		//As there is only one drive, pass it through
		this.getOutputData().setContent(drivewishes);
		
	}

}
