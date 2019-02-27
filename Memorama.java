import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Memorama extends JFrame implements ActionListener {
	
	private String NombresImagenes [] = {"Calamar.jpg", "Pulpo1.png", "Pulpo.png", "Tiburon1.jpg","Tiburon.jpg", "Hipo.jpg", 
			"Manta.jpg", "Zorro.jpg", "Mono.jpg", "Vaca1.jpg", "Vaca.jpg", "Cabra.jpg", "Llamas.jpg", "Erizo.jpg", "Dino.jpg",
			"Elefante.jpg", "Leon1.jpg","Borrego.jpg", "Jirafa.jpg","Panda.jpg"};
	private BotonImagen VBotones [];
	
	private int NoPares, cont = 0, NoEncontrados = 0; 
	private BotonImagen Seleccion1, Seleccion2;
	
	public Memorama() {
		NoPares = Rutinas.nextInt(10, 20);
		VBotones = new BotonImagen [NoPares*2];
		HazInterfaz();
		HazEscuchas();
	}
	
	public void HazInterfaz() {
		//Llenar el vector de botones
		LlenarVector();
		//Mezclar vector
		MezclarVector();
		//Crear interfaz
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 4, 10, 10));
		
		for (int i = 0; i < VBotones.length; i++)
			add(VBotones[i]);
		
		setVisible(true);
	}
	
	public void HazEscuchas() {
		for (int i = 0; i < VBotones.length; i++)
			VBotones[i].addActionListener(this);;
	}

	public void actionPerformed(ActionEvent Evt) {
		BotonImagen BtnAux = (BotonImagen)Evt.getSource();
		BtnAux.ChangeIcon();
		cont++;
		if (cont==1)
			Seleccion1 = BtnAux;
		if(cont==2) {
			Seleccion2 = BtnAux;
			Seleccion2.update(Seleccion2.getGraphics());
			try{
				Thread.sleep(1000);
			} catch (Exception e){}
			Verificar();
			cont = 0;
		}
	}
	
	public void LlenarVector() {
		int pos = -1;
		for (int i=0 ; i<VBotones.length; i++) {
			pos++;
			VBotones[i] = new BotonImagen(NombresImagenes[pos]);
			i++;
			VBotones[i] = new BotonImagen(NombresImagenes[pos]);
		}
	}
	
	public void MezclarVector() {
		int aux1, aux2;
		BotonImagen BtnAux;
		for (int i = 0; i < 500 ; i++) {
			aux1 = Rutinas.nextInt(0, VBotones.length-1);
			aux2 = Rutinas.nextInt(0, VBotones.length-1);
			BtnAux = VBotones[aux1];
			VBotones[aux1] = VBotones[aux2];
			VBotones[aux2] = BtnAux;
		}
	}
	
	public void Verificar() {
		if (Seleccion1.getNombreImagen().compareTo(Seleccion2.getNombreImagen())!=0 || Seleccion1==Seleccion2) {
			Seleccion1.ChangeIcon();
			Seleccion2.ChangeIcon();
			Seleccion1 = null;
			return;
		}
		NoEncontrados++; 
		Seleccion1.setEnabled(false);
		Seleccion1.setDisabledIcon(Seleccion1.getIcon());
		
		Seleccion2.setEnabled(false);
		Seleccion2.setDisabledIcon(Seleccion2.getIcon());
		if(NoEncontrados == NoPares) {
			JOptionPane.showMessageDialog(this, "¡FELICIDADES, GANASTE!");
			dispose();
		}
	}
	
	public static void main (String [] a) {
		new Memorama();
	}
}