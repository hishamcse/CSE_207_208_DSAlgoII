import java.util.LinkedList;
import java.util.List;

public class BaseballEliminator {

    private final int totalTeams;
    private final List<String> allTeams;
    private final int[] winCountOf;
    private final int[] remainingCountOf;
    private final int[][] remainingGamesBetween;
    private int maximumWinCount;
    private String maximumWinningTeam;
    private EdmondsKarp edmondsKarp;

    public BaseballEliminator(FileUtils fileUtils) {
        totalTeams = fileUtils.readInt();
        allTeams = new LinkedList<>();
        winCountOf = new int[totalTeams];
        int[] lossCountOf = new int[totalTeams];
        remainingCountOf = new int[totalTeams];
        remainingGamesBetween = new int[totalTeams][totalTeams];
        maximumWinCount = 0;

        int prevCount;
        for (int i = 0; i < totalTeams; i++) {
            allTeams.add(fileUtils.readString());
            winCountOf[i] = fileUtils.readInt();
            lossCountOf[i] = fileUtils.readInt();
            remainingCountOf[i] = fileUtils.readInt();

            for (int j = 0; j < totalTeams; j++) {
                remainingGamesBetween[i][j] = fileUtils.readInt();
            }

            prevCount = maximumWinCount;
            maximumWinCount = Math.max(maximumWinCount, winCountOf[i]);
            maximumWinningTeam = maximumWinCount != prevCount ? allTeams.get(i) : maximumWinningTeam;
        }
    }

    private int totalRemainingGamesOf(int index) {
        int totalRemaining = 0;
        for (int firstId = 0; firstId < totalTeams; firstId++) {
            if (firstId == index) continue;

            for (int secondId = firstId + 1; secondId < totalTeams; secondId++) {
                if (secondId == index) continue;
                totalRemaining += remainingGamesBetween[firstId][secondId];
            }
        }
        return totalRemaining;
    }

    // using simple reason for elimination
    private boolean simpleElimination(int index) {
        return winCountOf[index] + remainingCountOf[index] < maximumWinCount;
    }

    // using max flow formulation
    private boolean complexElimination(int index) {
        // game vertices + team vertices + source + sink  = (n-1)C2 + (n-1) + 1 + 1 =  (n(n-1) / 2) + 2
        // 0 to n-2 team vertices, n-1 to (n(n-1)/2 - 1) game vertices, source = n(n-1)/2, sink = source + 1

        int networkSize = (totalTeams * (totalTeams - 1) / 2) + 2;
        FlowNetworkGraph networkGraph = new FlowNetworkGraph(networkSize);
        int source = networkSize - 2;
        int sink = source + 1;
        constructFlowNetwork(networkGraph, source, sink, index);

        edmondsKarp = new EdmondsKarp(networkGraph, source, sink);
        return (int) edmondsKarp.getMaxFlow() != totalRemainingGamesOf(index);
    }

    private void constructFlowNetwork(FlowNetworkGraph graph, int source, int sink, int indexOfTeam) {

        // team vertices to sink
        int teamVertex;
        for (int id = 0; id < totalTeams; id++) {
            if (id == indexOfTeam) continue;
            teamVertex = id > indexOfTeam ? id - 1 : id;
            int capacity = winCountOf[indexOfTeam] + remainingCountOf[indexOfTeam] - winCountOf[id];
            if (capacity >= 0) {
                graph.addEdge(new FlowEdge(teamVertex, sink, capacity));
            }
        }

        // source to game vertices, game vertices to team vertices
        int gameVertex = totalTeams - 1, indexOfFirst, indexOfSecond;
        for (int firstId = 0; firstId < totalTeams; firstId++) {
            if (firstId == indexOfTeam) continue;
            indexOfFirst = firstId > indexOfTeam ? firstId - 1 : firstId;

            for (int secondId = firstId + 1; secondId < totalTeams; secondId++) {
                if (secondId == indexOfTeam) continue;
                indexOfSecond = secondId > indexOfTeam ? secondId - 1 : secondId;

                graph.addEdge(new FlowEdge(source, gameVertex, remainingGamesBetween[firstId][secondId]));
                graph.addEdge(new FlowEdge(gameVertex, indexOfFirst, Double.POSITIVE_INFINITY));
                graph.addEdge(new FlowEdge(gameVertex++, indexOfSecond, Double.POSITIVE_INFINITY));
            }
        }
    }

    // using min cut, find the accountable teams for a team elimination
    private List<String> accountableTeams(String teamName) {
        int index = allTeams.indexOf(teamName);

        List<String> eliminatedBy = new LinkedList<>();

        if (simpleElimination(index)) {
            eliminatedBy.add(maximumWinningTeam);
        } else if (complexElimination(index)) {
            eliminatedBy = new LinkedList<>();
            int teamVertex;
            for (int id = 0; id < totalTeams; id++) {
                if (id == index) continue;
                teamVertex = id > index ? id - 1 : id;
                if (edmondsKarp.insideCut(teamVertex)) {
                    eliminatedBy.add(allTeams.get(id));
                }
            }
        }

        return eliminatedBy;
    }

    private int totalInBetweenGames(List<String> teams) {
        int total = 0;
        for (int i = 0; i < teams.size(); i++) {
            int firstId = allTeams.indexOf(teams.get(i));
            for (int j = i + 1; j < teams.size(); j++) {
                int secondId = allTeams.indexOf(teams.get(j));
                total += remainingGamesBetween[firstId][secondId];
            }
        }
        return total;
    }

    public List<String> getAllTeams() {
        return allTeams;
    }

    public void eliminationCertificate(String teamName) {
        List<String> accountableTeams = accountableTeams(teamName);
        if (accountableTeams.size() == 0) return;

        int size = accountableTeams.size();
        int totalGames = 0;
        int totalInbetweenGames = totalInBetweenGames(accountableTeams);
        int index = allTeams.indexOf(teamName);

        System.out.println(teamName + " is eliminated.");
        System.out.println("They can win at most " + winCountOf[index] + " + "
                + remainingCountOf[index] + " = " + (winCountOf[index] + remainingCountOf[index]) + " games.");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            String team = accountableTeams.get(i);
            if (i > 0 && i == size - 1) {
                sb.delete(sb.length() - 2, sb.length());
                sb.append(" and ").append(team);
            } else {
                sb.append(team).append(", ");
            }

            totalGames += winCountOf[allTeams.indexOf(team)];
        }
        if (size == 1) sb.delete(sb.length() - 2, sb.length()).append(" has ");
        else sb.append(" have ");

        double average = (totalGames + totalInbetweenGames) / (double) size;
        System.out.println(sb + "won a total of " + totalGames + " games");
        System.out.println("They play each other " + totalInbetweenGames + " times");
        System.out.println("So on average, each of the teams wins " + (totalGames + totalInbetweenGames)
                + "/" + size + " = " + average + " games.");
        System.out.println();
    }
}