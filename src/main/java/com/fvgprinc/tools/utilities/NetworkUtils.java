package com.fvgprinc.tools.utilities;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
/**
 *
 * @author Ryzen9-Gaming
 */
public class NetworkUtils {

    public static String getLocalIPAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // Filtramos interfaces inactivas o de loopback (127.0.0.1)
                if (iface.isLoopback() || !iface.isUp()) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    // Buscamos una dirección IPv4 que no sea local
                    if (addr instanceof java.net.Inet4Address) {
                        return addr.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            return "127.0.0.1"; // Fallback en caso de error
        }
        return "127.0.0.1";
    }
}
