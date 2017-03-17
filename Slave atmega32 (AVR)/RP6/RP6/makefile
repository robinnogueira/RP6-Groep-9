MCU   = atmega32

CFLAGS = -mmcu=$(MCU)  -Os -Wall -std=gnu99 
CC = avr-gcc
AVRDUDE = avrdude
PROGRAMMER_ARGS_AR = -b 115200 -P /dev/tty.usbmodem1411 -D
PROGRAMMER_TYPE_AR = stk500v2

SRC= rp6.c

RP6_LIB_PATH=-L.
RP6LIB = -lslavewire

OBJ= $(SRC:.c=.o)
TARGET = rp6sl123

OBJCOPY = avr-objcopy
FORMAT  = ihex

all:elf hex

elf: $(TARGET).elf
hex: $(TARGET).hex


$(TARGET).elf: $(OBJ)
	$(CC)  $(CFLAGS) $^ -o $@  $(RP6_LIB_PATH) $(RP6LIB)

%.hex: %.elf
	$(OBJCOPY) -O $(FORMAT) -R .eeprom $< $@


%.o:%.c i2c.h
	$(CC)  $(CFLAGS) -c -Os $<


clean:
	rm -f *.o

flash:$(TARGET).hex
	$(AVRDUDE) -c $(PROGRAMMER_TYPE_AR) -p $(MCU) $(PROGRAMMER_ARGS_AR) -U flash:w:$<	

program:flash

bib:i2c.o 
	avr-ar rcs libslavewire.a i2c.o 
	
