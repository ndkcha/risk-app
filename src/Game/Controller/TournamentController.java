package Game.Controller;

import Game.View.TournamentView;

public class TournamentController {
    private TournamentView tournamentView = new TournamentView();

    TournamentController() { }

    public void start() {
        tournamentView.initComponents();
    }
}
