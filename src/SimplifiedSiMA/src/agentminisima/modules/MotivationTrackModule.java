package agentminisima.modules;

import datastructures.Chunk;
import datastructures.ChunkImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class MotivationTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		log.debug("Start motivation track");
		//Get drives
		Chunk drive1 = this.getInputData().get(Names.DRIVE1NAME);
		
		//Create goals
		Chunk drivewish1 = ChunkImpl.newChunk(drive1, Names.DRIVEWISH1).build();
		
		//Create general drive wish structure
		Chunk drivewishes = ChunkImpl.newChunk(Names.DRIVEWISHESADDRESS).addSubChunk(drivewish1, this.getOutputData()).build();
		
		//Get emotions
		//TODO: Implement emotions
		
		//Perform decision making
		//TODO: Implement decision making
		
		//Set output
		//As there is only one drive, pass it through
		this.getOutputData().setContent(drivewishes);
		
	}

}
