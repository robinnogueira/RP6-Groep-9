#include <avr/io.h>
#include "i2c_mst.h"
#include <util/delay.h>
#include <stdlib.h>
#include <avr/interrupt.h>
#include <string.h>

#define F_CPU 16000000
#define DEVICE_ADRES   8
#define BAUDRATE 9600
#define UBRR_BAUD (((long)F_CPU/((long)16 * BAUDRATE))-1)
#define KOMPAS_ADRES 0X60

uint8_t motor[] = {0, 0, 0}; //Richting, motor rechts en motor links
//Bandenrichting: 0: vooruit. 4: rechts achteruit. 8: links achteruit. 12: achteruit
int langeafstand = 0; //slaat op of we wel of niet voor lange afstanden bezig zijn
int knop,vorigeknop=0; //Een int met hierin de binaire representatie van welke knoppen zijn ingedrukt
int snelheid = 0; //snelheid die het laatste gebruikt is
int instelsnelheid=125; //snelheid die de gebruiker in kan stellen
uint8_t adress = 8;
int route[9][2];
int ultrasoon; //ultrasone afstand gedeeld
uint8_t kompasstand;
uint8_t dataprog = 0;
int progafstand;
int progcounter = 0;
int progposition = 0;
int progstop = 0;

#define US_PORT PORTA
#define US_PIN  PINA
#define US_DDR  DDRA
#define US_POS  PA0
#define US_ERROR 0xffff
#define US_NO_OBSTACLE 0xfffe
volatile int afstand; // hier word afstand in cm opgeslagen

void achteruit();
void links();
void rechts();
void rechtdoor();
void stop();
void doStuff();
void initUSART();
char uart_getchar(void);
int charToInt(char);
void preprog();
void verzenden(uint8_t ,uint8_t ,uint8_t ,uint8_t);
void prog();
void draai(int);
void rijden(int);
int omObjectHeen();
void init_master();
void getultrasoondistance();
uint16_t getPulseWidth();
void voorObject();
uint8_t kompas();
void verzendData();
void progData(char);
//Prototypes voor de functies

ISR(USART0_RX_vect)
{
	char x = UDR0;
	if ((int) x == 50 || dataprog == 1) {
		dataprog = 1;
		progData(x);
	} else {
		knop = (int) x;
	}
	//ChartoInt for USART
}

int main(void)
{
	uint8_t sensoren[2];
	sei();
	PORTD = 0x03; //pullup SDA en SCL
	initUSART();
	//uint8_t data[10];
	init_master();
	writeString("Een testje\n\r");
	while(1)
	{
		getultrasoondistance();
		//writeInteger(afstand,10);
		//knop = uart_getchar();//charToInt(uart_getchar());
		if (knop >= 127)
		{
			stop();
		} //Als de noodstop knop ingedrukt is moeten we deze direct uitvoeren
		else
		{
			doStuff();
		}
		if (motor[1] < 0) {motor[1] = 0;}
		if (motor[1] > 250) {motor[1] = 250;}
		if (motor[2] < 0) {motor[2] = 0;}
		if (motor[2] > 250) {motor[2] = 250;}
		//Onze snelheid mag niet meer dan 250 zijn, maar ook niet minder dan 0.
		switch(motor[0])
		{
			case 0:
			case 12:
			snelheid = (motor[1] + motor[2]) / 2;
			break;

			case 4:
			case 8:
			snelheid = 0;
			break;
		}//Hier slaan we de huidige snelheid op
		if (ultrasoon < 5) {
			voorObject();
		}
		verzenden(adress, motor[0],motor[1],motor[2]);
		_delay_ms(50);
		ontvangen(8,sensoren,2);
		if (sensoren[0]!=128)
		//writeInteger(sensoren[0],10);
		if (sensoren[1]!=128)
		//writeInteger(sensoren[1],10);
		kompasstand = kompas();
		verzendData();
	}
}

