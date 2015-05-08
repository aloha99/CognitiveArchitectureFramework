package agentminisima.modules;

import agentminisima.Names;
import datastructures.Concept;
import framework.ModuleImpl;

public class EvaluationTrackModule extends ModuleImpl {
	
	//Dynamic variables
	private double previousHungerIntensity = 0;
	private double hungerReward = 0;
	
	@Override
	protected void executeModuleFunction() {
		log.debug("Start evaluation track");
		
		//Check if pain or pleasure was created
		
		
		//Check the drive reward
		//Get the intensity of the drive HUNGER
		Concept hungerDrive = this.getInputData().get(Names.DRIVE1NAME);
		double hungerDriveIntensity = Double.valueOf(hungerDrive.getDefaultValue());
		//Compare with previous hunger intensity and calculate the reward
		this.hungerReward = - (hungerDriveIntensity - previousHungerIntensity);
		this.previousHungerIntensity = hungerDriveIntensity;
		log.debug("Hunger drive intensity={}, reward in this cycle={}", hungerDriveIntensity, this.hungerReward);
		
		//Create an emotion
		//TODO: Create an emotion
		
		
		//Put emotion on the output
		this.getOutputData().setContent(Concept.newConcept(Names.DRIVE1REWARD).newDefaultValue(String.valueOf(this.hungerReward)).build());
	}

}
