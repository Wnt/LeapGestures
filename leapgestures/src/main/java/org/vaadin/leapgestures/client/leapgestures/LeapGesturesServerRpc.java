package org.vaadin.leapgestures.client.leapgestures;

import com.vaadin.shared.communication.ServerRpc;

public interface LeapGesturesServerRpc extends ServerRpc {
    public void circleGestureStart();
    public void swipeGestureStart(float x, float y, float z);
}