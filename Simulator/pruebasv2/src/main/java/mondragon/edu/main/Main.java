package mondragon.edu.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mondragon.edu.clases.Line;
import mondragon.edu.clases.Order;
import mondragon.edu.clases.Parking;
import mondragon.edu.clases.Product;
import mondragon.edu.clases.Segment;
import mondragon.edu.clases.Workstation;
import mondragon.edu.config.AppConfig;
import mondragon.edu.control.ControlOrders;
import mondragon.edu.control.ControlVehicles;
import mondragon.edu.dbThread.HearDB;

/**
 * Main class of the project
 * 
 * @author unaiagirre
 *
 */
public class Main {
	
	List<Segment> SegmentList;
	List<Product> order1;
	Order order;
	ControlOrders controlOrders;
	ControlVehicles controlVehicles;
	HearDB hearDB;
//	Scanner teclado;//pa simular que entran orders
//	int ejec;//pa simnular que entran orders
	
	public Main() {

	//	teclado=new Scanner (System.in);
		AnnotationConfigApplicationContext context = 
	            new AnnotationConfigApplicationContext(AppConfig.class);

		controlVehicles=new ControlVehicles(context);
		inicializar();
		controlVehicles.setSegmentList(SegmentList);
		addOrder();
		hearDB = new HearDB(context, controlOrders);
		inicializarEscuchaBD();
	/*	do {
			ejec = menu();
			
			switch(ejec) {
			case 1: 
				hacerPedido();
			break;
			case 2:
				System.out.println("ERROR 404-HORARIO COMPLETO HASTA FIN DE AÑO");

					Workstation w=(Workstation) SegmentList.get(16);
					w.askForPriority();
					System.out.println("pene" + w.getId());

			break;
			}
		}while (ejec != -1);*/
		
	}
	/**
	 * Init the HearBD class, for looking to new orders
	 */
	private void inicializarEscuchaBD() {
	
		Thread thread= new Thread(hearDB);
		thread.start();
		
	}

	/*public void hacerPedido() {
		Order order;
		List<Product> lista=new ArrayList<>();
		do {
			ejec=menu2();
			switch(ejec) {
			case 1: 
			   	lista.add(new Product(0,"Monitor Asus", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 3));
			break;
			case 2:
			   	lista.add(new Product(0,"Laptop", (Workstation)SegmentList.get(18), (Workstation)SegmentList.get(16), 7));
			break;
			case 3:
			   lista.add(new Product(0,"Ordenador sobremesa", (Workstation)SegmentList.get(19), (Workstation)SegmentList.get(16), 10));
			break;
			case 4:
			   	lista.add(new Product(0,"Printer", (Workstation)SegmentList.get(20), (Workstation)SegmentList.get(16), 5));
			break;
			case 5:
			   	lista.add(new Product(0,"Periferals", (Workstation)SegmentList.get(21), (Workstation)SegmentList.get(16), 2));
			break;
			
			}
		}while (ejec != 0);
		order = new Order(0, lista);
		controlOrders.addOrder(order);
	}*/
	
/*	public int menu() {
		int sele;
		System.out.println("Selecciona ");
		System.out.println("1: meter order");
		System.out.println("2: pedir cita con la hermana del kepa");
		System.out.println("0: salir ");
		sele=teclado.nextInt();
		teclado.nextLine();
		return sele;
	}
	public int menu2() {
		int sele;
		System.out.println("Elige un producto.");
		System.out.println("1-Monitor Asus");
		System.out.println("2-Laptot hp");
		System.out.println("3-Ordernador sobremesa");
		System.out.println("4-Printer");
		System.out.println("5-Periferals");
		System.out.println("0-Salir(no con la hermana del kepa, mas te gustaria. en plan dejar de elegir productos)");
		sele=teclado.nextInt();
		teclado.nextLine();
		return sele;
	}
	*/
   /* private void addSegmentsToDatabase() {
    	//SegmentItemFacade database= new SegmentItemFacade();
		for (Segment s: SegmentList) {
			//database.saveSegment(s);
		}
	}*/
	/**
	 * Function for initializating controlOrders
	 */
	private void addOrder() {
		controlOrders=new ControlOrders(SegmentList);
	//	controlOrders.addOrder(order);
		
	}

