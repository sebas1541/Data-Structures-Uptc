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

    // Getters and Setters
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<User> getMembers() {
        return members;
    }

    // Methods to manage members
    public void addMember(User member) {
        members.add(member);
    }

    public void removeMember(User member) {
        members.remove(member);
    }

    public boolean isMember(String username) {
        for (User member : members) {
            if (member.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


    public User getMemberById(String userId) {
        for (User member : members) {
            if (member.getUserId().equals(userId)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "FamilyGroup{" +
                "groupId='" + groupId + '\'' +
                ", members=" + members +
                '}';
    }

    // Additional functionality
    public int getTotalMembers() {
        return members.size();
    }

    public void clearAllMembers() {
        members.clear();
    }
}
