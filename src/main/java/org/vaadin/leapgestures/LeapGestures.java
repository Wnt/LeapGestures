package org.vaadin.leapgestures;

import java.util.EventListener;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

import org.vaadin.leapgestures.client.leapgestures.LeapGesturesServerRpc;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.UI;

@JavaScript({ "leap.js" })
public class LeapGestures extends AbstractExtension {
	private LeapGesturesServerRpc rpc = new LeapGesturesServerRpc() {

		@Override
		public void swipeGestureStart(float x, float y, float z) {
			float absX = Math.abs(x);
			float absY = Math.abs(y);
			float absZ = Math.abs(x);
			// if X component is the biggest
			if (absX > absY && absX > absZ) {
				// if x is positive
				if (x > 0f) {
					raiseSwipeLeftEvent(new SwipeLeftArgs(new Object()));
				}
				else {
					raiseSwipeRightEvent(new SwipeRightArgs(new Object()));
				}
			}
			else if(absY > absX && absY > absZ) {
				if (y > 0f) {
					raiseSwipeUpEvent(new SwipeUpArgs(new Object()));
				}
				else {
					raiseSwipeDownEvent(new SwipeDownArgs(new Object()));
				}
			}
		}

		@Override
		public void circleGestureStart() {
			
			raiseCircleGestureEvent(new CircleGestureArgs(new Object()));
		}
	};
	public LeapGestures() {
		registerRpc(rpc);
	}

	public static LeapGestures extend(UI ui) {
		LeapGestures g = new LeapGestures();
		g.extend((AbstractClientConnector) ui);
		return g;
	}
	
	/* Event listener stuff */

	

	private final List<SwipeLeftListener> SwipeLeftListeners = new LinkedList<SwipeLeftListener>();

	public final void addSwipeLeftListener(SwipeLeftListener listener) {
		synchronized (SwipeLeftListeners) {
			SwipeLeftListeners.add(listener);
		}
	}

	public final void removeSwipeLeftListener(SwipeLeftListener listener) {
		synchronized (SwipeLeftListeners) {
			SwipeLeftListeners.remove(listener);
		}
	}

	private void raiseSwipeLeftEvent(SwipeLeftArgs args) {
		synchronized (SwipeLeftListeners) {
			for (SwipeLeftListener listener : SwipeLeftListeners)
				listener.onSwipeLeft(args);
		}
	}

	public interface SwipeLeftListener extends EventListener {
		public void onSwipeLeft(SwipeLeftArgs args);
	}

	public class SwipeLeftArgs extends EventObject {
		public SwipeLeftArgs(Object source) {
			super(source);
		}
	}
	

	private final List<SwipeRightListener> SwipeRightListeners = new LinkedList<SwipeRightListener>();

	public final void addSwipeRightListener(SwipeRightListener listener) {
		synchronized (SwipeRightListeners) {
			SwipeRightListeners.add(listener);
		}
	}

	public final void removeSwipeRightListener(SwipeRightListener listener) {
		synchronized (SwipeRightListeners) {
			SwipeRightListeners.remove(listener);
		}
	}

	private void raiseSwipeRightEvent(SwipeRightArgs args) {
		synchronized (SwipeRightListeners) {
			for (SwipeRightListener listener : SwipeRightListeners)
				listener.onSwipeRight(args);
		}
	}

	public interface SwipeRightListener extends EventListener {
		public void onSwipeRight(SwipeRightArgs args);
	}

	public class SwipeRightArgs extends EventObject {
		public SwipeRightArgs(Object source) {
			super(source);
		}
	}
	

	private final List<SwipeDownListener> SwipeDownListeners = new LinkedList<SwipeDownListener>();

	public final void addSwipeDownListener(SwipeDownListener listener) {
		synchronized (SwipeDownListeners) {
			SwipeDownListeners.add(listener);
		}
	}

	public final void removeSwipeDownListener(SwipeDownListener listener) {
		synchronized (SwipeDownListeners) {
			SwipeDownListeners.remove(listener);
		}
	}

	private void raiseSwipeDownEvent(SwipeDownArgs args) {
		synchronized (SwipeDownListeners) {
			for (SwipeDownListener listener : SwipeDownListeners)
				listener.onSwipeDown(args);
		}
	}

	public interface SwipeDownListener extends EventListener {
		public void onSwipeDown(SwipeDownArgs args);
	}

	public class SwipeDownArgs extends EventObject {
		public SwipeDownArgs(Object source) {
			super(source);
		}
	}
	

	private final List<SwipeUpListener> SwipeUpListeners = new LinkedList<SwipeUpListener>();

	public final void addSwipeUpListener(SwipeUpListener listener) {
		synchronized (SwipeUpListeners) {
			SwipeUpListeners.add(listener);
		}
	}

	public final void removeSwipeUpListener(SwipeUpListener listener) {
		synchronized (SwipeUpListeners) {
			SwipeUpListeners.remove(listener);
		}
	}

	private void raiseSwipeUpEvent(SwipeUpArgs args) {
		synchronized (SwipeUpListeners) {
			for (SwipeUpListener listener : SwipeUpListeners)
				listener.onSwipeUp(args);
		}
	}

	public interface SwipeUpListener extends EventListener {
		public void onSwipeUp(SwipeUpArgs args);
	}

	public class SwipeUpArgs extends EventObject {
		public SwipeUpArgs(Object source) {
			super(source);
		}
	}
	

	private final List<CircleGestureListener> CircleGestureListeners = new LinkedList<CircleGestureListener>();

	public final void addCircleGestureListener(CircleGestureListener listener) {
		synchronized (CircleGestureListeners) {
			CircleGestureListeners.add(listener);
		}
	}

	public final void removeCircleGestureListener(CircleGestureListener listener) {
		synchronized (CircleGestureListeners) {
			CircleGestureListeners.remove(listener);
		}
	}

	private void raiseCircleGestureEvent(CircleGestureArgs args) {
		synchronized (CircleGestureListeners) {
			for (CircleGestureListener listener : CircleGestureListeners)
				listener.onCircleGesture(args);
		}
	}

	public interface CircleGestureListener extends EventListener {
		public void onCircleGesture(CircleGestureArgs args);
	}

	public class CircleGestureArgs extends EventObject {
		public CircleGestureArgs(Object source) {
			super(source);
		}
	}
}
