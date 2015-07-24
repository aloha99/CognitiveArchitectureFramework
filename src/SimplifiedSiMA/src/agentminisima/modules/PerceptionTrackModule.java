package agentminisima.modules;

import agentminisima.Names;
import datastructures.Chunk;
import datastructures.ChunkImpl;
import framework.ModuleImpl;

public class PerceptionTrackModule extends ModuleImpl {
	


	@Override
	protected void executeModuleFunction() {
		log.debug("Start perception track");
		//Get body perception
		Chunk bodyperception = this.getInputData().get(Names.BODYPERCEPTIONADDRESS);
		
		//Get external perception
		Chunk externalPerception = this.getInputData().get(Names.EXTERNALPERCEPTIONADDRESS);
		
		//Generate a self
		Chunk self = ChunkImpl.newChunk(Names.SELFADDRESS).addSubChunk(bodyperception, this.getOutputData()).build();
		
		//Create an image from the objects if necessary
		Chunk perceivedImage = ChunkImpl.newChunk(externalPerception, Names.PERCEIVEDIMAGEADDRESS).addSubChunk(self, this.getOutputData()).build();
		
		//Set output
		this.getOutputData().setContent(perceivedImage);
		log.debug("Perceived Image={}", perceivedImage);
		
	}

}
