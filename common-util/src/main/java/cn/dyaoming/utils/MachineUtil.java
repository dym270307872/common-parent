package cn.dyaoming.utils;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * <p>设备工具类</p>
 * @author dym
 *
 */
public class MachineUtil {

	/**
	 * 获取当前机器端口号
	 * 
	 * @throws MalformedObjectNameException
	 * @throws MBeanException 
	 * @throws ReflectionException 
	 * @throws AttributeNotFoundException 
	 * @throws InstanceNotFoundException 
	 */
	public static String getLocalPort() throws Exception {
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("*:type=Connector,*"), null);
		if (objectNames == null || objectNames.size() <= 0) {
			throw new IllegalStateException("Cannot get the names of MBeans controlled by the MBean server.");
		}
		for (ObjectName objectName : objectNames) {
			String protocol = String.valueOf(mBeanServer.getAttribute(objectName, "protocol"));
			String port = String.valueOf(mBeanServer.getAttribute(objectName, "port"));
			// windows下属性名称为HTTP/1.1, linux下为org.apache.coyote.http11.Http11NioProtocol
			if ("HTTP/1.1".equals(protocol) || "org.apache.coyote.http11.Http11NioProtocol".equals(protocol)) {
				return port;
			}
		}
		throw new IllegalStateException("Failed to get the HTTP port of the current server");
	}
 
	/**
	 * 获取当前机器的IP
	 * 
	 * @throws UnknownHostException
	 */
	public static String getLocalIp() throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		return ipAddrStr;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(getLocalIp());
	}

}
