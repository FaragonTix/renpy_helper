package java_renpy_appl;
import java.io.*;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException {
        String line;
        String lineb = "";
        String a = "\"";
        String lines;
        int p = 0;
        String defaultIdentifier="";

        HashMap<String, String[]> config = new HashMap<>();

        try {
            BufferedReader configFile = new BufferedReader(new FileReader("config.txt"));
            
            while ((line = configFile.readLine()) != null) {
            	if (line.startsWith("@notag=")) {
                    defaultIdentifier = line.substring(7);
                    continue;
                }
            	try {
					String[] parts = line.split(";");
					String keyword = parts[0];
					String keyname = parts[1];
					String identifier = parts[2];
					String[] commands = new String[]{"hide " + keyname, "show " + keyname + " at"};
					config.put(keyword, new String[]{commands[0], commands[1], identifier});
				} catch (Exception e) {
					ShowError(e);
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            configFile.close();
        } catch (IOException e) {
        	ShowError(e);
            e.printStackTrace();
        }

        try {
            BufferedReader start = new BufferedReader(new InputStreamReader(new FileInputStream("start.txt"), "Cp1251"));
            BufferedWriter end = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("end.txt"),"Cp1251"));

            while ((line = start.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                line = line.trim();
                p=0;
                for (String keyword : config.keySet()) {
                    if (line.startsWith(keyword)) {
                        String[] commands = config.get(keyword);
                        end.write(commands[0] + "\n");
                        end.write(commands[1] + "\n");
                        lineb = commands[2] + "\"";
                        line = line.substring(keyword.length(), line.length());
                        p = 1;
                        lineb = lineb + line.trim() + a;
                        break;
                    }
                }
                if (p != 1) {
                	lineb = defaultIdentifier + "\"" + line + a;
                }

                System.out.println(lineb);
                end.write(lineb + "\n");
                p = 0;
            }
            end.close();
            JOptionPane.showMessageDialog(null,
                    "Text was changed.",
                    "Sucess",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
        	ShowError(e);
            e.printStackTrace();
        }
    }
    
    public static void ShowError(Exception e) {
    	JOptionPane.showMessageDialog(null,
    			e.toString(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
