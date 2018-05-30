package ru.sendto.crxmarkets.webapp.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import ru.sendto.crxmarkets.dto.CalculateVolumeRequest;
import ru.sendto.gwt.client.dom.events.InputEvent;
import ru.sendto.gwt.client.html.Div;
import ru.sendto.gwt.client.html.Input;
import ru.sendto.gwt.client.util.Bus;
import ru.sendto.rest.gwt.Websocket;
/**
 * Input widget for array of integers
 * @author Lev Nadeinsky
 *
 */
public class InputLandscapeView extends Composite {

	interface FormUiBinder extends UiBinder<Widget, InputLandscapeView> {}
	{initWidget(((FormUiBinder)GWT.create(FormUiBinder.class)).createAndBindUi(this));}
	
	@UiField
	Input landscape;
	@UiField
	Div placeholder;
	
	
	
	ArrayList<Integer> array = new ArrayList<>();
	
	@UiHandler("landscape")
	void preprocessInput(InputEvent e) {
		array.clear();
		String text = landscape.getValue();
		text = text.replaceAll("^\\D+", "").replaceAll("\\D+", ", ").trim();
		landscape.setValue(text);
		String[] split = text.split("\\D+");
		for (String num : split) {
			if(num.isEmpty())
				continue;
			array.add(Integer.parseInt(num));
		}
		Bus.getBy("clear").fire("clean");
	}
	

	@UiHandler("landscape")
	void sendRequest(KeyDownEvent e) {
		if(e.getNativeKeyCode()==KeyCodes.KEY_ENTER) {
			Websocket.send(
					new CalculateVolumeRequest()
						.setLevels(getArray()));
		}
	}
	
	/**
	 * get arrays of integers
	 * @return
	 */
	public Integer[] getArray() {
		return  array.toArray(new Integer[array.size()]);
	}

	/**
	 * set tabindex 
	 * @param tabindex 
	 */
	public void setTabIndex(int i) {
		landscape.setTabIndex(i);
	}
	/**
	 * set autoficus
	 * @param isAutoFocus 
	 */
	public void setAutoFocus(Boolean isAutoFocus) {
		landscape.setAttribute("autofocus", isAutoFocus+"");
	}
	public void disable() {
		landscape.disable();
	}
	public void enable() {
		landscape.enable();
	}
}
