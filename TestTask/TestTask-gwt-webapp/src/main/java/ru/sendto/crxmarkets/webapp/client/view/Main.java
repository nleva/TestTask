package ru.sendto.crxmarkets.webapp.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import ru.sendto.crxmarkets.dto.CalculateVolumeRequest;
import ru.sendto.crxmarkets.dto.Volume;
import ru.sendto.dto.ErrorDto;
import ru.sendto.gwt.client.html.Div;
import ru.sendto.gwt.client.html.Log;
import ru.sendto.gwt.client.util.Bus;
import ru.sendto.rest.gwt.URest;
import ru.sendto.rest.gwt.Websocket;

/**
 * Main view of TestTask
 * 
 * @author Lev Nadeinsky
 *
 */
public class Main extends Composite {

	interface FormUiBinder extends UiBinder<Widget, Main> {}
	{initWidget(((FormUiBinder)GWT.create(FormUiBinder.class)).createAndBindUi(this));}
	
	@UiField
	Div view;
	@UiField
	InputLandscapeView wsInput;

	@UiField
	Div sendByWs;
	@UiField
	Div sendByRst;
	
	
	@UiHandler("sendByWs")
	void sendWsRequest(ClickEvent e) {
		sendWsRequest();
	}
	@UiHandler("sendByRst")
	void sendRstRequest(ClickEvent e) {
		sendRstRequest();
	}
	
	@UiHandler("sendByWs")
	void sendWsRequest(KeyDownEvent e) {
		sendWsRequest();
	}
	
	@UiHandler("sendByRst")
	void sendRstRequest(KeyDownEvent e) {
		sendRstRequest();
	}
	
	void sendWsRequest() {
		wsInput.disable();
		sendByWs.disable();
		sendByRst.disable();
		Websocket.send(new CalculateVolumeRequest()
				.setLevels(wsInput.getArray()));
		
	}
	
	void sendRstRequest() {
		wsInput.disable();
		sendByWs.disable();
		sendByRst.disable();
		URest.send(new CalculateVolumeRequest()
				.setLevels(wsInput.getArray()));
	}
	

	{Bus.getBy(CalculateVolumeRequest.class).listen(Volume.class, this::printResult);}
	void printResult(Volume v) {
		Log.console("volume: " + v.getValue());
	}
	
	{Bus.getBy(CalculateVolumeRequest.class).listen(Volume.class, this::enableInput);}
	void enableInput(Volume v) {
		wsInput.enable();
		sendByWs.enable();
		sendByRst.enable();
	}
	
	{Bus.getBy(CalculateVolumeRequest.class).listen(ErrorDto.class, this::printError);}	
	void printError(ErrorDto v) {
		Log.console("exception:" + v.getError());
	}
	
	{Bus.getBy(CalculateVolumeRequest.class).listen(ErrorDto.class, this::enableInput);}
	void enableInput(ErrorDto v) {
		wsInput.enable();
		sendByWs.enable();
		sendByRst.enable();
	}
	
}
