package edu.mcw.scge.datamodel;

import java.io.Serializable;

/**
 * Created by jthota on 10/9/2019.
 */
public class PersonInfo implements Serializable {
    private int personId;
    private int groupId;
    //private int subGroupId;
    private int roleKey;
    private int grantId;
    private String groupName;
    private String groupType;
    //private String subGroupName;
    private String grantTitle;
    private String grantInitiative;
    private String role;


    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(int roleKey) {
        this.roleKey = roleKey;
    }

    public int getGrantId() {
        return grantId;
    }

    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGrantTitle() {
        return grantTitle;
    }

    public void setGrantTitle(String grantTitle) {
        this.grantTitle = grantTitle;
    }

    public String getGrantInitiative() {
        return grantInitiative;
    }

    public void setGrantInitiative(String grantInitiative) {
        this.grantInitiative = grantInitiative;
    }

}
