import turtle
import time
import random

# Configuración de la ventana
wn = turtle.Screen()
wn.title("Juego de Snake")
wn.bgcolor("black")
wn.setup(width=600, height=600)
wn.tracer(0)  # Desactiva la actualización automática de la pantalla

# Serpiente
serpiente = []
for i in range(3):
    segmento = turtle.Turtle()
    segmento.speed(0)
    segmento.shape("square")
    segmento.color("white")
    segmento.penup()
    segmento.goto(-20 * i, 0)
    serpiente.append(segmento)

# Comida
comida = turtle.Turtle()
comida.speed(0)
comida.shape("circle")
comida.color("red")
comida.penup()
comida.goto(0, 100)

# Movimiento
direccion = "stop"

def mover_arriba():
    global direccion
    if direccion != "down":
        direccion = "up"

def mover_abajo():
    global direccion
    if direccion != "up":
        direccion = "down"

def mover_izquierda():
    global direccion
    if direccion != "right":
        direccion = "left"

def mover_derecha():
    global direccion
    if direccion != "left":
        direccion = "right"

# Teclado
wn.listen()
wn.onkeypress(mover_arriba, "w")
wn.onkeypress(mover_abajo, "s")
wn.onkeypress(mover_izquierda, "a")
wn.onkeypress(mover_derecha, "d")

# Bucle principal del juego
while True:
    wn.update()

    # Colisión con la comida
    if serpiente[0].distance(comida) < 20:
        # Mover la comida a una nueva posición
        x = random.randint(-290, 290)
        y = random.randint(-290, 290)
        comida.goto(x, y)

        # Agregar un nuevo segmento a la serpiente
        nuevo_segmento = turtle.Turtle()
        nuevo_segmento.speed(0)
        nuevo_segmento.shape("square")
        nuevo_segmento.color("grey")
        nuevo_segmento.penup()
        serpiente.append(nuevo_segmento)

    # Mover la serpiente
    for i in range(len(serpiente) - 1, 0, -1):
        x = serpiente[i - 1].xcor()
        y = serpiente[i - 1].ycor()
        serpiente[i].goto(x, y)

    if direccion == "up":
        serpiente[0].sety(serpiente[0].ycor() + 20)
    if direccion == "down":
        serpiente[0].sety(serpiente[0].ycor() - 20)
    if direccion == "left":
        serpiente[0].setx(serpiente[0].xcor() - 20)
    if direccion == "right":
        serpiente[0].setx(serpiente[0].xcor() + 20)

    # Colisión con los bordes
    if (serpiente[0].xcor() > 290 or serpiente[0].xcor() < -290 or
        serpiente[0].ycor() > 290 or serpiente[0].ycor() < -290):
        time.sleep(1)
        wn.clear()
        wn.bgcolor("black")
        wn.title("Juego de Snake - Game Over")
        break

    time.sleep(0.1)

wn.mainloop()
