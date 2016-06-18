/**
 * 
 */
package com.ems.user.constants;

/**
 * @author Bharath Arya
 *
 */
public enum DbConstantsEnum {
	
	// User related constants
	REGISTERED("REGISTERED"),
	SELF_REGISTERED("SELF_REGISTERED"),
	ACTIVE("ACTIVE"),  
	INACTIVE("INACTIVE"),
	DELETED("DELETED"),
	// Gender related constants
	MALE("MALE"),
	FEMALE("FEMALE"),
	TRANS_GENDER("TRANS_GENDER"),
	// Event related constants
	CREATED("CREATED"), 
	CONFIRMED("CONFIRMED"), 
	PENDING("PENDING"), 
	SUSPENDED("SUSPENDED"), 
	INPROGRESS("INPROGRESS"),
	CANCELED("CANCELED"),
	CLOSED("CLOSED"),
	COMPLETED("COMPLETED"),
	// Medical Certificate related constants
	NA("NA"),
	TYPE1("TYPE1"),
	TYPE2("TYPE2"),
	TYPE3("TYPE3"),
	// Difficulty Grade related constants
	GRADE1("GRADE1"),
	GRADE2("GRADE2"),
	GRADE3("GRADE3"),
	GRADE4("GRADE4"),
	GRADE5("GRADE5"),
	// Fittness Level related constants
	LEVEL1("LEVEL1"),
	LEVEL2("LEVEL2"),
	LEVEL3("LEVEL3"),
	LEVEL4("LEVEL4"),
	// Event type related constants
	TREK("TREK"),
	EXPEDITION("EXPEDITION"),
	COURSE("COURSE"),
	SEMINAR("SEMINAR"),
	WORKSHOP("WORKSHOP"),
	// Partner type related constants
	INDIVIDUAL("INDIVIDUAL"),
	ORGANIZATION("ORGANIZATION"),
	// Admin type related constants
	SUPER_ADMIN("SUPER_ADMIN"),
	ADMIN("ADMIN"),
	// Event Registration status related constants
	PAYED("PAYED"),
	PARTICIPATED("PARTICIPATED");
	
	private final String value;

	DbConstantsEnum(String value) {
        this.value = value;
    }
	
	public String value() {
        return value;
    }

    public static DbConstantsEnum fromValue(String value) {
        for (DbConstantsEnum userType : DbConstantsEnum.values()) {
            if (userType.value.equals(value)) {
                return userType;
            }
        }
        throw new IllegalArgumentException(value);
    }

}