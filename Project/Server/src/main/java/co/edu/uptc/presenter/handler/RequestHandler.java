package co.edu.uptc.presenter.handler;

import co.edu.uptc.model.UserManager;
import com.google.gson.Gson;
import java.io.DataOutputStream;
import java.io.IOException;

public interface RequestHandler {
    void handle(String data, DataOutputStream output, UserManager userManager, Gson gson) throws IOException;
}
