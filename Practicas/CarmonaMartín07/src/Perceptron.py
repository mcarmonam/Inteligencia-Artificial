import numpy as np

class Perceptron:

    ''' Metodo constructor de perceptron incicaliza los pesos
    aleatoriamente entre -0.5 y 0.5 '''
    def __init__(self,input_num, bias = 0, le_rate = 0.1):
        self.wheights = np.random.uniform(low = -0.5,
                                          high = 0.5,
                                          size = (input_num,))
        self.bias = bias
        self.le_rate = le_rate
       

    ''' Metodo para obtener el error de un resultado
    del perceptron dado
    '''
    def error(self,guessed,expected_value):
        return expected_value - guessed

    ''' Funcion de activacion que en este caso es la 
    funcion escalon
    '''
    def activation_func(self,x):
        return 1 if x >= 0 else 0

    ''' Metodo que "adivina" la salida que da el perceptron
    dado un arreglo con las entradas que necesita el perceptron.
    '''
    def guess(self, p_input):
        sum = 0
        for i in range(len(self.wheights)):
            sum += (self.wheights[i]*p_input[i])#-self.bias
        sum += self.bias
        return self.activation_func(sum)

    ''' Metodo que entrena el perceptron dado un conjunto de
    entrenamiento que contiene len(train_set) arreglos con 
    las entradas que necesita el perceptron y el resultado 
    esperado en la ultima posicion.
    '''    
    def train(self, train_set):
        # recorremos las entradas de entrenamiento
        for i in range(len(train_set)):
            # obtenemos el valor "adivinado" del perceptron
            y = self.guess(train_set[i])
            yd = train_set[i][len(self.wheights)]
            
            # modificamos los pesos del perceptron
            for j in range(len(self.wheights)):
                # obtenemos el cambio de peso
                # multiplicando el valor de aprendizaje
                # por el valor de la entrada
                dW = self.le_rate * train_set[i][j]
                # por el error obtenido a partir del
                # "adivinado" y el esperado
                dW *= self.error(y,yd)
                self.wheights[j] += dW
            self.bias += self.le_rate * self.error(y,yd)

    def toString(self):
        return " Wheights are :{} \n Bias is :{} \n Learning rate is :{} \n".format(self.wheights,
                                                                                    self.bias,
                                                                                    self.le_rate)




# VARIABLES PARA EJECUTAR EL CODIGO #


# variable para escoger el tipo de perceptron (and | or)
per_val = "or"

# variable para escoger cuantas iteraciones sobre
# el conjunto de entrenamiento se entrenara al
# perceptron (n > 0)
train_num = 100;

# variable para escojer el conjunto de entrenamiento (1-5)
train_set_var = 4;


# EJECUCION #


# Creamos el perceptron e imprimimos sus valores
perceptron = Perceptron(3,-0.25);
print("Perceptron: \"",per_val,"\"")
print(perceptron.toString())

# Creamos el conjunto completo de valores
if (per_val == "and"):
    all_comb = np.array([[0,0,0,0],[0,0,1,0],[0,1,0,0],
                         [0,1,1,0],[1,0,0,0],[1,0,1,0],
                         [1,1,0,0],[1,1,1,1]])
elif (per_val == "or"):
    all_comb = np.array([[0,0,0,0],[0,0,1,1],[0,1,0,1],
                         [0,1,1,1],[1,0,0,1],[1,0,1,1],
                         [1,1,0,1],[1,1,1,1]])
else:
    all_comb = np.array([[0,0,0,0],[0,0,1,1],[0,1,0,1],
                         [0,1,1,1],[1,0,0,1],[1,0,1,1],
                         [1,1,0,1],[1,1,1,1]])
test_set = np.array([[0,0,0],[1,0,1],[0,0,1],[1,1,1]])

# Seteamos el conjunto de entrenamiento correcpondiente
# a cada tipo de perceptron
if (train_set_var == 1):
    train_set = np.array([[0,0,0,0],[1,1,1,1]])
elif (train_set_var == 2):
    train_set = all_comb
elif (train_set_var == 3):
    if (per_val == "and"):
        train_set = np.array([[0,0,0,0],[0,0,1,0],[0,1,0,0]])
    elif (per_val == "or"):
        train_set = np.array([[0,0,0,0],[0,0,1,1],[0,1,0,1]])
elif (train_set_var == 4):
    if (per_val == "and"):
        train_set = np.array([[0,1,1,0],[1,0,1,0],[0,1,0,0]])
    elif (per_val == "or"):
        train_set = np.array([[1,1,1,1],[0,0,1,1],[1,1,0,1]])
elif (train_set_var == 5):
    if (per_val == "and"):
        train_set = np.array([[1,1,1,1],[0,0,1,0],[0,0,0,0]])
    elif (per_val == "or"):
        train_set = np.array([[1,1,1,1],[0,0,0,0],[1,1,0,1]])

    
# Probamos los resultados
print("Resultados del perceptron antes de entrenar \n")
for i in range(len(all_comb)):
    print(all_comb[i]," guess: ",perceptron.guess(all_comb[i]),"\n")

print("Entrenando...\n")
# Entrenamos el perceptron
for i in range(train_num):
    perceptron.train(train_set)
print("Entrenamiento finalizado..\n")


# Comprobamos los resultados con el conjunto de prueba
print("Resultados del perceptron despues de entrenar \n")
for i in range(len(test_set)):
    print("Test input",test_set[i]," output: ",perceptron.guess(test_set[i]),"\n")
