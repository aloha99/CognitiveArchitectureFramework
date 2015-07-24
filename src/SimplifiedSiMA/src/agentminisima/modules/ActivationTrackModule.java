package agentminisima.modules;

import agentminisima.Names;
import datastructures.Chunk;
import datastructures.ChunkImpl;
import framework.ModuleImpl;

public class ActivationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Similar images like in perception shall be activated
		//TODO: Create a search function
		
		
		
		Chunk activatedImages = ChunkImpl.newChunk(Names.ACTIVATEDIMAGESADDRESS).build();
		this.getOutputData().setContent(activatedImages);
	}

}
