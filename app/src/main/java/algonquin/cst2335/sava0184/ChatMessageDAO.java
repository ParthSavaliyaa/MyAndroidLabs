package algonquin.cst2335.sava0184;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ChatMessageDAO {
    @Insert
    public void insertMessage(ChatMessage m);

    @Query( "Select * from ChatMessage")
    public List<ChatMessage> getAllMessages();

    @Delete
    public void deleteMessage(ChatMessage m);

    @Query("Delete from ChatMessage")
    void deleteAll();


}
