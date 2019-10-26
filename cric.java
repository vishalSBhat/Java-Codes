import java.io.IOException;
import java.util.*;
class cric
{
    static int a[]={1,1,1,-1,-1,-1,-1,-1,-1,2,2,2,3,3,4,4,4,6,6,6};
    int index[]=new int[8];
    int overs,runs,wickets;
    Random r=new Random();
    Scanner in=new Scanner(System.in);

    cric()
    {
        overs=0;
        runs=0;
        wickets=2;
    }

    public static void clearScreen() 
    {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    int generate()
    {
        return r.nextInt(20);
    }

    void create()
    {
        int j;
        for(j=0;j<8;j++)
        index[j]=generate();
    }

    void display(int cond)
    {
        int i;
        clearScreen();
        if(cond==0)
        System.out.println("Player 1\n\n");
        else if(cond==1)
        System.out.println("Player 2\n\n");
        else
        {
            System.out.println("Player 1 scored "+cond+"\n");
            System.out.println("Player 2\n\n");
        }
        System.out.println("OVERS - "+overs/6+"."+overs%6+"\nRUNS - "+runs+"\nWICKETS - "+wickets+"\n\n");
        for (i=0;i<8;i++) 
        {
            if(a[index[i]]!=-1)
            System.out.print(a[index[i]]+"\t");
            else
            System.out.print("\033[1;91mW\t\u001B[0m");
        }
        System.out.print("\n");
    }

    int difficult(int cond) throws IOException
    {
        int i=0,temp;
        boolean flag=true;
        while(true)
        {
            display(cond);
            temp=i;
            while(temp--!=0)
            System.out.print("\t");
            System.out.print("|");
            delay(150);
            if(flag)
            {
                i++;
                if(i==7)
                flag=false;
            }
            else
            {
                i--;
                if(i==0)
                flag=true;
            }
            if(!(System.in.available() == 0))
            break;
        }
        overs++;
        if((flag && i!=0) || (i==7))
        return a[index[--i]];
        else if(!flag || (i==0))
        return a[index[++i]];
        return 0;
    }

    void delay(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(Exception e)
        {
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String[] args) throws IOException
    {
        Scanner in=new Scanner(System.in);
        int runs;
        cric obj=new cric();
        cric obj1=new cric();
        while((obj.overs<12) && (obj.wickets>0))
        {
            obj.create();
            runs=obj.difficult(0);
            in.nextLine();
            if(runs!=-1)
            obj.runs+=runs;
            else
            obj.wickets--;
            obj.delay(1000);
        }
        obj.display(0);
        while((obj1.overs<12) && (obj1.wickets>0))
        {
            obj1.create();
            runs=obj1.difficult(obj.runs);
            in.nextLine();
            if(runs!=-1)
            obj1.runs+=runs;
            else
            obj1.wickets--;
            obj1.delay(1000);
        }
        obj1.display(1);
        obj1.delay(1000);
        clearScreen();
        System.out.println("Player 1 scored "+obj.runs+"\nPlayer 2 scored "+obj1.runs+"\n");
        if(obj.runs>obj1.runs)
        System.out.println("\nPlayer 1 wins\n");
        else if(obj.runs<obj1.runs)
        System.out.println("\nPlayer 2 wins\n");
        else
        System.out.println("\nTie Match!!\n");
    }
}