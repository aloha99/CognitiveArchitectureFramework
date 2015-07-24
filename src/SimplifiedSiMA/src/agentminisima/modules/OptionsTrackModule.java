package agentminisima.modules;

import datastructures.Chunk;
import datastructures.ChunkImpl;
import agentminisima.Actions;
import agentminisima.Names;
import framework.ModuleImpl;

public class OptionsTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//=== Get inputs ===//
		//Get drive wishes
		Chunk driveWishes = this.getInputData().get(Names.DRIVEWISHESADDRESS);
		//Get activated seqeunces
		Chunk episodes = this.getInputData().get(Names.EPISODESADDRESS);
		
		//Get emotions
		//TODO: Implement emotions
		
		//Get current state
		Chunk currentState = this.getInputData().get(Names.CURRENTSTATEADDRESS);
		
		
		//=== Generate options ===//
		//There is only one drive wish at the moment and it is to eat something
		//FIXME: Just generate random action
		String[] actions = Actions.getAllActions();
		String action = actions[(int) (actions.length*Math.random())];
		Chunk option1 = ChunkImpl.newChunk("RANDOMOPTION").
				addSubChunk(ChunkImpl.newChunk(Names.ACTIONADDRESS).newDefaultValue(action).build(), this.getOutputData()).
				build();
		
		
		//=== Evaluate options ===//
		
		
		//=== Select option ===//
		//newConcept only copies the strings but not any sub concepts
		Chunk selectedOption = ChunkImpl.newChunk(option1, Names.SELECTEDOPTIONADDRESS).build();
		
		
		//=== Set output ===//
		this.getOutputData().setContent(selectedOption);
	}

}
