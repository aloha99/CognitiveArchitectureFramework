package agent;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import logger.MyLogger;

import org.junit.Test;
import org.slf4j.Logger;

import framework.SimaAgent;

public class SimaAgentTest {
	
	private static final Logger log = MyLogger.getLog("Sima");

	@Test
	public void test() {
		try {
			SimaAgent simaAgent = new SimaAgentImpl();
			
			//Set input data and look what output data is created
			final String address = "testvalue";
			final int expectedResult = 5;
			int driveInput = 0;
			int perceptionInput = 0;
			
			Map<String, String> driveMap = new HashMap<String, String>();
			driveMap.put(address, String.valueOf(driveInput));
			
			Map<String, String> perceptionMap = new HashMap<String, String>();
			perceptionMap.put(address, String.valueOf(perceptionInput));
			
			simaAgent.init();
			
			simaAgent.setDriveModuleInput(driveMap);
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
