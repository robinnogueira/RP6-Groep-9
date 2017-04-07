/*
 * RP6 code
 *
 * Created: 3/22/2017 2:14:44 PM
 * Author : Groep 9
 */ 

/*
	Hier worden libaries en headers geinclude
*/
#include <avr/io.h>
#include <util/twi.h>
#include "i2c.h"
#include "rp6aansluitingen.h"

/*
	globale variabelen difinen/initiakliseren
*/
#define BAUDRATE		38400  
#define UBRR_BAUD	(((long)F_CPU/((long)16 * BAUDRATE))-1)
#define resetData()  for(uint8_t i=0;i<20;++i) data[i]=0
#define TRUE 0xFF;
#define FALSE 0;
#define aantalsensoren 5
uint8_t data_ont[20]; //max 20
volatile uint8_t data_flag = FALSE;
volatile uint8_t databyte=0x33;
uint8_t motorbyte[3],i=0;
int sensoren[aantalsensoren];
int debounce1,debounce2,ontvangen=0;

/*
	functies definieren 
*/
void ontvangData(uint8_t [],uint8_t);
uint8_t verzendByte();
void (*ontfunc) (uint8_t[],uint8_t);
uint8_t (*verfunc) ();
void pwm_init();
void motoren();
void encoder_init();
void readaccu();
void bumpers();
uint16_t readADC(uint8_t channel);

ISR(TWI_vect) {					//I2C interupt

	slaaftwi();				//data ontvang functie

}

ISR(TIMER0_COMP_vect)				//timer interupt functie
{
	static volatile int i=0;		//i is een is om verschillende timers te kunnen gebruiken
	i++;
	if(i>20)
	{
		i=0;
		if(debounce1<2)debounce1++;	//debounce linker encoder
		if(debounce2<2)debounce2++;	//debounce rechter encoder
		if(ontvangen<2)ontvangen++;	//geen data ontvangen verhogen voor noodstop
	}
}

ISR (INT0_vect)					//externe intterupt van linker encoder
{
	if (debounce1>1)			//wanneer er gedebounced is
	{
		(motorbyte[0]==0||motorbyte[0]==4)?sensoren[1]++:sensoren[1]--;		//wanneer de encoder vooruit draait sensoren verhogen, anders aftellen
		debounce1=0;			//debounce ressetten
	}
}

ISR (INT1_vect)					//externe intterupt van rechter encoder
{
	if (debounce2>1)			//wanneer er gedebounced is
	{
		(motorbyte[0]==0||motorbyte[0]==8)?sensoren[2]++:sensoren[2]--;		//wanneer de encoder vooruit draait sensoren verhogen, anders aftellen
			debounce2=0;		//debounce ressetten
		}
}

int main(void)					//main functie
{
	DDRC=0xBF;				//outputs definieren
	encoder_init();				//encoders initialiseren
	init_i2c_slave(8);			//I2C slave initialiseren
	pwm_init();				//Pulse Width Modulation initialiseren
	
	/*ontvangData is de functie die uitgevoerd wordt 
	wanneer een byte via de i2c bus ontvangen wordt
	*/
	init_i2c_ontvang(ontvangData); 
	
	/*verzendByte is de functie die aangeroepen wordt
	wanneer de slave een byte naar de master verzend*/
	init_i2c_verzend(verzendByte);
	
	sei(); //interupts starten
	
    while (1) 
    {    
	readaccu();				//accu spanning uitlezen
	bumpers();				//bumpers uitlezen
	if(sensoren[0]||ontvangen>2)		//als er een bumper is ingedrukt of er worden geen gegevens via I2C ontvangen
		for(int i=0;i<3;i++)		//alle motoren uit zetten
			motorbyte[i]=0;
	motoren();				//motoren aansturen
	if(data_flag) 				//als data is ontvangen
	{					//motor waardes vullen
		for (int i =0;i<3;i++)
			motorbyte[i] = data_ont[i];	
	    data_flag = FALSE;			//data vlag resseten
	}
    }
}
 /*slave heeft data ontvangen van de master
 data[] een array waarin de ontvangen data staat
 tel het aantal bytes dat ontvangen is*/

