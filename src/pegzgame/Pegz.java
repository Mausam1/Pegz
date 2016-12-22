package pegzgame;

import java.io.IOException;
import java.util.*; 

public class Pegz { 
public static String strFrom,strTo; // Store From value input and To value input
public static int intFrom,intTo,PlayerAScore,PlayerBScore,ChangePlayer; // intFrom: store number according to From input value, intTo: store number according to To input value,PlayerAScore: to update and keep track of player a's score,PlayerBScore: To update and keep track of player b's score,ChangePlayer: To change the players turn 
public static ArrayList<Integer> BlankValues = new ArrayList<Integer>(25); // It stores value of delete pegz
public static Boolean isSingalPlayer;// for single player or double player game
    /** 
     * @param args the command line arguments 
     */ 
    public static void main(String[] args) throws IOException { 
        ChangePlayer=1;
        BlankValues.add(13); 
        AskforGamePlay();
        GenrateGameBoard(); 
        RefreshGameBoard(); 
    } 
    
public static void AskforGamePlay(){
    try {
        System.out.print("For From and To value please enter values like a1-a5, b1-b5, c1-c5, d1-d5, e1-e5");
        System.out.print("\nTo quit game anytime please enter q"); 
        System.out.print("\nFor Single Player enter 1 and For Double Player enter 2:"); 
        Scanner in = new Scanner(System.in);
        String str=in.nextLine();
        if ((str.contains("1")) || (str.contains("2"))){
        if ((str.equals("2")))isSingalPlayer=false;
        else isSingalPlayer=true;
        }
        else{
            System.out.println("\n Please Enter 1 or 2");
            AskforGamePlay();
        }
        } catch (Exception e){
          System.out.println(e);
        }
}
public static void RefreshGameBoard() throws IOException{ 
        GetFromValueInput(); 
        if(ValidateInsertedValue()) 
            UpdateGameBoard(); 
        else{ 
            System.out.print("Entered values are not valid"); 
            RefreshGameBoard(); 
        }
}  

public static Boolean ValidateInsertedValue(){ 
    try {
    if (intFrom != intTo){ 
      if (BlankValues.contains(intFrom) == false && BlankValues.contains(intTo) == true){ 
          if (intFrom==1|| intFrom==6 || intFrom==11 || intFrom==16|| intFrom==21 || intFrom==2 || intFrom==7 || intFrom==12 || intFrom==17 || intFrom==22){
            if (intFrom+2==intTo && !BlankValues.contains(intFrom+1))                
                return true;
            else if (intFrom+10==intTo && !BlankValues.contains(intFrom+5))                
                return true;
            else if (intFrom-10==intTo && !BlankValues.contains(intFrom-5))                
                return true;
            else return false;
          }
          else if (intFrom==10 || intFrom==15 || intFrom==20 || intFrom==25 || intFrom==4 || intFrom==9 || intFrom==14 || intFrom==19 || intFrom==24) {
             if (intFrom-2==intTo && !BlankValues.contains(intFrom-1))                
                return true;
            else if (intFrom+10==intTo && !BlankValues.contains(intFrom+5))                
                return true;
            else if (intFrom-10==intTo && !BlankValues.contains(intFrom-5))                
                return true;
            else return false;
          }
          else{
            if (intFrom+2==intTo && !BlankValues.contains(intFrom+1))                
                return true;
            else if (intFrom-2==intTo && !BlankValues.contains(intFrom-1))                
                return true;
            else if (intFrom+10==intTo && !BlankValues.contains(intFrom+5))                
                return true;
            else if (intFrom-10==intTo && !BlankValues.contains(intFrom-5))                
                return true;
            else return false;
          }
    } 
        else  return false; 
  }        
    else  return false; 
     } catch (Exception e) {
         System.out.println(e);
    }
    return false;
} 

public static void UpdateGameBoard() throws IOException{ 
    try{
        GetNumbersForDelete(); 
         System.out.print("\n   A B C D E"); 
         System.out.print("\n  +-+-+-+-+-+"); 
     for (int i=1; i<=25; i++ ){ 
         if (i==1 || i==11 || i==16 || i==21 || i==6){ 
             if (i!=1) 
             System.out.print("\n  +-+-+-+-+-+"); 
             System.out.print("\n"+((i/5)+1)); 
             System.out.print(" |"); 
             if (BlankValues.contains(i) == false) 
             System.out.print("X|"); 
             else 
             System.out.print(" |");     
         } 
         else if(BlankValues.contains(i)==true) 
             System.out.print(" |"); 
         else 
             System.out.print("X|");
     } 
          System.out.print("\n  +-+-+-+-+-+");
          if (!isSingalPlayer) {
            System.out.print("\n\n  PlayerA Score: "+ PlayerAScore); 
            System.out.print("\n  PlayerB Score: "+ PlayerBScore);          
        }
          else System.out.print("\n\n  Score: "+PlayerAScore);
         if(CheckPossibleMove()){
             if (!isSingalPlayer) {
               if(ChangePlayer==1)ChangePlayer=2;
               else if (ChangePlayer==2)ChangePlayer=1;
               if (ChangePlayer==1) System.out.print("\n  PlayerA turn"); 
               else System.out.print("\n  PlayerB turn"); 
             }
           RefreshGameBoard(); 
         }
         else{
             if (!isSingalPlayer) {
               if (PlayerAScore > PlayerBScore)
                 System.out.print("\n  PlayerA Win");
             else if (PlayerAScore<PlayerBScore)
                 System.out.print("\n  PlayerB Win");
             else System.out.print("\n  Game Draw");  
             }
             else System.out.print("\n  Final Score: "+PlayerAScore);
             System.out.print("\n  ******************  END GAME  *********************"); 
         }
    } catch (Exception e) {
         System.out.println(e);
    }
}

public static boolean CheckPossibleMove() {
    try{
    for (int i=1; i<26 ; i++){
        if(!BlankValues.contains(i)){
            if (i==1|| i==6 || i==11 || i==16|| i==21 || i==2 || i==7 || i==12 || i==17 || i==22){
                 if (!BlankValues.contains(i+1) && BlankValues.contains(i+2)){
                return true;
                 }
            else if (!BlankValues.contains(i+5) && BlankValues.contains(i+10)){  
                return true;
            }
            else if (!BlankValues.contains(i-5) && BlankValues.contains(i-10)){
                return true;            
            }
        }
         else if (i==5 || i==10 || i==15 || i==20 || i==25 || i==4 || i==9 || i==14 || i==19 || i==24) {
             if (!BlankValues.contains(i-1) && BlankValues.contains(i-2)){  
                return true;            
             }
            else if (!BlankValues.contains(i+5) && BlankValues.contains(i+10)){  
                return true;            
            }
            else if (!BlankValues.contains(i-5) && BlankValues.contains(i-10)) {
                return true;            
            }
        }
         else{
            if (!BlankValues.contains(i+1) && BlankValues.contains(i+2)) {
                return true;
            }
            else if (!BlankValues.contains(i-1) && BlankValues.contains(i-2)) {
                return true;    
            }
            else if (!BlankValues.contains(i+5) && BlankValues.contains(i+10)) {
                return true;            
            }
            else if (!BlankValues.contains(i-5) && BlankValues.contains(i-10)){
                return true;       
            }
         }
        }      
    }
    } catch (Exception e) {
         System.out.println(e);
    }
       return false;
}

public static void GetNumbersForDelete(){ 
    try{
    if ((intFrom-intTo)==2) { 
        if(BlankValues.contains(intFrom-1) == false) {
        BlankValues.add(intFrom-1);
        if (!isSingalPlayer) {
        if(ChangePlayer==1)PlayerAScore++;
        else PlayerBScore++;
        }
        else PlayerAScore++;}
    } 
    else if ((intFrom-intTo)==-2) { 
        if(BlankValues.contains(intFrom+1) == false){ 
        BlankValues.add(intFrom+1);
        if (!isSingalPlayer) {
        if(ChangePlayer==1)PlayerAScore++;
        else PlayerBScore++;
        }
        else PlayerAScore++;}
    } 
    else if ((intFrom-intTo)==10) { 
        if(BlankValues.contains(intFrom-5) == false) {
        BlankValues.add(intFrom-5); 
        if (!isSingalPlayer) {
        if(ChangePlayer==1)PlayerAScore++;
        else PlayerBScore++;
        }
        else PlayerAScore++; }
    } 
    else if ((intFrom-intTo)==-10) { 
        if(BlankValues.contains(intFrom+5) == false) {
        BlankValues.add(intFrom+5); 
           if (!isSingalPlayer) {
        if(ChangePlayer==1)PlayerAScore++;
        else PlayerBScore++;
        }
        else PlayerAScore++;}
    } 
        BlankValues.remove((Integer)intTo); 
        BlankValues.add(intFrom); 
         } catch (Exception e) {
         System.out.println(e);
    }
} 

