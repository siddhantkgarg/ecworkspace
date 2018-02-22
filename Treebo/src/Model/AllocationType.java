package Model;

public enum AllocationType {
	TOP_TO_BOTTOM(0),
	BOTTOM_TO_TOP(1);
	
	private int type;
	
	AllocationType(int type){
		this.type = type;
	}
}

