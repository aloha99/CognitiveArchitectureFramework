package framework;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import logger.MyLogger;
import modules.DummyPerceptionModule;
import modules.DummyTestModule;
import modules.TesterModule;

import org.junit.Test;
import org.slf4j.Logger;

import datastructures.Concept;
import datastructures.Datapackage;
import datastructures.DatapackageImpl;
import datastructures.Concept.ConceptBuilder;

public class ModuleTest {
	
	private static final Logger log = MyLogger.getLog("Sima");
	
	private static final String BODYTYPE = "BODYTYPE";
	private static final String X = "X";
	private static final String Y = "Y";
	private static final String OBJECTID = "OBJECTID";
	private static final String OBJECTNAME = "OBJECTNAME";

	@Test
	public void driveTrackModuleTest() {
		try {
			log.info("=== Test if a string can be passed through 2 modules ===");
			
			//Create a dummy test module
			log.info("Create modules");
			TesterModule sourceModule = new TesterModule();
			sourceModule.init("Tester");
			
			DummyTestModule testModule = new DummyTestModule();
			testModule.init("dummy");
			
			sourceModule.setValue("Action", "MOVE_FORWARD");
			
			//Register modules
			sourceModule.registerModule(testModule);
			
			//Run modules
			log.info("Run source module");
			sourceModule.runModule();
			
			testModule.runModule();
			
			//Get output
			Datapackage output = testModule.getOutputData();
			
			assertEquals("MOVE_FORWARD", output.get("Action").getDefaultValue());
		} catch (Exception e) {
			log.error("Error", e);
			fail("Fail");
		}
	}
	
	@Test
	public void perceptionInputTest() {
		try {
			log.info("=== Test if the perception is correctyl received by the module ===");
			
			DummyPerceptionModule module = new DummyPerceptionModule();
			module.init("PERCEPTIONTESTER");
			module.setTargetAddress("PERCEPTION");
			
			//Create fake perception with concepts
			//Create perception
			ConceptBuilder perceptionBuilder = Concept.newConcept("PERCEPTION");
			for (int i=0;i<5;i++) {
				Concept percept = Concept.newConcept("id" + i).
						newValue(BODYTYPE, "bodytypeSchnitzel").
						newValue(X, String.valueOf(i)).
						newValue(Y, String.valueOf(i+1)).
						newValue(OBJECTID, "Schnitzel" + i).
						newValue(OBJECTNAME, "Schnitzel").
						build();
				
				perceptionBuilder.addSubconcept(percept);
			}
			
			Concept perception = perceptionBuilder.build();
			Map<String, Concept> perceptionMap = new HashMap<String, Concept>();
			perceptionMap.put(perception.getName(), perception);
			
			//Set input
			module.setInputData(DatapackageImpl.newDatapackage(perceptionMap));
			
			//Run module
			module.runModule();
			
			//Get output Schnitzel5 X and Y position
			Concept actualPerception = module.getOutputData().get("PERCEPTION");
			
			int actualx = Integer.valueOf(actualPerception.getSubConcept("id46").getValue(X));
			int actualy = Integer.valueOf(actualPerception.getSubConcept("id46").getValue(Y));
			String actualobjectid  = actualPerception.getSubConcept("id46").getValue(OBJECTID);
			
			//Expected values
			int expectedX = 4;
			int expectedY = 5;
			String expectedid = "Schnitzel4";
			
			assertEquals(actualx, expectedX);
			assertEquals(actualy, expectedY);
			assertEquals(actualobjectid, expectedid);
			
			//fail("Not finished");
		} catch (Exception e) {
			log.error("Error", e);
			fail("Fail due to errors");
		}
		
		
	}

}
