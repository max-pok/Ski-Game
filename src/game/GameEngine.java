package game;

import game.competition.Competition;
import game.competition.Competitor;

/**
 * Engine that runs the game step by step.
 */
public class GameEngine {

	/**
	 * Class singleton instance
	 */
	private static GameEngine instance;
	/**
	 * @return singleton instance of the game engine
	 */
	public static GameEngine getInstance() {
		if (instance == null) {
			instance = new GameEngine();
		}
		return instance;
	}

	/**
	 * private empty Ctor for game engine
	 */
	private GameEngine() {}


	/**
	 * play all turns of the competition and print results at the end
	 * @param competition competition to run
	 */
	public void startRace(Competition competition) {
		synchronized (this) {
			for (int i = 0; i < competition.getActiveCompetitors().size(); i++) {
				Thread run = new Thread(competition.getActiveCompetitors().get(i));
				run.start();
			}
		}
	}

	/**
	 * print competition result from first to last based on who crossed the finish line first
	 * @param competition printed competition
	 */
	private void printResults(Competition competition){
		System.out.println("Race results:");
		int place = 1;
		for (Competitor skier : competition.getFinishedCompetitors()) {
			System.out.println(place + ". " + skier);
			place++;
		}
	}
}
