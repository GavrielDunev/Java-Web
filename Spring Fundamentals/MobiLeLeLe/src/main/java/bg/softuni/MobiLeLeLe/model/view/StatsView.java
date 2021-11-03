package bg.softuni.MobiLeLeLe.model.view;

public class StatsView {

    private final int authRequest;
    private final int anonymousRequest;

    public StatsView(int authRequest, int anonymousRequest) {
        this.authRequest = authRequest;
        this.anonymousRequest = anonymousRequest;
    }

    public int getAuthRequest() {
        return authRequest;
    }


    public int getAnonymousRequest() {
        return anonymousRequest;
    }


    public int getTotalRequest() {
        return this.authRequest + this.anonymousRequest;
    }
}
