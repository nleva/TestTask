package ru.sendto.crxmarkets.webapp.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import ru.sendto.crxmarkets.dto.CalculateVolumeRequest;
import ru.sendto.crxmarkets.dto.Volume;
import ru.sendto.dto.ErrorDto;
import ru.sendto.gwt.client.html.Div;
import ru.sendto.gwt.client.html.Span;
import ru.sendto.gwt.client.util.Bus;

/**
 * Widget for request results rendering
 * 
 * @author Lev Nadeinsky
 *
 */
public class VolumeView extends Composite {

	interface FormUiBinder extends UiBinder<Widget, VolumeView> {}
	{initWidget(((FormUiBinder)GWT.create(FormUiBinder.class)).createAndBindUi(this));}
	
	@UiField
	Div view;
	@UiField
	Span value;
	@UiField
	Span fail;

	{Bus.getBy(CalculateVolumeRequest.class).listen(Volume.class, this::setVolume);}
	void setVolume(Volume dto) {
		value.setText(dto.getValue()+"");
	}
	
	{Bus.getBy(CalculateVolumeRequest.class).listen(ErrorDto.class, this::setError);}
	void setError(ErrorDto dto) {
		fail.setText(dto.getError());
		value.setText("");
	}
	{Bus.getBy("clear").listen(String.class, this::clearError);}
	void clearError(String dto) {
		fail.setText("");
	}
	
}
