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


void pwm_init()
{
  	TCNT1 = 0;
  	TCCR1A = (0 << WGM10) | (1 << WGM11) | (1 << COM1A1) | (1 << COM1B1);
  	TCCR1B = (1 << WGM13) | (0 << WGM12) | (1 << CS10);

  	ICR1 = 0xFF;
  	OCR1A = 0x50;
  	OCR1B = 0x50;

	// make sure to make OC0 pin (pin PB3 for atmega32) as output pin
	DDRD|=MOTOR_R;
	DDRD|=MOTOR_L;
	DDRC|=DIR_R;
	DDRC|=DIR_L;
	 
	 
}

int main(void)
{
pwm_init();
motor();
}

void motor()
{
	uint8_t richting=12,snelheidA=255,snelheidB=255;
	PORTC|=(12&richting);
	OCR1B = snelheidA;
	OCR1A = snelheidB;
}