 public static void GenrateGameBoard(){ 
    try{
     System.out.print("   A B C D E"); 
     System.out.print("\n  +-+-+-+-+-+"); 
     for (int i=1; i<=25; i++ ){ 
         if (i==1 || i==11 || i==16 || i==21 || i==6){ 
             if (i!=1) 
            System.out.print("\n  +-+-+-+-+-+"); 
             System.out.print("\n"+((i/5)+1)); 
             System.out.print(" |X|"); 
         } 
         else if(i==13) System.out.print(" |"); 
         else   System.out.print("X|"); 
     } 
          System.out.print("\n  +-+-+-+-+-+");
          if (!isSingalPlayer) System.out.print("\n  PlayerA turn");
           } catch (Exception e) {
         System.out.println(e);
    }
 }  

 public static void GetFromValueInput(){
     try{
         if (PlayerAScore==0) System.out.print("\n\n  From:");
         else System.out.print("\n  From:"); 
     Scanner in = new Scanner(System.in); 
     String strinput = in.nextLine(); 
     int from = GetNumberFromInput(strinput); 
     if (from!=26){ 
         intFrom=from; 
         GetToValueInput(); 
     } 
     else GetFromValueInput(); 
      } catch (Exception e) {
         System.out.println(e);
    }
 } 

