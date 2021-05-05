package light.mvc.cxf.impl;

import javax.jws.WebService;

import light.mvc.cxf.Constants;
import light.mvc.cxf.WebServiceDemoI;
import light.mvc.service.sys.UserServiceI;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(endpointInterface = "light.mvc.cxf.WebServiceDemoI", serviceName = "webServiceDemo", targetNamespace = Constants.NS)
public class WebServiceDemoImpl implements WebServiceDemoI {

	@Autowired
	private UserServiceI userService;

	@Override
	public String helloWs(String name) {
		if (StringUtils.isBlank(name)) {
			name = "test";
		}
		String str = "hello[" + name.trim() + "]WebService test ok!";
		System.out.println(str);
		return str;
	}

}
