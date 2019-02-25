package projeto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class projeto extends JPanel{
	
	JTextField t1 , t2 ,t3,t4 ;
	JButton calc;
	JLabel aut;
	 JList taxas;
	 JLabel lbjuros,lb2,lb3,lb4; 
	 JScrollPane spLista;
	 double res;
	 double res1;
	public projeto()  {
		
		inicializarcomponentes();
		definireventos();
		
	}
	
	
	public void inicializarcomponentes () {
		
		setLayout( null);
		
		String[] txJuros = { "A vista-5% de desconto","12x-3% de juros", "24x-5% de juros", "36x-10% de juros","48x-15% de juros" };		
		taxas = new JList(txJuros);
		lbjuros = new JLabel("Opções de pagamento");
		lb2 = new JLabel("Valor Final do Produto:");
		lb3 = new JLabel("Valor Original do Produto:");
		lb4 = new JLabel("Valor das Parcelas ao Mês:");
		spLista = new JScrollPane(taxas);
		 t1 = new JTextField ();
		 t2 = new JTextField ();
		 t3 = new JTextField ();
		 t4 = new JTextField ();
		calc = new JButton ("calcular");
		aut= new JLabel ("Auto Vendas");
		
		 add(t1);
		 add(t2);
		 add(t3);
		 add(t4);
	    add(calc);
	    add(aut);
	    add(lbjuros);
	    add(spLista);
	    add(lb2);
	    add(lb3);
	    add(lb4);
	    
	    aut.setBounds(100, 0, 200, 60);
	    t1.setBounds(100,50, 120,40);
	    t2.setBounds(300, 150, 120, 35);
	    t3.setBounds(300, 190, 120, 35);
	    t4.setBounds(300, 230, 130, 35);
	    lbjuros.setBounds(250, 0, 140, 40);
	    spLista.setBounds(250, 50, 155, 40);
	    calc.setBounds(100, 100, 100, 40);
	    lb2.setBounds(100,145, 140,40);
	    lb3.setBounds(100,185, 180,40);
	    lb4.setBounds(100,225, 180,40);
	    
	}

public void definireventos () {
	
	calc.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg0){
			if(t1.getText().equals("")){
				t1.requestFocus();
				return;
			}
			float valor = Float.parseFloat(t1.getText());
			if(taxas.getSelectedIndex()==-1){
				JOptionPane.showMessageDialog(null,"Selecione um item da lista: ");
				return;
			}
			float desconto = 0f;
			if(taxas.getSelectedIndex()==0){
			desconto =0.05f;
			res=valor*desconto;
			res1=valor-res;
			t2.setEditable(false);
			t3.setEditable(false);
			t4.setEditable(false);
			t2.setText(" "+res1); 
			t3.setText(" "+valor);
			
			
			}
			
			if(taxas.getSelectedIndex()==1){
				desconto = 0.03f;
				res=valor*desconto*12+valor;
				res1=res/12;
				t2.setEditable(false);
				t3.setEditable(false);
				t4.setEditable(false);
				t2.setText(" "+res); 
				t3.setText(" "+valor);
				t4.setText(" "+res1);
			}
			if(taxas.getSelectedIndex()==2){
				desconto = 0.05f;
				res=((valor*desconto*24))+valor;
				res1=res/24;
				t2.setEditable(false);
				t3.setEditable(false);
				t4.setEditable(false);
				t2.setText(" "+res); 
				t3.setText(" "+valor);
				t4.setText(" "+res1);
			}
			if(taxas.getSelectedIndex()==3){
				desconto = 0.1f;
				res=((valor*desconto*36))+valor;
				res1=res/36;
				t2.setEditable(false);
				t3.setEditable(false);
				t4.setEditable(false);
				t2.setText(" "+res); 
				t3.setText(" "+valor);
				t4.setText(" "+res1);
			}
			if(taxas.getSelectedIndex()==4){
				desconto = 0.15f;
			res=((valor*desconto*48))+valor;
			res1=res/48;
			t2.setEditable(false);
			t3.setEditable(false);
			t4.setEditable(false);
			t2.setText(" "+res); 
			t3.setText(" "+valor);
			t4.setText(" "+res1);
			}
			
			
		}
	
});
}
public static void main(String args[]){
		JFrame frame = new JFrame("Auto Vendas");
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.getContentPane().add(new projeto());
	 frame.setBounds(300,200,500,400);
	 frame.setVisible(true);
 }
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
