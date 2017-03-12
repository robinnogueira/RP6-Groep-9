#ifndef I2C_SLAVE_H
#define I2C_SLAVE_H

void I2C_init(void);

ISR(TWI_vect);

#endif // I2C_SLAVE_H
