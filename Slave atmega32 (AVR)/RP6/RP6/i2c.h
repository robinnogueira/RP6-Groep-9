#ifndef F_CPU
#define F_CPU 8000000
#endif

/*
SCL_frequency=F_CPU/(16 + 2 ( TWBR) * 4 exp(TWPS))
Bij TWPS=0;
TWBR = (F_CPU/SCL_frequentie - 16)/2

*/
#define SCL_frequentie 100000

//#define BAUDRATE		9600  
//#define UBRR_BAUD	(((long)F_CPU/((long)16 * BAUDRATE))-1)


#include <stdlib.h>
#include <avr/io.h>
#include <avr/interrupt.h>
#include <util/twi.h>



void init_i2c_slave(uint8_t ad);
void init_i2c_ontvang( void (*ontvanger) (uint8_t [],uint8_t));
void init_i2c_verzend( uint8_t (*verzender) ());
void slaaftwi();

void initUSART();
void writeInteger(int16_t number, uint8_t base);
void writeString(char *string);

