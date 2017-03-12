#include <avr/io.h>
#include <util/twi.h>
#include <avr/interrupt.h>
#include "I2C_slave.h"

volatile uint8_t comunicatie=0,sensor[6];


void I2C_init()
{
	TWAR = (0b01001010 << 1);										// address in TWI register plaatsen
	TWCR = (1<<TWIE) | (1<<TWEA) | (1<<TWINT) | (1<<TWEN);			// set the TWCR to enable address matching and enable TWI, clear TWINT, enable TWI interrupt
}

ISR(TWI_vect)
{
	if( (TWSR & 0xF8) == TW_SR_SLA_ACK )							// is geroepen
	{  
		comunicatie=0;												// eerste comunicatie selecteren
		TWCR |= (1<<TWIE) | (1<<TWINT) | (1<<TWEA) | (1<<TWEN);		// clear TWI interrupt flag, prepare to receive next byte and acknowledge
	}
	else if( (TWSR & 0xF8) == TW_SR_DATA_ACK )						// data has been received in slave receiver mode
	{	
		switch (comunicatie)
		{
			case 0:
				PORTC|=(12&TWDR);								// band richting instellen
				break;
			case 1:
				OCR1B = TWDR;									// rechter motorsnelheid instellen
				break;
			case 2:
				OCR1A = TWDR;									// linker motorsnelheid instellen
				break;
		}
		comunicatie++;
			
		// if there is still enough space inside the buffer
		if(comunicatie < 2)
		{
			// clear TWI interrupt flag, prepare to receive next byte and acknowledge
			TWCR |= (1<<TWIE) | (1<<TWINT) | (1<<TWEA) | (1<<TWEN); 
		}
		else
		{
			// clear TWI interrupt flag, prepare to receive last byte and don't acknowledge
			TWCR |= (1<<TWIE) | (1<<TWINT) | (0<<TWEA) | (1<<TWEN); 
		}	
	}
	else if( (TWSR & 0xF8) == TW_ST_DATA_ACK ){ // device has been addressed to be a transmitter
		
		// copy the specified buffer address into the TWDR register for transmission
		TWDR = sensor[comunicatie];
		// increment buffer read address
		comunicatie++;
		
		// if there is another buffer address that can be sent
		if(comunicatie < 5){
			// clear TWI interrupt flag, prepare to send next byte and receive acknowledge
			TWCR |= (1<<TWIE) | (1<<TWINT) | (1<<TWEA) | (1<<TWEN); 
		}
		else{
			// clear TWI interrupt flag, prepare to send last byte and receive not acknowledge
			TWCR |= (1<<TWIE) | (1<<TWINT) | (0<<TWEA) | (1<<TWEN); 
		}
	}
	else{
		// if none of the above apply prepare TWI to be addressed again
		TWCR |= (1<<TWIE) | (1<<TWEA) | (1<<TWEN);
	} 
}

int main()
{
	while(1)
	{
	
	}
}