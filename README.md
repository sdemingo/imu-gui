# Interfaz gráfica para captura de sensor IMU (MPU-6050)

Sencilla GUI para capturar lecturas de una IMU a través del puerto serie. Se ha
usado el chip GY-521.



## Dependencias

Para iniciar el script de prueba en Python necesitamos instalar `pyserial`:

```
pip3 install pyserial
```

En el caso de usar la aplicación el Linux necesitamos que nuestro usuario
pertenezca al grupo `dialout` y al `tty` para no tener problemas de permisos a
la hora de leer el fichero `/dev/ttyACM0`:

```
# usermod -aG dialout usuario
# usermod -aG tty usuario
```




