package agentminisima;

public enum Actions {
	MOVE_FORWARD,
	TURN_LEFT,
	TURN_RIGHT,
	EAT;
	
	public static String[] getAllActions() {
		String[] result = new String[4];
		result[0] = Actions.EAT.toString();
		result[1] = Actions.MOVE_FORWARD.toString();
		result[2] = Actions.TURN_LEFT.toString();
		result[3] = Actions.TURN_RIGHT.toString();
		
		return result;
	}
}
