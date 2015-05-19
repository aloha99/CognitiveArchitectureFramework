package agentminisima.modules;

import agentminisima.Names;
import datastructures.Concept;
import framework.ModuleImpl;

public class DriveTrackModule extends ModuleImpl {
	

	
	@Override
	protected void executeModuleFunction() {
		//Just put the input to the output
		//this.getOutputData().setContent(this.getInputData().getViewOfAllData());
		
		log.debug("Start drive track");
		//Get the intensity of the drive HUNGER
		Concept hungerDrive = this.getInputData().get(Names.DRIVE1NAME);
		//double hungerDriveIntensity = Double.valueOf(hungerDrive.getDefaultValue());
		
		//Add driveobject and action
		//TODO: Replace with database
		hungerDrive.addValue(Names.DRIVEOBJECTADDRESS, Names.DRIVE1OBJECT);
		hungerDrive.addValue(Names.DRIVEACTIONADDRESS, Names.DRIVE1ACTION);
		
		
		//Apply rules
		//TODO: Add rules for drives here
		
		
		
		//Set the output data
		this.getOutputData().setContent(hungerDrive);
		
		//log.debug("Received invalue={}. Set outvalue={}", inValue, outValue);
	}



}
