#include <avr/io.h>
int motor[] = {0, 0, 0}; //Richting, motor rechts en motor links
//Bandenrichting: 0: vooruit. 4: rechts achteruit. 8: links achteruit. 12: achteruit
int langeafstand; //slaat op of we wel of niet voor lange afstanden bezig zijn
int knop; //Een int met hierin de binaire representatie van welke knoppen zijn ingedrukt
int lastbuttonpressed= 0; //TODO juiste datatype gebruiken
int snelheid = 0; //snelheid die het laatste gebruikt is
int instelsnelheid; //snelheid die de gebruiker in kan stellen

void achteruit();
void links();
void rechts();
void rechtdoor();
void stop();
//Prototypes voor de functies

int main(void)
{
	//TODO get value buttonpressed, longdistance, instelsnelheid
	if (knop >= 128) {
		stop();
	} //Als de noodstop knop ingedrukt is moeten we deze direct uitvoeren
	else {
		switch (knop) {
			case 0: //niks, maar mag geen error returnen
				if (langeafstand == 0) {
					stop();
				}
			break;

			case 1: //pijl naar boven
				rechtdoor();
			break;
			
			case 2: //pijl naar onder
				achteruit();
			break;

			case 4: //pijl naar rechts
				rechts();
			break;

			case 5: //pijl naar boven & rechts
				rechtdoor();
				rechts();
			break;

			case 6: //pijl naar onder & rechts
				achteruit();
				rechts();
			break;
			
			case 8://pijl naar links
				links();
			break;

			case 9: //pijl naar boven & links
				rechtdoor();
				links();
			break;
			
			case 10: //pijl naar onder & links
				achteruit();
				links();
			break;
			
			default:
			//TODO return error voor invalide combinatie.
			break;
		}//TODO correcte waardes toekennen
	}
	if (motor[1] < 0) {motor[1] = 0;}
	if (motor[1] > 250) {motor[1] = 250;}
	if (motor[2] < 0) {motor[2] = 0;}
	if (motor[2] > 250) {motor[2] = 250;}
	//Onze snelheid mag niet meer dan 250 zijn, maar ook niet minder dan 0.
	switch(motor[0]) {
		case 0:
		case 12:
			snelheid = (motor[1] + motor[2]) / 2;
		break;
		
		case 4:
		case 8:
			snelheid = 0;
		break;
	}
	//Hier slaan we de huidige snelheid op
}

//TODO doe iets met sensoren
//TODO pad planning implementeren

void rechtdoor () {
	if (langeafstand == 1) {
		switch(motor[0]) {
			case 0:
			case 4:
			case 8:
				motor[0] = 0;
				motor[1] = snelheid + 25;
				motor[2] = snelheid + 25;
			break;
			//Als de RP6 vooruit rijd of om zijn as draait willen we dat deze (meer) snelheid maakt
			
			case 12:
				motor[0] = 12;
				motor[1] = snelheid - 25;
				motor[2] = snelheid - 25;
			break;
			//Als de RP6 achteruit rijd willen we dat deze in snelheid mindert
			}
		} else {
		motor[0] = 0;
		motor[1] = instelsnelheid;
		motor[2] = instelsnelheid;
		//Als de RP6 in de andere mode zit willen we dat deze met de ingestelde snelheid naar achteren rijd
	}
	return;
}
void achteruit () {
	if (langeafstand == 1) {
		switch(motor[0]) {
			case 0:
				motor[0] = 0;
				motor[1] = snelheid - 25;
				motor[2] = snelheid - 25;
			break;
			//Als de RP6 vooruit rijd willen we dat deze in snelheid mindert
			
			case 4:
			case 8:
			case 12:
				motor[0] = 12;
				motor[1] = snelheid + 25;
				motor[2] = snelheid + 25;
			break;
			//Als de RP6 achteruit rijd of om zijn as draait willen we dat deze (meer) snelheid maakt
			}
		} else {
		motor[0] = 12;
		motor[1] = instelsnelheid;
		motor[2] = instelsnelheid;
		//Als de RP6 in de andere mode zit willen we dat deze met de ingestelde snelheid naar achteren rijd
	}
	return;
}

void links () {
	switch(motor[0]){
		case 0:
		case 12:
		if (snelheid != 0) { 		//Als de RP6 aan het rijden is willen we dat deze een bocht maakt
			motor[1] = snelheid - 25;
			motor[2] = snelheid + 25;
			} else {
			motor[0] = 8;
			motor[1] = 50;
			motor[2] = 50;
		}
		break;
		//Als de RP6 stilstaat willen we dat deze om zijn as draait
		
		case 4:
		case 8:
		motor[0] = 8;
		motor[1] = 50;
		motor[2] = 50;
		break;
		//In dit geval is de RP6 al om zijn as aan het draaien
	}
}
void rechts () { //Wordt aangeroepen als we willen dat de Arduino naar rechts gaat
	switch(motor[0]){
		case 0:
		case 12:
			if (snelheid != 0) { //Als de RP6 aan het rijden is willen we dat deze een bocht maakt
				motor[1] = snelheid + 25;
				motor[2] = snelheid - 25;
			} else {
				motor[0] = 4;
				motor[1] = 50;
				motor[2] = 50;
			}
		break;
		//Als de RP6 stilstaat willen we dat deze om zijn as draait
		
		case 4:
		case 8:
			motor[0] = 4;
			motor[1] = 50;
			motor[2] = 50;
		break;
		//In dit geval is de RP6 al om zijn as aan het draaien
	}
}

void stop () { //Wordt aangeroepen als de RP6 compleet stil moet zijn
	motor[0] = 0;
	motor[1] = 0;
	motor[2] = 0;
}