package com.arlania.net.login;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Lookup {

    private String ip;
    private String countryCode;
    private String countryName;
    private int asn;
    private String isp;
    private int block;
	public int getBlock() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setBlock(int block) {
		this.block = block;
	}

}