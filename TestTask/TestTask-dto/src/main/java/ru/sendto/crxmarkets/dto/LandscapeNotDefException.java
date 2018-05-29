package ru.sendto.crxmarkets.dto;

public class LandscapeNotDefException extends RuntimeException {
	public LandscapeNotDefException() {
		super("Landscape array is not set");
	}
}
