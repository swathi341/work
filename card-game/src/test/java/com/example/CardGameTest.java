package com.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CardGameTest {

    @Test
    public void testCardGameAPI() throws Exception {
        String baseUrl = "https://deckofcardsapi.com/api/deck";
        String newDeckUrl = baseUrl + "/new/shuffle/?deck_count=1";

        JSONObject newDeck = getJSONResponse(newDeckUrl);
        assertTrue(newDeck.has("deck_id"), "Response should contain deck_id");

        String deckId = newDeck.getString("deck_id");

        String drawUrl = baseUrl + "/" + deckId + "/draw/?count=6";
        JSONObject draw = getJSONResponse(drawUrl);
        JSONArray cards = draw.getJSONArray("cards");
        assertEquals(cards.length(), 6, "Should draw 6 cards");

        JSONArray player1 = new JSONArray();
        JSONArray player2 = new JSONArray();

        for (int i = 0; i < cards.length(); i++) {
            if (i % 2 == 0)
                player1.put(cards.getJSONObject(i));
            else
                player2.put(cards.getJSONObject(i));
        }

        int player1Total = calculateHand(player1);
        int player2Total = calculateHand(player2);

        System.out.println("Player 1 total: " + player1Total);
        System.out.println("Player 2 total: " + player2Total);

        if (player1Total == 21) {
            System.out.println("Player 1 has blackjack!");
        } else if (player2Total == 21) {
            System.out.println("Player 2 has blackjack!");
        } else {
            System.out.println("No blackjack.");
        }
    }

    private JSONObject getJSONResponse(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new JSONObject(response.toString());
    }

    private int calculateHand(JSONArray hand) {
        int total = 0;
        for (int i = 0; i < hand.length(); i++) {
            String value = hand.getJSONObject(i).getString("value");
            switch (value) {
                case "KING":
                case "QUEEN":
                case "JACK":
                case "10":
                    total += 10;
                    break;
                case "ACE":
                    total += 11;
                    break;
                default:
                    total += Integer.parseInt(value);
            }
        }
        return total;
    }
}
