package agent1;

import java.util.Map;

import org.slf4j.Logger;

import agent1.modules.ActionModule;
import agent1.modules.DecisionTrackModule;
import agent1.modules.DriveTrackModule;
import agent1.modules.EvaluationTrackModule;
import agent1.modules.PerceptionTrackModule;
import datastructures.Concept;
import datastructures.Datapackage;
import datastructures.DatapackageImpl;
import framework.AgentMain;
import framework.Module;
import logger.MyLogger;

public class TestAgentImpl implements AgentMain {
	protected static final Logger log = MyLogger.getLog("Sima");
		
	//Inputs
	private Map<String, Concept> drives;
	private Map<String, Concept> bodyPerception;
	private Map<String, Concept> perception;
	
	//Action
	private String action = "";
	
	
	//Modules
	private Module driveTrackModule = new DriveTrackModule();
	private Module perceptionTrackModule = new PerceptionTrackModule();
	private Module evaluationTrackModule = new EvaluationTrackModule();
	private Module decisionTrackModule = new DecisionTrackModule();
	private Module actionModule = new ActionModule();
	
	
	public void init() {
		//Add modules hard-coded initialized
		this.driveTrackModule.init("DriveTrack");
		this.perceptionTrackModule.init("PerceptionTrack");
		this.evaluationTrackModule.init("EvaluationTrack");
		this.decisionTrackModule.init("DecisionTrack");
		this.actionModule.init("ActionModule");
		
		//Create module structure
		//Pass from drive to decision making
		this.driveTrackModule.registerModule(this.decisionTrackModule);
		//Pass from perception to decision making
		this.perceptionTrackModule.registerModule(this.decisionTrackModule);
		//Pass from evaluation to decision making
		this.evaluationTrackModule.registerModule(this.decisionTrackModule);
		//Pass from drive to evaluation
		this.driveTrackModule.registerModule(this.evaluationTrackModule);
		//Pass from decision to action
		this.decisionTrackModule.registerModule(this.actionModule);
		
	}
	
	
	
	public void startCycle() {
		//Set module inputs
		//Set drives
		//Run all modules
		runModules();
		
		//Get results
		//this.action = this.actionModule.getOutputData().get("ACTION").getDefaultValue();
		
	}

	private void runModules() {
		log.debug("Run drive track");
		try {
			this.driveTrackModule.runModule();
			log.debug("Run perception track");
			this.perceptionTrackModule.runModule();
			log.debug("Run evaluation track");
			this.evaluationTrackModule.runModule();
			log.debug("Run decision making");
			this.decisionTrackModule.runModule();
			log.debug("Run action track");
			this.actionModule.runModule();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void setDriveInput(Map<String, Concept> drives) {
		this.drives = drives;
		
		Datapackage driveData = DatapackageImpl.newDatapackage(this.drives);
		this.driveTrackModule.setInputData(driveData);
		
	}

	@Override
	public void setBodyPerceptionInput(Map<String, Concept> bodyPerception) {
		this.bodyPerception = bodyPerception;
		
		Datapackage bodyPerceptionData = DatapackageImpl.newDatapackage(this.bodyPerception);
		this.perceptionTrackModule.setInputData(bodyPerceptionData);
	}

	@Override
	public void setPerceptionInput(Map<String, Concept> perception) {
		this.perception= perception;
		
		this.perceptionTrackModule.setInputData(DatapackageImpl.newDatapackage(this.perception));
	}

	@Override
	public String getAction() {
		return this.action;
	}

	@Override
	public void killMind() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void setDriveModuleInput(Map<String, Concept> map) {
//		this.driveTrackModule.setInputData(DatapackageImpl.newDatapackage(map));
//		
//	}

//	@Override
//	public void setPerceptionInput(Map<String, String> map) {
//		this.perceptionTrackModule.setInputData(DatapackageImpl.newDatapackage(map));
//		
//	}

	@Override
	public String getActionModuleOutput(String key) {
		return this.actionModule.getOutputData().getViewOfAllData().get(key).getDefaultValue();
	}
}
