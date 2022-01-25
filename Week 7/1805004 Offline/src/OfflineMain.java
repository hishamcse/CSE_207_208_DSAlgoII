/*
   Name : Syed Jarullah Hisham
   Roll: 1805004
   CSE'18 Section A1
 */

public class OfflineMain {

    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils("./input.txt");
        BaseballEliminator eliminator = new BaseballEliminator(fileUtils);

        for(String team: eliminator.getAllTeams()) {
            eliminator.eliminationCertificate(team);
        }
    }
}
