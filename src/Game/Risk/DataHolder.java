package Game.Risk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import Game.Controller.StartupController;
import Game.Model.ContinentData;
import Game.Model.CountryData;
import Game.Model.GameLogsData;
import Game.Model.MapData;
import Game.Model.PhaseData;
import Game.Model.Player;
import Game.View.GameLogsView;

/**
 * A singleton class to hold the entire data set throughout the application.
 * 
 * @author Jay, ndkcha
 * @version 1.2.0
 */
public class DataHolder implements Serializable {
    /** A communication bridge to get the GameLogs flowing thru the application */
    private GameLogsData gameLogs = new GameLogsData();
    /** a holder that manipulates the phases */
    private PhaseData phaseData = new PhaseData();
    /** instance of the singleton class */
    private static DataHolder dataHolder;
    /** List of continents on the map */
    private List<ContinentData> continentDataList = new ArrayList<>();
    /** List of countries on the map */
    private List<CountryData> countryDataList = new ArrayList<>();
    /** List of player in the game */
    private HashMap<String, Player> playerList = new HashMap<>();
    /** List of number of armies a player has */
    private HashMap<Integer,Integer> playersArmiesList=new HashMap<>();
    /** list of players conquered in the game **/
    private List<Player> conqueredPlayerList = new  ArrayList<>();
    /** Meta data of the map */
    public MapData mapData = new MapData();
    /** Image file of the map */
    public File bmpFile;
    /** Is the armies distribution be automatic or manual? */
    public boolean isArmiesAutomatic = false;
    /** max limit for the turns */
    public int maxTurnLimit = 20;

    public void refreshHolder() {
        countryDataList.clear();
        continentDataList.clear();
        playersArmiesList.clear();
        conqueredPlayerList.clear();
        mapData.cleanUpMapData();
        phaseData.refreshPhase();
        phaseData.setTotalPlayers(this.getPlayerList().size());
        gameLogs.logs.clear();

        for (Map.Entry<String, Player> playerEntry : this.playerList.entrySet()) {
            String name = playerEntry.getKey();
            Player player = playerEntry.getValue();

            player.resetPlayer();

            this.playerList.put(name, player);
        }
    }

    /**
     * Sets the game identifier in the tournament mode
     * @param id id of the game
     */
    public void setGameId(String id) {
        this.phaseData.setGameId(id);
    }

    /** 
     * Returns the active phase
     * @return phaseData current phase 
     */
    public int getCurrentPhase() {
        return this.phaseData.getCurrentPhase();
    }

    /**
     * Attaches the observer to feed data into the logs view
     * @param obj the object to attach
     */
    public void attachObserverToLogsView(Observer obj) {
        this.gameLogs.addObserver(obj);
    }

    /**
     * It sends the game logs to the appropriate view.
     * @param log the log to display
     */
    public void sendGameLog(String log) {
        this.gameLogs.sendLogs(log);
    }

    /**
     * Checks if armies are assigned for everyone
     * @return true if all the armies are allocated to a country
     */
    public boolean isArmiesAssignedForAll() {
        for (Map.Entry<String, Player> playerEntry : this.playerList.entrySet()) {
            Player player = playerEntry.getValue();

            if (player.getNoOfArmiesToAssign() != 0)
                return false;
        }

        return true;
    }

    /**
     * Based on number of players, this method determines the number of armies
     * allowed for the initial game play
     */
    public void determineOfInitialArmy() {
        int noOfArmiesToAssign = 40 - ((this.playerList.size() - 2) * 5);
        for (Map.Entry<String, Player> playerEntry : this.playerList.entrySet()) {
            Player player = playerEntry.getValue();
            player.setMaxInitialArmies(noOfArmiesToAssign);
            this.playerList.put(playerEntry.getKey(), player);
        }
    }

    /**
     * Attaches the observer to the phase data to detect changes in phases
     * @param object the observer to attach
     */
    public void attachObserverToPhase(Observer object) {
        this.phaseData.deleteObserver(object);
        this.phaseData.addObserver(object);
    }

    /**
     * Attaches the observer to all the players
     * @param object the observer to attach
     */
    public void attachObserverToPlayers(Observer object) {
        for (Map.Entry<String, Player> entry : this.playerList.entrySet()) {
            Player player = entry.getValue();
            player.deleteObserver(object);
            player.addObserver(object);
        }
    }

