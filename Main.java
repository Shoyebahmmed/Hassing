import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

class Main {

//----------- Calculate First Hash Function
static int hash_function_one(int value, int M){
  int temp_index = value%M;
  return temp_index;
} 


//----------- Calculate Second Hash Function
static int hash_function_two (int value	, int q){
	int temp_index = q * (value % q);
	return temp_index; 
}



//------------LIFO Function
    public static HashMap<Integer, Integer> Lifo_Add_Function(ArrayList<Integer> data, int M){
        HashMap<Integer, Integer>table = new HashMap<Integer, Integer>();
        int counter = 0;
        for (int i=0; i < data.size(); i++){ 
            int key =  hash_function_one(data.get(i), M);
            if(table.containsKey(key)){

                int b = data.get(i);
                int TempKey = key;
                while (table.containsKey(TempKey)){
                    TempKey++;
                    counter++;
                }
            counter++;
            table.put(TempKey, b);
            }
            else{
                counter++;
                table.put(key, data.get(i));
            }
        
        }
      System.out.println("AVG of prob: " + (counter/data.size()));
      return table;   
    }

    public static boolean Lifo_Search_Key(HashMap<Integer, Integer> Map, int M, int y){
      int key = hash_function_one(y, M); 
       if(Map.containsKey(key) && Map.get(key)==y){ 
         return true;
       }

       else if(Map.containsKey(key) && Map.get(key) != y){
        int tempK = key;
        while(Map.containsKey(tempK) && Map.get(key)!=y){
          tempK++;
        }
        return true;    
       }
      return false;
    }

    public static void Lifo_Search_Solution(HashMap<Integer, Integer> Map, ArrayList<Integer> data, int Trg, int M){
      int point = 1;
      for(int i = 0; i < data.size(); i++){
        int y = Trg - data.get(i);
        if(Lifo_Search_Key(Map, M, y)){

          System.out.println("Point " + point + " is: ["+data.get(i)+", "+y+"]");
          point++;
        }
      }
    }


//----------- Add function to add into the array
static HashMap<Integer, Integer> add_function (ArrayList<Integer> data, int q, int M){
	HashMap<Integer, Integer> list = new HashMap<>();

int count = 0;
	for (int i = 0; i < data.size(); i++) {
		int key1 = hash_function_one (data.get(i), M);
    //count++;
		boolean status1 = list.containsKey(key1);

		if(status1 == true) {
      int key2 = hash_function_two (data.get(i), q) ;




      int f = 1;
      for(int g = 0; g < 3; g++){
        int pos = (key1 + (f * key2 )) % M;
        boolean status2 = list.containsKey(pos);
        //count++;

        if(status2 == false) {
          count++;
          list.put(pos, data.get(i));
					break;
          }
          else {
          count++;
          f++;
          g = 0;
          continue;
          }
      }
		}
		else{
      count++;
			list.put(key1, data.get(i));
      }
  }

  System.out.println("AVG of prob: " + (count/data.size()));
	return list;
}



// function to search value
static boolean search_in_key_position(HashMap<Integer, Integer> list, int q, int M, int value){
  	boolean status = false;
	  int key1 = hash_function_one (value, M);
    boolean status1 = list.containsKey(key1);

     if(status1 == true && list.get(key1) == value){
      status = true;
    }

		else if(status1 == true && list.get(key1) != value) {
			int key2 = hash_function_two (value, q);


      int f = 1;
      int pos = (key1 + (f * key2 )) % M;
      while (list.containsKey(pos) && list.get(pos) != value){
        f++;
        pos = (key1 + (f * key2 )) % M;
      }
      status = true;
    }

	return status;
}




// function to solve 2-sum problem 
static void search_for_solution(ArrayList<Integer> data, HashMap<Integer, Integer> list, int tar_int, int q, int M){
  int point = 1;
	for(int i = 0; i < data.size(); i++) {
    int y = tar_int - data.get(i);
		if(search_in_key_position(list, q, M, y)){
      System.out.println("Point " + point + " is: ["+data.get(i)+", "+y+"]");
          point++;
    }
    }
  }




