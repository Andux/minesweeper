import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Face extends Button {
    static String dead = "file:res/face-dead.png";
    static String O = "file:res/face-O.png";
    static String smile = "file:res/face-smile.png";
    static String win = "file:res/face-win.png";

    public Face() {
        this.setSmile();
    }

    public void setDead() {
        this.setGraphic(new ImageView(dead));
    }

    public void setO() {
        this.setGraphic(new ImageView(O));
    }

    public void setSmile() {
        this.setGraphic(new ImageView(smile));
    }
    public void setWin() {
        this.setGraphic(new ImageView(win));
    }
}
