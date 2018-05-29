package ru.sendto.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("cvr")
public class CalculateVolumeRequest extends Dto{
	Integer[] levels;
}
