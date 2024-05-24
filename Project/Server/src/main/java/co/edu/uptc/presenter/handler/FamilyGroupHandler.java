package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.User;
import co.edu.uptc.model.UserManager;
import co.edu.uptc.net.FamilyMemberData;
import co.edu.uptc.net.Response;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;

public class FamilyGroupHandler {
    private UserManager userManager;
    private Gson gson;

    public FamilyGroupHandler(UserManager userManager, Gson gson) {
        this.userManager = userManager;
        this.gson = gson;
    }

    public void addMember(String data, DataOutputStream output) throws IOException {
        FamilyMemberData familyMemberData = gson.fromJson(data, FamilyMemberData.class);
        User user = userManager.getUserByUsername(familyMemberData.getUserId());
        if (user != null) {
            if (user.isMemberAlreadyAdded(familyMemberData.getUsername())) {
                String responseJson = gson.toJson(new Response("error", "Member already added"));
                output.writeUTF(responseJson);
            } else {
                User newMember = userManager.getUserByUsername(familyMemberData.getUsername());
                if (newMember != null) {
                    user.addFamilyMember(newMember);
                    String responseJson = gson.toJson(new Response("success", "Member added successfully"));
                    output.writeUTF(responseJson);
                } else {
                    String responseJson = gson.toJson(new Response("error", "User to be added as a member not found"));
                    output.writeUTF(responseJson);
                }
            }
        } else {
            String responseJson = gson.toJson(new Response("error", "User not found"));
            output.writeUTF(responseJson);
        }
    }


    public void viewMembers(String data, DataOutputStream output) throws IOException {
        User user = userManager.getUserByUsername(data);
        if (user != null) {
            if (user.getFamilyGroup() != null) {
                String membersJson = gson.toJson(user.getFamilyGroup().getMembers());
                String responseJson = gson.toJson(new Response("success", membersJson));
                output.writeUTF(responseJson);
            } else {
                String responseJson = gson.toJson(new Response("error", "No family group found"));
                output.writeUTF(responseJson);
            }
        } else {
            String responseJson = gson.toJson(new Response("error", "User not found"));
            output.writeUTF(responseJson);
        }
    }
}
