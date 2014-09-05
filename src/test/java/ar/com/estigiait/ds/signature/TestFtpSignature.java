package ar.com.estigiait.ds.signature;

import org.apache.camel.main.Main;

import ar.com.estigiait.ds.server.FileRoute;

public class TestFtpSignature {
	
	private TestFtpSignature() {
	}

	public static void main(String[] args) throws Exception {
	     Main main = new Main();
	     main.addRouteBuilder(new FileRoute());
	     main.enableHangupSupport();
	     main.run();
	}


}
