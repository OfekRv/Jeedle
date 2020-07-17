package dropper;

import java.io.IOException;

import utils.HttpUtil;

public class DropperApplication {
	public static void main(String args[]) {
		try {
			HttpUtil.sendGET("http://" + args[0] + "/submit");
		} catch (IOException e) {
		}

		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
		}
	}
}
