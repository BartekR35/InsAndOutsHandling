import java.util.Scanner;
import java.io.*;
import java.net.*;

public class BankResercher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            //Entering first 3 digits of Bank Account
            System.out.println("Enter 3 first digits of bank Account: ");
            String accountBeginning = sc.nextLine();

            //URL of data
            String urlBankDatabase = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";

            //Exception Handling of temporary file
            File tempFileBankDatabase = null;
            try {
                tempFileBankDatabase = tempFileBankDatabase = File.createTempFile("bank_data", ".txt");
            } catch (IOException e) {
                System.err.println("An error occurred while creating a temporary file: " + e.getMessage());
                return;
            }


            //Downloading file from URL
            try {BufferedInputStream in = new BufferedInputStream(new URL(urlBankDatabase).openStream());
                FileOutputStream fileOutputStream = new FileOutputStream((tempFileBankDatabase)); {
                    byte[] dataBuffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                        fileOutputStream.write(dataBuffer, 0, bytesRead);
                    }
                }

                // File reading and searching for the right bank
                boolean bankFound = false;
                try (BufferedReader reader = new BufferedReader(new FileReader(tempFileBankDatabase))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith(accountBeginning)) {
                            // Extract bank information
                            String[] parts = line.split("\t"); //lines separated by tabulators
                            String bankCode = parts[0];
                            String bankName = parts[1];

                            System.out.println("Bank code: " + bankCode);
                            System.out.println("Bank name: " + bankName);

                            bankFound = true;
                            break;
                        }
                    }
                }
                if (!bankFound) {
                    System.out.println("No bank found.");
                }
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } finally {
            sc.close();
        }
    }
}

