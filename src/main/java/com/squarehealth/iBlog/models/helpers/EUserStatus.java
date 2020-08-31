package com.squarehealth.iBlog.models.helpers;

public enum EUserStatus {
	    ACTIVE("Draft"),
	    
	    BLOCKED("Published"),
	    
		INACTIVE("Need Admin Approval to be published");
		
	    private String name;

	    EUserStatus(String name){
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getId(){
	        return name();
	    }

	    @Override
	    public String toString() {
	        return getName();
	    }


}
