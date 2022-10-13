package UI.PageObject.PageSteps;

import static UI.PageObject.PageElements.LogInElements.*;

public final class LogInElementsSteps {
    public void authorization(String login, String password) {
        logInButton.click();
        usernameField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
    }
}
