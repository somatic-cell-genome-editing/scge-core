package edu.mcw.scge.datamodel;

/**
 * Created by jthota on 10/9/2019.
 */
public class PersonInfo {
    private int personId;
    private int groupId;
    private int subGroupId;
    private int roleKey;
    private int grantId;
    private String groupName;
    private String subGroupName;
    private String grantTitle;
    private String grantInitiative;
    private String role;
    private String group;
    private String subgroup;

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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
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

    public String getSubGroupName() {
        return subGroupName;
    }

    public void setSubGroupName(String subGroupName) {
        this.subGroupName = subGroupName;
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

    public int getSubGroupId() {
        return subGroupId;
    }

    public void setSubGroupId(int subGroupId) {
        this.subGroupId = subGroupId;
    }
}
