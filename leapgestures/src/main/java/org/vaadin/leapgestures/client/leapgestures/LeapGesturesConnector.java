package org.vaadin.leapgestures.client.leapgestures;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.leapgestures.LeapGestures;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.shared.ui.Connect;

@Connect(LeapGestures.class)
public class LeapGesturesConnector extends AbstractExtensionConnector {
	
	
	public LeapGesturesConnector() {

	}

	@Override
	protected void extend(ServerConnector target) {
		target.addStateChangeHandler(new StateChangeEvent.StateChangeHandler() {

			@Override
			public void onStateChanged(StateChangeEvent stateChangeEvent) {
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						doSomething();
					}
				});
			}
		});

	}

	public native void doSomething()
	/*-{
	 var self = this;
	 var controller = new $wnd.Leap.Controller({enableGestures: true});
	 controller.loop(function(frame) {
	 var gestures = frame.gestures;
	 	if (gestures.length != 0 && gestures[0].state == "start") {
		 		console.log(frame.gestures[0]);
	 		if(gestures[0].type == "swipe") {
		 		self.@org.vaadin.leapgestures.client.leapgestures.LeapGesturesConnector::swipeGestureStart(Lorg/vaadin/leapgestures/client/leapgestures/SwipeGesture;)(frame.gestures[0]);
	 		}
	 		else if(gestures[0].type == "circle") {
	 			self.@org.vaadin.leapgestures.client.leapgestures.LeapGesturesConnector::circleGestureStart(Lorg/vaadin/leapgestures/client/leapgestures/LeapGesture;)(frame.gestures[0]);
	 		}
	 	}
	 });
	 }-*/;
	public void swipeGestureStart(SwipeGesture gesture) {
		Logger.getLogger("LeapGesturesConnector").log(Level.WARNING, gesture.toString());
		Logger.getLogger("LeapGesturesConnector").log(Level.WARNING, gesture.getType() +", " + gesture.getType());
		LeapVector direction = gesture.getDirection();
		Logger.getLogger("LeapGesturesConnector").log(Level.WARNING, direction.toString());
		float x = direction.getX();
		float y = direction.getY();
		float z = direction.getZ();
		Logger.getLogger("LeapGesturesConnector").log(Level.WARNING, "Direction: [" + x + ", " + y + ", " + z + "]");
		
		LeapGesturesServerRpc rpc = getRpcProxy(LeapGesturesServerRpc.class);

		rpc.swipeGestureStart(x, y, z);
	}
	public void circleGestureStart(LeapGesture gesture) {
		Logger.getLogger("LeapGesturesConnector").log(Level.WARNING, gesture.toString());
		LeapGesturesServerRpc rpc = getRpcProxy(LeapGesturesServerRpc.class);

		rpc.circleGestureStart();
		
	}

}
