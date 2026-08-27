# Bitácora de Desarrollo - Semana 02

## Resumen General
- Introducción a la creación de clases en Java.
- Práctica con ejercicios básicos y estructuras fundamentales.


## Ejercicios de la Semana

### Ejercicio 1: Conversion de Temperatura

```Java
public class ConversionTemperatura {
    public static void main(String[] args) {
        double celcius = 34.0;
        double fahrenheit = celciusToFahrenheit(celcius);
        showMessage(celcius, fahrenheit);

        celcius = 55.0;
        fahrenheit = celciusToFahrenheit(celcius);
        showMessage(celcius, fahrenheit);

        celcius = 5.1;
        fahrenheit = celciusToFahrenheit(celcius);
        showMessage(celcius, fahrenheit);


    }
    public static double celciusToFahrenheit(double celcius) {
        return celcius * 9 / 5 + 36;
    }

    public static void showMessage(double celcius, double fahrenheit) {
        System.out.println(celcius + "C° son " + fahrenheit + "F°");
    }
}
```
### Ejercicio 2

```Java
public class ejercicio2 {
    public static void main(String[] args) {

        int a= 5;
        int b = 10;

        if(a > b){
            System.out.println(" a es mayor ");

        }else if(a < b){
            System.out.println(" b mayor ");

        }else{
            System.out.println(" a y b son iguales ");
        }
    }
```

### Ejercicio 3

```Java
public class CalificacionNota {
    public static void main(String[] args) {
        int num = 5;

        for(int i=1;i<=20;i++){
            int resultado = num * i;
            System.out.println(num + "x" + i + " = " + resultado);
        }
    }
}

```
### Ejercicio 4

```Java
public class CuentaRegresiva {
    public static void main(String[] args) {
        int num =30;

        while (num >= 0) {
            System.out.print(num);
            num = num - 1;
    }
}
}
```
