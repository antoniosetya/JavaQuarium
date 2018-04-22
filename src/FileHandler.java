import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import lib.*;

public class FileHandler {
	private char mode;
	private String filename;
	private String statDetail;
	private MainBoard target;
	
	private class FileFormatException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FileFormatException() {}
	}
	
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
		File fname = new File(filename);
		if (mode == 'r') {
			Scanner sc = null;
			String buffer;
			try {
				sc = new Scanner(fname).useDelimiter(" ");
				buffer = sc.next();
				target.getOverworld().setNumOfCoins(Long.parseLong(buffer));
				buffer = sc.next();
				target.setNumEgg(Integer.parseInt(buffer));
				while (sc.hasNext()) {
					buffer = sc.next();
					i = 0;
					if (buffer.equals("<Guppy>")) {
						buffer = sc.next();
						while (sc.hasNext() && !buffer.equals("</Guppy>")) {
							double x = Double.parseDouble(buffer);
							buffer = sc.next();
							double y = Double.parseDouble(buffer);
							buffer = sc.next();
							int growthStage = Integer.parseInt(buffer);
							buffer = sc.next();
							int numEaten = Integer.parseInt(buffer);
							target.getOverworld().createNewObject('G',x,y);
							target.getOverworld().getGuppies().get(i).setGrowthStage(growthStage);
							target.getOverworld().getGuppies().get(i).setNumEaten(numEaten);
							i++;
							System.out.println("Created Guppy " + growthStage + " at (" + x + "," + y + ")");
							buffer = sc.next();
						}
						if (!buffer.equals("</Guppy>")) {
							throw new FileFormatException();
						}
					}
					else if (buffer.equals("<Piranha>")) {
						buffer = sc.next();
						while (sc.hasNext() && !buffer.equals("</Piranha>")) {
							double x = Double.parseDouble(buffer);
							buffer = sc.next();
							double y = Double.parseDouble(buffer);
							target.getOverworld().createNewObject('P',x,y);
							System.out.println("Created Piranha at (" + x + "," + y + ")");
							buffer = sc.next();
						}
						if (!buffer.equals("</Piranha>")) {
							throw new FileFormatException();
						}
					}
					else if (buffer.equals("<Snail>")) {
						buffer = sc.next();
						while (sc.hasNext() && !buffer.equals("</Snail>")) {
							double x = Double.parseDouble(buffer);
							buffer = sc.next();
							double y = Double.parseDouble(buffer);
							target.getOverworld().setSnail(new Siput(x,y));
							System.out.println("Created Snail at (" + x + "," + y + ")");
							buffer = sc.next();
						}
						if (!buffer.equals("</Snail>")) {
							throw new FileFormatException();
						}
					}
				}
				if (target.getOverworld().getSnail() == null) {
					throw new FileFormatException();
				}
				status = true;
			}
			catch (FileNotFoundException fe) {
				statDetail = "File not found!";
				fe.printStackTrace();
			}
			catch (NumberFormatException nfe) {
				statDetail = "Corrupt file format detected!";
				nfe.printStackTrace();
			}
			catch (FileFormatException ffe) {
				statDetail = "Corrupt file format detected!";
				ffe.printStackTrace();
			}
			catch (NoSuchElementException nse) {
				statDetail = "Corrupt file format detected!";
				nse.printStackTrace();
			}
			finally {
				if (sc != null) {
					sc.close();
				}
			}
		}
		else if (mode == 'w') {
			FileWriter fwrite;
			try {
				fname.createNewFile();
				fwrite = new FileWriter(fname);
				fwrite.write(target.getOverworld().getNumOfCoins() + " " + target.getNumEgg() + " ");
				// Writes Guppy data to file
				fwrite.write("<Guppy> ");
				size = target.getOverworld().getGuppies().getSize();
				List<Guppy> Gtemp = target.getOverworld().getGuppies();
				for (i = 0; i < size; i++) {
					fwrite.write(Gtemp.get(i).getX() + " " + Gtemp.get(i).getY() + " ");
					fwrite.write(Gtemp.get(i).getGrowthStage() + " " + Gtemp.get(i).getNumEaten() + " ");
				}
				fwrite.write("</Guppy> ");
				// Writes Piranha data to file
				fwrite.write("<Piranha> ");
				size = target.getOverworld().getPiranhas().getSize();
				List<Piranha> Ptemp = target.getOverworld().getPiranhas();
				for (i = 0; i < size; i++) {
					fwrite.write(Ptemp.get(i).getX() + " " + Ptemp.get(i).getY() + " ");
				}
				fwrite.write("</Piranha> ");
				// Write Snail data to file
				Siput Stemp = target.getOverworld().getSnail();
				fwrite.write("<Snail> ");
				fwrite.write(Stemp.getX() + " " + Stemp.getY() + " ");
				fwrite.write("</Snail> ");
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
