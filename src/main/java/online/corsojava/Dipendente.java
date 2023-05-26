package online.corsojava;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

public class Dipendente {
	private int id;
    private String nome;
    private String cognome;
    private char sesso;
    private String dataDiNascita;
    private double stipendio;
    private String tip_impiego;
    private LocalDateTime now;

    
    public Dipendente(int id, String nome, String cognome, char sesso, String dataDiNascita, double stipendio, String tip_impiego, LocalDateTime now) {
        this.id = id;
    	this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.stipendio = stipendio;
        this.tip_impiego = tip_impiego;
        this.now = now;
        
    }
    public double getStipendio() {
        return stipendio;
    }

    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }
    
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    
    public void visualizzaInformazioni() {
        System.out.println("Nominativo: " + cognome +" "+ nome);
        System.out.println("Sesso: " + sesso);
        System.out.println("Data di nascita: " + dataDiNascita);
        System.out.printf(Locale.GERMANY, "%.2f", stipendio); System.out.println(" €");
        System.out.println("Tipologia di Impiego: " + tip_impiego);
    }
	public String getNome() {
		return nome;
	}
	
	public char getSesso() {
		return sesso;
	}
	
	public String getDataDiNascita() {
		return dataDiNascita;
	}
	public String getTip_impiego() {
		return tip_impiego;
	}
	public void setTip_impiego(String tip_impiego) {
		this.tip_impiego = tip_impiego;
	}
	public LocalDateTime getNow() {
		return now;
	}
	public void setNow(LocalDateTime now) {
		this.now = now;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}