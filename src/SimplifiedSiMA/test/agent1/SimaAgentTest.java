package agent1;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import logger.MyLogger;

import org.junit.Test;
import org.slf4j.Logger;

import agent1.TestAgentImpl;
import datastructures.ChunkImpl;
import datastructures.Datapackage;
import datastructures.DatapackageImpl;
import framework.AgentMain;

public class SimaAgentTest {
	
	private static final Logger log = MyLogger.getLog("Sima");

	@Test
	public void testDummySimaAgent() {
		try {
			AgentMain simaAgent = new TestAgentImpl();
			
			//Set input data and look what output data is created
			final String address = "testvalue";
			final int expectedResult = 5;
			int driveInput = 0;
			int perceptionInput = 0;
			
			//Map<String, ConceptImpl> driveMap = new HashMap<String, ConceptImpl>();
			Datapackage driveMap = DatapackageImpl.newDatapackage();
			driveMap.setContent(ChunkImpl.newChunk(address).newDefaultValue(String.valueOf(driveInput)).build());
			//driveMap.put(address, ConceptImpl.newConcept(address).newDefaultValue(String.valueOf(driveInput)).build());
			
			//Map<String, ConceptImpl> perceptionMap = new HashMap<String, ConceptImpl>();
			Datapackage perceptionMap = DatapackageImpl.newDatapackage();
			perceptionMap.setContent(ChunkImpl.newChunk(address).newDefaultValue(String.valueOf(perceptionInput)).build());
			
			simaAgent.init();
			
			simaAgent.setDriveInput(driveMap);
			simaAgent.setPerceptionInput(perceptionMap);
			
			simaAgent.startCycle();
			
			int actualResult = Integer.valueOf(simaAgent.getActionModuleOutput(address));
			
			assertEquals(expectedResult, actualResult);
		} catch (Exception e) {
			log.error("Failed test", e);
			fail("Execution error");
		}
	}

}
