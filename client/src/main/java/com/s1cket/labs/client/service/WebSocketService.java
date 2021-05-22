package com.s1cket.labs.client.service;

import com.s1cket.labs.client.controller.user.chat.HistoryCotroller;
import com.s1cket.labs.client.events.IFrameListener;
import com.s1cket.labs.client.model.dto.EnvelopeDto;
import com.s1cket.labs.client.model.dto.EnvelopeWebDto;
import com.s1cket.labs.client.model.dto.UserDto;
import com.s1cket.labs.client.service.exception.ValidationException;
import org.mapstruct.control.MappingControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class WebSocketService {
    private final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    private static final String URL = "http://localhost:8080/mess";
    private static final String SEND_ADDRESS_FORMAT = "/app/chat/%s/%s/";
    private static final String SUBSCRIBE_ADDRESS_FORMAT = "/topic/chat/%s/private/";

    private List<IFrameListener> listeners = new LinkedList<>();

    private WebSocketStompClient stompClient;
    private StompSession stompSession;

    private EnvelopeService envelopeService;
    private UserDto userDto;

    @Autowired
    public WebSocketService(EnvelopeService envelopeService) {
        this.envelopeService = envelopeService;
        stompClient = new WebSocketStompClient(createWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        logger.info("Created WebSocketService");
    }

    public boolean connect(UserDto userDto) {
        boolean result = false;
        this.userDto = userDto;
        try {
            stompSession = stompClient.connect(URL, new ClientStompSessionHandler()).get(5, SECONDS);
            result = true;
            logger.info("Connected to " + URL);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean send(EnvelopeWebDto envelope) {
        boolean result = false;
        if (stompSession.isConnected()) {
            stompSession.send(
                    String.format(SEND_ADDRESS_FORMAT, userDto.getAddress(), envelope.getAddressTo()),
                    envelope);
            result = true;
        }

        return result;
    }

    private WebSocketClient createWebSocketClient() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return new SockJsClient(transports);
    }

    public void registerListener(IFrameListener listener) {
        listeners.add(listener);
    }

    private class ClientStompSessionHandler extends StompSessionHandlerAdapter {

        private final Logger logger = LoggerFactory.getLogger(ClientStompSessionHandler.class);

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return EnvelopeWebDto.class;
        }

        @Override
        public void afterConnected(StompSession session, StompHeaders headers) {
            logger.info("Client connected: headers {}", headers);
            session.subscribe(String.format(SUBSCRIBE_ADDRESS_FORMAT, userDto.getAddress()), this);
            logger.info("Subscribed to " + String.format(SUBSCRIBE_ADDRESS_FORMAT, userDto.getAddress()));
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            logger.info("Client received: payload {}, headers {}", payload, headers);
            // Save envelope
            EnvelopeDto envelopeDto;
            try {
                envelopeDto = envelopeService.save((EnvelopeWebDto) payload, userDto);
            } catch (ValidationException e) {
                logger.error(e.getMessage());
                return;
            }
            // Notify UI about envelope
            listeners.forEach(l -> l.onFrameReceive(envelopeDto));
        }

        @Override
        public void handleException(StompSession session, StompCommand command,
                                    StompHeaders headers, byte[] payload, Throwable exception) {
            logger.error("Client error: exception {}, command {}, payload {}, headers {}",
                    exception.getMessage(), command, payload, headers);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            logger.error("Client transport error: error {}", exception.getMessage());
        }
    }
}