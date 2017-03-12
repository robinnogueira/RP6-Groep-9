#ifndef I2C_MASTER_H
#define I2C_MASTER_H

void i2c_init(void);
uint8_t I2C();
uint8_t i2c_start(uint8_t taak);
uint8_t i2c_write(uint8_t i);
void i2c_read_ack(uint8_t i);
void i2c_read_nack();

#endif // I2C_MASTER_H
