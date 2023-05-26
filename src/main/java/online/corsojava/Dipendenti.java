package online.corsojava;
import java.net.URI;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;  


public class Dipendenti {
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
        int menuscelta;
        int numDipendenti=0;
        int numDipendentiTotale=0;  
        int cntdip=0;
       
        
        Dipendente[] dipendenti=new Dipendente[0];
        Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "sqd";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url+dbName,userName,password);
		System.out.println("Connected to the database");
		PreparedStatement ps= conn.prepareStatement("insert into dipendenti values(?,?,?,?,?,?,?,?)"); //????? numero di valori della tabella, ovvero cinque, nome-cognome-sesso-datadinascita-stipendio
		Statement stmt = conn.createStatement();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        do {
        System.out.println("Menu: ");
        System.out.println("       ");
        System.out.println("(1) - Inserisci Dipendenti");
        System.out.println("(2) - Stampa Lista Dipendenti");
        System.out.println("(3) - Ricerca Dipendente");
        System.out.println("(4) - Stampa la Media Stipendi dei Dipendenti");
        System.out.println("(5) - Stampa la Somma Stipendi dei Dipendenti");
        System.out.println("(6) - Ricerca e Modifica delle Informazioni del Dipendente");
        System.out.println("(7) - Salva su File i dati dell'utente");
        System.out.println("(8) - Stampa dei dati su un formato virtuale PDF");
        System.out.println("(9) - Filtrare per cognome tramite il database");
        System.out.println("(10) - Modificare dei dati nel database");
        System.out.println("(11) - Eliminare i dati nel database");
        System.out.println("(12) - Visualizzare i dati salvati nel database");
        System.out.println("(0) - Chiudi il programma");
        System.out.println("       ");
        System.out.println("Inserisci il numero della funzione che vuoi eseguire:");
        menuscelta = input.nextInt();
        
        
        if(menuscelta==1) {
        	
            
        	numDipendentiTotale++;
        		
            dipendenti = new Dipendente[numDipendentiTotale];
            
            for (int i = 0; i < numDipendentiTotale; i++) {
            
                System.out.println("Inserisci le informazioni per il dipendente " + (cntdip+=1) + ":");
                System.out.println("Inserisci l'ID del dipendente: ");
                int id = input.nextInt();
                System.out.print("Nome: ");
                String nome = br.readLine();
                if(nome.length()<3) {
                	do {
                		System.out.println("Nome non valido, Inserire di nuovo il nome del dipendente:");
                		nome = br.readLine();
                	}while(nome.length()<3);
                }
                
                System.out.print("Cognome: ");
                String cognome = br.readLine();
                if(cognome.length()<3) {
                	do {
                		System.out.println("Cognome non valido, Inserire di nuovo il cognome del dipendente:");
                		cognome = br.readLine();
                	}while(cognome.length()<3);
                }
                
				
				

                System.out.print("Sesso (M/F): ");
                char sesso = input.next().charAt(0);
                if(sesso=='M' || sesso=='F'){
                	
                }else{
                	do {
                	System.out.println("Sesso non valido, Inserire di nuovo il sesso del dipendente:");
                	sesso = input.next().charAt(0);
                	}while(sesso!='M' && sesso!='F');
                }
                
                
                String sesso2 = String.valueOf(sesso);
               
                

                System.out.print("Data di nascita (formato aaaa/mm/gg) ");
                
                
                
                System.out.print("Inserisci il giorno:");
                int giorno = input.nextInt();
                System.out.print("Inserisci il mese:");
                int mese = input.nextInt();
                if(mese>12) { 
                	
                	do {
                		System.out.println("Mese Non Valido, Inserisci di nuovo il mese di nascita:");
                		mese = input.nextInt();
                	}while(mese>12);
                }
                System.out.print("Inserisci l'anno:");
                int anno = input.nextInt();
                if(anno<1900 || anno>2023) {
                	do {
                		System.out.println("Anno Non Valido, Inserisci di nuovo l'anno di nascita:");
                		anno = input.nextInt();
                	}while(anno<1900 || anno>2023);
                }
                LocalDate dataDiNascita = LocalDate.of(anno, mese, giorno);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.GERMANY);
                String text = dataDiNascita.format(formatter);
                LocalDate parsedDate = LocalDate.parse(text, formatter);
                String date2 = parsedDate.toString();
                
                System.out.print("Stipendio: ");
                double stipendio = input.nextDouble();
                if(stipendio<0) {
                	do {
                		System.out.println("Stipendio NON valido, Inserire una cifra al di sopra dello zero (0):");
                		stipendio = input.nextDouble();
                	}while(stipendio<0);
                }
                
                date2 = giorno + "/" + mese + "/" + anno;
                System.out.println("Inserisci la tipologia di impiego tra le seguenti opzioni tra le categorie seguenti:");
                System.out.println("(1) - Amministrativo");
                System.out.println("(2) - Commerciale");
                System.out.println("(3) - Tecnico");
                System.out.println("(4) - Titolare");
                System.out.println("Inserisci la tua opzione:");
                String tip_impiego="";
                int sceltatip = input.nextInt();
                
                if(sceltatip==1) {
                	System.out.println("Hai inserito: Amministrativo");
                	tip_impiego = "Amministrativo";
                }
                if(sceltatip==2) {
                	System.out.println("Hai inserito: Commerciale");
                	tip_impiego = "Commerciale";
                }
                if(sceltatip==3) {
                	System.out.println("Hai inserito: Tecnico");
                	tip_impiego = "Tecnico";
                }
                if(sceltatip==4) {
                	System.out.println("Hai inserito: Titolare");
                	tip_impiego = "Titolare";
                }
                else {
                	while(sceltatip!=1 && sceltatip!=2 && sceltatip!=3 && sceltatip!=4) {
                	System.out.println("Non hai inserito le tipologie riportate, Inserisci di nuovo:");
                	sceltatip = input.nextInt();
                	if(sceltatip==1) {
                    	System.out.println("Hai inserito: Amministrativo");
                    	tip_impiego = "Amministrativo";
                    }
                    if(sceltatip==2) {
                    	System.out.println("Hai inserito: Commerciale");
                    	tip_impiego = "Commerciale";
                    }
                    if(sceltatip==3) {
                    	System.out.println("Hai inserito: Tecnico");
                    	tip_impiego = "Tecnico";
                    }
                    if(sceltatip==4) {
                    	System.out.println("Hai inserito: Titolare");
                    	tip_impiego = "Titolare";
                    }
                	}
                }
                
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();  
                String orario = String.valueOf(now);
                
                ps.setInt(1,id);
                ps.setString(2,nome);
				ps.setString(3,cognome);
				ps.setString(4, sesso2);
				ps.setString(5, date2);
				ps.setDouble(6, stipendio);
				ps.setString(7, tip_impiego);
				ps.setString(8, orario);
				
				int il=ps.executeUpdate();
				
                dipendenti[i] = new Dipendente(id,nome, cognome, sesso, date2, stipendio, tip_impiego, now);
                
                
                }
        }
        
  
        
        if(menuscelta==2) {
        	for (int i = 0; i < numDipendentiTotale; i++) {
                System.out.println("Informazioni per il dipendente " + (i) + ":");
                dipendenti[i].visualizzaInformazioni();
            }
        }
        
        if(menuscelta==3) {
        	System.out.print("Inserisci il cognome del dipendente da cercare: ");
            String cognomeDaCercare = input.next();

           
            boolean dipendenteTrovato = false;
            for (int i = 0; i < numDipendentiTotale; i++) {
                if (dipendenti[i].getCognome().equalsIgnoreCase(cognomeDaCercare)) {
                    System.out.println("Dipendente trovato:");
                    dipendenti[i].visualizzaInformazioni();
                    dipendenteTrovato = true;
                }
            }
            if (!dipendenteTrovato) {
                System.out.println("Nessun dipendente trovato con il cognome specificato.");
            }
        }
        if(menuscelta==4) {
        	double sommaStipendi = 0;
        	for (int i = 0; i < numDipendentiTotale; i++) {
        	    sommaStipendi += dipendenti[i].getStipendio();
        	}
        	double mediaStipendi = sommaStipendi / numDipendentiTotale;
        	System.out.println("La media degli stipendi dei dipendenti è: ");System.out.printf(Locale.GERMANY, "%.2f", mediaStipendi); System.out.println(" €");
        }
        
        if(menuscelta==5) {
        	double sommaStipendi = 0;
        	for (int i = 0; i < numDipendentiTotale; i++) {
        	    sommaStipendi += dipendenti[i].getStipendio();
        	}
        	System.out.print("La somma degli stipendi dei dipendenti è: ");System.out.printf(Locale.GERMANY, "%.2f", sommaStipendi); System.out.println(" €");
        }
        
        if(menuscelta==6) {
        	System.out.print("Inserisci il cognome del dipendente da cercare: ");
            String cognomeDaCercare = input.next();

           
            boolean dipendenteTrovato = false;
            for (int i = 0; i < numDipendentiTotale; i++) {
                if (dipendenti[i].getCognome().equalsIgnoreCase(cognomeDaCercare)) {
                    System.out.println("Dipendente trovato:");
                    dipendenti[i].visualizzaInformazioni();
                    System.out.println("Quali dei seguenti vuoi modificare?");
                    int selezione = input.nextInt();
                    dipendenteTrovato = true;
                    System.out.println("Quali delle seguenti informazioni vuoi modificare?");
                    System.out.println("(1) - Nome");
                    System.out.println("(2) - Cognome");
                    System.out.println("(3) - Sesso");
                    System.out.println("(4) - Data di Nascita");
                    System.out.println("(5) - Stipendio");
                    int caso = input.nextInt();
                    if(caso==1) {
                    	dipendenti[i].getNome();
                    	System.out.print("Nome Modificato: ");
                        String nome = input.next(); 
                    }
                    else if(caso==2) {
                    	dipendenti[i].getCognome();
                    	System.out.print("Cognome Modificato: ");
                        String cognome = input.next();
                    }
                    else if(caso==3) {
                    	dipendenti[i].getSesso();
                    	System.out.print("Sesso (M/F) Modificato: ");
                        char sesso = input.next().charAt(0);
                    }
                    else if(caso==4) {
                    	dipendenti[i].getDataDiNascita();
                    	System.out.print("Data di nascita (formato gg/mm/aaaa) Modificato");
                        System.out.print("Inserisci il giorno:");
                        int giorno = input.nextInt();
                        System.out.print("Inserisci il mese:");
                        int mese = input.nextInt();
                        System.out.print("Inserisci l'anno:");
                        int anno = input.nextInt();
         
                    }else if(caso==5) {
                    	dipendenti[i].getStipendio();
                    	System.out.print("Stipendio Modificato: ");
                        double stipendio = input.nextDouble();
                        if(stipendio<0) {
                        	do {
                        		System.out.println("Stipendio NON valido, Inserire una cifra al di sopra dello Zero");
                        		stipendio = input.nextDouble();
                        	}while(stipendio<0);
                        }
                    }else {
                    	System.out.println("Non hai scelto nessuna delle corrispettive informazioni!");
                    }
                }
            }
            if (!dipendenteTrovato) {
                System.out.println("Nessun dipendente trovato con il cognome specificato.");
            }
        }
        
        if(menuscelta==7) {
        	try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\OneDrive\\Desktop\\dipendenti.txt"));

                
                for (int i = 0; i < numDipendentiTotale; i++) {
                    writer.write(dipendenti[i].getNome() + " ");
                    writer.write(dipendenti[i].getCognome() + " ");
                    writer.write(dipendenti[i].getSesso() + " ");
                    writer.write(dipendenti[i].getDataDiNascita() + " ");
                    writer.write(dipendenti[i].getStipendio() + "\n");
                    writer.write(dipendenti[i].getTip_impiego() + "\n");
                }
                Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\User\\OneDrive\\Desktop\\dipendenti.txt");
                p.waitFor();

                
                writer.close();

                System.out.println("I dati dei dipendenti sono stati salvati sul file dipendenti.txt.");
            } catch (IOException e) {
                System.out.println("Si è verificato un errore durante la scrittura del file.");
            }
        	
        	}
        if(menuscelta==8) {
    	PDDocument document = new PDDocument();
    	PDPage newPage = new PDPage();
    	int i=0;
    	try {
    		PDPageContentStream contentStream = new PDPageContentStream(document, newPage);
    		
    		contentStream.beginText();
    		contentStream.setFont(PDType1Font.COURIER, 15);
    		
    		contentStream.newLineAtOffset(10, 700);
    		for(i=0; i<numDipendentiTotale; i++) {
    		contentStream.showText("Nome: " + dipendenti[i].getNome());
    		contentStream.newLineAtOffset(0, -20);
    		contentStream.showText("Cognome: " + dipendenti[i].getCognome());
    		contentStream.newLineAtOffset(0, -20);
    		contentStream.showText("Sesso: " + dipendenti[i].getSesso());
    		contentStream.newLineAtOffset(0, -20);
    		contentStream.showText("Data Di Nascita: " + dipendenti[i].getDataDiNascita());
    		contentStream.newLineAtOffset(0, -20);
    		contentStream.showText("Stipendio: " + dipendenti[i].getStipendio());
    		contentStream.newLineAtOffset(0, -20);
    		contentStream.showText("Tipologia di Impiego: " + dipendenti[i].getTip_impiego());
    		contentStream.newLineAtOffset(0, -20);
    		contentStream.newLineAtOffset(0, -20);
    		}
    		contentStream.endText();
    		contentStream.close();
    		document.addPage(newPage);
    		document.save(new File("C:\\Users\\User\\OneDrive\\Desktop\\PDF.pdf"));
    		
    		}catch(IOException e) {
    		e.printStackTrace();
    	   }
          }
        
        if(menuscelta==9) {
        	String sql = "SELECT Cognome FROM dipendenti";
        	stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);
        	System.out.println(" ");
        	System.out.println("Cognomi:");
        	while (rs.next()) {
                String value = rs.getString("Cognome");
                System.out.println(value);
            }
        	System.out.println(" ");

            
            rs.close();
            stmt.close();
            conn.close();	
        }
        
        if(menuscelta==10) {
        	System.out.println("Quale valore vuoi modificare?");
        	System.out.println("(1) - Nome");
        	System.out.println("(2) - Cognome");
        	System.out.println("(3) - Sesso");
        	System.out.println("(4) - Data di Nascita");
        	System.out.println("(5) - Stipendio");
        	System.out.println("(6) - Stipendio");
        	
        	int sceltaMod = input.nextInt();
        	
        	if(sceltaMod==1) {
        		System.out.print("Inserisci il nuovo valore: ");
        		input.nextLine();
                String nome = input.nextLine();

                
                String sql = "UPDATE dipendenti SET Nome = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, nome);
                ps.executeUpdate();
                
        	}
            if(sceltaMod==2) {
            	System.out.print("Inserisci il nuovo valore: ");
        		input.nextLine();
                String nome = input.nextLine();

                
                String sql = "UPDATE dipendenti SET Nome = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(2, nome);
                ps.executeUpdate();
                
        	}
            if(sceltaMod==3) {
            	System.out.print("Inserisci il nuovo valore: ");
        		input.nextLine();
                String nome = input.nextLine();

                
                String sql = "UPDATE dipendenti SET Nome = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(3, nome);
                ps.executeUpdate();
                
        	}
            if(sceltaMod==4) {
            	System.out.print("Inserisci il nuovo valore: ");
        		input.nextLine();
                String nome = input.nextLine();

                
                String sql = "UPDATE dipendenti SET Nome = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(4, nome);
                ps.executeUpdate();
                
        	}
            if(sceltaMod==5) {
            	System.out.print("Inserisci il nuovo valore: ");
        		input.nextLine();
                String nome = input.nextLine();

                
                String sql = "UPDATE dipendenti SET Nome = ? WHERE id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(5, nome);
                ps.executeUpdate();
                
        	}
        }
        if(menuscelta==11) {
        	try {
        		String selectQuery = "SELECT MAX(id) FROM dipendenti";
        		
        		stmt = conn.createStatement();
        		ResultSet rs = stmt.executeQuery(selectQuery);
        		rs.next();
        		System.out.print("Inserisci l'id in base all'ordine di questa lista(0-...): ");
        		int lastId = input.nextInt();
        		

        		String deleteQuery = "DELETE FROM dipendenti WHERE id = " + lastId;

        		
        		stmt.executeUpdate(deleteQuery);
        		rs.close();
        		stmt.close();
                
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if(menuscelta==12) {
        	String myURL = "http://localhost/Dipendenti/dipendenti.php";
        	java.awt.Desktop myNewBrowserDesktop = java.awt.Desktop.getDesktop ();
        	java.net.URI myNewLocation = new java.net.URI (myURL);
        	myNewBrowserDesktop.browse (myNewLocation);
        }
        if(menuscelta!=0 && menuscelta!=1 && menuscelta!=2 && menuscelta!=4 && menuscelta!=5 && menuscelta!=6 && menuscelta!=7 && menuscelta!=8 && menuscelta!=9 && menuscelta!=10 && menuscelta!=11 && menuscelta!=12) {
        	System.out.println("Hai inserito un opzione errata!");
        }
        }while(menuscelta!=0);
        System.out.println("Chiusura in corso...");
        input.close();
    }
}

    


