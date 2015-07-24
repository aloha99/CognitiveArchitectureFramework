package agentminisima;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import logger.MyLogger;

import org.junit.Test;
import org.slf4j.Logger;

import datastructures.Chunk;
import datastructures.ChunkImpl;
import datastructures.ChunkImpl.ChunkBuilder;
import datastructures.Datapackage;
import datastructures.DatapackageImpl;
import framework.AgentMain;

public class AgentMiniSimaTest {
	
	private static final Logger log = MyLogger.getLog("Sima");
	
	private static final String BODYTYPE = "BODYTYPE";
	private static final String X = "X";
	private static final String Y = "Y";
	private static final String OBJECTID = "OBJECTID";
	private static final String OBJECTNAME = "OBJECTNAME";

	@Test
	public void test() {
		try {
			//Create sima
			AgentMain simaAgent = new MiniSimaAgentMainImpl();
			simaAgent.init();
			
			//Create inputs
			//Create fake perception with concepts
			//Create perception
			Datapackage perceptionMap = DatapackageImpl.newDatapackage();
			ChunkBuilder perceptionBuilder = ChunkImpl.newChunk(Names.EXTERNALPERCEPTIONADDRESS);
			for (int i=0;i<5;i++) {
				Chunk percept = ChunkImpl.newChunk("id" + i).
						newValue(BODYTYPE, "bodytypeSchnitzel").
						newValue(X, String.valueOf(i)).
						newValue(Y, String.valueOf(i+1)).
						newValue(OBJECTID, "Schnitzel" + i).
						newValue(OBJECTNAME, "Schnitzel").
						build();
				
				perceptionBuilder.addSubChunk(percept, perceptionMap);
			}
			
			Chunk perception = perceptionBuilder.build();
			perceptionMap.setContent(perception);
			//perceptionMap.put(perception.getName(), perception);
			
			//Create drive input
			Datapackage drivesMap = DatapackageImpl.newDatapackage();
			drivesMap.setContent(ChunkImpl.newChunk(Names.DRIVE1NAME).newDefaultValue("0.6").build());
			
			//Create body perception input
			Datapackage bodyperceptionMap =  DatapackageImpl.newDatapackage();
			bodyperceptionMap.setContent(ChunkImpl.newChunk(Names.BODYPERCEPTIONADDRESS).newDefaultValue("-0.2").build());
			
			//Set input
			simaAgent.setPerceptionInput(perceptionMap);
			simaAgent.setDriveInput(drivesMap);
			simaAgent.setBodyPerceptionInput(bodyperceptionMap);
			
			//Execute agent
			simaAgent.startCycle();
			
			//Get action
			String action = simaAgent.getAction();
			
			boolean testResult = false;
			if (action.equals("")==false) {
				testResult = true;
			}
			
			assertTrue(testResult);
			
		} catch (Exception e) {
			log.error("Cannot execute test", e);
			fail("Error");
		}
		
		
		
	}

}
