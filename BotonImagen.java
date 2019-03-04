import javax.swing.*;

public class BotonImagen extends JButton{
	private String NombreImagen;
	private ImageIcon Imagen, Inicio;
	private boolean Bandera;
	
	public BotonImagen(String NombreImagen) {
		this.NombreImagen = NombreImagen;
		Bandera = false;
		Inicio = Rutinas.AjustarImagen("Signo.png", 80, 80);
		Imagen = Rutinas.AjustarImagen(NombreImagen, 80, 80);
		setIcon(Inicio);
	}
	public String getNombreImagen() {
		return NombreImagen;
	}
	public void ChangeIcon() {
		if(Bandera) {
			setIcon(Inicio);
			Bandera = false;
		}
		else {
			setIcon(Imagen);
			Bandera = true;
		}
	}
}

