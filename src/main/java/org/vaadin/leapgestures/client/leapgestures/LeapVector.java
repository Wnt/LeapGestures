package org.vaadin.leapgestures.client.leapgestures;

import com.google.gwt.core.client.JavaScriptObject;

public class LeapVector extends JavaScriptObject {
    protected LeapVector(){}
    public final native float getX() /*-{ 
        return this[0]; 
    }-*/;
    public final native float getY() /*-{ 
        return this[1]; 
    }-*/;
    public final native float getZ() /*-{ 
        return this[2]; 
    }-*/;
}
