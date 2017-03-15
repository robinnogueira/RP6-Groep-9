#define F_CPU 16000000
#include <avr/io.h>
#include <util/delay.h>
#include <stdlib.h>

void writeChar(char x);
void writeString(char st[]);
void writeInt(int i);

int main(void) {

	UCSR0A = 0;
	UCSR0B = (1 << RXEN0); // Enable de USART Transmitter
	UCSR0C = (1 << UCSZ01) | (1 << UCSZ00); /* 8 data bits, 1 stop bit */
	UBRR0H = 00;
	UBRR0L = 103; //baudrade 9600 bij
	DDRB = 0xFF;
	while(1) {
		int x = readInt();
		_delay_ms(250);
	}
	return (0);
}

int readInt() {
	int a = UDR0;
	a = a - 48;
	PORTB = 0x00;
	if (a>=8) {
		PORTB = PORTB | 0x08;
		a = a - 8;
	}
	if (a>=4) {
		PORTB = PORTB | 0x04;
		a = a - 4;
	}
	if (a>=2) { 
		PORTB = PORTB | 0x02;
		a = a - 21
		;
	}
	if (a==1) {
		PORTB = PORTB | 0x01;
	}
	return a;
}

void writeChar(char x) {
	while(~UCSR0A & (1 << UDRE0));
	UDR0 = x;
}

void writeString(char st[]) {
	for (int j = 0; j < 8; j++) {
		writeChar(st[j]);
	}
}

void writeInt(int i) {
	char buffer[8];
	itoa(i, buffer, 10);
	writeString(buffer);
}