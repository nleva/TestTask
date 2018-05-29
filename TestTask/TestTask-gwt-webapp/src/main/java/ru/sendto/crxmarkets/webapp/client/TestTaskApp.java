package ru.sendto.crxmarkets.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import ru.sendto.crxmarkets.webapp.client.view.Main;
import ru.sendto.rest.gwt.URest;
import ru.sendto.rest.gwt.Websocket;

/**
 * TestTask entry point class
 * @author Lev Nadeinsky
 *
 */
public class TestTaskApp implements EntryPoint {

	/**
	 * entry point
	 * init websocket
	 * init Rest
	 * add view to DOM
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void onModuleLoad() {
		URest.setServiceRoot(Window.Location.getProtocol()+"//"+Window.Location.getHost() + "/rst");
		Websocket.init(Window.Location.getProtocol()+"//"+Window.Location.getHost() + "/ws");
		RootPanel.get().add(new Main());
	}

}
