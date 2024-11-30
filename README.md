# Kata-Cuenta-Corriente-Java
<h1> Cuenta bancaria </h1>
<p>
Desarrollar un programa que modele una cuenta bancaria que tiene los siguientes atributos, que deben ser de acceso protegido: <br>
Saldo, de tipo float. <br>
Número de consignaciones con valor inicial cero, de tipo int. <br>
Número de retiros con valor inicial cero, de tipo int.<br>
Tasa anual (porcentaje), de tipo float.<br>
Comisión mensual con valor inicial cero, de tipo float. <br>
La clase Cuenta tiene un constructor que inicializa los atributos saldo y tasa anual con valores pasados como parámetros. <br>
La clase Cuenta tiene los siguientes métodos:
Consignar una cantidad de dinero en la cuenta actualizando su saldo. <br>
Retirar una cantidad de dinero en la cuenta actualizando su saldo. El valor a retirar no debe superar el saldo. <br>
Calcular el interés mensual de la cuenta y actualiza el saldo correspondiente. <br>
Extracto mensual: actualiza el saldo restándole la comisión mensual y calculando el interés mensual correspondiente (invoca el método anterior). <br>
Imprimir: retorno los valores de los atributos.<br>

La clase Cuenta tiene dos clases hijas:
<br>
<b>Cuenta de ahorros:</b> posee un atributo para determinar si la cuenta de ahorros está activa (tipo boolean). Si el saldo es menor a $10000, la cuenta está inactiva, en caso contrario se considera activa. Los siguientes métodos se redefinen:<br>
Consignar: se puede consignar dinero si la cuenta está activa. Debe invocar al método heredado.<br>
Retirar: es posible retirar dinero si la cuenta está activa. Debe invocar al método heredado. <br>
Extracto mensual: si el número de retiros es mayor que 4, por cada retiro adicional, se cobra $1000 como comisión mensual. Al generar el extracto, se determina si la cuenta está activa o no con el saldo.<br>
Un nuevo método imprimir que retorna el saldo de la cuenta, la comisión mensual y el número de transacciones realizadas (suma de cantidad de consignaciones y retiros).
<br>
<b>Cuenta corriente:</b> posee un atributo de sobregiro, el cual se inicializa en cero. Se redefinen los siguientes métodos: <br>
Retirar: se retira dinero de la cuenta actualizando su saldo. Se puede retirar dinero superior al saldo. El dinero que se debe queda como sobregiro. <br>
Consignar: invoca al método heredado. Si hay sobregiro, la cantidad consignada reduce el sobregiro.<br>
Extracto mensual: invoca al método heredado. <br>
Un nuevo método imprimir que retorna el saldo de la cuenta, la comisión mensual, el número de transacciones realizadas (suma de cantidad de consignaciones y retiros) y el valor de sobregiro.
</p>
<h2>Requisitos: </h2>
Diagrama UML de clases
<p align="center">
  <img src="https://github.com/GuadalupeGFigueroa/Kata-Cuenta-Corriente-Java/blob/dev/UML.png"width=70% height=50%/>
</p>

Tests unitarios obligatorios (cobertura mínima 70%)
<p align="center"> 

