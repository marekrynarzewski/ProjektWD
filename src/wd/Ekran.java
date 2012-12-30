package wd;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ekran
{
	private static int iloscProb = 3;
	private static Scanner wejscie = new Scanner(System.in);
	
	/**
	 * wyświetla obiekt na ekranie bez nowej linii.
	 * @param obj
	 */
	public static void wyswietl(Object obj)
	{
		System.out.print(obj);
	}
	
	/**
	 * wyświelta obiekt na ekran wraz z nową linią
	 * @param obj
	 */
	public static void wyswietlZNowaLinia(Object obj)
	{
		System.out.println(obj);
	}
	
	/**
	 * prosi o podanie liczby
	 * @return liczba
	 */
	public static int uzyskajLiczbe(String zacheta)
	{
		if (Ekran.iloscProb != 0)
		{
			Ekran.znakZnachety(zacheta);
			try
			{
				return Ekran.wejscie.nextInt();
			}
			catch (InputMismatchException e)
			{
				--Ekran.iloscProb;
				Ekran.ostrzez("Podałeś wartość inną od oczekiwanej.");
				Ekran.wejscie = new Scanner(System.in);
				return Ekran.uzyskajLiczbe(zacheta);
			}
		}
		Ekran.blad("Przekroczyłeś ilość prób.");
		return 0;
	}
	
	/**
	 * wyświetla ostrzeżenie dla użytkownika
	 * @param obj
	 */
	public static void ostrzez(Object obj)
	{
		// TODO Auto-generated method stub
		Ekran.wyswietlZNowaLinia("[Ostrzeżenie] "+obj);
	}
	
	public static void informacja(Object obj)
	{
		Ekran.wyswietlZNowaLinia("[Informacja] "+obj);
	}
	
	public static void blad(Object obj)
	{
		Ekran.wyswietlZNowaLinia("[Błąd] "+obj);
		System.exit(1);
	}
	
	private static void znakZnachety(String zacheta)
	{
		Ekran.wyswietl(zacheta);
	}
	
	public static String uzyskajSlowo(String zacheta)
	{
		Ekran.wejscie = new Scanner(System.in);
		Ekran.znakZnachety(zacheta);
		try
		{
			return Ekran.wejscie.nextLine();
		}
		catch(NoSuchElementException e)
		{
			Ekran.ostrzez("Nie podałeś nic.");
		}
		return "";
	}
	
	public static void sukces(Object obj)
	{
		Ekran.wyswietlZNowaLinia("[Sukces] "+obj);
	}
}
