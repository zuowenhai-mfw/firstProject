package light.mvc.cxf.client;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;

public class Demo {
	public static void main(String[] args) throws Exception {
		JaxWsDynamicClientFactory dynamicClient = JaxWsDynamicClientFactory.newInstance();
		Client client = dynamicClient.createClient("http://localhost:8080/lightmvc/ws/webServiceDemo?wsdl");

		/**
		 * 方法一.
		 * 
		 * **/
		// // CXF动态客户端处理命名空间问题，暂不知道如何解决，调用axis2发布的webservice没有问题【已解决】需要定义targetNamespace
		try {
			Object[] result = client.invoke("helloWs", "test");
			if ((result != null) && (result.length > 0)) {
				System.out.println(result[0]); // 基本类型返回就直接输出,或者强转对应类型
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * 方法二.
		 * 
		 * **/
		// 下面一段处理 WebService接口和实现类namespace不同的情况
		// CXF动态客户端在处理此问题时，会报No operation was found with the name的异常
		Endpoint endpoint = client.getEndpoint();
		QName opName = new QName(endpoint.getService().getName().getNamespaceURI(), "helloWs");
		BindingInfo bindingInfo = endpoint.getEndpointInfo().getBinding();
		if (bindingInfo.getOperation(opName) == null) {
			for (BindingOperationInfo operationInfo : bindingInfo.getOperations()) {
				if ("helloWs".equals(operationInfo.getName().getLocalPart())) {
					opName = operationInfo.getName();
					break;
				}
			}
		}

		Object[] res = client.invoke(opName, "test");
		System.out.println(res[0]);

	}

}