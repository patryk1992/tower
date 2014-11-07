package com.Client;

import com.badlogic.gdx.math.Vector2;

public class Packet {
	public static class Packet0LoginRequest {
	
	}

	public static class Packet1LoginAnswer {
		boolean accepted =false;
		
	}

	public static class Packet2Message {
		public String message;
	}
	
}
