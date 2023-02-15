/**
 *
 * @author manuela
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Grafo {

    // se crea una lista de lista de tipo entero, la idea es poder representar un
    // grafo como una lista enlazada
    List<List<Integer>> lista_grafo;
    boolean visitados[];
    int nodos;

    // Constructor la clase, recibe como parametro el número de nodos

    Grafo(int nodos) {

        // inicializa la lista
        this.lista_grafo = new ArrayList<>();
        // inicializa una lista auxiliar para el dfs de tipo booleano, con el fin de
        // identificar si un nodo está o no en el grafo
        visitados = new boolean[nodos];
        this.nodos = nodos;

        // Crea #nodos listas mas, donde podrá albergar las conexiones que tiene cada
        // nodo
        for (int i = 0; i < nodos; i++) {
            lista_grafo.add(i, new ArrayList<>());
        }

    }

    public void añadirArista(int a, int b) {

        // Función List.get() : Returns the element at the specified position in this
        // list.

        lista_grafo.get(a).add(b); // ingresa el nodo (a), junto con el otro nodo que tiene conexión (b)
        lista_grafo.get(b).add(a);// al ser un grafo no dirigido se replica inversamente

    }

    public boolean grafoConectado() {

        int contador = 1;
        int indiceInicio = 0;
        int bandera = 0;
        // le mando el numero 0 al dfs
        dfs(indiceInicio);

        // recorre el vector auxiliar de visitados, si encuentra un false en la matriz
        // booleana devuelve un falso.
        for (int i = 0; i < visitados.length; i++) {

            if (visitados[i] == false) {

                // apenas encuentra un F, mandara a dfs a recorrer dicho indice, mientras
                // modifica la matriz booleana de visitados
                dfs(i);
                bandera = 1;
                contador++;
            }
        }

        if (bandera == 1) {
            System.out.println("Compuesto");
            System.out.println(" El número de elementos es: " + contador);
            return true;
        } else {

            // si recorre toda la lista y no encuentra un false en la matriz booleana,
            // devuelve un true, dado que es un grafo conectado
            System.out.println("Sustancia pura");
            return true;

        }
    }

    public void dfs(int inicio) {

        Stack<Integer> pila = new Stack<>(); // crea una pila
        pila.push(inicio); // ingresa el nodo por el cual quiere empezar el recorrido
        visitados[inicio] = true; // arreglo booleano auxiliar en la posición del valor ingresado, lo ve como
                                  // verdadero

        while (!pila.isEmpty()) {
            // saca el nodo
            Integer nodo = pila.pop();

            // crea una lista para albergar los nodos adyacentes del nodo que saca de la
            // pila
            // esto lo alberga en una variable tipo list dado que devuelve una lista
            List<Integer> vecinos = lista_grafo.get(nodo);

            // recorre la lista que yace del nodo que se sacó de la pila
            for (Integer vecino : vecinos) {

                // si en la lista de visitados, el vecino no se ha recorrido
                if (!visitados[vecino]) {

                    // agregar el vecino a la pila
                    pila.push(vecino);
                    // volver al vecino como ya recorrido
                    visitados[vecino] = true;
                }
            }

        }
    }

    public static boolean tipoDeSustancia(int nodos, int aristas) {

        // recibe parejas que se conectan
        int a, b;
        Scanner s = new Scanner(System.in);

        // inicializa el grafo
        Grafo grafo_prueba = new Grafo(nodos);

        // recorre el numero de aristas pidiendo las parejas
        for (int i = 0; i < aristas; i++) {
            a = s.nextInt();
            b = s.nextInt();
            grafo_prueba.añadirArista(a, b);
        }

        /// revisa si el grafo es conectado, dicha funcion en su interior tiene dfs

        return grafo_prueba.grafoConectado();

    }

    public static void main(String[] args) {

        int n, nodos, aristas;

        Scanner s = new Scanner(System.in);
        n = s.nextInt();

        for (int i = 0; i < n; i++) {

            nodos = s.nextInt();
            aristas = s.nextInt();
            Grafo.tipoDeSustancia(nodos, aristas);

        }
    }
}