    /**
     * Get the instance of the singleton class.
     * It first checks if there is an existing instance.
     * If not, then it creates a new one.
     * @return the instance of the singleton object.
     */
    public static DataHolder getInstance() {
        if (dataHolder == null)
            dataHolder = new DataHolder();
        return dataHolder;
    }

    public List<Player> getConqueredPlayerList() {
        return conqueredPlayerList;
    }

    public void setConqueredPlayerList(List<Player> conqueredPlayerList) {
        this.conqueredPlayerList = conqueredPlayerList;
    }

    /** Deletes all the players from the game play */
    public void clearPlayers() {
        this.playerList.clear();
    }
    
    /**
     * Get card from the country in the map
     * @param countryName name of the country
     * @return type of the card
     */
    public String getCardFromCountry(String countryName) {
        for (CountryData country : this.countryDataList) {
            if (country.getName().equalsIgnoreCase(countryName))
                return country.getCardType();
        }
        return null;
    }
    
    public void useCardOfCountry(String countryName) {
        int index = -1;
        
        for (int i = 0; i < this.countryDataList.size(); i++) {
            if (this.countryDataList.get(i).getName().equalsIgnoreCase(countryName)) {
                index = i;
                break;
            }
        }
        
        if (index == -1)
            return;
        
        CountryData country = this.countryDataList.get(index);
        country.useCard();
        
        this.countryDataList.remove(index);
        this.countryDataList.add(country);
    }

    /**
     * Add a player into the game play
     * @param data data object of the player
     */
    public void addPlayer(Player data) {
        this.playerList.put(data.getName(), data);
        this.phaseData.setTotalPlayers(this.playerList.size());
    }

    /**
     * Add a country into the game play
     * @param data data object of the player
     */
    public void addContinent(ContinentData data) {
        this.continentDataList.add(data);
    }

    /**
     * Add a country into the game play
     * @param data data object of the player
     */
    public void addCountry(CountryData data) {
        this.countryDataList.add(data);
    }

    public List<ContinentData> getContinentDataList() {
        return continentDataList;
    }

    public List<CountryData> getCountryDataList() {
        return countryDataList;
    }

    /**
     * Get the country data object of a particular country
     * @param name name of the country
     * @return data object of the country
     */
    public CountryData getCountry(String name) {
        for (CountryData countryData : this.countryDataList) {
            if (countryData.getName().equalsIgnoreCase(name))
                return countryData;
        }

        return null;
    }

    /**
     * Return the list of players
     * @return players List of players
     */
    public List<Player> getPlayerList() {
        List<Player> players = new ArrayList<>();

        for (Map.Entry<String, Player> playerEntry : this.playerList.entrySet()) {
            players.add(playerEntry.getValue());
        }

        return players;
    }

    public HashMap<Integer, Integer> getPlayersArmiesList() {
        return playersArmiesList;
    }

    public void setPlayersArmiesList(int playerId, int noOfArmies) {
        if(playersArmiesList.keySet().contains(playerId)) {
            playersArmiesList.replace(playerId, noOfArmies);
        }
        else {
            playersArmiesList.put(playerId, noOfArmies);
        }
    }

    /**
     * Update the list of players in the game.
     * @param players list of players to update to.
     */
    public void updatePlayerList(List<Player> players) {
        for (Player player : players) {
            this.playerList.put(player.getName(), player);
        }
    }

    /**
     * get the list of countries in the continent
     * @param continentName name of the continent
     * @return data object of the continent
     */
    public List<CountryData> countCountriesInContinent(String continentName) {
        List<CountryData> countryDataList = new ArrayList<>();

        for (CountryData country : this.countryDataList) {
            if (country.getContinent().equalsIgnoreCase(continentName))
                countryDataList.add(country);
        }

        return countryDataList;
    }

    /** Changes the player for the turn */
    public void changeTurn() {
        this.phaseData.changeTurn();
    }

    /**
     * Get the player object from his name
     * @param name name of the player
     * @return data object of the player
     */
    public Player getPlayer(String name) {
        return this.playerList.get(name);
    }

    /** changes the phases in each turn. and it automatically changes the player turn */
    public void changePhases() {
        this.phaseData.changePhase();
    }

