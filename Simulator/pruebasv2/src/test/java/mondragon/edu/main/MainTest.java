package mondragon.edu.main;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import mondragon.edu.main.Main;

public class MainTest {
	
	Main main;
	@Before
	public void init() {
		main=new Main();
	}
	
	@Test
	public void testInitSegments() {
		main.inicializar();
		assertEquals("Failure - segments not well iniciates",(Integer)16 ,main.getSegmentList().get(15).getId());
	}

}
