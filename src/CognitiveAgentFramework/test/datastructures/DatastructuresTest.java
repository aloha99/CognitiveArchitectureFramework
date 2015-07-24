package datastructures;

import static org.junit.Assert.*;
import logger.MyLogger;
import org.junit.Test;
import org.slf4j.Logger;

import datastructures.ChunkImpl.ChunkBuilder;

public class DatastructuresTest {
	
	private static final Logger log = MyLogger.getLog("Sima");
	
	private static final String BODYTYPE = "BODYTYPE";
	private static final String X = "X";
	private static final String Y = "Y";
	private static final String OBJECTID = "OBJECTID";
	private static final String OBJECTNAME = "OBJECTNAME";

	@Test
	public void test() {
		
		//Test if all concepts in the address map can be found in the id map. They must be found. In the other direction, not all
		//concepts of the id-map must be found in the address map.
		
		
		fail("Not yet implemented");
	}
	
	@Test
	public void datapackageTest() {
		try {
			log.info("=== Test if a datapackage can be correctly generated and read ===");
			
			//DummyPerceptionModule module = new DummyPerceptionModule();
			//module.init("PERCEPTIONTESTER");
			//module.setTargetAddress("PERCEPTION");
			
			//Create an empty data package, which shall keep all concepts
			Datapackage perceptionPackage = DatapackageImpl.newDatapackage();
			
			//Create fake perception with concepts
			//Create perception
			ChunkBuilder perceptionBuilder = ChunkImpl.newChunk("PERCEPTION");
			for (int i=0;i<5;i++) {
				Chunk percept = ChunkImpl.newChunk("Schnitzel" + i).
						newValue(BODYTYPE, "bodytypeSchnitzel").
						newValue(X, String.valueOf(i)).
						newValue(Y, String.valueOf(i+1)).
						newValue(OBJECTID, "Schnitzel" + i).
						newValue(OBJECTNAME, "Schnitzel").
						build();
				log.debug("concept={}", percept);
				
				perceptionBuilder.addSubChunk(percept, perceptionPackage);
			}
			
			Chunk perception = perceptionBuilder.build();
			//Map<String, Concept> perceptionMap = new HashMap<String, Concept>();
			//perceptionMap.put(perception.getName(), perception);
			perceptionPackage.setContent(perception);
			log.debug("Perception={}", perception);
			
			//Set input
			//module.setInputData(DatapackageImpl.newDatapackage(perceptionPackage));
			
			//Run module
			//module.runModule();
			
			//Get output Schnitzel5 X and Y position
			Chunk actualPerception = perceptionPackage.get(perception.getName());
			
			int actualx = Integer.valueOf(actualPerception.getSubChunk("Schnitzel4", perceptionPackage).getValue(X));
			int actualy = Integer.valueOf(actualPerception.getSubChunk("Schnitzel4", perceptionPackage).getValue(Y));
			String actualobjectid  = actualPerception.getSubChunk("Schnitzel4", perceptionPackage).getValue(OBJECTID);
			
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
