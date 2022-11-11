package interfaces;

public interface ITateti {

    public void inicializar();

    public void turno(boolean esPrimeroJugador);

    public boolean jugar(int posicionF, int posicionC);

}
