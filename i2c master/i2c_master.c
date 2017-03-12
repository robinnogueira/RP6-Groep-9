#include <avr/io.h>
#include <util/twi.h>
#include "i2c_master.h"
uint8_t sensor[6], motor[3];
void i2c_init(void)
{
	TWBR = ((16000000UL / 100000UL) - 16 ) / 2;
}

uint8_t I2C()
{
	if(i2c_start(0))return(1);
	for (uint8_t i=0; i<3;i++)
	{
		if(i2c_write(i))return(1);
	}
	TWCR = (1<<TWINT) | (1<<TWEN) | (1<<TWSTO);				// transmit STOP condition	
	if(i2c_start(1))return(1);
	for (uint8_t i=0; i<5;i++)
	{
		i2c_read_ack(i);
	}
	i2c_read_nack();
	TWCR = (1<<TWINT) | (1<<TWEN) | (1<<TWSTO);				// transmit STOP condition	
	return 0;
}

uint8_t i2c_start(uint8_t taak)
{
	TWCR = 0;												// reset TWI control register
	TWCR = (1<<TWINT) | (1<<TWSTA) | (1<<TWEN);				// transmit START condition
	while( !(TWCR & (1<<TWINT)) );							// wait for end of transmission
	if((TWSR & 0xF8) != TW_START){ return 1; }				// check if the start condition was successfully transmitted
	TWDR = 0b00101010|taak;									// load slave address into data register
	TWCR = (1<<TWINT) | (1<<TWEN);							// start transmission of address
	while( !(TWCR & (1<<TWINT)) );							// wait for end of transmission
	// check if the device has acknowledged the READ / WRITE mode
	uint8_t twst = TW_STATUS & 0xF8;
	if ( (twst != TW_MT_SLA_ACK) && (twst != TW_MR_SLA_ACK) ) return 1;		// error zenden
	return 0;
}

uint8_t i2c_write(uint8_t i)
{
	TWDR = motor[i];										// load data into data register
	TWCR = (1<<TWINT) | (1<<TWEN);							// start transmission of data
	while( !(TWCR & (1<<TWINT)) );							// wait for end of transmission
	if( (TWSR & 0xF8) != TW_MT_DATA_ACK ){ return 1; }
	return 0;
}

void i2c_read_ack(uint8_t i)
{
	TWCR = (1<<TWINT) | (1<<TWEN) | (1<<TWEA);				// start TWI module and acknowledge data after reception
	while( !(TWCR & (1<<TWINT)) );							// wait for end of transmission
	sensor[i] = TWDR;										// return received data from TWDR
}

void i2c_read_nack(void)
{
	TWCR = (1<<TWINT) | (1<<TWEN);							// start receiving without acknowledging reception
	while( !(TWCR & (1<<TWINT)) );							// wait for end of transmission
	sensor[5] = TWDR;										// return received data from TWDR
}

int main()
{
	i2c_init();
	I2C();
}
