package Model;

import java.io.Serializable;

public enum CourseType implements Serializable{
	ONLINE("Online"),
	CLASSROOM("Classroom")
	;
	
	private final String text;

    
    private CourseType(final String text) {
        this.text = text;
    }

    
    @Override
    public String toString() {
        return text;
    }
    
    
	
	
}
