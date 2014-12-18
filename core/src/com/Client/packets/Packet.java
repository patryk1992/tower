package com.Client.packets;



public class Packet {
	public static class Packet0LoginRequest {	
	}

	public static class Packet1LoginAnswer {
		public boolean accepted =false;
		public int connections ;
	}

	public static class Packet2Message {
		public String message;
	}
	public static class PacketEndGameRequest {		
		public String message="EndGameRequest";
	}
	public static class PacketEndGameAnswer {		
		public String message="EndGameRequest";
	}
	
}
