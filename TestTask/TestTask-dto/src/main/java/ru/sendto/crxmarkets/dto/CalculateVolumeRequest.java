package ru.sendto.crxmarkets.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sendto.dto.Dto;

/**
 * Dto request 
 * Contains landscape
 * 
 * @author Lev Nadeinsky
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("cvr")
public class CalculateVolumeRequest extends Dto{
	Integer[] levels;
}
