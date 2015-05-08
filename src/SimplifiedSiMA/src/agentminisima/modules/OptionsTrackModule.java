package agentminisima.modules;

import datastructures.Concept;
import agentminisima.Actions;
import agentminisima.Names;
import framework.ModuleImpl;

public class OptionsTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//=== Get inputs ===//
		//Get drive wishes
		Concept driveWishes = this.getInputData().get(Names.DRIVEWISHESADDRESS);
		//Get activated seqeunces
		Concept episodes = this.getInputData().get(Names.EPISODESADDRESS);
		
		//Get emotions
		//TODO: Implement emotions
		
		//Get current state
		Concept currentState = this.getInputData().get(Names.CURRENTSTATEADDRESS);
		
		
		//=== Generate options ===//
		//There is only one drive wish at the moment and it is to eat something
		//FIXME: Just generate random action
		String[] actions = Actions.getAllActions();
		String action = actions[(int) (actions.length*Math.random())];
		Concept option1 = Concept.newConcept("RANDOMOPTION").
				addSubconcept(Concept.newConcept(Names.ACTIONADDRESS).newDefaultValue(action).build()).
				build();
		
		
		//=== Evaluate options ===//
		
		
		//=== Select option ===//
		Concept selectedOption = Concept.newConcept(option1, Names.SELECTEDOPTIONADDRESS).build();
		
		
		//=== Set output ===//
		this.getOutputData().setContent(selectedOption);
	}

}
