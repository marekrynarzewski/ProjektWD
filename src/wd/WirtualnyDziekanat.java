package wd;

import polaczenie.Polaczenie;

public class WirtualnyDziekanat
{
	
	/**
	 * tworzy obiekt WirtualnyDziekanat
	 * @param args - argumenty programu
	 */
	public static void main(String[] args)
	{
		new WirtualnyDziekanat();
	}

	/**
	 * wyświetla menu i podmenu
	 */
	public WirtualnyDziekanat()
	{
		int wybor = this.menu(true);
		while (wybor != 0)
		{
			this.podmenu(wybor);
			wybor = this.menu(false);
		}
		this.wyjscie();
	}
	

	private void wyjscie()
	{
		Ekran.wyswietlZNowaLinia("Koniec pracy programu.");
		System.exit(0);
	}
	
	private void podmenu(int wybor)
	{
		switch(wybor)
		{
			case 0:
				this.wyjscie();
				break;
			case 1:
				Polaczenie.testuj();
				break;
			case 2:
				break;
			case 3:
				Polaczenie.wybierzBaze();
				break;
			default:
				Ekran.ostrzez("Liczba spoza zakresu");
				break;
		}
	}

	private int menu(boolean pierwsze)
	{
		if (pierwsze)
		{
			Ekran.wyswietlZNowaLinia("Program Wirtualny Dziekanat.");
		}
		Ekran.wyswietlZNowaLinia("0 - Koniec pracy programu.");
		Ekran.wyswietlZNowaLinia("1 - Testuj połączenie z serwerem.");
		Ekran.wyswietlZNowaLinia("2 - Testuj uprawnienia do bazy.");
		Ekran.wyswietlZNowaLinia("3 - Wybierz bazę.");
		return Ekran.uzyskajLiczbe("Twój wybór: ");
	}
}
