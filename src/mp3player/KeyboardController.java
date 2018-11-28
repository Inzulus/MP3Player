package mp3player;

import de.hsrm.mi.prog.util.StaticScanner;

public class KeyboardController {

    static MP3Player mp3Player = new MP3Player("01_LastMembrane.mp3");

    public static void main(String args[]){
        start();
        //mp3Player.play("pl.m3u");




    }

    public static void start(){
        String eingabe = "";
        String[] eingabeArray;
        eingabe = StaticScanner.nextString();
        eingabeArray = eingabe.split(" ",2);
        while(!eingabeArray[0].equals("quit")){
            switch(eingabeArray[0]){
                case "play":
                    if(eingabeArray.length ==1)
                        mp3Player.play();
                    else
                        mp3Player.play(eingabeArray[1]);
                    break;
                case "pause":
                    mp3Player.pause();
                    break;
                case "stop":
                    mp3Player.stop();
                    break;
                case "next":
                    mp3Player.stop();
                    mp3Player.next();
                    break;
                case "prev":
                    mp3Player.stop();
                    mp3Player.prev();
                case "volume":
                    mp3Player.volume(Float.parseFloat(eingabeArray[1]));

            }
            eingabe = StaticScanner.nextString();
            eingabeArray = eingabe.split(" ",2);
        }
        //mp3Player.play();
    }


}
