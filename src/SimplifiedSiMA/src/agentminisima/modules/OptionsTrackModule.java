package agentminisima.modules;

import datastructures.ConceptImpl;
import agentminisima.Actions;
import agentminisima.Names;
import framework.ModuleImpl;

public class OptionsTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//=== Get inputs ===//
		//Get drive wishes
		ConceptImpl driveWishes = this.getInputData().get(Names.DRIVEWISHESADDRESS);
		//Get activated seqeunces
		ConceptImpl episodes = this.getInputData().get(Names.EPISODESADDRESS);
		
		//Get emotions
		//TODO: Implement emotions
		
		//Get current state
		ConceptImpl currentState = this.getInputData().get(Names.CURRENTSTATEADDRESS);
		
		
		//=== Generate options ===//
		//There is only one drive wish at the moment and it is to eat something
		//FIXME: Just generate random action
		String[] actions = Actions.getAllActions();
		String action = actions[(int) (actions.length*Math.random())];
		ConceptImpl option1 = ConceptImpl.newConcept("RANDOMOPTION").
				addSubconcept(ConceptImpl.newConcept(Names.ACTIONADDRESS).newDefaultValue(action).build()).
				build();
		
		
		//=== Evaluate options ===//
		
		
		//=== Select option ===//
		ConceptImpl selectedOption = ConceptImpl.newConcept(option1, Names.SELECTEDOPTIONADDRESS).build();
		
		
		//=== Set output ===//
		this.getOutputData().setContent(selectedOption);
	}

}
