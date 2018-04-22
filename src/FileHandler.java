import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import lib.*;

public class FileHandler {
	private char mode;
	private String filename;
	private String statDetail;
	private MainBoard target;
	
	public FileHandler(final String filename, MainBoard target, final char mode) {
		this.filename = filename;
		this.target = target;
		this.mode = mode;
	}
	
	public char getMode() {
		return mode;
	}

	public String getFilename() {
		return filename;
	}

	public String getStatDetail() {
		return statDetail;
	}

	public MainBoard getTarget() {
		return target;
	}

	public void setMode(char mode) {
		this.mode = mode;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setTarget(MainBoard target) {
		this.target = target;
	}

	public boolean process() {
		boolean status = false;
		int i, size;
		if (mode == 'r') {
			
		}
		else if (mode == 'w') {
			File fname = new File(filename);
			FileWriter fwrite;
			try {
				fname.createNewFile();
				fwrite = new FileWriter(fname);
				fwrite.write(target.getNumOfCoins() + " " + target.getNumEgg() + "\n");
				// Writes Guppy data to file
				fwrite.write("<Guppy>\n");
				size = target.getOverworld().getGuppies().getSize();
				List<Guppy> Gtemp = target.getOverworld().getGuppies();
				for (i = 0; i < size; i++) {
					fwrite.write(Gtemp.get(i).getX() + " " + Gtemp.get(i).getY() + " ");
					fwrite.write(Gtemp.get(i).getGrowthStage() + "\n");
				}
				fwrite.write("</Guppy>\n");
				// Writes Piranha data to file
				fwrite.write("<Piranha>\n");
				size = target.getOverworld().getPiranhas().getSize();
				List<Piranha> Ptemp = target.getOverworld().getPiranhas();
				for (i = 0; i < size; i++) {
					fwrite.write(Ptemp.get(i).getX() + " " + Ptemp.get(i).getY() + "\n");
				}
				fwrite.write("</Piranha>\n");
				fwrite.close();
				status = true;
			}
			catch (FileNotFoundException fe) {
				statDetail = "File not found!";
				fe.printStackTrace();
				fwrite = null;
			}
			catch (IOException ie) {
				statDetail = "Error writing to file!";
				fwrite = null;
			}
		}
		else {
			statDetail = "Wrong mode!";
		}
		return status;
	}
}
