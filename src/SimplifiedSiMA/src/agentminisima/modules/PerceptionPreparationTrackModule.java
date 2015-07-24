package agentminisima.modules;

import datastructures.Chunk;
import datastructures.ChunkImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class PerceptionPreparationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Get perception
		Chunk perceivedImage = this.getInputData().get(Names.PERCEIVEDIMAGEADDRESS);
		
		//Test social rules on perception
		//TODO: implement social rules
		
		//Get emotion
		//TODO: Implement emotion
		
		//Get drive reward
		Chunk driveReward = this.getInputData().get(Names.DRIVE1REWARD);
		
		//Create state
		Chunk currentState = ChunkImpl.newChunk(Names.CURRENTSTATEADDRESS).
				addSubChunk(perceivedImage, this.getOutputData()).
				addSubChunk(driveReward, this.getOutputData()).
				build();
		
		//Set output
		this.getOutputData().setContent(currentState);
	}

}
