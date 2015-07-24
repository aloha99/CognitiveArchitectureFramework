package agentminisima.modules;

import datastructures.Chunk;
import datastructures.ChunkImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class EpisodeTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Load episodes for activated images
		Chunk activatedImages = this.getInputData().get(Names.ACTIVATEDIMAGESADDRESS);
		
		//Create concept for all loaded sequences
		Chunk episodes = ChunkImpl.newChunk(Names.EPISODESADDRESS).build();
		
		//Set output
		this.getOutputData().setContent(episodes);
		
	}

}
