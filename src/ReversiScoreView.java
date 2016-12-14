import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ReversiScoreView  implements PropertyChangeListener{
    ReversiModel model;

    public void propertyChange(PropertyChangeEvent e){
        if(e.getPropertyName().equals("scoreChange") && e.getSource().getClass() == ReversiModel.class){
            System.out.println("White markers: " + this.model.getWhiteScore()
                    + "\tBlack markers: " + this.model.getBlackScore() +
                    "\tCurrent player: " + this.model.getNextTurn());
        }
    }

    public void setModel(final ReversiModel model) {
        this.model = model;
    }
}
