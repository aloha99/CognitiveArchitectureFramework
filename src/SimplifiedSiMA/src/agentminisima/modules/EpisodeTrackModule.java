package agentminisima.modules;

import datastructures.Concept;
import agentminisima.Names;
import framework.ModuleImpl;

public class EpisodeTrackModule extends ModuleImpl {

	@Override
	protected void executeModuleFunction() {
		//Load episodes for activated images
		Concept activatedImages = this.getInputData().get(Names.ACTIVATEDIMAGESADDRESS);
		
		//Create concept for all loaded sequences
		Concept episodes = Concept.newConcept(Names.EPISODESADDRESS).build();
		
		//Set output
		this.getOutputData().setContent(episodes);
		
	}

}
