package com.arlania.net.login;

import com.arlania.net.PlayerSession;
import com.google.common.collect.Sets;

import java.net.InetSocketAddress;
import java.util.Set;


public final class RemoteAddressFilter {
    private final Set<String> badIps = Sets.newConcurrentHashSet();

    public boolean contains(PlayerSession session) {
        String ip = ((InetSocketAddress) session.getChannel().getRemoteAddress()).getAddress().getHostAddress();
        if (badIps.contains(ip)) {
            return true;
        }
        return false;
    }

    public void addIp(String ip) {
        badIps.add(ip);
    }
}
