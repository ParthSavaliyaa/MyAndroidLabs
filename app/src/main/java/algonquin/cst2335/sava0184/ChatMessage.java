package algonquin.cst2335.sava0184;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id" )
    protected int id;
    @ColumnInfo(name = "message")
    public String message;
    @ColumnInfo(name = "TimeSent")
    public String timeSent;
    @ColumnInfo(name = "SendOrRecieve")
    public boolean isSentButton;

    public  ChatMessage(){};

    ChatMessage(String m, String t, boolean sent)
    {

        message =m;
        timeSent = t;
        isSentButton = sent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }

    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }
}
