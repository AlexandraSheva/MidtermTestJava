import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ToyRaffle {
  public static void main(String[] args) {
    String inpuString = "1 constuctor 2; 2 doll 2; 3 robot 6" ;
    String[] toysArr = inpuString.split(";");
    int[] idToy = new int[toysArr.length]; 
    String[] nameToy = new String[toysArr.length];
    double[] weightToy = new double[toysArr.length];
    
    for (int index = 0; index < toysArr.length; index++) {
      String toyStr = toysArr[index];
      String[] toysField = toyStr.split(" ");
      idToy[index] = Integer.parseInt(toysField[0]);
      nameToy[index] = toysField[1];
      weightToy[index] = Double.parseDouble(toysField[2]);
    }

    PriorityQueue <Toy> toyQueue = new PriorityQueue<>(Comparator.comparingDouble(Toy::getWeight));
    for (int i = 0; i < toysArr.length; i++) {
      Toy toy = new Toy(idToy[i], nameToy[i], weightToy[i]);
      toyQueue.offer(toy);      
    }

    Queue <Toy> generalQueue = new LinkedList<>();
    while (!toyQueue.isEmpty()) {
      generalQueue.offer(toyQueue.poll());
    }

    


  }
}
