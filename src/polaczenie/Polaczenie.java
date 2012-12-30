package polaczenie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import wd.Ekran;

public class Polaczenie
{
	/**
	 * określa czy połączenie ma być testowane
	 */
	public static boolean testuj = false;

	private Connection polaczenie = null;

	/**
	 * dane do połączenia się z bazą. Użytkownik
	 */
	private static final String uzytkownik = "dbadproj";
	
	/**
	 * dane do połączenia się z bazą. hasło
	 */
	private static final String haslo = "abc";
	
	/**
	 * dane do połączenia się z bazą. adres IP
	 */
	private static final String adresIP = "192.168.0.3";
	
	/**
	 * dane do połączenia się z bazą. Nazwa bazy
	 */
	private static final String nazwaBazy = "ProjektWD";
	
	private boolean pokazWyjatki = true;

	public Polaczenie()
	{
		if (this.polacz())
		{
			if (Polaczenie.testuj)
			{
				Ekran.sukces("Połączenie nawiązane!");

			}
		}
		else
		{
			if (Polaczenie.testuj)
			{
				Ekran.blad("Błąd podczas połączenia.");
				//TODO: zapis o szczegółach błędu do pliku
			}
			
		}
	}
	public boolean polacz()
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//this.polaczenie = DriverManager.getConnection( "jdbc:sqlserver://" + Polaczenie.adresIP + ";instance=SQLEXPRESS;databaseName=" + Polaczenie.nazwaBazy, Polaczenie.uzytkownik, Polaczenie.haslo);
			this.polaczenie = this.uzyskajPolaczenie("sqlserver", Polaczenie.adresIP, "SQLEXPRESS", Polaczenie.nazwaBazy, Polaczenie.uzytkownik, Polaczenie.haslo);
		}
		catch (Exception e)
		{
			if (this.pokazWyjatki)
				Ekran.blad(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public ResultSet wyslijZapytanie(String zapytanie)
	{
		// statement;
		try
		{
			Statement statement = this.polaczenie.createStatement();
			return statement.executeQuery(zapytanie);
		}
		catch (SQLException e)
		{
			if (this.pokazWyjatki)
				Ekran.ostrzez(e.getMessage());
			return null;
		}
	}
	
	/*
	public void wyslijZapytanieBezOdpowiedzi(String zapytanie)
	
	{
		try
		{
			Statement statement = this.polaczenie.createStatement();
			statement.executeQuery(zapytanie);
		}
		catch (SQLException e)
		{
			if (this.pokazWyjatki)
				Ekran.ostrzez(e.getMessage());
		}
	}
	*/
	public static void testuj()
	{
		Polaczenie.testuj = true;
		Polaczenie myc = new Polaczenie();
		myc.polacz();
		Polaczenie.testuj = false;
	}
	
	public static void wybierzBaze()
	{
		Polaczenie pol = new Polaczenie();
		String nazwaBazy = Ekran.uzyskajSlowo("Podaj nazwę bazy: ");
		Ekran.wyswietlZNowaLinia("Wybieram bazę o nazwie: "+nazwaBazy);
		pol.wyslijZapytanie("USE "+nazwaBazy);
		if (pol.sprawdz(nazwaBazy))
		{
			Ekran.sukces("Wybrano bazę danych.");
		}
		else
		{
			Ekran.ostrzez("Błąd podczas wyboru bazy.");
		}
	}
	public boolean sprawdz(String nazwaBazy)
	{
		String obecnaBaza = this.uzywanaBazaDanych();
		return obecnaBaza.equals(nazwaBazy);
	}
	
	public String uzywanaBazaDanych()
	{
		ResultSet wynikZapytania = this.wyslijZapytanie("SELECT db_name()");
		try
		{
			if (wynikZapytania.next())
			{
				return wynikZapytania.getString(1);
			}
		}
		catch(SQLException e)
		{
			Ekran.ostrzez(e.getMessage());
		}
		return "";
	}
	
	private Connection uzyskajPolaczenie(String typ, String adres, String instancja, String nazwaBazy, String uzytkownik, String haslo) throws SQLException
	{
		return DriverManager.getConnection( "jdbc:" + typ + "://" + adres + ";instance=" +instancja +";databaseName=" + nazwaBazy, uzytkownik, haslo);
	}
}