void rechtdoor ()
{
	if (langeafstand == 1)
	{
		switch(motor[0])
		{
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
	}
	else
	{
		motor[0] = 0;
		motor[1] = instelsnelheid;
		motor[2] = instelsnelheid;
		//Als de RP6 in de andere mode zit willen we dat deze met de ingestelde snelheid naar achteren rijd
	}
	return;

}

void achteruit ()
{
	if (langeafstand == 1)
	{
		switch(motor[0])
		{
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
	}
	else
	{
		motor[0] = 12;
		motor[1] = instelsnelheid;
		motor[2] = instelsnelheid;
		//Als de RP6 in de andere mode zit willen we dat deze met de ingestelde snelheid naar achteren rijd
	}
	return;
}

void links ()
{
	switch(motor[0])
	{
		case 0:
		if (snelheid != 0)
		{ //Als de RP6 aan het rijden is willen we dat deze een bocht maakt
			motor[0] = 0;
			motor[1] = snelheid - 25;
			motor[2] = snelheid + 25;
		}
		else
		{
			motor[0] = 8;
			motor[1] = 100;
			motor[2] = 100;
		}
		break;
		//Als de RP6 stilstaat willen we dat deze om zijn as draait

		case 4:
		case 8:
		motor[0] = 8;
		motor[1] = 100;
		motor[2] = 100;
		break;
		//In dit geval is de RP6 al om zijn as aan het draaien
		
		case 12: 
		if (snelheid != 0)
		{ //Als de RP6 aan het rijden is willen we dat deze een bocht maakt
			motor[0] = 12;
			motor[1] = snelheid - 25;
			motor[2] = snelheid + 25;
		}
		else
		{
			motor[0] = 8;
			motor[1] = 100;
			motor[2] = 100;
		} //Als de RP6 stilstaat willen we dat deze om zijn as draait
		break;
	}
}

void rechts () { //Wordt aangeroepen als we willen dat de Arduino naar rechts gaat
	switch(motor[0])
	{
		case 0:
		if (snelheid != 0)
		{ //Als de RP6 aan het rijden is willen we dat deze een bocht maakt
			motor[0] = 0;
			motor[1] = snelheid + 25;
			motor[2] = snelheid - 25;
		}
		else
		{
			motor[0] = 4;
			motor[1] = 100;
			motor[2] = 100;
		}
		break;
		//Als de RP6 stilstaat willen we dat deze om zijn as draait

		case 4:
		case 8:
		motor[0] = 4;
		motor[1] = 100;
		motor[2] = 100;
		break;
		//In dit geval is de RP6 al om zijn as aan het draaien
		
		
		case 12:
		if (snelheid != 0)
		{ //Als de RP6 aan het rijden is willen we dat deze een bocht maakt
			motor[0] = 12;
			motor[1] = snelheid + 25;
			motor[2] = snelheid - 25;
		}
		else
		{
			motor[0] = 4;
			motor[1] = 100;
			motor[2] = 100;
		} //Als de RP6 stilstaat willen we dat deze om zijn as draait
		break;
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
		if (langeafstand == 0)
		{
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

		case 100:
		preprog();
		break;
		
		default:
		//TODO return error voor invalide combinatie.
		break;
	}//TODO correcte waardes toekennen
}

int charToInt(char in) {
	int x = 0;
	switch(in) {
		case 'w':
		x = 1;
		break; //W will be used to go forward

		case 's':
		x = 2;
		break; //S will be used to go backward

		case 'a':
		x = 4;
		break; //A will be used to go left

		case 'd':
		x = 8;
		break; //D will be used to go right

		case ' ':
		x = 127;
		break; //Spacebar will be used as a brake
		
		case 'l':
		x = 100;
		break;

		default:
		x = 0;
		break;
	}
	return x;
} // Used to take input from command line for now

void init_master() {
	TWSR = 0;
	// Set bit rate
	TWBR = ( ( F_CPU / SCL_frequentie ) - 16) / 2;
	TWCR = (1<<TWEN);
}

void ontvangen(uint8_t ad,uint8_t b[],uint8_t max) {
	uint8_t op[15];
	TWCR |= (1<<TWSTA);
	while(!(TWCR & (1<<TWINT)));
	op[0] = TWSR;

	TWDR=(ad<<1)+1;
	TWCR=(1<<TWINT)|(1<<TWEN);
	while(!(TWCR & (1<<TWINT)));

	op[1] = TWSR;
	b[0]=TWDR;
	uint8_t tel=0;
	do{
		if(tel == max-1)
		TWCR=(1<<TWINT)|(1<<TWEN);
		else
		TWCR=(1<<TWINT)|(1<<TWEN)|(1<<TWEA);
		while(!(TWCR & (1<<TWINT)));
		op[tel] = TWSR;
		b[tel]=TWDR;
	}while(op[tel++] == 0x50);

	TWCR=(1<<TWINT)|(1<<TWSTO)|(1<<TWEN);

	//   for(uint8_t i=0;i<tel;++i) {
	// writeString("\n\r");writeInteger(op[i],16);
	// writeString(" data ");writeInteger(b[i],10);
	//   }

}

void verzenden(uint8_t ad,uint8_t b1,uint8_t b2,uint8_t b3)
{
	//  uint8_t op[5];

	TWCR |= (1<<TWSTA);
	while(!(TWCR & (1<<TWINT)));
	//   op[0] = TWSR;
	TWDR=(ad<<1);
	TWCR=(1<<TWINT)|(1<<TWEN);
	while(!(TWCR & (1<<TWINT)));
	//    op[1] = TWSR;
	TWDR=b1;
	TWCR=(1<<TWINT)|(1<<TWEN);
	while(!(TWCR & (1<<TWINT)));
	TWDR=b2;
	TWCR=(1<<TWINT)|(1<<TWEN);
	while(!(TWCR & (1<<TWINT)));
	TWDR=b3;
	TWCR=(1<<TWINT)|(1<<TWEN);
	while(!(TWCR & (1<<TWINT)));
	//  op[2] = TWSR;

	TWCR=(1<<TWINT)|(1<<TWSTO)|(1<<TWEN);
	// while(!(TWCR & (1<<TWINT)));
	//  for(uint8_t i=0;i<3;++i) {
	// writeString("\n\r");writeInteger(op[0],16);
	// writeString(" ");writeInteger(op[1],16);
	// writeString(" ");writeInteger(op[2],16);
}

void initUSART() {

	UBRR0H = UBRR_BAUD >> 8;
	UBRR0L = (uint8_t) UBRR_BAUD;
	UCSR0A = 0x00;
	UCSR0C = (1<<UCSZ01)|(1<<UCSZ00);
	UCSR0B = (1 << TXEN0) | (1 << RXEN0)|(1<<RXCIE0);
	writeString("usart werkt nog\n\r");
}

void writeChar(char ch)
{
	while (!(UCSR0A & (1<<UDRE0)));
	UDR0 = (uint8_t)ch;
}

void writeString(char *string)
{
	while(*string)
	writeChar(*string++);
}

void writeInteger(int16_t number, uint8_t base)
{
	char buffer[17];
	itoa(number, &buffer[0], base);
	writeString(&buffer[0]);
}

uint16_t getPulseWidth() //functie om US uittelezen
{
	uint32_t i,result;

	//wacht op input
	for(i=0;i<600000;i++)
	{
		if(!(US_PIN & (1<<US_POS))) continue; else break;
	}

	if(i==600000)
	return 0xffff; // niks terug gekomen

	//als er input is

	//timer 1 init
	TCCR1A=0X00;
	TCCR1B=(1<<CS11); //Prescaler = 8
	TCNT1=0x00;       //Init counter

	//wacht tot input low is
	for(i=0;i<600000;i++)
	{
		if(US_PIN & (1<<US_POS))
		{
			if(TCNT1 > 60000) break; else continue;
		}
		else
		break;
	}

	if(i==600000)
	return 0xffff; //als er niks is gebeurt(timeout)

	//als inputlow is

	result=TCNT1;

	//Stop Timer
	TCCR1B=0x00;

	if(result > 60000)
	return 0xfffe; //geen obstakel
	else
	return (result>>1);
}

void getultrasoondistance(){//regel afstand omgezet naar cm
	uint16_t r;
	//US port op output
	US_DDR|=(1<<US_POS);
	_delay_us(10);
	//stuur de US
	US_PORT|=(1<<US_POS);   //High
	_delay_us(15);
	US_PORT&=(~(1<<US_POS));//Low
	_delay_us(20);
	//Now make the pin input
	US_DDR&=(~(1<<US_POS));
	//US waardes uitlezen
	r=getPulseWidth();
	//Handle Errors
	if(r == US_ERROR)
	{
		ultrasoon = 0;
	}
	else  if(r== US_NO_OBSTACLE)
	{
		ultrasoon = 400;
	}
	else
	{
		ultrasoon = (r/58.0); //Convert to cm
	}
	if (ultrasoon > 200){
		ultrasoon = 200;
	}
	ultrasoon = ultrasoon / 2;
}

void prog() {
	writeString("Rijden wah \n");
	verzenden(adress, 0, 0, 0);
	int stand = 0;
	langeafstand = 0;
	instelsnelheid = 125;
	int x = 0;
	writeString("Ik ga loopje in wah \n");
	while (x < 8 && route[x][0] != 0) {
		writeString("Weer ff loopen jwz jonguh \n");
		//TODO stop opvragen, programma verlaten
		if (route[x][1] != stand) {
			writeString("Kmoet draaien wah \n");
			draai(abs(route[x][1] - stand));
			stand = route[x][1];
		}
		rijden(route[x][0]);
		x++;
		stand = 0;
	}
	//TODO een respons verzenden dat we zijn aangekomen
	writeString("Ben klaar met rijden wah \n");
	verzenden(adress, 0, 0, 0);
	return;
}

void draai(int x) {
	int i;
	switch(x) {
		case 1:
		verzenden(adress, 4, 100, 100);
		for (i = 0; i < 10; i++){
			_delay_ms(240);
		}
		break;
		
		case 2:
		verzenden(adress, 4, 100, 100);
		for (i = 0; i < 20; i++){
			_delay_ms(240);
		}
		break;
		
		case 3:
		verzenden(adress, 8, 100, 100);
		for (i = 0; i < 10; i++){
			_delay_ms(240);
		}
		break;
	}
}

void rijden(int afstand) {
	verzenden(adress, 0, 125, 125);
	writeString("lekker rijden wah \n");
	int i;
	int x;
	for (i = 0; i < afstand; i++){
		getultrasoondistance();
		if (ultrasoon < 10) {
			x = omObjectHeen();
			i = i + x;
		}
		if (knop == 127) {
			stop();
			return;
		}
		_delay_ms(95);
	}
}

int omObjectHeen() {
	draai(1);
	int passed = 0;
	int x = 0;
	int ret = 0;
	while (passed == 0) {
		rijden(20);
		x++;
		draai(3);
		getultrasoondistance();
		if (ultrasoon > 20) {
			passed++;
			} else {
			draai(1);
		}
	}
	while (passed == 1) {
		rijden(20);
		ret++;
		draai(3);
		getultrasoondistance();
		if (ultrasoon > 20) {
			passed++;
			} else {
			draai(1);
		}
	}
	rijden(x*20);
	draai(1);
	return ret * 20;
}

void voorObject() {
	draai(2);
	writeString("Ik wil hier niet tegenaan rijden wah \n");
	verzenden(adress, 0, 0, 0);
}

void preprog() {
	route[0][0] = 200;
	route[0][1] = 0;
	route[1][0] = 0;
	route[1][1] = 0;
	route[2][0] = 0;
	route[2][1] = 0;
	route[3][0] = 0;
	route[3][1] = 0;
	route[4][0] = 0;
	route[4][1] = 0;
	route[5][0] = 0;
	route[5][1] = 0;
	prog();
}

uint8_t kompas(){
	uint8_t data[10];
	int a;//omzetten naar een windrichting
	static int windrichting=0;//omgezette waarde moet nog een int zijn
	verzenden(KOMPAS_ADRES, 1, 0, 0);
	ontvangen(KOMPAS_ADRES,data,1);
	a = data [0];
	if (a > 240 || a < 15){
		windrichting = 1;
	}
	
	if (a >= 17 && a <= 46){
		windrichting = 2;
	}
	if (a >= 48 && a <= 76){
		windrichting = 3;
	}
	if (a >= 78 && a <= 110){
		windrichting = 4;
	}
	if (a >= 112 && a <= 142){
		windrichting = 5;
	}
	if (a >= 144 && a <= 174){
		windrichting = 6;
	}
	if (a >= 176 && a <= 206){
		windrichting = 7;
	}
	if (a > 208 && a < 238){
		windrichting = 8;
	}
	
	return windrichting;
}

void verzendData() {
	writeString("Afstand tot object: ");
	writeInteger(ultrasoon, 10);
	writeString(". We staan nu richting: ");
	switch(kompasstand) {
		case 1:
		writeString("Noord");
		break;
		
		case 2:
		writeString("Noord-Oost");
		break;
		
		case 3:
		writeString("Oost");
		break;
		
		case 4:
		writeString("Zuid-Oost");
		break;
		
		case 5:
		writeString("Zuid");
		break;
		
		case 6:
		writeString("Zuid-West");
		break;
		
		case 7:
		writeString("West");
		break;
		
		case 8:
		writeString("Noord-West");
		break;
	}
	writeChar('\n');
}

void progData(char x) {
	switch (progcounter) {
		case 0:
			progafstand = 0;
			progafstand = progafstand + (int) x * 128;
			progcounter++;
		break;
		
		case 1:
			progafstand = progafstand + (int) x;
			progcounter++;
			route[progposition][0] = progafstand;
			if (progafstand == 0) {
				for (progposition; progposition < 9; progposition++) {
					route[progposition][0] = 0;
					route[progposition][1] = 0;
				}
				progcounter = 3;
			}
		break;
		
		case 2:
			route[progposition][1] = (int) x;
			if (progposition == 8) {
				progcounter = 3;
			} else {
				progcounter = 0;	
			}
		break;	
		
		case 3:
			progstop = (int) x;
			progcounter = 0;
			progposition = 0;
			dataprog == 0;
		break;
	}
}