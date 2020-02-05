//Aymen Rumi
//260661663

public class CoinMachine {
    
    public static void main(String args[])
    {
        if(args.length < 2)
        {
            System.out.println("You need to enter two arguments to this program. Try typing 'run CoinMachine 400 215' in Dr. Java, or 'java VendingMachine 400 215' on the command line.");
            return;
        }
        
        
      
        int cash = getInputInteger(args[0]);
        int price = getInputInteger(args[1]);
        
        //========================
        //Enter your code below
        
        int change = cash-price;
        
        if(change<0)
        {
        	System.out.println("Not Enough Cash!!");
        }
        else if(change%5!=0)
        {
        	System.out.println("Change not a multiple of 5!!");
        }   	
        else
        {
        	System.out.println("Amount received: "+cash);
            System.out.println("Cost of item: "+price);
            System.out.println("Required change: "+change);
                             
            
            int toonies=change/200;
            change=change%200;
            int loonies=change/100;
            change=change%100;
            int quarters=change/25;
            change=change%25;
            int dime=change/10;
            change=change%10;
            int nickel=change/5;
            change=change%5;
                        
                             
            System.out.println("Change:");
            System.out.println("    toonies x "+toonies);
            System.out.println("    loonie x "+loonies);
            System.out.println("    quarter x "+quarters);
            System.out.println("    dime x "+dime);
            System.out.println("    nickel x "+nickel);
        }
        
        //Enter your code above
        //========================
    }
    
    public static int getInputInteger(String arg) {
        try
        {
            return Integer.parseInt(arg);
        } catch(NumberFormatException e) {
            System.out.println("ERROR: " + e.getMessage() + " This argument must be an integer!");
        }
        
        //error, return 0
        return 0;
    }
}