	public static void main(String[] args) throws Exception {

    	@SuppressWarnings("unused")
		Main main= new Main();
 
    	
    }

	/**
	 * Calls to two functions to iniciate segments and segment threads
	 */
	public void inicializar() {
    	inicializarSegmentos();
   // 	inicializarOrders();
    	initAllThreads();
    }
    

/*	private void inicializarOrders() {
    	order1=new ArrayList<Product>();
    	order1.add(new Product(0,"Monitor Asus 1", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5));
    	order1.add(new Product(1,"Monitor Asus 2", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5));
    	order1.add(new Product(2,"Monitor Asus 3", (Workstation)SegmentList.get(17), (Workstation)SegmentList.get(16), 5));
    	order1.add(new Product(3,"Laptop hp 1", (Workstation)SegmentList.get(18), (Workstation)SegmentList.get(16), 7));
    	order1.add(new Product(4,"Laptop hp 2", (Workstation)SegmentList.get(18), (Workstation)SegmentList.get(16), 7));
		order=new Order(1, order1);
	}*/

	/**
	 * This function iniciate all the segments the simulation has
	 */
	public void inicializarSegmentos() {
		SegmentList=new ArrayList<Segment>();
		
    	SegmentList.add(new Line(1, 2, 13, controlVehicles));//-1 equivale a null
    	SegmentList.add(new Line(2, 3, -1, controlVehicles));
    	SegmentList.add(new Line(3, 4, 15, controlVehicles));
    	SegmentList.add(new Line(4, 5, -1, controlVehicles));
    	SegmentList.add(new Line(5, 6, -1, controlVehicles));
    	SegmentList.add(new Line(6, 7, -1, controlVehicles));
    	SegmentList.add(new Line(7, 8, 16, controlVehicles));
    	SegmentList.add(new Line(8, 9, -1, controlVehicles));
    	SegmentList.add(new Line(9, 10, 14, controlVehicles));
    	SegmentList.add(new Line(10, 11, -1, controlVehicles));
    	SegmentList.add(new Line(11, 12, -1, controlVehicles));
    	SegmentList.add(new Line(12, 1, -1, controlVehicles));
    	SegmentList.add(new Line(13, 11, -1, controlVehicles));
    	SegmentList.add(new Line(14, 3, -1, controlVehicles));
    	SegmentList.add(new Line(15, 9, -1, controlVehicles));
    	SegmentList.add(new Line(16, 5, -1, controlVehicles));
    	
    	
     	SegmentList.add(new Workstation(17,"workspace 1",  SegmentList.get(0).getId(), controlVehicles));
     	SegmentList.add(new Workstation(18,"workspace 2",  SegmentList.get(2).getId(), controlVehicles));
     	SegmentList.add(new Workstation(19,"workspace 3",  SegmentList.get(4).getId(), controlVehicles));
     	SegmentList.add(new Workstation(20,"workspace 4",  SegmentList.get(6).getId(), controlVehicles));
     	SegmentList.add(new Workstation(21,"workspace 5",  SegmentList.get(8).getId(), controlVehicles));
     	SegmentList.add(new Workstation(22,"workspace 6",  SegmentList.get(10).getId(), controlVehicles));
     	
     	SegmentList.add(new Parking(23,"parking 1", SegmentList.get(1).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(24,"parking 2", SegmentList.get(3).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(25,"parking 3", SegmentList.get(7).getId(), controlVehicles)); 
     	SegmentList.add(new Parking(26,"parking 4", SegmentList.get(9).getId(), controlVehicles));    	
    }
	/**
	 * Iniciate all the threads
	 */
	private void initAllThreads() {
		for (int i=0;i<SegmentList.size();i++) {
			initThread(SegmentList.get(i));
		}
	}

	/**
	 * Iniciate a thread
	 * @param s its a segment. We iniciate his thread
	 */
	private void initThread(Segment s) {
		Thread thread=new Thread(s);
		thread.setName("segment thread id: "+s.getId());
		thread.start();
		
	}

	public List<Segment> getSegmentList() {
		return SegmentList;
	}

}
