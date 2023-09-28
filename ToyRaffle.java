import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class ToyRaffle {
  public static void main(String[] args) {
    String inpuString = "1 constuctor 5;2 doll 3;3 robot 2" ;
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

    try (PrintWriter writer = new PrintWriter(new FileWriter("raffle.txt"))) {
      Random random = new Random();
      for (int i = 0; i < 10; i++) {
        double randomNum = random.nextDouble()*10;
        Toy toy = generalQueue.peek();
        if(toy!=null && randomNum < toy.getWeight()) {
          writer.println(generalQueue.poll().getName());
        } else {
          writer.println("constructor");
        }
        
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
