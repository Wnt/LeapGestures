package org.vaadin.leapgestures;

import org.vaadin.leapgestures.LeapGestures.CircleGestureArgs;
import org.vaadin.leapgestures.LeapGestures.CircleGestureListener;
import org.vaadin.leapgestures.LeapGestures.SwipeDownArgs;
import org.vaadin.leapgestures.LeapGestures.SwipeDownListener;
import org.vaadin.leapgestures.LeapGestures.SwipeLeftArgs;
import org.vaadin.leapgestures.LeapGestures.SwipeLeftListener;
import org.vaadin.leapgestures.LeapGestures.SwipeRightArgs;
import org.vaadin.leapgestures.LeapGestures.SwipeRightListener;
import org.vaadin.leapgestures.LeapGestures.SwipeUpArgs;
import org.vaadin.leapgestures.LeapGestures.SwipeUpListener;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("leapgestures")
public class LeapgesturesUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		layout.addComponent(button);
		
		LeapGestures gestureGrabber = LeapGestures.extend(this);
		gestureGrabber.addCircleGestureListener(new CircleGestureListener() {
			
			@Override
			public void onCircleGesture(CircleGestureArgs args) {
				layout.addComponentAsFirst(new Label("CircleGesture received!"));
				
			}
		});
		gestureGrabber.addSwipeUpListener(new SwipeUpListener() {
			
			@Override
			public void onSwipeUp(SwipeUpArgs args) {
				layout.addComponentAsFirst(new Label("SwipeUp received!"));
				
			}
		});
		gestureGrabber.addSwipeDownListener(new SwipeDownListener() {
			
			@Override
			public void onSwipeDown(SwipeDownArgs args) {
				layout.addComponentAsFirst(new Label("SwipeDown received!"));
				
			}
		});
		gestureGrabber.addSwipeRightListener(new SwipeRightListener() {
			
			@Override
			public void onSwipeRight(SwipeRightArgs args) {
				layout.addComponentAsFirst(new Label("SwipeRight received!"));
				
			}
		});
		gestureGrabber.addSwipeLeftListener(new SwipeLeftListener() {
			
			@Override
			public void onSwipeLeft(SwipeLeftArgs args) {
				layout.addComponentAsFirst(new Label("SwipeLeft received!"));
				
			}
		});
	}

}