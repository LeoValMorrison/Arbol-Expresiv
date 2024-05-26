import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

class Nodo {
    String valor;
    Nodo izquierdo, derecho;

    Nodo(String valor) {
        this.valor = valor;
        izquierdo = derecho = null;
    }
}

class ExpresionArbol {
    Nodo raiz;

    // Constructor
    ExpresionArbol() {
        raiz = null;
    }

    // Método para insertar nodos
    void insertar(String valor) {
        raiz = insertarRec(raiz, valor);
    }

    // Método auxiliar para insertar nodos de forma recursiva
    Nodo insertarRec(Nodo raiz, String valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }

        if (esOperador(valor)) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, valor);
        } else {
            raiz.derecho = insertarRec(raiz.derecho, valor);
        }
        return raiz;
    }

    // Método para verificar si un nodo es operador
    boolean esOperador(String valor) {
        return valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/");
    }

    // Método para crear el árbol
    void crearArbol(String expresion) {
        String[] tokens = expresion.split(" ");
        for (String token : tokens) {
            insertar(token);
        }
    }

    // Método para mostrar el árbol
    void mostrarArbol() {
        JFrame frame = new JFrame("Árbol de Expresiones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode nodoRaiz = new DefaultMutableTreeNode("Expresión");
        crearNodos(raiz, nodoRaiz);

        JTree arbol = new JTree(nodoRaiz);
        frame.add(new JScrollPane(arbol));
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    // Método auxiliar para crear nodos del árbol
    void crearNodos(Nodo raiz, DefaultMutableTreeNode nodoPadre) {
        if (raiz != null) {
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(raiz.valor);
            nodoPadre.add(nodo);
            crearNodos(raiz.izquierdo, nodo);
            crearNodos(raiz.derecho, nodo);
        }
    }
}

public class expresiones {
    public static void main(String[] args) {
        // Crear instancia del árbol de expresiones
        ExpresionArbol arbol = new ExpresionArbol();

        // Ingresa la expresión a evaluar
        String expresion = JOptionPane.showInputDialog("Ingrese la expresión matemática (en notación postfija):");

        // Crear el árbol
        arbol.crearArbol(expresion);

        // Mostrar el árbol
        arbol.mostrarArbol();
    }
}
