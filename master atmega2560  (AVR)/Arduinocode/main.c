#include <avr/io.h>
#include "i2c_mst.h"
#include <util/delay.h>
#include <stdlib.h>

#define F_CPU 16000000
#define DEVICE_ADRES   8
#define BAUDRATE		9600
#define UBRR_BAUD	(((long)F_CPU/((long)16 * BAUDRATE))-1)

int motor[] = {0, 0, 0}; //Richting, motor rechts en motor links
//Bandenrichting: 0: vooruit. 4: rechts achteruit. 8: links achteruit. 12: achteruit
int langeafstand = 1; //slaat op of we wel of niet voor lange afstanden bezig zijn
int knop; //Een int met hierin de binaire representatie van welke knoppen zijn ingedrukt
int snelheid = 0; //snelheid die het laatste gebruikt is
int instelsnelheid; //snelheid die de gebruiker in kan stellen
uint8_t adress = 0;
int route[5][2];

void achteruit();
void links();
void rechts();
void rechtdoor();
void stop();
void doStuff();
void prog();
int omObjectHeen();
void draai(int);
void initUSART();
void rijden(int);
char uart_getchar(void);
int charToInt(char);
void verzenden(uint8_t,uint8_t);
//Prototypes voor de functies

int main(void) {
	route[0][0] = 0;
	initUSART();
	while(1) {
		knop = charToInt(uart_getchar()); //TODO actuele variabelen van GUI opvragen
		if (route[0][0] != 0) {
			prog();
		} else if (knop >= 127) {
			stop();
		} //Als de noodstop knop ingedrukt is moeten we deze direct uitvoeren
		else {
			doStuff();
		}
		if (motor[1] < 0) {motor[1] = 0;}
		if (motor[1] > 125) {motor[1] = 125;}
		if (motor[2] < 0) {motor[2] = 0;}
		if (motor[2] > 125) {motor[2] = 125;}
		//Onze snelheid mag niet meer dan 125 zijn, maar ook niet minder dan 0.
		switch(motor[0]) {
			case 0:
			case 12:
				snelheid = (motor[1] + motor[2]) / 2;
			break;
		
			case 4:
			case 8:
				snelheid = 0;
			break;
		}//Hier slaan we de huidige snelheid op
	verzenden(adress, (uint8_t) motor[0]);
	verzenden(adress, (uint8_t) motor[1]);
	verzenden(adress, (uint8_t) motor[2]);
	_delay_ms(50);
	}
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

void doStuff() {
	switch (knop) {
		case 0: //als we niks indrukken en we zijn niet met de instelsnelheid bezig, stop.
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

void initUSART() {

	UBRR0H = UBRR_BAUD >> 8;
	UBRR0L = (uint8_t) UBRR_BAUD;
	UCSR0A = 0x00;
	UCSR0C = (1<<UCSZ01)|(1<<UCSZ00);
	UCSR0B = (1 << TXEN0) | (1 << RXEN0);
	//writeString("usart werkt nog\n\r");
}

char uart_getchar(void) {
	loop_until_bit_is_set(UCSR0A, RXC0); /* Wait until data exists. */
	return UDR0;
}

int charToInt(char in) {
	int x;
	switch(in) {
		case 'w':
			x = 1;
			break; //W will be used to go forward
		
		case 's':
			x = 2;
			break; //S will be used to go backward
			
		case 'a':
			x = 8;
			break; //A will be used to go left
			
		case 'd':
			x = 4;
			break; //D will be used to go right
			
		case ' ':
			x = 127;
			break; //Spacebar will be used as a brake
			
		default:
			x = 0;
			break;
	}
	return x;
} // Used to take input from command line for now

void verzenden(uint8_t ad,uint8_t b) {
	//  uint8_t op[5];

	TWCR |= (1<<TWSTA);
	while(!(TWCR & (1<<TWINT)));
	//   op[0] = TWSR;
	TWDR=(ad<<1);
	TWCR=(1<<TWINT)|(1<<TWEN);
	while(!(TWCR & (1<<TWINT)));
	//    op[1] = TWSR;

	TWDR=b;
	TWCR=(1<<TWINT)|(1<<TWEN);
	while(!(TWCR & (1<<TWINT)));
	//  op[2] = TWSR;

	TWCR=(1<<TWINT)|(1<<TWSTO)|(1<<TWEN);
	//	while(!(TWCR & (1<<TWINT)));
	//  for(uint8_t i=0;i<3;++i) {
	// writeString("\n\r");writeInteger(op[0],16);
	// writeString(" ");writeInteger(op[1],16);
	// writeString(" ");writeInteger(op[2],16);
}

//Onderstaande code nog niet gebruiken: opzet voor ingeprogrammeerde route

void prog() {
	stop();
	int stand = 0;
	int afstand = 0;
	langeafstand = 0;
	instelsnelheid = 125;
	int x = 0;
	while (route[x][1] != 0) {
		//TODO stop opvragen, programma verlaten
		if (route[x][2] != stand) {
			draai(abs(route[x][2] - stand));
			stand = route[x][2];				
		}
		rijden(route[x][1]);
		x++;
	}
	//TODO een respons verzenden dat we zijn aangekomen
	return;
}

void draai(int x) {
	int i;
	switch(x) {
		case 1:
			for (i = 0; i < 45; i++) {
				rechts();
			}
			break;
			
		case 2: 
			for (i = 0; i < 90; i++) {
				rechts();
			}
			break;
			
		case 3:
			for (i = 0; i < 45; i++) {
				links();
			}
			break;
	}
	stop();
}

void rijden(int afstand) {
	int i;
	for (i = 0; i < (afstand / 10); i++) {
		rechtdoor();
	}
	stop();
}

int omObjectHeen() {
	draai(1);
	int passed = 0;
	int x = 0;
	int ret = 0;
	int afstand;
	while (passed == 0) {
		rijden(10);
		x++;
		draai(3);
		afstand = 10;//TODO check afstand tot object
		if (afstand > 100) {
			passed++;
		} else {
			draai(1);
		}
	}
	while (passed == 1) {
		rijden(10);
		ret++;
		draai(3);
		afstand = 10;//TODO check afstand tot object
		if (afstand > 100) {
			passed++;
			} else {
			draai(1);
		}
	}
	rijden(x*10);
	draai(1);
	return ret;
}