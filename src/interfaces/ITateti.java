package interfaces;

// creamos una interfaz para estructurar mejor el Objeto Tateti

public interface ITateti {

    public void inicializar();

    public void turno(boolean esPrimeroJugador);

    public boolean jugarJugador(int posicionF, int posicionC);

}
