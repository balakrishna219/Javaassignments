package assignment;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Tester {

	private static final String FOLDER_PATH = "C:\\Users\\user\\Documents";

	public static void main(String[] args) throws IOException {
		ClassC c = new ClassC();

		ClassA a = new ClassA();
		a.b.c = c.b.a;

		File dir = new File(FOLDER_PATH);

		File[] files = dir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File directory, String fileName) {
				if (fileName.endsWith(".txt")) {
					return true;
				}
				return false;
			}
		});

		Map<Date, List<File>> mapFiles = new TreeMap<>();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
		for (File file : files) {
			try {
				Date date = sdf.parse(sdf.format(new Date(file.lastModified())));
				List<File> group = mapFiles.get(date);
				if (group == null) {
					group = new ArrayList<>(25);
					mapFiles.put(date, group);
				}
				group.add(file);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}

		for (Date date : mapFiles.keySet()) {
			System.out.println(sdf.format(date));
			for (File file : mapFiles.get(date)) {
				System.out.println("    " + file);
			}
		}
	}
}