 public static void GetToValueInput(){ 
     try{
     System.out.print("  To:"); 
     Scanner in = new Scanner(System.in); 
     String strinput = in.nextLine(); 
     int to = GetNumberFromInput(strinput); 
     if (to!=26){ 
         intTo=to; 
     } 
     else GetToValueInput();
      } catch (Exception e) {
         System.out.println(e);
    }
 } 

 public static int GetNumberFromInput(String str){ 
     int value=0; 
     try{
     if (str.equals("a1")|| str.equals("A1")) value=1; 
     else if (str.equals("b1")|| str.equals("B1")) value=2; 
     else if (str.equals("c1")|| str.equals("C1")) value=3; 
     else if (str.equals("d1")|| str.equals("D1")) value=4; 
     else if (str.equals("e1")|| str.equals("E1")) value=5; 
     else if (str.equals("a2")|| str.equals("A2")) value=6; 
     else if (str.equals("b2")|| str.equals("B2")) value=7; 
     else if (str.equals("c2")|| str.equals("C2")) value=8; 
     else if (str.equals("d2")|| str.equals("D2")) value=9; 
     else if (str.equals("e2")|| str.equals("E2")) value=10; 
     else if (str.equals("a3")|| str.equals("A3")) value=11; 
     else if (str.equals("b3")|| str.equals("B3")) value=12; 
     else if (str.equals("c3")|| str.equals("C3")) value=13; 
     else if (str.equals("d3")|| str.equals("D3")) value=14; 
     else if (str.equals("e3")|| str.equals("E3")) value=15; 
     else if (str.equals("a4")|| str.equals("A4")) value=16; 
     else if (str.equals("b4")|| str.equals("B4")) value=17; 
     else if (str.equals("c4")|| str.equals("C4")) value=18; 
     else if (str.equals("d4")|| str.equals("D4")) value=19; 
     else if (str.equals("e4")|| str.equals("e4")) value=20; 
     else if (str.equals("a5")|| str.equals("A5")) value=21; 
     else if (str.equals("b5")|| str.equals("B5")) value=22; 
     else if (str.equals("c5")|| str.equals("C5")) value=23; 
     else if (str.equals("d5")|| str.equals("D5")) value=24; 
     else if (str.equals("e5")|| str.equals("E5")) value=25; 
     else if (str.equals("q")|| str.equals("Q")){
         if (!isSingalPlayer) {
            System.out.print("\n\n  PlayerA Score: "+ PlayerAScore); 
            System.out.print("\n  PlayerB Score: "+ PlayerBScore);          
               if (PlayerAScore > PlayerBScore)
                 System.out.print("\n  PlayerA Win");
             else if (PlayerAScore<PlayerBScore)
                 System.out.print("\n  PlayerB Win");
             else System.out.print("\n  Game Draw");  
             }
             else System.out.print("\n  Final Score: "+PlayerAScore);
             System.out.print("\n  ******************  END GAME  *********************"); 
             System.exit(0); }
     else {value =26; 
         System.out.println("Please enter Valid Value"); 
     } 
      } catch (Exception e) {
         System.out.println(e);
    }
     return value; 
 }
} 


