package framework;

import static org.junit.Assert.*;
import junit.framework.Assert;
import logger.MyLogger;
import modules.DummyTestModule;
import modules.TesterModule;

import org.junit.Test;
import org.slf4j.Logger;

public class ModuleTest {
	
	private static final Logger log = MyLogger.getLog("Sima");

	@Test
	public void DriveTrackModuleTest() {
		try {
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
			
			assertEquals("MOVE_FORWARD", output.getContent("Action"));
		} catch (Exception e) {
			log.error("Error", e);
			fail("Fail");
		}
		
		
	}

}
