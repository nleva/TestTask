package ru.sendto.crxmarkets.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sendto.dto.Dto;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("cvr")
public class CalculateVolumeRequest extends Dto{
	Integer[] levels;
}
