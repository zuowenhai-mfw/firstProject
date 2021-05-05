package light.mvc.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "webServiceDemo", targetNamespace = Constants.NS)
public interface WebServiceDemoI {

	public String helloWs(@WebParam(name = "userName") String name);

}
