package dropper.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ResourceUtil {
	public static String writeFileToLocalMachine(String fileName) throws IOException {
		File targetFile = new File(fileName);
		fileName = "/" + fileName;
		System.out.println(ClassLoader.getSystemResource(fileName));
		System.out.println(ResourceUtil.class.getResourceAsStream(fileName));
		System.out.println(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));

		Files.copy(ClassLoader.getSystemResourceAsStream(fileName), targetFile.toPath(),
				StandardCopyOption.REPLACE_EXISTING);
		return targetFile.getAbsolutePath();
	}
}
