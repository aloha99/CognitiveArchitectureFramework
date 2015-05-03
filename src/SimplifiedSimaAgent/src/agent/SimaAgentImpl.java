package agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;

import datastructures.Concept;
import framework.Datapackage;
import framework.DatapackageImpl;
import framework.Module;
import framework.SimaAgent;
import logger.MyLogger;
import modules.ActionModule;
import modules.DecisionTrackModule;
import modules.DriveTrackModule;
import modules.EvaluationTrackModule;
import modules.PerceptionTrackModule;

public class SimaAgentImpl implements SimaAgent {
	protected static final Logger log = MyLogger.getLog("Sima");
		
	//Inputs
	private HashMap<String, Double> drives;
	private HashMap<String, Double> bodyPerception;
	private ArrayList<Concept> perception;
	
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
		Datapackage driveData = DatapackageImpl.newDatapackage();
		for (Entry<String, Double> drive : drives.entrySet()) {
			driveData.setContent(drive.getKey(), String.valueOf(drive.getValue()));
		}
		this.driveTrackModule.setInputData(driveData);
		
		
		
		
		
		//Run all modules
		runModules();
		
		//Get results
		this.action = this.actionModule.getOutputData().getContent("ACTION");
		
	}

	private void runModules() {
		log.debug("Run drive track");
		this.driveTrackModule.runModule();
		log.debug("Run perception track");
		this.perceptionTrackModule.runModule();
		log.debug("Run evaluation track");
		this.evaluationTrackModule.runModule();
		log.debug("Run decision making");
		this.decisionTrackModule.runModule();
		log.debug("Run action track");
		this.actionModule.runModule();
	}

	@Override
	public void setDriveInput(HashMap<String, Double> drives) {
		this.drives = drives;
	}

	@Override
	public void setBodyPerceptionInput(HashMap<String, Double> bodyPerception) {
		this.bodyPerception = bodyPerception;
	}

	@Override
	public void setPerceptionInput(ArrayList<Concept> perception) {
		this.perception= perception; 
		
	}

	@Override
	public String getAction() {
		return this.action;
	}

	@Override
	public void killMind() {
		// TODO Auto-generated method stub
		
	}
}
