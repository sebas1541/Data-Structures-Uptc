package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;

public class FamilyGroup {
    private String groupId;
    private List<User> members;

    public FamilyGroup(String groupId) {
        this.groupId = groupId;
        this.members = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<User> getMembers() {
        return members;
    }

    public void addMember(User member) {
        members.add(member);
    }

    public void removeMember(User member) {
        members.remove(member);
    }
}
