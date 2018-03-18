package com.example.armin.wave.ticket;

import com.example.armin.wave.ticket.model.Item;
import com.example.armin.wave.ticket.model.TicketResponse;

import java.util.List;

/**
 * Created by Una on 29.10.2017..
 */

public interface ITicketContract {

    interface Preseneter{
        void getTickets(String betType);
        void Submit();
        void Clear();
        void Remove(int position,String matchId,List<Item> items);
    }

    interface View{
        void onTicketsFetched(TicketResponse response);
        void onSubmit(boolean success,String message);
        void onClear(boolean success,String message);
        void onRemove(List<Item> items);
    }

}
