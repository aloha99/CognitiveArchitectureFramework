package agentminisima.modules;

import datastructures.Chunk;
import datastructures.ChunkImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class SituationLearningTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Get state
		Chunk currentState = this.getInputData().get(Names.CURRENTSTATEADDRESS);
		
		//Save the current state
		//TODO: @Kamil
		
		//Update associations
		//TODO: @Kamil
	}

}
