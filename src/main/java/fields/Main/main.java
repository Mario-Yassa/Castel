// Mario Yassa
// CSC 241

package fields.Main;

import fields.Characters.Player.Player;
import fields.Rooms.Game;
import fields.Rooms.Room;
import fields.XMLhandler.Handler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game gameData = new Game();
        Handler handler;
        Player player;

        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            InputStream input = main.class.getClassLoader().getResourceAsStream("GameData.xml");
            if (input == null) {
                throw new FileNotFoundException("File is NOT found");
            }
            SAXParser saxParser = spf.newSAXParser();
            handler = new Handler(gameData);
            saxParser.parse(input, handler);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        player= handler.getPlayer();
        player.play(scanner);


    }

}




