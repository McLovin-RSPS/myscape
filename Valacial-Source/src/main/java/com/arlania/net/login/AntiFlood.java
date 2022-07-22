package com.arlania.net.login;


import com.arlania.net.PlayerSession;
import com.arlania.world.World;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jboss.netty.channel.Channel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class AntiFlood {

    private static ArrayList<String> connections = new ArrayList<String>(2000);
    private static final String IPHUB_API_KEY = "MTIxOTE6NWk3ckJuWXZFV1R0dHFvUWxVZHg5UER1VGhlbFBoZEw=";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final Gson GSON = new Gson();

    public static boolean contains(PlayerSession session) {
        Channel channel = session.getChannel();
        String ip = ((InetSocketAddress) channel.getRemoteAddress()).getAddress().getHostAddress().substring(1).split(":")[0];
        if(connections.contains(ip))
            return true;
        return false;
    }

    public static void add(PlayerSession session) {
        Channel channel = session.getChannel();
        String ip = ((InetSocketAddress) channel.getRemoteAddress()).getAddress().getHostAddress().substring(1).split(":")[0];
        if(!connections.contains(ip))
            connections.add(ip);
    }

    public static void remove(PlayerSession session) {
        Channel channel = session.getChannel();
        String ip = channel.getRemoteAddress().toString().substring(1).split(":")[0];
        if(connections.contains(ip))
            connections.remove(ip);
    }

    public static boolean isBlockedIp(PlayerSession session){
        RemoteAddressFilter filter = World.getRemoteAddressFilter();
        System.out.println(session.getChannel().getRemoteAddress().toString()+"");
       if(session.getChannel().getRemoteAddress().toString().contains("127.0.0.1")){
           return false;
       }
        if (filter != null) {

           if(filter.contains(session)){
               return true;
           }
        }
        return false;
    }


    public static boolean verifyIp(PlayerSession session) {
        String ip = session.getChannel().getRemoteAddress().toString();
        Request request = new Request.Builder().header("X-Key", IPHUB_API_KEY).url("http://v2.api.iphub.info/ip/" + ip).build();

        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.code() == HttpURLConnection.HTTP_OK) {
                try (ResponseBody body = response.body()) {
                    String rawResponseBody = body.string();
                    Lookup lookup = GSON.fromJson(rawResponseBody, Lookup.class);
                    if (lookup.getBlock() == 1) {
                        System.out.println("Blocked bad session from address: "+ip);
                        RemoteAddressFilter filter = World.getRemoteAddressFilter();
                        if (filter != null) {
                            filter.addIp(ip);
                        }
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error look up from address: "+ip);
        }
        return true;
    }

    }
