package org.vaadin.leapgestures.client.leapgestures;

import com.google.gwt.core.client.JavaScriptObject;

public class LeapGesture extends JavaScriptObject {
    protected LeapGesture(){}
    public final native String getState()/*-{ 
        return this.state; 
    }-*/;
    public final native String getType()/*-{ 
        return this.type; 
    }-*/;
}