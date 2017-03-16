#ifndef F_CPU
   #define F_CPU 16000000
#endif

#define SCL_frequentie 100000

#include <util/twi.h>


void verzenden(uint8_t ad,uint8_t b);
void ontvangen(uint8_t ad,uint8_t[],uint8_t);
void init_master();

void initUSART();
void writeInteger(int16_t number, uint8_t base);
void writeString(char *string);
