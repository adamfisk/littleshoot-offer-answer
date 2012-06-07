package org.lastbamboo.common.offer.answer;

/**
 * Convenience class for easily configuring ICE.
 */
public class IceConfig {
    
    private static boolean tcp = true;
    private static boolean udp = true;
    
    private static String[] cipherSuites = null;
    
    private static boolean disableUdpOnLocalNetwork = true;
    
    /**
     * Sets whether or not to use UDP.
     * 
     * @param udp Whether or not to use UDP.
     */
    public static void setUdp(final boolean udp) {
        IceConfig.udp = udp;
    }

    /**
     * Accessor for whether or not to negotiate UDP connections.
     * 
     * @return <code>true</code> if we should negotiate UDP connections, 
     * otherwise <code>false</code>.
     */
    public static boolean isUdp() {
        return udp;
    }

    /**
     * Sets whether or not to use TCP.
     * 
     * @param udp Whether or not to use TCP.
     */
    public static void setTcp(final boolean tcp) {
        IceConfig.tcp = tcp;
    }

    /**
     * Accessor for whether or not to negotiate TCP connections.
     * 
     * @return <code>true</code> if we should negotiate TCP connections, 
     * otherwise <code>false</code>.
     */
    public static boolean isTcp() {
        return tcp;
    }

    public static void setCipherSuites(final String[] cipherSuites) {
        IceConfig.cipherSuites = cipherSuites;
    }

    public static String[] getCipherSuites() {
        return cipherSuites;
    }

    public static void setDisableUdpOnLocalNetwork(
        final boolean disableUdpOnLocalNetwork) {
        IceConfig.disableUdpOnLocalNetwork = disableUdpOnLocalNetwork;
    }

    public static boolean isDisableUdpOnLocalNetwork() {
        return disableUdpOnLocalNetwork;
    }

}
