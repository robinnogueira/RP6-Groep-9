/*
 * i2cw.c
 *
 * Created: 3/19/2016 2:14:44 PM
 * Author : john
 */ 


#include <avr/io.h>
#include <util/twi.h>
#include "i2c.h"
#include "rp6aansluitingen.h"

#define TRUE 0xFF;
#define FALSE 0;

//volatile TWIMode toestand =  Ready;


#define SCL_frequentie 100000

#define BAUDRATE		38400
#define UBRR_BAUD	(((long)F_CPU/((long)16 * BAUDRATE))-1)
#define resetData()  for(uint8_t i=0;i<20;++i) data[i]=0



void (*ontfunc) (uint8_t[],uint8_t);
uint8_t (*verfunc) ();

void init_i2c_slave(uint8_t ad) {
	
	TWSR = 0;
	TWBR = ((F_CPU / SCL_frequentie) - 16) / 2;
	TWCR = (1 << TWIE) | (1 << TWEN) | (1 << TWEA);
	TWAR = ad<<1;
}

void slaaftwi() {
	static uint8_t data[40];
	static uint8_t teller=0;
	switch(TWSR) {
		case 0x10:			//repeat start
		case 0x08:			//start
		break;
		
		case 0x60:			//TW_SR_SLA_ACK

		teller=0;

		break;
		case 0x68:			//TW_SR_ARB_LOST_SLA_ACK

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
		break;
		case 0xC0:   //NACK		TW_ST_DATA_NACK
		break;
		case 0xC8:			//TW_ST_LAST_DATA
		break;
	}
	TWCR |= (1<<TWINT);    // Clear TWINT Flag
}

void initUSART() {

	UBRRH = UBRR_BAUD >> 8;
	UBRRL = (uint8_t) UBRR_BAUD;
	UCSRA = 0x00;
	UCSRC = (1<<URSEL)|(1<<UCSZ1)|(1<<UCSZ0);
	UCSRB = (1 << TXEN) | (1 << RXEN);
	writeString("usart werkt nog\n\r");
}


void init_i2c_ontvang( void (*ontvanger) (uint8_t [],uint8_t)) {
	ontfunc=ontvanger;
}

void init_i2c_verzend( uint8_t (*verzender) ()) {
	verfunc=verzender;
}

void writeChar(char ch)
{
	while (!(UCSRA & (1<<UDRE)));
	UDR = (uint8_t)ch;
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


/*de interrupt routine van de i2c
de functie slaaftwi() staat in de library
*/

ISR(TWI_vect) {

	slaaftwi();

}

uint8_t data_ont[20]; //max 20
volatile uint8_t data_flag = FALSE;
volatile uint8_t databyte=0x33;

void ontvangData(uint8_t [],uint8_t);
uint8_t verzendByte();

int main(void)
{

	DDRC=0xFF;
	initUSART();
	init_i2c_slave(8);
	
	/*ontvangData is de functie die uitgevoerd wordt 
	wanneer een byte via de i2c bus ontvangen wordt
	*/
	init_i2c_ontvang(ontvangData); 
	
	/*verzendByte is de functie die aangeroepen wordt
	wanneer de slave een byte naar de master verzend*/
	init_i2c_verzend(verzendByte);
	
	sei(); //De slave van i2c werkt met interrupt
	
    /* Replace with your application code */
    while (1) 
    {    
		if(data_flag) {
		writeInteger(data_ont[0],10);	
			switch(data_ont[0]) {
				case 1:PORTC |= SL1;
				break;
				case 2:PORTC |= SL2;
				break;
				case 3:PORTC |= SL3;
				break;
				case 4:PORTC &= ~SL1;
				break;
				case 5:PORTC &= ~SL2;
				break;
				case 6:PORTC &= ~SL3;
				break;
				
				
			}
			
		    data_flag = FALSE;	
		}
    }
	
}
 /*slave heeft data ontvangen van de master
 data[] een array waarin de ontvangen data staat
 tel het aantal bytes dat ontvangen is*/
 
void ontvangData(uint8_t data[],uint8_t tel){
	for(int i=0;i<tel;++i)
	    data_ont[i]=data[i];
	data_flag = TRUE;
	writeString("o\n\r");
}

/* het byte dat de slave verzend naar de master
in dit voorbeeld een eenvoudige teller
*/

uint8_t verzendByte() {
		return databyte++;
}
