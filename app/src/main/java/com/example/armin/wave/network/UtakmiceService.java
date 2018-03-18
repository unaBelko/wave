package com.example.armin.wave.network;

import com.example.armin.wave.standings.model.StandingsResponse;
import com.example.armin.wave.ticket.model.TicketResponse;
import com.example.armin.wave.utakmice.model.UtakmiceResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Una on 28.10.2017..
 */

public class UtakmiceService {
    private static String BASE_URL = "https://wave.sdmiransan.com/";

    public interface UtakmiceServiceApi{
        @GET("/api/match_schedule_java")
        Call<UtakmiceResponse> getMatches(@Header("Cookie") String token);

        @GET("/api/match_schedule_java")
        Call<ResponseBody> getMatchesJson(@Header("Cookie") String token);

        @GET("/api/tournament_standings_json")
        Call<List<StandingsResponse>> GetStandings(@Header("Cookie") String token, @Query("tournament_id") String tournamentId);

        @GET("/api/match_results")
        Call<ResponseBody> getMatchResults(@Header("Cookie") String token);

        @FormUrlEncoded
        @POST("/api/addBetItem")
        Call<ResponseBody> getOrCheckTicket(@Header("Cookie") String token,@Field("match_id") String matchId,@Field("fulltimeresult") String fullTimeResult,
                                            @Field("selector") String selector);

        @GET("/api/my_bet")
        Call<TicketResponse> ticketGet(@Header("Cookie") String token, @Query("bet_type") String betType);

        @POST("/api/placeCurrentBet")
        Call<ResponseBody> placeBet(@Header("Cookie") String token);

        @POST("/api/clearCurrentBet")
        Call<ResponseBody> clearBet(@Header("Cookie") String token);

        @FormUrlEncoded
        @POST("/api/removeBetItem")
        Call<ResponseBody> clearMatch(@Header("Cookie") String token,@Field("match_id") String matchId);


    }

    public UtakmiceServiceApi GetUtakmiceApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(UtakmiceServiceApi.class);
    }

}
