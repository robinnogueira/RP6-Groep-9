/*
 * rp6 rijd.c
 *
 * Created: 15-3-2017 14:34:49
 * Author : robin
 */ 

#include <avr/io.h>
#include <util/delay.h>
#include "rp6aansluitingen.h"

void pwm_init();
void motoren();
int main(void)
{
	pwm_init();
    /* Replace with your application code */
    while (1) 
    {
		motoren();
    }
}

void motoren()
{
		PORTC|=(12&0);									//motor richting instellen
		OCR1A = 255;										//motor snelheid links
		OCR1B = 255;
		for(int i;i<32;i++)
		{
			_delay_ms(250);
		}
		PORTC|=(12&0);									//motor richting instellen
		OCR1A = 255;										//motor snelheid links
		OCR1B = 140;
		for(int i;i<32;i++)
		{
			_delay_ms(250);
		}
		PORTC|=(12&0);									//motor richting instellen
		OCR1A = 140;										//motor snelheid links
		OCR1B = 255;
		for(int i;i<32;i++)
		{
			_delay_ms(250);
		}
		PORTC|=(12&0);									//motor richting instellen
		OCR1A = 0;										//motor snelheid links
		OCR1B = 0;
		for(int i;i<32;i++)
		{
			_delay_ms(250);
		}
		PORTC|=(12&4);									//motor richting instellen
		OCR1A = 0;										//motor snelheid links
		OCR1B = 255;
		for(int i;i<32;i++)
		{
			_delay_ms(250);
		}
		PORTC|=(12&8);									//motor richting instellen
		OCR1A = 255;										//motor snelheid links
		OCR1B = 0;
		for(int i;i<32;i++)
		{
			_delay_ms(250);
		}
		PORTC|=(12&12);									//motor richting instellen
		OCR1A = 255;										//motor snelheid links
		OCR1B = 255;
		for(int i;i<32;i++)
		{
			_delay_ms(250);
		}
}

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
}