  public static void main(String[] args) throws FileNotFoundException {

      Scanner in = new Scanner(System.in);

      int [] trg = {3, 4 , 5, 6, 7, 8,  9, 10};
      long startTime, stopTime;

      HashMap<Integer, Integer> map = new HashMap<>();
      ArrayList<Integer> arr = new ArrayList<Integer>();

      File file = new File("dataset.txt");
      Scanner fin = new Scanner(file);
      while (fin.hasNextLine()){

        int num;
          try {
            //double value = fin.nextLine();
            num = Integer.parseInt(fin.nextLine());
            arr.add(num);
            }
          catch (NumberFormatException e) {
            num = 0;
          }
        }

//       System.out.println(arr);
//       System.out.println("Size: " +arr.size());

//   }
// }
      for(int e = 0; e < 3; e++){
        System.out.println("For Using Method 1 (LIFO) Press: 1 ");
        System.out.println("For Using Method 2 (Double hashing ) Press: 2 ");
        System.out.println("For Exit Press: 0 ");

        int userChoice = in.nextInt();

        switch(userChoice){
          case 1:
            for(int f = 0; f < 3; f++){
              System.out.println("For Using \"0.25\" Press: 1 ");
              System.out.println("For Using \"0.50\" Press: 2 ");
              System.out.println("For Using \"0.75\" Press: 3 ");
              System.out.println("For Exit Press: 0 ");

            int userChoice3 = in.nextInt();

            switch(userChoice3){
              case 1:
                int M1 = 4000000;
                map = Lifo_Add_Function(arr, M1);

                startTime = System.nanoTime();
                for(int i = 0; i < trg.length; i++){
                System.out.println("\nFor target value " + trg[i] + " :\n" );

                Lifo_Search_Solution(map, arr, trg[i], M1);
              }

                stopTime = System.nanoTime();
                System.out.println("And the execution: " + (stopTime - startTime));
              f = 0;
              break;

              case 2:
                int M2 = 2000000;
                map = Lifo_Add_Function(arr, M2);

                startTime = System.nanoTime();
                for(int i = 0; i < trg.length; i++){
                System.out.println("\nFor target value " + trg[i] + " :\n" );

                Lifo_Search_Solution(map, arr, trg[i], M2);
              }
              
                stopTime = System.nanoTime();
                System.out.println("And the execution: " + (stopTime - startTime));
              f = 0;
              break;

              case 3:
                int M3 = 1000000;
                map = Lifo_Add_Function(arr, M3);

                startTime = System.nanoTime();
                for(int i = 0; i < trg.length; i++){
                System.out.println("\nFor target value " + trg[i] + " :\n" );

                Lifo_Search_Solution(map, arr, trg[i], M3);               
              }
              
                stopTime = System.nanoTime();
                System.out.println("And the execution: " + (stopTime - startTime)); 
              f = 0;
              break;
              

              case 0:
              System.out.println("You have succesfully exit from Method 1.");
              f = 5;
              break;

              default:
              System.out.println("Sorry you entered a wrong choice.\nPlease enter the right input.");
              f = 0;
              break;
              }
            }
            e = 0;
            break;

          case 2:
            for(int f = 0; f < 3; f++){
              System.out.println("For Using \"0.25\" Press: 1 ");
              System.out.println("For Using \"0.50\" Press: 2 ");
              System.out.println("For Using \"0.75\" Press: 3 ");
              System.out.println("For Exit Press: 0 ");

            int userChoice2 = in.nextInt();

            switch(userChoice2){
              case 1:
                int M1 = 4000000, q1 = 3999971;
                map = add_function(arr, q1, M1);

                startTime = System.nanoTime();
                for(int i = 0; i < trg.length; i++){
                System.out.println("\nFor target value " + trg[i] + " :\n" );

                search_for_solution(arr, map, trg[i], q1, M1);
              }
              
                stopTime = System.nanoTime();
                System.out.println("And the execution: " + (stopTime - startTime));  
              f = 0;
              break;

              case 2:
                int M2 = 2000000, q2 = 1999993;
                map = add_function(arr, q2, M2);

                startTime = System.nanoTime();
                for(int i = 0; i < trg.length; i++){
                System.out.println("\nFor target value " + trg[i] + " :\n" );

                search_for_solution(arr, map, trg[i], q2, M2); 
              }
              
                stopTime = System.nanoTime();
                System.out.println("And the execution: " + (stopTime - startTime));
              f = 0;
              break;

              case 3:
                int M3 = 1000000, q3 = 1333331;
                map = add_function(arr, q3, M3);

                startTime = System.nanoTime();
                for(int i = 0; i < trg.length; i++){
                System.out.println("\nFor target value " + trg[i] + " :\n" );

                search_for_solution(arr, map, trg[i], q3, M3);     
              }
              
                stopTime = System.nanoTime();
                System.out.println("And the execution: " + (stopTime - startTime));  
              f = 0;
              break;
              

              case 0:
              System.out.println("You have succesfully exit from Method 2.");
              f = 5;
              break;

              default:
              System.out.println("Sorry you entered a wrong choice.\nPlease enter the right input.");
              f = 0;
              break;
              }
            }
            e = 0;
            break;

          case 0:
            System.out.println("You have succesfully exit from the Program.");
            e = 5;
            break;
          
          default:
            System.out.println("Sorry you entered a wrong choice.\nPlease enter the right input.");
            e = 0;
            break;
        }
      }



      //   int M = 4000000, q = 3999971;
      //  map = add_function(arr, q, M);

      // for(int i=0; i<trg.length; i++){
      //   search_for_solution(arr, map, trg[i], q, M);
      // }

    // System.out.println(map);
    //    System.out.println("Status");
    //   boolean status = search_in_key_position(map, q, M, 40);
    // System.out.println("Status = " + status);
  }
}