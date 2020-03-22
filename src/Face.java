import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Face extends Button {
    String dead = "file:res/face-dead.png";
    String O = "file:res/face-O.png";
    String smile = "file:res/face-smile.png";
    String win = "file:res/face-win.png";

    public Face() {
        setSmile();
    }
    public void setDead() {
        setGraphic(new ImageView(dead));
    }
    public void setO() {
        setGraphic(new ImageView(O));
    }
    public void setSmile() {
        setGraphic(new ImageView(smile));
    }
    public void setWin() {
        setGraphic(new ImageView(win));
    }
}
