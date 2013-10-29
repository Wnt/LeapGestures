package org.vaadin.leapgestures.client.leapgestures;

public class SwipeGesture extends LeapGesture {

	protected SwipeGesture(){}

    public final native LeapVector getDirection()/*-{
        return this.direction; 
    }-*/;
}
