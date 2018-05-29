package ru.sendto.crxmarkets.dto;

/**
 * Exception unchecked exception
 * Produced in case of null landscape array
 * 
 * @author Lev Nadeinsky
 *
 */
public class LandscapeNotDefException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public LandscapeNotDefException() {
		super("Landscape array is not set");
	}
}
