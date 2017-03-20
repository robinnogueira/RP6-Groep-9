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


void rijden();
volatile uint8_t motoren[3]={0,0,0},sensoren[6]={0,0,0,0,0,0};

void init_i2c_slave(uint8_t ad) {
	
	TWSR = 0;
	TWBR = ((F_CPU / SCL_frequentie) - 16) / 2;
	TWCR = (1 << TWIE) | (1 << TWEN) | (1 << TWEA);
	TWAR = ad<<1;
}

void slaaftwi() {
	static uint8_t teller=0;
	switch(TWSR) {
	case 0x10:			//repeat start
	case 0x08:			//start
	for (int i=0;i<3;i++)
		motoren[i]=0;
	break;
	
	case 0x60:			//TW_SR_SLA_ACK

	teller=0;

	break;
	case 0x68:			//TW_SR_ARB_LOST_SLA_ACK

	break;
	case 0x80:			//W_SR_DATA_ACK
	motoren[teller++] = TWDR;

	break;
	case 0xA0:			//TW_SR_STOP
	rijden();
	break;
	case 0xA8:			//TW_ST_SLA_ACK
	teller=0;
	TWDR=sensoren[teller++];
	break;
	case 0xB8:			//TW_ST_DATA_ACK
	TWDR=sensoren[teller++];
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

volatile uint8_t data_flag = FALSE;

int main(void)
{

	DDRC=0xFF;
	initUSART();
	init_i2c_slave(8);
	sei(); //De slave van i2c werkt met interrupt
    while (1) 
    {    

    }
	
}

void rijden()
{
	PORTC|=(12&motoren[0]);
	OCR1A = motoren[1];
	OCR1B = motoren[2];
}

void pwm_init()
{
	TCNT1 = 0;
	TCCR1A = (0 << WGM10) | (1 << WGM11) | (1 << COM1A1) | (1 << COM1B1);
	TCCR1B = (1 << WGM13) | (0 << WGM12) | (1 << CS10);

	ICR1 = 0xFF;
	//OCR1A = 0x50;
	//OCR1B = 0x50;

	// make sure to make OC0 pin (pin PB3 for atmega32) as output pin
	DDRD|=0x30;
	DDRC|=0x0C;
}