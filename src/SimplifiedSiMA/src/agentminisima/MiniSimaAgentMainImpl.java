package agentminisima;

import java.util.Map;

import org.slf4j.Logger;

import agentminisima.modules.ActionModule;
import agentminisima.modules.ActivationTrackModule;
import agentminisima.modules.EpisodeTrackModule;
import agentminisima.modules.MotivationTrackModule;
import agentminisima.modules.DriveTrackModule;
import agentminisima.modules.EvaluationTrackModule;
import agentminisima.modules.OptionsTrackModule;
import agentminisima.modules.PerceptionPreparationTrackModule;
import agentminisima.modules.PerceptionTrackModule;
import agentminisima.modules.SituationLearningTrackModule;
import datastructures.ConceptImpl;
import datastructures.Datapackage;
import datastructures.DatapackageImpl;
import framework.AgentMain;
import framework.Module;
import logger.MyLogger;

public class MiniSimaAgentMainImpl implements AgentMain {
	protected static final Logger log = MyLogger.getLog("Sima");
		
	//Inputs
	private Map<String, ConceptImpl> drives;
	private Map<String, ConceptImpl> bodyPerception;
	private Map<String, ConceptImpl> perception;
	
	//Action
	private String action = "";
	
	
	//Modules
	private Module driveTrackModule = new DriveTrackModule();
	private Module perceptionTrackModule = new PerceptionTrackModule();
	private Module activationTrackModule = new ActivationTrackModule();
	private Module evaluationTrackModule = new EvaluationTrackModule();
	private Module motivationTrackModule = new MotivationTrackModule();
	private Module optionsTrackModule = new OptionsTrackModule();
	private Module actionModule = new ActionModule();
	private Module episodeTrackModule = new EpisodeTrackModule();
	private Module perceptionPreparationTrackModule = new PerceptionPreparationTrackModule();
	private Module situationLearningTrackModule = new SituationLearningTrackModule();
	
	
	public void init() {
		//Add modules hard-coded initialized
		this.driveTrackModule.init("DriveTrack");
		this.perceptionTrackModule.init("PerceptionTrack");
		this.activationTrackModule.init("ActivationTrackModule");
		this.evaluationTrackModule.init("EvaluationTrack");
		this.motivationTrackModule.init("DecisionTrack");
		this.optionsTrackModule.init("OptionsTrack");
		this.episodeTrackModule.init("EpisodeTrack");
		this.perceptionPreparationTrackModule.init("PerceptionPreparationTrack");
		this.situationLearningTrackModule.init("SituationLearningTrack");
		this.actionModule.init("ActionModule");
		
		//Create module structure
		//=== Primary process ===//
		//Pass from drive to evaluation
		this.driveTrackModule.registerModule(this.evaluationTrackModule);
		//Pass from perception to evaluation track
		this.perceptionTrackModule.registerModule(this.evaluationTrackModule);
		//Pass from perception to activation track
		this.perceptionTrackModule.registerModule(this.activationTrackModule);
		
		
		//=== Secondary Process ===//
		//Pass from drive to decision making
		this.driveTrackModule.registerModule(this.motivationTrackModule);
		//Pass from evaluation to decision making for drives
		this.evaluationTrackModule.registerModule(this.motivationTrackModule);
		
		//Pass from activation to episode track
		this.activationTrackModule.registerModule(this.episodeTrackModule);
		
		//Pass from perception to perception preparation
		this.perceptionTrackModule.registerModule(this.perceptionPreparationTrackModule);
		//Pass from evaluation to perception preparation
		this.evaluationTrackModule.registerModule(this.perceptionPreparationTrackModule);
		
		//Pass from perception preparation to learning
		this.perceptionPreparationTrackModule.registerModule(this.situationLearningTrackModule);
		
		//Pass from motivation track to options track
		this.motivationTrackModule.registerModule(this.optionsTrackModule);
		//Pass from evaluation track to options track
		this.evaluationTrackModule.registerModule(this.optionsTrackModule);
		//Pass from episode track to options track
		this.episodeTrackModule.registerModule(this.optionsTrackModule);
		//Pass from perception preparation track to options track
		this.perceptionPreparationTrackModule.registerModule(this.optionsTrackModule);

		//Pass from decision to action
		this.optionsTrackModule.registerModule(this.actionModule);
		
	}
	
	
	
	public void startCycle() {
		//Set module inputs
		//Set drives
		//Run all modules
		runModules();
		
		//Get results
		this.action = this.actionModule.getOutputData().get(Names.ACTIONADDRESS).getDefaultValue();
		
	}

	private void runModules() {
		try {
			//Primary process
			log.debug("Start primary process");
			log.debug("Run drive track");
			this.driveTrackModule.runModule();
			log.debug("Run perception track");
			this.perceptionTrackModule.runModule();
			log.debug("Run evaluation track");
			this.evaluationTrackModule.runModule();
			log.debug("Run activation track");
			this.activationTrackModule.runModule();
			
			//Secondary process
			log.debug("Start secondary process");
			log.debug("Run decision making");
			this.motivationTrackModule.runModule();
			log.debug("Run episode track");
			this.episodeTrackModule.runModule();
			log.debug("Run perception preparation track");
			this.perceptionPreparationTrackModule.runModule();
			log.debug("Run situation learning track");
			this.situationLearningTrackModule.runModule();
			log.debug("Run options track");
			this.optionsTrackModule.runModule();
			log.debug("Run action track");
			this.actionModule.runModule();
			
		} catch (Exception e) {
			log.error("Failed to complete cycle");
		}
		
	}

	@Override
	public void setDriveInput(Map<String, ConceptImpl> drives) {
		this.drives = drives;
		
		Datapackage driveData = DatapackageImpl.newDatapackage(this.drives);
		this.driveTrackModule.setInputData(driveData);
		
	}

	@Override
	public void setBodyPerceptionInput(Map<String, ConceptImpl> bodyPerception) {
		this.bodyPerception = bodyPerception;
		
		Datapackage bodyPerceptionData = DatapackageImpl.newDatapackage(this.bodyPerception);
		this.perceptionTrackModule.setInputData(bodyPerceptionData);
	}

	@Override
	public void setPerceptionInput(Map<String, ConceptImpl> perception) {
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
		return this.actionModule.getOutputData().getViewOfAllConcepts().get(key).getDefaultValue();
	}
}
