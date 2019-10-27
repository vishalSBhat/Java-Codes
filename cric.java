import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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

    int generate(int min,int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    void create()
    {
        int j;
        for(j=0;j<8;j++)
        index[j]=generate(0,19);
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
            delay(generate(70, 150));
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

    public static void delay(int time)
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
        int runs,overs,temp;
        cric p1=new cric();
        cric p2=new cric();
        clearScreen();
        System.out.println("How many overs do you want to play??\n");
        overs=in.nextInt();
        in.nextLine();
        System.out.println("Get Ready!!");
        delay(1000);
        temp=overs*6;
        while((temp>0) && (p1.wickets>0))
        {
            p1.create();
            runs=p1.difficult(0);
            in.nextLine();
            if(runs!=-1)
            p1.runs+=runs;
            else
            p1.wickets--;
            delay(1000);
            temp--;
        }
        temp=overs*6;
        p1.display(0);
        while((temp>0) && (p2.wickets>0))
        {
            p2.create();
            runs=p2.difficult(p1.runs);
            in.nextLine();
            if(runs!=-1)
            p2.runs+=runs;
            else
            p2.wickets--;
            delay(1000);
            temp--;
        }
        p2.display(1);
        delay(1000);
        clearScreen();
        System.out.println("Player 1 scored "+p1.runs+"\nPlayer 2 scored "+p2.runs+"\n");
        if(p1.runs>p2.runs)
        System.out.println("\nPlayer 1 wins\n");
        else if(p1.runs<p2.runs)
        System.out.println("\nPlayer 2 wins\n");
        else
        System.out.println("\nTie Match!!\n");
    }
}