void slaaftwi() {
	static uint8_t data[40];
	static uint8_t teller=0;
	switch(TWSR) {		
		case 0x60:			//TW_SR_SLA_ACK
			teller=0;
		break;
		case 0x80:			//W_SR_DATA_ACK
			data[teller++] = TWDR;
		break;
		case 0xA0:			//TW_SR_STOP
			ontfunc(data,teller);
			resetData();
		break;
		case 0xA8:			//TW_ST_SLA_ACK
			teller=0;
			TWDR=verfunc();
		break;
		case 0xB8:			//TW_ST_DATA_ACK
			TWDR=verfunc();
			sensoren[1]=128;
			sensoren[2]=128;
		break;
	}
	TWCR |= (1<<TWINT);    			// Clear TWINT Flag
}


void init_i2c_slave(uint8_t ad) {		//I2C initiaseren
	
	TWSR = 0;				//status register reseten
	TWBR = ((F_CPU / SCL_frequentie) - 16) / 2;	//baudrate instellen op 9600
	TWCR = (1 << TWIE) | (1 << TWEN) | (1 << TWEA);	//klaar voor ontvangen instellen
	TWAR = ad<<1;				//addres instellen
}

void init_i2c_ontvang( void (*ontvanger) (uint8_t [],uint8_t)) {//I2C ontvang functie instellen (door school geleverd)
	ontfunc=ontvanger;
}

void init_i2c_verzend( uint8_t (*verzender) ()) {//I2C verzend functie instellen (door school geleverd)
	verfunc=verzender;
}

void ontvangData(uint8_t data[],uint8_t tel){	//I2C data ontvang functie 
	for(int i=0;i<tel;++i)			//loop om alle data te ontvangen
		data_ont[i]=data[i];
	data_flag = TRUE;			//aangeven dat alle data is ontvangen
	ontvangen=0;				//data ontvangen timer (voor noodstop) resseten 
}

/* het byte dat de slave verzend naar de master
in dit voorbeeld een eenvoudige teller
geleverd door de opleiding
*/
uint8_t verzendByte() {
	if (i>=aantalsensoren)
		i=0;
	return sensoren[i++];
}

void motoren()					//funtie die de motoren aanstuurd
{
	PORTC =(12&motorbyte[0]);		//motor richting instellen
	OCR1A = motorbyte[1];			//motor snelheid links
	OCR1B = motorbyte[2];			//motor snelheid rechts	
}

void pwm_init()					//pulse width modulation initialiseren
{
	//fast PWM initialiseren op op 1a en 1b
	TCNT1 = 0;
	TCCR1A = (0 << WGM10) | (1 << WGM11) | (1 << COM1A1) | (1 << COM1B1);
	TCCR1B = (1 << WGM13) | (0 << WGM12) | (1 << CS10);
	ICR1 = 0xFF;			//max instellen

	// zorgen dat de motor pinnen als output staan
	DDRD|=MOTOR_R;
	DDRD|=MOTOR_L;
}

void encoder_init()				//encoder initialiseren
{
	DDRB |=  PWRON;				//de encoder led als output zetten
	PORTB |= PWRON;				//de encoder led aan zetten
	PORTD |= (ENC_L | ENC_R);		//encoders met pullup weerstand instellen
	//externe interupt instellen op INT0 en INT1
	MCUCR |= (1<<ISC00) | (1<<ISC10) | (0<<ISC11) | (0<<ISC01);	
	GICR |= (1<<INT0 | 1<<INT1);
}

void bumpers (){
	sensoren[0] = 0;
	if (PINB&SL6)
	sensoren[0] += 1;
	if (PINC&SL3)
	sensoren[0] += 2;
}

void timer_init()
{
	TCCR0 = (1 << WGM12);				//timer op ctc stand zetten (reset on compare)
	OCR0 = 156;					//aantal ticks voor er wat gebeurt op exact 400 seconden zetten
	TIMSK = (1 << OCIE0);				//juiste register selecteren
	sei();						//interupts definieren
	TCCR0 |= (1 << CS12) | (1 << CS10);		//prescale op 1024 zetten
}

void readaccu(){
	uint16_t uitlees;
	uitlees = readADC(ACS);
	sensoren[3] = uitlees / 128;
	sensoren[4] = uitlees % 128;
	
}

uint16_t readADC(uint8_t channel)
{
	if((ADCSRA & (1<<ADSC))) return 0; // check if ADC is buisy...
	ADMUX = (1<<REFS0) | (0<<REFS1) | (channel<<MUX0);
	ADCSRA = (0<<ADIE) | (1<<ADSC) | (1<<ADEN) | (1<<ADPS2) | (1<<ADPS1) | (1<<ADIF);
	while ((ADCSRA & (1<<ADSC)));
	ADCSRA |= (1<<ADIF);
	return ADC;
}
