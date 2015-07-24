package agentminisima.modules;

import datastructures.Chunk;
import datastructures.ChunkImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class ActionModule extends ModuleImpl {
	
	@Override
	protected void executeModuleFunction() {
		log.debug("Start action track");
		//Get action from input
		Chunk selectedOption = this.getInputData().get(Names.SELECTEDOPTIONADDRESS);
		Chunk action = selectedOption.getSubChunk(Names.ACTIONADDRESS, this.getInputData());
		
		
		//Set output
		this.getOutputData().setContent(action);
		
	}

}
