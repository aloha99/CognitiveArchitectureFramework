package agentminisima.modules;

import datastructures.ConceptImpl;
import agentminisima.Names;
import framework.ModuleImpl;

public class EpisodeTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Load episodes for activated images
		ConceptImpl activatedImages = this.getInputData().get(Names.ACTIVATEDIMAGESADDRESS);
		
		//Create concept for all loaded sequences
		ConceptImpl episodes = ConceptImpl.newConcept(Names.EPISODESADDRESS).build();
		
		//Set output
		this.getOutputData().setContent(episodes);
		
	}

}
