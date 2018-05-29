package ru.sendto.crxmarkets.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sendto.dto.Dto;

/**
 * Result wrapper
 * Contains volume of wells
 * 
 * @author Lev Nadeinsky
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@JsonTypeName("vol")
public class Volume extends Dto{
	long value;
}