    /**
     * End the game forcefully
     * @param winner name of the player who've won
     */
    public void forceEndGame(String winner) {
        this.phaseData.forceEnd(winner);
    }

    /** 
     * get the active player on the board 
     * 
     * @return activePlayer The active player for the game.
     */
    public Player getActivePlayer() {
        return this.getPlayerList().get(this.phaseData.getPlayerTurn());
    }

    /**
     * Get the player object from the country he conquers.
     * @param countryName name of country
     * @return player object
     */
    public Player getPlayerFromCountryName(String countryName) {
        for (Map.Entry<String, Player> playerEntry : this.playerList.entrySet()) {
            Player player = playerEntry.getValue();

            if (player.getCountriesConquered().containsKey(countryName))
                return player;
        }

        return null;
    }

    /**
     * Update the active player object.
     * It's used in order to update the conquered countries and its armeis.
     * @param player data object of the player
     */
    public void updatePlayer(Player player) {
        this.playerList.put(player.getName(), player);
    }

    /**
     * Gets number of continents of the player
     * @param player player object
     * @return number of continents conquered
     */
    public int getNoOfContinents(Player player) {
        int noOfContinents = 0;
        for (ContinentData continentData : this.continentDataList) {
            String continentName = continentData.getName();
            boolean skipContinent = false;

            List<CountryData> countries = this.countCountriesInContinent(continentName);
            for (CountryData country : countries) {
                if (!player.getCountriesConquered().containsKey(country.getName())) {
                    skipContinent = true;
                    break;
                }
            }

            if (!skipContinent)
                noOfContinents++;
        }

        return noOfContinents;
    }

    /**
     * Checks if the player has won or not
     * @param player player object
     * @return true if won
     */
    public boolean hasPlayerWon(Player player) {
        int totalCountries = this.countryDataList.size();
        int playerCountries = player.getCountriesConquered().size();

        double limit = ((double) totalCountries) * (3.0/4.0);

        return (playerCountries >= limit);
    }

    /**
     * Has everyone reached the max limit?
     * It's supposed end the game if true
     * @return true if game should be ended
     */
    public boolean areAllPlayerDone() {
        for (Player player : this.getPlayerList()) {
            if (player.canTakeMoreTurns(maxTurnLimit))
                return false;
        }

        return true;
    }
    
    /**
	 * Clear the entire map holder data.
	 */
	public void clearDataHolder() {
		this.playerList.clear();
		this.continentDataList.clear();
		this.countryDataList.clear();
	}

    /**
     * Saves the state of the game
     * @param filename name of the file to save it to
     */
    public void saveGameState(String filename) {
		try {
			// Saving of object in a file
			filename = filename.endsWith(".ser") ? filename : filename + ".ser";
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of objects
			out.writeObject(dataHolder.bmpFile);
			out.writeObject(this.continentDataList);
			out.writeObject(this.countryDataList);
			out.writeObject(this.playerList);
			out.writeObject(this.playersArmiesList);
			out.writeObject(this.conqueredPlayerList);
			out.writeObject(this.getActivePlayer());
			out.writeObject(this.phaseData);
			out.writeObject(this.gameLogs);
			
			out.close();
			file.close();

		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}


    /**
     * Loads the state of the game from a file
     * @param inputFile file to load the game from
     */
    @SuppressWarnings("unchecked")
	public void loadSavedGame(File inputFile) {
		try {
			FileInputStream file = new FileInputStream(inputFile);
			ObjectInputStream in = new ObjectInputStream(file);
			try {
				this.bmpFile = (File) in.readObject();
				this.continentDataList = (ArrayList<ContinentData>) in.readObject();
				this.countryDataList = (ArrayList<CountryData>) in.readObject();
				this.playerList = (HashMap<String, Player>) in.readObject();
				this.playersArmiesList = (HashMap<Integer, Integer>) in.readObject();
				this.conqueredPlayerList = (List<Player>) in.readObject();
				this.updatePlayer((Player) in.readObject()); 
				this.phaseData = (PhaseData) in.readObject();
				this.gameLogs=(GameLogsData) in.readObject();
					
				StartupController startController = new StartupController();
				startController.resumeGame();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}

			in.close();
			file.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

    /**
     * Get the list of predefined logs
     * @return list of logs to display
     */
	public List<String> getGameLogs() {
        return this.gameLogs.logs;
    }

}
