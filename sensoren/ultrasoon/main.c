/*
 * usartrp6.c
 *
 * Created: 3/14/2017 3:00:51 PM
 * Author : josh
 */ 

#define F_CPU 16000000UL
#include <avr/io.h>
#include <util/delay.h>
#include <stdlib.h>

#define US_PORT PORTA
#define US_PIN   PINA
#define US_DDR  DDRA
#define US_POS PA0
#define US_ERROR 0xffff
#define  US_NO_OBSTACLE 0xfffe


void initUsart(){
	
	UCSR0A = 0;
	UCSR0B = (1 << TXEN0); // Enable de USART Transmitter
	UCSR0C = (1 << UCSZ01) | (1 << UCSZ00); /* 8 data bits, 1 stop bit */
	UBRR0H=00;
	UBRR0L=103; //baudrade 9600 bij
}

void writeChar(char x) {
	UDR0 = x;
}


void writeString(char st[]) {
	for(uint8_t i = 0 ; st[i] != 0 ; i++) {
		writeChar( st[i] );
		_delay_us(800);
	}

}
void writeInt(int i) {
	char buffer[8];
	itoa(i,buffer,10);
	writeString(buffer);
}

uint16_t getPulseWidth()
   {
       uint32_t i,result;

       //Wait for the rising edge
       for(i=0;i<600000;i++)
       {
          if(!(US_PIN & (1<<US_POS))) continue; else break;
       }

       if(i==600000)
          return 0xffff; //Indicates time out

       //High Edge Found

       //Setup Timer1
       TCCR1A=0X00;
      TCCR1B=(1<<CS11); //Prescaler = Fcpu/8
       TCNT1=0x00;       //Init counter

       //Now wait for the falling edge
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
          return 0xffff; //Indicates time out

      //Falling edge found

       result=TCNT1;

       //Stop Timer
       TCCR1B=0x00;

       if(result > 60000)
         return 0xfffe; //No obstacle
       else
          return (result>>1);
    }

void Wait()
   {
	      uint8_t i;
	      for(i=0;i<10;i++)
	        _delay_loop_2(0);
   }
   
int main(void)
{
	uint16_t r;
	Wait();
	initUsart();
	writeString("Ultra Sonic");
    /* Replace with your application code */
    while (1) 
    {
		 //Set Ultra Sonic Port as out
		         US_DDR|=(1<<US_POS);
		 
		          _delay_us(10);
		 
		          //Give the US pin a 15us High Pulse
		          US_PORT|=(1<<US_POS);   //High
		 
		          _delay_us(15);
		 
		          US_PORT&=(~(1<<US_POS));//Low
		 
		          _delay_us(20);
		 
		          //Now make the pin input
		          US_DDR&=(~(1<<US_POS));
		 
		          //Measure the width of pulse
		          r=getPulseWidth();
		 
		          //Handle Errors
		          if(r == US_ERROR)
		          {
		           writeString(/*0,0,*/"Error !");
		         }
		         else  if(r== US_NO_OBSTACLE)
		         {
		            writeString(/*0,0,*/"Clear !");
		         }
		         else
		         {
		
		            int d;
		
		            d=(r/58.0); //Convert to cm
		
		            writeInt(d);
					_delay_ms(50);
		            writeString(" cm");
		
		            Wait();
		         }
    }
}

