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

	public static class PacketEndGame {		
		public String message="EndGame";
		public int idWinner=0;//0 to przerwanie gry w trakcie;
	}

	public static class PacketRestartGame {		
		public String message="RestartGame";		
	}
}
