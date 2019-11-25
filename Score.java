package Football;

public class Score extends RoundResult {
	Team team;
	Player player;

	Score(Team team, Player player) {
		this.team = team;
		this.player = player;
	}
}
