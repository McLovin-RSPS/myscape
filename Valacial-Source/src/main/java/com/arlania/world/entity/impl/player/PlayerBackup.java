package com.arlania.world.entity.impl.player;

import com.arlania.GameSettings;
import com.arlania.util.Stopwatch;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PlayerBackup {

	private static final int TIME = 1800000;// Every 30 mins
	private static final String PLAYER_SAVES_PATH = "./data/saves";
	private static final String BACKUP_LOCATION = "./data/backup";
	private static Stopwatch timer = new Stopwatch().reset();
	public static void sequence() {
		if (timer.elapsed(TIME)) {
			new Thread(() -> {
				if(!backup()) {
					System.err.println("Error backing up player saves");
				}
			}).start();
			timer.reset();
		}
	}

	public static boolean backup() {
		try {
			String year = new SimpleDateFormat("yyyy").format(new Date());
			String month = new SimpleDateFormat("MM").format(new Date());
			String day = new SimpleDateFormat("dd").format(new Date());
			File file = new File(BACKUP_LOCATION + "/", new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".zip");
			file.getParentFile().mkdirs();

			zipDir(file.getAbsolutePath(), PLAYER_SAVES_PATH);

			File[] files = new File(BACKUP_LOCATION).listFiles();

			if(files.length >= GameSettings.LOCAL_BACKUP_MAX) {
				Arrays.sort(files, (f1, f2) -> Long.valueOf(f1.lastModified()).compareTo(f2.lastModified()));

				for(int i = GameSettings.LOCAL_BACKUP_MAX; i < files.length; i++)
					files[i].delete();
			}

			System.out.println("Backup complete: " + new SimpleDateFormat("dd hh-mm").format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private static void zipDir(String zipFileName, String dir) throws Exception {
		File dirObj = new File(dir);
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
		addDir(dirObj, out);
		out.close();
	}

	static void addDir(File dirObj, ZipOutputStream out) throws IOException {
        File[] files = dirObj.listFiles();
		byte[] tmpBuf = new byte[1024];

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				addDir(files[i], out);
				continue;
			}
			FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
			out.putNextEntry(new ZipEntry(files[i].getAbsolutePath().substring(System.getProperty("user.dir").length() + 1, files[i].getAbsolutePath().length())));
			int len;
			while ((len = in.read(tmpBuf)) > 0)
				out.write(tmpBuf, 0, len);
			out.closeEntry();
			in.close();
		}
	}
}
