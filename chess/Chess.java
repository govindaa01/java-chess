

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;   

public class Chess {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Start p=new Start();
        
    }
    
}
class Start extends JFrame implements ActionListener{
    JButton btn[][];
    JFrame jf;
    GridLayout gl;
    Container c;
    int chal=1;//0 for no ,1 for whites -1 for blacks
    int yellowx=-1,yellowy=-1;
     int x=0;int p=0,q=0,flag=0;
    int a[][]= new int[8][8];
    int chl =1;//it knows ki kiski chaal hai
    Start(){
       
        jf=new JFrame();
      btn= new JButton[8][8]; 
      jf.setTitle("chess");
      jf.setBounds(0, 0, 700, 700);
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      c=jf.getContentPane();
       ImageIcon bbishop=new ImageIcon("bbishop.png");
       ImageIcon bking=new ImageIcon("bking.png");
       ImageIcon bqueen=new ImageIcon("bqueen.png");
       ImageIcon bknight=new ImageIcon("bk.png");
       ImageIcon bcastle=new ImageIcon("bcastle.png");
       ImageIcon bpawn=new ImageIcon("bpawn.png");
       ImageIcon wbishop=new ImageIcon("wbishop.png");
       ImageIcon wking=new ImageIcon("wking.png");
       ImageIcon wqueen=new ImageIcon("wqueen.png");
       ImageIcon wknight=new ImageIcon("wknight.png");
       ImageIcon wcastle=new ImageIcon("wcastle.png");
      
      ImageIcon wpawn=new ImageIcon("wpawn.png"); 
      for(int i=0;i<8;i++)
          for(int j=0;j<8;j++){
              if(i==0){
                  
              if((j==2||j==5)){
              btn[i][j]=new JButton(bbishop);
              a[i][j]=-4;}
              else  if(j==0||j==7){
              btn[i][j]=new JButton(bcastle);a[i][j]=-2;}
              else if(j==1||j==6){
                   btn[i][j]=new JButton(bknight);a[i][j]=-3;}
              else if(j==3){
                   btn[i][j]=new JButton(bking);a[i][j]=-6;}
              else if(j==4){
                  btn[i][j]=new JButton(bqueen);a[i][j]=-5;}
              else
               btn[i][j]=new JButton("");    
              }
              else if(i==7){
              if((j==2||j==5)){
              btn[i][j]=new JButton(wbishop);a[i][j]=4;}
              else  if(j==0||j==7){
              btn[i][j]=new JButton(wcastle);a[i][j]=2;}
              else if(j==1||j==6){
                   btn[i][j]=new JButton(wknight);a[i][j]=3;}
              else if(j==3){
                   btn[i][j]=new JButton(wking);a[i][j]=6;}
              else if(j==4){
                  btn[i][j]=new JButton(wqueen);a[i][j]=5;}
              else
               btn[i][j]=new JButton("");    
              }
              else if(i==1){
                  a[i][j]=-1;
               btn[i][j]=new JButton(bpawn);}
              else if(i==6){a[i][j]=1;
                  btn[i][j]=new JButton(wpawn);}
       else
                  btn[i][j]=new JButton("");
          }for(int i=0;i<8;i++)
          for(int j=0;j<8;j++){
              btn[i][j].addActionListener(this);
          }
         for(int i=0;i<8;i++)
          for(int j=0;j<8;j++){
              btn[i][j].setText(a[i][j]+"");
          } 
        setc();
      gl=new GridLayout(8, 8);
      c.setLayout(gl);
      for(int i=0;i<8;i++)
          for(int j=0;j<8;j++)
              c.add(btn[i][j]);
      jf.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        
        for(int i=0;i<8;i++)
          for(int j=0;j<8;j++){
              if((JButton)e.getSource()==btn[i][j]){
                  p=i;q=j;
                 break;  
              }
                 
          }
        
        //JOptionPane.showMessageDialog(null, "this is the turn for "+chal);
        JButton bt=(JButton)e.getSource();
      
        
        if(bt.getBackground()!=Color.red)
	{   
		 setc();
		    if(Integer.parseInt(bt.getText())!=0)
			  {
            btn[p][q].setBackground(Color.yellow);
            yellowx=p;
            yellowy=q;
               if(Integer.parseInt(bt.getText())==1||Integer.parseInt(bt.getText())==-1)
                  pawnmove(p,q);
              else if(Integer.parseInt(bt.getText())==3||Integer.parseInt(bt.getText())==-3){
                  knightmove(p,q);
               }
              else if(Integer.parseInt(bt.getText())==2||Integer.parseInt(bt.getText())==-2)
                  castlemove(p,q);
               else if(Integer.parseInt(bt.getText())==4||Integer.parseInt(bt.getText())==-4)
                   bishopmove(p,q);
               else if(Integer.parseInt(bt.getText())==5||Integer.parseInt(bt.getText())==-5)
               {
               bishopmove(p,q);
               castlemove(p,q);
               }
               
               else 
                   kingmove(p,q);
             }
     
        } 
        else
        {   turn(p,q);
            setc();
        }
 }//killing of each other (pure war!)
    public void kill(int p,int q){
    Icon temp;
                  a[yellowx][yellowy]=0;
                  a[p][q]=Integer.parseInt(btn[yellowx][yellowy].getText());
        temp = btn[yellowx][yellowy].getIcon();
        btn[p][q].setIcon(temp);
        btn[yellowx][yellowy].setIcon(null);
        setc();
}
      public void turn(int p,int q){
		 
                  Icon temp;
                  a[yellowx][yellowy]=0;
                  a[p][q]=Integer.parseInt(btn[yellowx][yellowy].getText());
                  btn[p][q].setText(a[p][q]+"");
                  btn[yellowx][yellowy].setText("0");
        temp = btn[yellowx][yellowy].getIcon();
        btn[p][q].setIcon(temp);
        btn[yellowx][yellowy].setIcon(null);
        setc();
	  }
        public void setc(){
             for(int i=0;i<8;i++){
          if(x==0)
              x=1;
          else
              x=0;
          for(int j=0;j<8;j++){
              if(x==0){
                  btn[i][j].setBackground(new java.awt.Color(102, 51, 0));
                  btn[i][j].setText(a[i][j]+"");
                  x=1;
              }
              else{
                  btn[i][j].setBackground(Color.white);
                  btn[i][j].setText(a[i][j]+"");
                  x=0;
              }
          }
      
        }
    }
        public void pawnmove(int p,int q)//pawn ki chal chalne ke liye function
        {
            if(a[p][q]==1){
                if(p==6){
                    if(a[p-1][q]==0){
                    btn[p-1][q].setBackground(Color.red);
                    if(a[p-2][q]==0)
                        btn[p-2][q].setBackground(Color.red);}
                    
                }
                else{
                     if(a[p-1][q]==0){
                    btn[p-1][q].setBackground(Color.red);}
                }
                if(p>=1&&q>=1)
               if(a[p-1][q-1]<0)
                   btn[p-1][q-1].setBackground(Color.red);
              if(p>=1&&q<=6)
               if(a[p-1][q+1]<0)
                   btn[p-1][q+1].setBackground(Color.red);
            }
            else{
                if(p==1){
                    if(a[p+1][q]==0){
                    btn[p+1][q].setBackground(Color.red);
                    if(a[p+2][q]==0)
                        btn[p+2][q].setBackground(Color.red);}
                    
                }
                else{
                    if(p<=6)
                     if(a[p+1][q]==0){
                    btn[p+1][q].setBackground(Color.red);}
                }
              
               if(a[p+1][q-1]>0)
                   btn[p+1][q-1].setBackground(Color.red);
              
               if(a[p+1][q+1]>0)
                   btn[p+1][q+1].setBackground(Color.red);
            }
        }
        public void knightmove(int p,int q){
            if(a[p][q]>0){
        if(p+2<8&&q+1<8&&a[p+2][q+1]<=0)
            btn[p+2][q+1].setBackground(Color.red);
         if(p+2<8&&q-1>=0&&a[p+2][q-1]<=0)
            btn[p+2][q-1].setBackground(Color.red);
         if(p-2>=0&&q+1<8&&a[p-2][q+1]<=0)
             btn[p-2][q+1].setBackground(Color.red);
         if(p-2>=0&&q-1>=0&&a[p-2][q-1]<=0)
             btn[p-2][q-1].setBackground(Color.red);
         if(p+1<8&&q+2<8&&a[p+1][q+2]<=0)
             btn[p+1][q+2].setBackground(Color.red);
         if(p-1>=0&&q+2<8&&a[p-1][q+2]<=0)
             btn[p-1][q+2].setBackground(Color.red);
         if(p+1<8&&q-2>=0&&a[p+1][q-2]<=0)
             btn[p+1][q-2].setBackground(Color.red);
         if(p-1>=0&&q-2>=0&&a[p-1][q-2]<=0)
             btn[p-1][q-2].setBackground(Color.red);
            }
            else{
                 if(p+2<8&&q+1<8&&a[p+2][q+1]>=0)
            btn[p+2][q+1].setBackground(Color.red);
         if(p+2<8&&q-1>=0&&a[p+2][q-1]>=0)
            btn[p+2][q-1].setBackground(Color.red);
         if(p-2>=0&&q+1<8&&a[p-2][q+1]>=0)
             btn[p-2][q+1].setBackground(Color.red);
         if(p-2>=0&&q-1>=0&&a[p-2][q-1]>=0)
             btn[p-2][q-1].setBackground(Color.red);
         if(p+1<8&&q+2<8&&a[p+1][q+2]>=0)
             btn[p+1][q+2].setBackground(Color.red);
         if(p-1>=0&&q+2<8&&a[p-1][q+2]>=0)
             btn[p-1][q+2].setBackground(Color.red);
         if(p+1<8&&q-2>=0&&a[p+1][q-2]>=0)
             btn[p+1][q-2].setBackground(Color.red);
         if(p-1>=0&&q-2>=0&&a[p-1][q-2]>=0)
             btn[p-1][q-2].setBackground(Color.red);
            }
}
        public void castlemove(int p,int q){
            int a1=p;
            while(true){
               a1++;
               if(a1>=8)
                   break;
               if(a[a1][q]>0&&a[p][q]>0||a[a1][q]<0&&a[p][q]<0)
                   break;
               if(a[p][q]>0&&a[a1][q]<0||a[p][q]<0&&a[a1][q]>0){
                   btn[a1][q].setBackground(Color.red);
                   break;
               }
               btn[a1][q].setBackground(Color.red);
            }
            a1=p;
             while(true){
               a1--;
               if(a1<0)
                   break;
               if(a[a1][q]>0&&a[p][q]>0||a[a1][q]<0&&a[p][q]<0)
                   break;
               if(a[p][q]>0&&a[a1][q]<0||a[p][q]<0&&a[a1][q]>0){
                   btn[a1][q].setBackground(Color.red);
                   break;
               }
               btn[a1][q].setBackground(Color.red);
            }
             a1=q;
              while(true){
               a1++;
               if(a1>=8)
                   break;
               if(a[p][a1]>0&&a[p][q]>0||a[p][a1]<0&&a[p][q]<0)
                   break;
               if(a[p][q]>0&&a[p][a1]<0||a[p][q]<0&&a[p][a1]>0){
                   btn[p][a1].setBackground(Color.red);
                   break;
               }
               btn[p][a1].setBackground(Color.red);
            }
              a1=q;
               while(true){
               a1--;
               if(a1<0)
                   break;
               if(a[p][a1]>0&&a[p][q]>0||a[p][a1]<0&&a[p][q]<0)
                   break;
               if(a[p][q]>0&&a[p][a1]<0||a[p][q]<0&&a[p][a1]>0){
                   btn[p][a1].setBackground(Color.red);
                   break;
               }
               btn[p][a1].setBackground(Color.red);
            } 
        }
      public void bishopmove(int p,int q){
          int a1=p,b1=q;
          while(true){
              a1++;
              b1++;
              if(a1>=8||b1>=8){
                  break;
              }
              if((a[a1][b1]>0&&a[p][q]>0)||(a[a1][b1]<0&&a[p][q]<0))
                   break;
               if((a[p][q]>0&&a[a1][b1]<0)||(a[p][q]<0&&a[a1][b1]>0)){
                  btn[a1][b1].setBackground(Color.red);
                   break;
               }
               btn[a1][b1].setBackground(Color.red);
          }
          a1=p;
          b1=q;
          while(true){
              a1--;
              b1--;
              if(a1<0||b1<0){
                  break;
              }
              if((a[a1][b1]>0&&a[p][q]>0)||(a[a1][b1]<0&&a[p][q]<0))
                   break;
               if((a[p][q]>0&&a[a1][b1]<0)||(a[p][q]<0&&a[a1][b1]>0)){
                   btn[a1][b1].setBackground(Color.red);
                   break;
               }
               btn[a1][b1].setBackground(Color.red);
          }
          a1=p;
          b1=q;
          while(true){
              a1++;
              b1--;
              if(a1>=8||b1<0){
                  break;
          }
          if(a[a1][b1]>0&&a[p][q]>0||a[a1][b1]<0&&a[p][q]<0)
                   break;
               if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0){
                  btn[a1][b1].setBackground(Color.red);
                   break;
               }
               btn[a1][b1].setBackground(Color.red);
          }
           a1=p;
          b1=q;
          while(true){
              a1--;
              b1++;
              if(b1>=8||a1<0){
                  break;
          }
          if(a[a1][b1]>0&&a[p][q]>0||a[a1][b1]<0&&a[p][q]<0)
                   break;
               if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0){
                   btn[a1][b1].setBackground(Color.red);
                   break;
               }
               btn[a1][b1].setBackground(Color.red);
          }
      } 
      public void kingmove(int p,int q){
          int a1=p,b1=q;
             a1++;
              b1++;
              if(a1<8&&b1<8)
              if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
              a1=p; b1=q;
              if(p>0&&q>0){
                  a1=p-1;
                  b1=q-1;
                  if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
              }
              if(p>0&&q<7){
                  a1=p-1;
                  b1=q+1;
                   if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
                   a1=p;
                  b1=q+1;
                   if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
                   a1=p-1;
                   b1=q;
                    if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
              }
              if(p<7&&q>0){
                  a1=p+1;
                  b1=q-1;
                   if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
                   a1=p+1;
                  b1=q;
                   if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
                   a1=p;
                  b1=q-1;
                   if(a[p][q]>0&&a[a1][b1]<0||a[p][q]<0&&a[a1][b1]>0||a[a1][b1]==0){
                   btn[a1][b1].setBackground(Color.red);
                   }
              }
                  
        }
      
}