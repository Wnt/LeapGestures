package org.vaadin.leapgestures.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.leapgestures.LeapGestures;
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
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("LeapGestures Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

	private static final String COLOR1 = "color_one";
	private static final String COLOR2 = "color_two";
	private String[] ACCORDION_CONTENT = {
		"<h1>Swipe down with your finger to begin</h1>",
		"<div style=\"background: url('http://localhost/testData/Early_Morning.jpg') no-repeat; height: 550px; padding: 20px;\"><h1 style=\"text-shadow: 0 0 25px #fff;\">You can change to the upper and lower tab by swiping up or down</h1></div>",
		"<h1>You can also advance to the left or right tab by swiping left or right!</h1>",
		
	};
	private String[] ACCORDION_LABELS = {
		"Start",
		"Up - Down",
		"Left - right",
	};
	private Accordion accordion;
	private TabSheet tabSheet;
	private Label labelWithBackgroundColor;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.leapgestures.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

	@Override
	protected void init(VaadinRequest request) {
		createTabSheet();

		addAccordionTab();

		addWebTab();

		addReloadTab();

		LeapGestures gestureGrabber = LeapGestures.extend(this);
		gestureGrabber.addCircleGestureListener(new CircleGestureListener() {
			@Override
			public void onCircleGesture(CircleGestureArgs args) {
				if (labelWithBackgroundColor.getStyleName().contains(COLOR2)) {
					labelWithBackgroundColor.addStyleName(COLOR1);
					labelWithBackgroundColor.removeStyleName(COLOR2);
				}
				else {
					labelWithBackgroundColor.addStyleName(COLOR2);
					labelWithBackgroundColor.removeStyleName(COLOR1);
				}
				
			}
		});
		gestureGrabber.addSwipeUpListener(new SwipeUpListener() {
			@Override
			public void onSwipeUp(SwipeUpArgs args) {
				toNextTab(accordion);
			}

		});
		gestureGrabber.addSwipeDownListener(new SwipeDownListener() {
			@Override
			public void onSwipeDown(SwipeDownArgs args) {
				toPreviousTab(accordion);
			}

		});
		gestureGrabber.addSwipeRightListener(new SwipeRightListener() {
			@Override
			public void onSwipeRight(SwipeRightArgs args) {
				toNextTab(tabSheet);
			}
		});
		gestureGrabber.addSwipeLeftListener(new SwipeLeftListener() {
			@Override
			public void onSwipeLeft(SwipeLeftArgs args) {
				toPreviousTab(tabSheet);
			}
		});
	}

	private void addReloadTab() {
		labelWithBackgroundColor = new Label("<h1>You can change the backgroundcolor of this tab by doing a circle gesture<h2>", ContentMode.HTML);
		tabSheet.addTab(labelWithBackgroundColor, "More gestures");
		labelWithBackgroundColor.setSizeFull();
	}

	private void createTabSheet() {
		tabSheet = new TabSheet();
		setContent(tabSheet);
		tabSheet.setHeight(100, Unit.PERCENTAGE);
	}

	private void addAccordionTab() {
		accordion = new Accordion();
		accordion.setHeight(100, Unit.PERCENTAGE);

		tabSheet.addTab(accordion, "Accordion");

		addAccordionTabs();
	}

	private void addWebTab() {
		BrowserFrame frame = new BrowserFrame("Browser", new ExternalResource(
				"https://rawgithub.com/roboleary/LeapCursor.js/master/demo.html"));
		tabSheet.addTab(frame, "Web");
		frame.setSizeFull();
	}

	private void addAccordionTabs() {
		for (int i = 0; i < ACCORDION_CONTENT.length; i++) {
			final VerticalLayout subLayout = new VerticalLayout(new Label(
					ACCORDION_CONTENT[i], ContentMode.HTML));
			subLayout.setMargin(true);
			accordion.addTab(subLayout, ACCORDION_LABELS[i]);
		}
	}

	private int getSelectedTabIndex(TabSheet ts) {
		Component selectedTabComponent = ts.getSelectedTab();
		Tab selectedTab = ts.getTab(selectedTabComponent);
		int index = ts.getTabPosition(selectedTab);
		return index;
	}

	private void selectTab(TabSheet ts, int index) {
		ts.setSelectedTab(ts.getTab(index));
	}

	private void toNextTab(TabSheet ts) {
		int index = getSelectedTabIndex(ts);
		if (index != 0) {
			selectTab(ts, index - 1);
		}
	}

	private void toPreviousTab(TabSheet ts) {
		int index = getSelectedTabIndex(ts);
		if (index != ts.getComponentCount()) {
			selectTab(ts, index + 1);
		}
	}

}