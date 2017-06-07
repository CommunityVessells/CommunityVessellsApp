package org.ffcc.communityVessells.app.helpers;


public class Helpers {

	public Helpers() {
		// TODO Auto-generated constructor stub
	}

	public static int returnIntFromBoolean(boolean input){
		return input ? 1 : 0;
	}
	
	public static boolean returnBooleanFromInt(int input){
		return input==1;
	}
}
