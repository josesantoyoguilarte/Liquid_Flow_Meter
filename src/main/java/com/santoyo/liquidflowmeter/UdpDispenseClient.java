package com.santoyo.liquidflowmeter;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sends {@link DispenseRequest} payloads to the Raspberry Pi UDP dispenser server.
 */
public final class UdpDispenseClient {

    private static final Logger LOG = LoggerFactory.getLogger(UdpDispenseClient.class);

    private final String host;

    public UdpDispenseClient(String host) {
        this.host = host;
    }

    /**
     * Send the given request to the configured host on the port matching the drink type.
     *
     * @throws IOException if the datagram cannot be sent
     */
    public void send(DispenseRequest request) throws IOException {
        byte[] payload = Integer.toString(request.millilitres()).getBytes(StandardCharsets.UTF_8);
        InetAddress address = InetAddress.getByName(host);
        DatagramPacket packet = new DatagramPacket(payload, payload.length, address, request.drink().port());

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.send(packet);
            LOG.info("Sent {} ml request for {} to {}:{}",
                    request.millilitres(), request.drink(), host, request.drink().port());
        }
    }
}
